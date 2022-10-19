package com.ejls.service.user.dto.response.list;

import java.util.ArrayList;
import java.util.List;

import com.ejls.service.user.dto.response.GenericResponse;
import com.ejls.service.user.dto.response.UserResponse;

public class UserResponseList extends GenericResponse {
    private List<UserResponse> users;

    public List<UserResponse> getUsersList() {
        if(users == null) {
            users = new ArrayList<>();
        }
        return this.users;
    }

    public void add(UserResponse response) {
        if(users == null) {
            users = new ArrayList<>();
        }
        users.add(response);
    }
}
