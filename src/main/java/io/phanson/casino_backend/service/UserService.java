package io.phanson.casino_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import io.phanson.casino_backend.dto.request.UserAddRequest;
import io.phanson.casino_backend.dto.request.UserBetRequest;
import io.phanson.casino_backend.dto.request.UserUpdateRequest;
import io.phanson.casino_backend.dto.response.UserBetResponse;
import io.phanson.casino_backend.dto.response.UserUpdateResponse;
import io.phanson.casino_backend.dto.response.UserViewResponse;
import io.phanson.casino_backend.entity.User;


@Service
public interface UserService {

    public List<UserViewResponse> getUserList();

    public UserUpdateResponse updateUser(UserUpdateRequest dto) throws Exception;

    public void removeUser(Long userId) throws Exception;

    public User createUser(UserAddRequest dto) throws Exception;

    public UserBetResponse bet(UserBetRequest dto) throws Exception;

}
