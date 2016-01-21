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

    @JsonView(Lot.Public.class)
    @Transactional
    @RequestMapping(value = "/filter/{input}")
    public List<Lot> getFilterData(@PathVariable String input, @RequestBody String num) {

        ArrayList<Lot> result = new ArrayList<>();

        if (subCategoryService.getOneByName(input) != null) {
            List<Lot> lots = subCategoryService.getOneByName(input).getLots();
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            result.addAll(lots.subList(start -10, end));
            return result;
        }
        else {
            List<Lot> lots = lotService.getBySearch(input);
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            result.addAll(lots.subList(start- 10, end));
            return result;
        }
    }
}