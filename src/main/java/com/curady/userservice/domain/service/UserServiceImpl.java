package com.curady.userservice.domain.service;

import com.curady.userservice.advice.exception.TendencyNotFoundException;
import com.curady.userservice.advice.exception.UserNotFoundException;
import com.curady.userservice.domain.entity.Tendency;
import com.curady.userservice.domain.entity.User;
import com.curady.userservice.domain.entity.UserTendency;
import com.curady.userservice.domain.mapper.UserMapper;
import com.curady.userservice.domain.repository.TendencyRepository;
import com.curady.userservice.domain.repository.UserRepository;
import com.curady.userservice.domain.repository.UserTendencyRepository;
import com.curady.userservice.web.dto.RequestTendency;
import com.curady.userservice.web.dto.RequestUserInfo;
import com.curady.userservice.web.dto.ResponseSignup;
import com.curady.userservice.web.dto.ResponseUserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final TendencyRepository tendencyRepository;
    private final UserTendencyRepository userTendencyRepository;

    @Override
    public ResponseSignup createUserInfo(RequestUserInfo request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFoundException::new);
        user.updateUserInfo(request);

        List<RequestTendency> requestTendency = request.getRequestTendencies();
        requestTendency.forEach(v -> {
            log.info(v.getTendencyName());
            Tendency tendency = tendencyRepository.findByName(v.getTendencyName()).orElseThrow(TendencyNotFoundException::new);

            userTendencyRepository.save(new UserTendency(user, tendency));
        });

        return ResponseSignup.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    @Override
    public ResponseUserInfo getUserInfoByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Override
    public Boolean checkUserEmailAuth(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return user.isEmailAuth();
    }
}
