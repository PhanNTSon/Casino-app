package io.phanson.casino_backend.dto.request;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserAddRequest {
    private String fullName;
    private String email;
    private String username;
    private String password;
    private Set<String> roleName;
}
