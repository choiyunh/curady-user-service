package com.curady.userservice.mapper;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.model.User;
import com.curady.userservice.vo.RequestUser;
import com.curady.userservice.vo.ResponseUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto requestToDto(RequestUser requestUser);

    User dtoToEntity(UserDto userDto);

    ResponseUser dtoToResponse(UserDto userDto);

    ResponseUser entityToResponse(User user);




}
