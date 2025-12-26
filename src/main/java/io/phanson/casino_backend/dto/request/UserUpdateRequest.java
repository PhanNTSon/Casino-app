package io.phanson.casino_backend.dto.request;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateRequest {
    @NotBlank
    @NotNull
    private Long id;
    
    private String fullName;
    private double walletBalance;
    private String password;
    private Set<String> roleNames;
}
