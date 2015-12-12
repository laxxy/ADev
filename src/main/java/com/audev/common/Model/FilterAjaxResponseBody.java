package com.audev.common.Model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cosxt on 12.12.2015.
 */
public class FilterAjaxResponseBody {

    @JsonView(com.audev.common.Views.JsonView.Public.class)

    private String dataToView;

    private String count;

    public String getDataToView() {
        return dataToView;
    }

    public void setDataToView(String dataToView) {
        this.dataToView = dataToView;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
