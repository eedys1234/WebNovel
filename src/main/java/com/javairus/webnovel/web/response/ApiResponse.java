package com.javairus.webnovel.web.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ApiResponse {

    private int status;
    private String message;
    private Object body;

    private ApiResponse(int status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.body = object;
    }

    public static ApiResponse of(int status, String message) {
        return new ApiResponse(status, message, null);
    }

    public static ApiResponse of(int status, String message, Object object) {
        return new ApiResponse(status, message, object);
    }
}
