package com.audev.common.Controller;

import com.audev.common.Model.AjaxResponseBody;
import com.audev.common.Model.SearchCriteria;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by cosxt on 25.11.2015.
 */

@RestController
public class SearchController {

    @JsonView(com.audev.common.Views.JsonView.Public.class)
    @RequestMapping(value = "/search")
    public AjaxResponseBody getSearchResult(@RequestBody SearchCriteria searchCriteria) {

        AjaxResponseBody responseBody = new AjaxResponseBody();

        String msg = searchCriteria.getSearchString();

        responseBody.setMessage(msg);

        return responseBody;
    }
}
