package io.phanson.casino_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import io.phanson.casino_backend.dto.response.UserAddResponse;
import io.phanson.casino_backend.dto.response.UserBetResponse;
import io.phanson.casino_backend.dto.response.UserUpdateResponse;
import io.phanson.casino_backend.dto.response.UserViewResponse;
import io.phanson.casino_backend.entity.User;
import io.phanson.casino_backend.entity.UserRole;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "status", source = "account.status")
    @Mapping(target = "username", source = "account.username")
    @Mapping(target = "roleNames", source = "userRoles")
    public UserViewResponse toUserViewResponse(User user);

    default String mapUserRoleToString(UserRole userRole) {
        if (userRole == null || userRole.getRole() == null) {
            return null;
        }
        return userRole.getRole().getRoleName();
    }

    @Mapping(target = "username", source = "account.username")
    @Mapping(target = "status", source = "account.status")
    public UserUpdateResponse toUserUpdateResponse(User user);

    @Mapping(source = "walletBalance", target = "newWalletBalance")
    @Mapping(source = "id", target = "userId")
    public UserBetResponse toUserBetResponse(User user);

    @Mapping(target = "username", source = "account.username")
    @Mapping(target = "status", source = "account.status")
    public UserAddResponse toUserAddResponse(User user);
}
