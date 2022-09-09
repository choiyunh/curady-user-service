package com.curady.userservice.domain.mapper;

import com.curady.userservice.domain.entity.User;
import com.curady.userservice.web.dto.ResponseUserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    ResponseUserInfo entityToResponse(User user);
}