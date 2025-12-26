package io.phanson.casino_backend.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SystemLogViewResponse {
    private Long id;
    private String actor;
    private String action;
    private String target;
    private String detail;
    private LocalDateTime createdAt;
}
