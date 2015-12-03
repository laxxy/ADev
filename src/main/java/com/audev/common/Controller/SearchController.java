package com.audev.common.Controller;

import com.audev.common.Entity.Lot;
import com.audev.common.Model.AjaxResponseBody;
import com.audev.common.Model.SearchCriteria;
import com.audev.common.Service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.Date;

/**
 * Created by cosxt on 25.11.2015.
 */

@RestController
public class SearchController {

    @Autowired
    private LotService lotService;

    @JsonView(com.audev.common.Views.JsonView.Public.class)
    @RequestMapping(value = "/search")
    public AjaxResponseBody getSearchResult(@RequestBody SearchCriteria searchCriteria) {

        AjaxResponseBody responseBody = new AjaxResponseBody();

        String msg = searchCriteria.getSearchString();

        if (!msg.isEmpty()) {
            for (Lot lot : lotService.getAll()) {
                if (lot.getLotName().startsWith(msg)) {
                    responseBody.setMessage(lot.getLotName());
                }
            }
            return responseBody;
        }
        else {
            responseBody.setMessage("Enter words here");
            return responseBody;
        }
    }
}
