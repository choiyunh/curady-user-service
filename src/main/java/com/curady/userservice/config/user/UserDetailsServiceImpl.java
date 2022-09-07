package com.curady.userservice.config.user;

import com.curady.userservice.domain.entity.User;
import com.curady.userservice.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.curady.userservice.advice.exception.UserNotFoundException;

import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        return UserDetailsImpl.builder()
                .email(user.getEmail())
                .password(user.getEncryptedPwd())
                .authorities(user.getRoles().stream()
                        .map(auth -> new SimpleGrantedAuthority(auth.toString()))
                        .collect(Collectors.toList()))
                .build();
    }
}