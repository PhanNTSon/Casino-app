package io.phanson.casino_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.phanson.casino_backend.dto.request.UserBetRequest;
import io.phanson.casino_backend.dto.response.UserBetResponse;
import io.phanson.casino_backend.dto.response.UserViewResponse;
import io.phanson.casino_backend.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/bet")
    public ResponseEntity<UserBetResponse> settleBet(@RequestBody UserBetRequest dto) throws Exception {

        return ResponseEntity.ok().body(userService.bet(dto));
    }

    @PreAuthorize("ADMIN")
    @GetMapping("/list")
    public ResponseEntity<List<UserViewResponse>> getUserList(@RequestParam String param) {
        return ResponseEntity.ok().body(userService.getUserList());
    }

}
