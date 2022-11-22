package com.curady.userservice.global.mapper;

import com.curady.userservice.domain.user.model.User;
import com.curady.userservice.domain.user.dto.ResponseUserInfo;
import com.curady.userservice.domain.user.dto.ResponseUserNicknameAndImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    ResponseUserInfo userToResponseInfo(User user);

    ResponseUserNicknameAndImage userToResponseNicknameAndImage(User user);

    List<ResponseUserNicknameAndImage> usersToResponseNicknameAndImageList(List<User> users);
}
