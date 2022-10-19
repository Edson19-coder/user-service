package com.ejls.service.user.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private Long userId;
    private String userName;
    private String email;
    private String password;
    private Integer active;
}
