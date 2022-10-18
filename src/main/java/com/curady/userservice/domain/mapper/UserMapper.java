package com.curady.userservice.domain.mapper;

import com.curady.userservice.domain.entity.User;
import com.curady.userservice.web.dto.ResponseUserInfo;
import com.curady.userservice.web.dto.ResponseUserNicknameAndImage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ResponseUserInfo entityToResponse(User user);

    @Named("E2R")
    ResponseUserNicknameAndImage userToResponse(User user);

    @IterableMapping(qualifiedByName = "E2R")
    List<ResponseUserNicknameAndImage> usersToResponseList(List<User> users);
}
