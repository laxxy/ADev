package com.audev.common.Model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cosxt on 25.11.2015.
 */
public class SearchCriteria {

    public interface ViewSearch{}

    private String searchString;

    @JsonView(ViewSearch.class)
    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }
}
