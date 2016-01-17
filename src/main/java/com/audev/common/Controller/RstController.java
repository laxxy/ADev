package com.audev.common.Controller;

import com.audev.common.Entity.Lot;
import com.audev.common.Model.SearchAjaxResponseBody;
import com.audev.common.Model.SearchCriteria;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cosxt on 25.11.2015.
 */

@RestController
public class RstController {

    @Autowired
    private LotService lotService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @JsonView(SearchCriteria.ViewSearch.class)
    @RequestMapping(value = "/search")
    public SearchAjaxResponseBody getSearchResult(@RequestBody SearchCriteria searchCriteria) {

        SearchAjaxResponseBody responseBody = new SearchAjaxResponseBody();

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

    @JsonView(Lot.Public.class)
    @Transactional
    @RequestMapping(value = "/filter/{input}")
    public List<Lot> getFilterData(@PathVariable String input, @RequestBody String num) {

        ArrayList<Lot> result = new ArrayList<>();

        if (subCategoryService.getOneByName(input) != null) {
            List<Lot> lots = subCategoryService.getOneByName(input).getLots();
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            for (int i = start -10; i < end; i++) {
                result.add(lots.get(i));
            }
            return result;
        }
        else {
            List<Lot> lots = lotService.getAll();
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            for (int i = start -10; i < end; i++) {
                if (lots.get(i).getLotName().equalsIgnoreCase(input) || lots.get(i).getLotName().startsWith(input))
                    result.add(lots.get(i));
            }
            return result;
        }
    }
}