package com.curady.userservice.service;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.model.Tendency;
import com.curady.userservice.model.User;
import com.curady.userservice.model.UserTendency;
import com.curady.userservice.repository.TendencyRepository;
import com.curady.userservice.repository.UserRepository;
import com.curady.userservice.repository.UserTendencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserTendencyService {
    UserTendencyRepository userTendencyRepository;
    UserRepository userRepository;
    TendencyRepository tendencyRepository;

    @Autowired
    public UserTendencyService(UserTendencyRepository userTendencyRepository, UserRepository userRepository, TendencyRepository tendencyRepository) {
        this.userTendencyRepository = userTendencyRepository;
        this.userRepository = userRepository;
        this.tendencyRepository = tendencyRepository;
    }

    public void createUserTendency(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail());
        List<Tendency> requestTendency = userDto.getTendency();

        for (Tendency td : requestTendency) {
            Optional<Tendency> tendency = tendencyRepository.findByName(td.getName());

            if (tendency.isEmpty()) {
                throw new RuntimeException();
            }
            userTendencyRepository.save(new UserTendency(user, tendency.get()));
        }
    }
}
