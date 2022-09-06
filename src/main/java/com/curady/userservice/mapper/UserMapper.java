package com.curady.userservice.mapper;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.entity.User;
import com.curady.userservice.vo.RequestSignup;
import com.curady.userservice.vo.ResponseSignup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto requestToDto(RequestSignup requestSignup);

    @Mapping(target = "emailAuth", constant = "false")
    @Mapping(target = "roles", constant = "ROLE_USER")
    @Mapping(source = "password", target = "encryptedPwd", qualifiedByName = "encryptPassword")
    User dtoToEntity(UserDto userDto);

    UserDto entityToDto(User user);

    ResponseSignup dtoToResponse(UserDto userDto);

    ResponseSignup entityToResponse(User user);

    @Named("encryptPassword") // 2
    default String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
