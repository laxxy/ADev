package com.audev.common.Controller;

import com.audev.common.Entity.Category;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.SubCategory;
import com.audev.common.Model.SearchAjaxResponseBody;
import com.audev.common.Model.SearchCriteria;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //@JsonView(com.audev.common.Views.JsonView.Public.class)
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

    @RequestMapping(value = "/filter/{input}")
    @JsonView(Lot.Public.class)
    public List<Lot> getFilterData(@PathVariable String input, @RequestBody String num) {

        List<Lot> list = new ArrayList<Lot>();

            list.add(lotService.getOne(1));
            list.add(lotService.getOne(2));

        return list;
    }

    @RequestMapping(value = "/category")
    @JsonView(Category.CatViewWithSubCategories.class)
    public List<Category> getCategory() {
        return categoryService.getAll();
    }
}
