package com.audev.common.Model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cosxt on 25.11.2015.
 */
public class AjaxResponseBody {

    @JsonView(com.audev.common.Views.JsonView.Public.class)

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
