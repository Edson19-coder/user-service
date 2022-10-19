package com.ejls.service.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejls.service.user.dto.request.UserRequest;
import com.ejls.service.user.service.UserService;
import com.ejls.service.user.utils.Constants;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController extends SuperController {
    
    @Autowired
    private UserService userService;

    @PostMapping(value = Constants.CREATE_USER)
    public @ResponseBody ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        executingMethodLog(log, Constants.CREATE_USER, request);
        return userService.createUser(request);
    }

    @PostMapping(value = Constants.UPDATE_USER)
    public @ResponseBody ResponseEntity<?> updateUser(@RequestBody UserRequest request) {
        executingMethodLog(log, Constants.UPDATE_USER, request);
        return userService.updateUser(request);
    }

    @PostMapping(value = Constants.GET_USER)
    public @ResponseBody ResponseEntity<?> getUser(@RequestBody UserRequest request) {
        executingMethodLog(log, Constants.GET_USER, request);
        return userService.getUser(request);
    }

    @PostMapping(value = Constants.GET_USERS)
    public @ResponseBody ResponseEntity<?> getUsers() {
        executingMethodLog(log, Constants.GET_USERS, null);
        return userService.getUsers();
    }

}
