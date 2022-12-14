package com.curady.userservice.domain.user.service;

import com.curady.userservice.config.jwt.JwtTokenProvider;
import com.curady.userservice.domain.auth.AccessToken;
import com.curady.userservice.domain.auth.profile.ProfileDto;
import com.curady.userservice.domain.emailAuth.model.EmailAuth;
import com.curady.userservice.domain.user.model.User;
import com.curady.userservice.domain.emailAuth.repository.EmailAuthRepository;
import com.curady.userservice.domain.user.repository.UserRepository;
import com.curady.userservice.domain.user.dto.*;
import com.curady.userservice.global.advice.exception.*;
import com.curady.userservice.web.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SignService {
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;
    private final EmailAuthRepository emailAuthRepository;
    private final EmailSenderService emailSenderService;
    private final ProviderService providerService;
    private final UserRepository userRepository;

    @Transactional
    public ResponseSignup createUser(RequestSignup request) {
        validateDuplicated(request.getEmail());
        EmailAuth emailAuth = emailAuthRepository.save(
                EmailAuth.builder()
                        .email(request.getEmail())
                        .authToken(UUID.randomUUID().toString())
                        .expired(false)
                        .build());

        emailSenderService.sendEmail(emailAuth.getEmail(), emailAuth.getAuthToken());
        User user = userRepository.save(
                User.builder()
                        .email(request.getEmail())
                        .encryptedPwd(passwordEncoder.encode(request.getPassword()))
                        .isEmailAuth(false)
                        .build());
        user.setDefaultNickname(user.getId());

        return ResponseSignup.builder()
                .id(user.getId())
                .email(user.getEmail())
                .expireDate(emailAuth.getExpireDate())
                .build();
    }

    public void validateDuplicated(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new UserEmailAlreadyExistsException();
    }

    @Transactional
    public void confirmEmail(RequestEmailAuth request) {
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(request.getEmail(), request.getAuthToken(), LocalDateTime.now())
                .orElseThrow(EmailAuthTokenNotFoundException::new);
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        emailAuth.useToken();
        user.verifyEmail();
    }

    @Transactional
    public ResponseLogin loginUser(RequestLogin request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        if (!passwordEncoder.matches(request.getPassword(), user.getEncryptedPwd()))
            throw new LoginFailureException();
        if (!user.isEmailAuth())
            throw new EmailNotAuthenticatedException();
        user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
        return new ResponseLogin(user.getId(), user.getNickname(), jwtTokenProvider.createToken(String.valueOf(user.getId())), user.getRefreshToken());
    }

    @Transactional
    public ResponseSocialLogin loginUserByProvider(String code, String provider) {
        AccessToken accessToken = providerService.getAccessToken(code, provider);
        ProfileDto profile = providerService.getProfile(accessToken.getAccess_token(), provider);

        Optional<User> findMember = userRepository.findByEmailAndProvider(profile.getEmail(), provider);
        if (findMember.isPresent()) {
            User user = findMember.get();
            user.updateRefreshToken(jwtTokenProvider.createRefreshToken());
            return new ResponseSocialLogin(user.getEmail(), user.getId(), user.getNickname(), null, false, jwtTokenProvider.createToken(String.valueOf(findMember.get().getId())), user.getRefreshToken());
        } else {
            User savedUser;
            if (userRepository.findByEmail(profile.getEmail()).isPresent()) {
                throw new UserEmailAlreadyExistsException();
            } else if (userRepository.findByNickname(profile.getNickname()).isPresent()) {
                savedUser = saveUser(profile.getEmail(), "", provider);
                savedUser.setDefaultNickname(savedUser.getId());
            } else {
                savedUser = saveUser(profile.getEmail(), profile.getNickname(), provider);
            }
            savedUser.updateRefreshToken(jwtTokenProvider.createRefreshToken());
            return new ResponseSocialLogin(savedUser.getEmail(),
                    savedUser.getId(), profile.getNickname(),
                    savedUser.getNickname(),
                    true,
                    jwtTokenProvider.createToken(String.valueOf(savedUser.getId())), savedUser.getRefreshToken());
        }
    }

    private User saveUser(String email, String nickname, String provider) {
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .encryptedPwd(null)
                .provider(provider)
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public ResponseToken reissueToken(RequestToken requestToken) {
        if (!jwtTokenProvider.validateTokenExpiration(requestToken.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        User user = findUserByToken(requestToken);

        if (!user.getRefreshToken().equals(requestToken.getRefreshToken()))
            throw new InvalidRefreshTokenException();

        String accessToken = jwtTokenProvider.createToken(String.valueOf(user.getId()));
        String refreshToken = jwtTokenProvider.createRefreshToken();
        user.updateRefreshToken(refreshToken);

        return new ResponseToken(accessToken, refreshToken);
    }

    public User findUserByToken(RequestToken requestToken) {
        return userRepository.findByRefreshToken(requestToken.getRefreshToken()).orElseThrow(UserNotFoundException::new);
    }
}
