package com.blogging.service.customResponses;

public class CustomErrorResponse {
    private String message;

    public CustomErrorResponse(String msg){
        this.message=msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
