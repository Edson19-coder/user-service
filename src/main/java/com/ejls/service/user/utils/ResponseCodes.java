package com.ejls.service.user.utils;

import java.util.HashMap;
import java.util.Map;

public enum ResponseCodes {
    OK_CODE(0,"00","Transaccion Valida"),
    BAD_REQUEST(1,"01","Mensaje request incompleto o erroneo"),
    GENERIC_ERROR(2,"02","Error generico"),
    NO_DATA_FOUND(3,"03","No se encontraron datos"),
    CREATED_FAILED(4,"04","No se ha podido registrar el usuario"),
    UPDATE_FAILED(5,"05","No se ha podido actualizar los datos del usuario"),
    USER_FOUND(6,"06","Email ingresado, ya esta en uso"),
    PASSWORD_INVALID(7,"07","Contraseña invalida");

    public String getCode() { return this.code; }
    public String getMessage() { return this.message; }
    
    private int value;
    private String code = "00";
    private String message = "Error generico";
    private static Map<Integer,ResponseCodes> map = new HashMap<>();

    private ResponseCodes(int value, String code, String message) {
        this.value = value;
        this.code = code;
        this.message = message;
    }

    static {
        for(ResponseCodes codes : ResponseCodes.values()) {
            map.put(codes.value, codes);
        }
    }

    public static ResponseCodes valueOf(Integer code) {
        return map.get(code);
    }

    public Integer getValue() {
        return value;
    }
}
