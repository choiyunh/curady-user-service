package com.curady.userservice.mapper;

import com.curady.userservice.dto.UserDto;
import com.curady.userservice.model.User;
import com.curady.userservice.vo.RequestUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto requestToDto(RequestUser requestUser);
    User dtoToEntity(UserDto userDto);
}
