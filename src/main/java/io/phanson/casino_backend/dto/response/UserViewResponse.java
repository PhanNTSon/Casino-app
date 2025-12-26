package io.phanson.casino_backend.dto.response;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserViewResponse {
    private Long id;
    private String fullName;
    private String username;
    private double walletBalance;
    private String status;
    private Set<String> roleNames;
}
