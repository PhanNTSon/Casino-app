package io.phanson.casino_backend.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import io.phanson.casino_backend.dto.response.AccountLoginResponse;
import io.phanson.casino_backend.entity.Account;

@Component
@Mapper(componentModel = "spring")
public interface AccountMapper {

    @Mapping(target = "id", source = "user.id")
    @Mapping(target = "fullName", source = "user.fullName")
    @Mapping(target = "walletBalance", source = "user.walletBalance")
    @Mapping(target = "status", source = "status")
    public AccountLoginResponse toAccountLoginResponse(Account account);

    
}
