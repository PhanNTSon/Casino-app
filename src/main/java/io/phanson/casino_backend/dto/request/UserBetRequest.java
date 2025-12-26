package io.phanson.casino_backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserBetRequest {
    @NotBlank
    private Long userId;
    private double betAmount;
    private double multiplier;
}
