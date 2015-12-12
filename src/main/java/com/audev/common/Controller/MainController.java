package com.audev.common.Controller;

import com.audev.common.Entity.Lot;
import com.audev.common.Service.LotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by cosxt on 04.12.2015.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private LotService lotService;

    @RequestMapping(value = "/")
    public String printMain() {
        return "index";
    }

    @RequestMapping(value = "/filter/{input}", method = RequestMethod.GET)
    public String printFilterPage(@PathVariable String input) {
        return "main";
    }

    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.GET)
    public String findLot(@PathVariable String lotid) {

        Lot lot = lotService.getOne(Long.valueOf(lotid));

        //here put lot to view

        return "lotdetails";
    }
}
