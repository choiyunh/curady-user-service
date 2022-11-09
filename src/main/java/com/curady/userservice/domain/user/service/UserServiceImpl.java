package com.curady.userservice.domain.user.service;

import com.curady.userservice.global.advice.exception.AuthenticationEntryPointException;
import com.curady.userservice.global.advice.exception.NicknameAlreadyExistsException;
import com.curady.userservice.global.advice.exception.TendencyNotFoundException;
import com.curady.userservice.global.advice.exception.UserNotFoundException;
import com.curady.userservice.domain.tendency.model.Tendency;
import com.curady.userservice.domain.user.model.User;
import com.curady.userservice.domain.userTendency.model.UserTendency;
import com.curady.userservice.global.mapper.UserMapper;
import com.curady.userservice.domain.tendency.repository.TendencyRepository;
import com.curady.userservice.domain.user.repository.UserRepository;
import com.curady.userservice.domain.userTendency.repository.UserTendencyRepository;
import com.curady.userservice.domain.tendency.dto.RequestTendency;
import com.curady.userservice.domain.user.dto.RequestUserInfo;
import com.curady.userservice.domain.user.dto.ResponseSignup;
import com.curady.userservice.domain.user.dto.ResponseUserInfo;
import com.curady.userservice.domain.user.dto.ResponseUserNicknameAndImage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TendencyRepository tendencyRepository;
    private final UserTendencyRepository userTendencyRepository;

    @Override
    @Transactional
    public ResponseSignup createUserInfo(RequestUserInfo request, String id) {
        Optional<User> userByNickname = userRepository.findByNickname(request.getNickname());
        if (userByNickname.isPresent()) {
            if (!userByNickname.get().getId().equals(Long.valueOf(id))) {
                throw new NicknameAlreadyExistsException();
            }
        }

        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(UserNotFoundException::new);
        user.updateUserInfo(request);

        List<RequestTendency> requestTendency = request.getRequestTendencies();
        requestTendency.forEach(v -> {
            Tendency tendency = tendencyRepository.findByNameAndType(v.getTendencyName(), v.getTendencyType()).orElseThrow(TendencyNotFoundException::new);

            Optional<UserTendency> userTendency = userTendencyRepository.findByUserAndTendency(user, tendency);
            if (userTendency.isEmpty()) {
                userTendencyRepository.save(new UserTendency(user, tendency));
            }
        });

        return ResponseSignup.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public ResponseUserInfo getUserInfo(String id) {
        User user = userRepository.findById(Long.valueOf(id)).orElseThrow(UserNotFoundException::new);
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Override
    public Boolean checkUserEmailAuth(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.isEmailAuth();
    }

    @Override
    public List<ResponseUserNicknameAndImage> getUsersNicknameAndImage(List<Long> list) {
        List<User> users = new ArrayList<>();
        list.forEach(v -> {
            users.add(userRepository.findById(v).orElseThrow(UserNotFoundException::new));
        });
        return UserMapper.INSTANCE.usersToResponseList(users);
    }

    @Override
    @Transactional
    public void deleteUser(String userId) {
        if (userId.equals("anonymousUser")) {
            throw new AuthenticationEntryPointException();
        }
        User user = userRepository.findById(Long.valueOf(userId)).orElseThrow(UserNotFoundException::new);
        userTendencyRepository.deleteAllByUser(user);
        userRepository.delete(user);
    }
}
