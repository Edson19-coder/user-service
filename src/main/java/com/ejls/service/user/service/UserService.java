package com.ejls.service.user.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ejls.service.user.dto.request.UserRequest;

@Service
public interface UserService {
    public ResponseEntity<?> createUser(UserRequest request);
    public ResponseEntity<?> updateUser(UserRequest request);
    public ResponseEntity<?> getUser(UserRequest request);
    public ResponseEntity<?> getUsers();
}
