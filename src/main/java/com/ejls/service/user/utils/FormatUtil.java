package com.ejls.service.user.utils;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ejls.service.user.dto.response.GenericResponse;
import com.ejls.service.user.dto.response.UserResponse;
import com.ejls.service.user.dto.response.list.UserResponseList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FormatUtil {

    public static ResponseEntity<GenericResponse> fillResponse(GenericResponse genericResponse, ResponseCodes responseCode, HttpStatus status) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        genericResponse.setCode(responseCode.getCode());
        genericResponse.setMessage(responseCode.getMessage());
        log.info("End Response: {}",gson.toJson(genericResponse));
        return new ResponseEntity<GenericResponse>(genericResponse, status);
    }

    public static GenericResponse fillErrorResponse(GenericResponse genericResponse, ResponseCodes responseCode, Class<?> level) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        log.error("An error has ocurred: {}", responseCode.getMessage());
        genericResponse.setCode(responseCode.getCode());
        genericResponse.setMessage(responseCode.getMessage());
        log.error("End of: {}",level.getName());
        log.error("End Response: {}",gson.toJson(genericResponse));
        return genericResponse;
    }

    public static UserResponseList fillUsers(List<?> users) {
        UserResponseList response = new UserResponseList();
        for(Object o : users) {
            Object[] obj = (Object[]) o;
            UserResponse user = new UserResponse();
            user.setUserId(Long.valueOf(String.valueOf(obj[0])));
            user.setEmail(String.valueOf(obj[1]));
            user.setUserName(String.valueOf(obj[2]));
            response.add(user);
        }
        return response;
    }
}
