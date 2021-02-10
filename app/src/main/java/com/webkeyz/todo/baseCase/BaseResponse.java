package com.webkeyz.todo.baseCase;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
