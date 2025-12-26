package io.phanson.casino_backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserUpdateResponse {
    private Long id;
    private String username;
    private String fullName;
    private double walletBalance;
    private String status;
}
