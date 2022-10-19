package com.ejls.service.user.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejls.service.user.dto.request.UserRequest;

@Repository
public interface UserRepository {
    Boolean createUser(UserRequest request);
    Boolean updateUser(UserRequest request);
    List<?> getUser(UserRequest request);
    List<?> getUsers();
}
