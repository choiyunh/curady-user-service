package com.curady.userservice.service;

import com.curady.userservice.advice.exception.EmailAuthTokenNotFountException;
import com.curady.userservice.advice.exception.MemberNotFoundException;
import com.curady.userservice.dto.UserDto;
import com.curady.userservice.entity.EmailAuth;
import com.curady.userservice.entity.User;
import com.curady.userservice.mapper.UserMapper;
import com.curady.userservice.repository.EmailAuthRepository;
import com.curady.userservice.repository.UserRepository;
import com.curady.userservice.vo.RequestEmailAuth;
import com.curady.userservice.vo.ResponseSignup;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignService {

    private final EmailAuthRepository emailAuthRepository;
    private final EmailSenderService emailSenderService;
    private final UserRepository userRepository;

    public ResponseSignup createUser(UserDto userDto) throws MessagingException {
        EmailAuth emailAuth = EmailAuth.builder()
                .email(userDto.getUsername())
                .authToken(UUID.randomUUID().toString())
                .expired(false)
                .build();
        emailAuthRepository.save(emailAuth);

        userRepository.save(UserMapper.INSTANCE.dtoToEntity(userDto));
        emailSenderService.sendEmail(emailAuth.getEmail(), emailAuth.getAuthToken());

        return UserMapper.INSTANCE.dtoToResponse(userDto);
    }

    @Transactional
    public void confirmEmail(RequestEmailAuth request) {
        log.info(request.getEmail());
        EmailAuth emailAuth = emailAuthRepository.findValidAuthByEmail(request.getEmail(), request.getAuthToken(), LocalDateTime.now())
                .orElseThrow(EmailAuthTokenNotFountException::new);
        User user = userRepository.findByUsername(request.getEmail()).orElseThrow(MemberNotFoundException::new);
        emailAuth.useToken();
        user.verifyEmail();
    }
}
