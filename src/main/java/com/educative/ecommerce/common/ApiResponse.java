package com.educative.ecommerce.common;

import java.time.LocalDateTime;

public class ApiResponse {
    private final boolean success;
    private final Object result;

    public ApiResponse(boolean success, Object result) {
        this.success = success;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public Object getResult() {
        return result;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }
}
