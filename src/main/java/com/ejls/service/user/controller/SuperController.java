package com.ejls.service.user.controller;

import org.slf4j.Logger;

import com.ejls.service.user.utils.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SuperController {
    public void executingMethodLog(Logger log, String method, Object request) {
        log.info(Constants.EXECUTING_ENDPOINT, method);
        if(request != null) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            log.info(Constants.INPUT, gson.toJson(request));
        }
    }
}
