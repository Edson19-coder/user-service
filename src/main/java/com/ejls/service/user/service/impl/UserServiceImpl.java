package com.ejls.service.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ejls.service.user.dto.request.UserRequest;
import com.ejls.service.user.dto.response.GenericResponse;
import com.ejls.service.user.dto.response.list.UserResponseList;
import com.ejls.service.user.repository.UserRepository;
import com.ejls.service.user.service.UserService;
import com.ejls.service.user.utils.FormatUtil;
import com.ejls.service.user.utils.ResponseCodes;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<?> createUser(UserRequest request) {
        GenericResponse response = new GenericResponse();

        if((request.getUserName() == null || request.getUserName().equals("")) || (request.getEmail() == null || request.getEmail().equals("")) ||
        (request.getPassword() == null || request.getPassword().equals(""))) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.BAD_REQUEST, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.BAD_REQUEST, HttpStatus.CONFLICT);
        }

        List<?> user = userRepository.getUser(request);
        System.out.println(user);
        if(!user.isEmpty()) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.USER_FOUND, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.USER_FOUND, HttpStatus.CONFLICT);
        }

        // TODO : ENCODE PASSWORD

        if(!userRepository.createUser(request)) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.CREATED_FAILED, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.CREATED_FAILED, HttpStatus.CONFLICT);
        }

        return FormatUtil.fillResponse(response, ResponseCodes.OK_CODE, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> updateUser(UserRequest request) {
        GenericResponse response = new GenericResponse();

        if(request.getUserId() == null || request.getActive() == null || (request.getUserName() == null || request.getUserName().equals("")) || (request.getPassword() == null || request.getPassword().equals(""))) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.BAD_REQUEST, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.BAD_REQUEST, HttpStatus.CONFLICT);
        }

        if(!userRepository.updateUser(request)) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.UPDATE_FAILED, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.UPDATE_FAILED, HttpStatus.CONFLICT);
        }

        return FormatUtil.fillResponse(response, ResponseCodes.OK_CODE, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> getUser(UserRequest request) {
        GenericResponse response = new GenericResponse();

        if((request.getEmail() == null || request.getEmail().equals("")) || (request.getPassword() == null || request.getPassword().equals(""))) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.BAD_REQUEST, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.BAD_REQUEST, HttpStatus.CONFLICT);
        }

        List<?> user = userRepository.getUser(request);

        if(user == null || user.isEmpty()) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.NO_DATA_FOUND, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.NO_DATA_FOUND, HttpStatus.CONFLICT);
        }

        UserResponseList userResponse = FormatUtil.fillUsers(user);        
        
        // TODO : VERIFY THAT THE PASSWORD IS CORRECT

        return FormatUtil.fillResponse(userResponse, ResponseCodes.OK_CODE, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getUsers() {
        GenericResponse response = new GenericResponse();
        List<?> users = userRepository.getUsers();

        if(users == null || users.isEmpty()) {
            response = FormatUtil.fillErrorResponse(response, ResponseCodes.NO_DATA_FOUND, this.getClass());
            return FormatUtil.fillResponse(response, ResponseCodes.NO_DATA_FOUND, HttpStatus.CONFLICT);
        }

        response = FormatUtil.fillUsers(users);

        return FormatUtil.fillResponse(response, ResponseCodes.OK_CODE, HttpStatus.OK);
    }
    
}
