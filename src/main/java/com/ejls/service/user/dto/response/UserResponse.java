package com.ejls.service.user.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String userName;
    private String email;
}
