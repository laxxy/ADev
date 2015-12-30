package com.audev.common.Controller;

import com.audev.common.Entity.Category;
import com.audev.common.Entity.Enums.UserRole;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.SubCategory;
import com.audev.common.Entity.User;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.SubCategoryService;
import com.audev.common.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by cosxt on 04.12.2015.
 */
@Controller
@RequestMapping(value = "/")
public class MainController {

    @Autowired
    private LotService lotService;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @RequestMapping(value = "/")
    public String printMain() {

        Category category = new Category();
        category.setName("test");

        SubCategory subCategory = new SubCategory();


        category.getSubCategories().add(subCategory);

        categoryService.addOne(category);

        subCategoryService.saveOne(subCategory);

        return "index";
    }

    @RequestMapping(value = "/login")
    public String printLogin(Model model) {

        model.addAttribute(new User());

        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String printRegister(@Valid User user, BindingResult bindingResult, ModelMap modelMap) throws Exception {

        if (bindingResult.hasErrors()) {
            return "login";
        }

        user.setUserRole(UserRole.USER);
        String passw = user.getPassword();
        user.setPassword(encrypt(passw));
        userService.addUser(user);
        modelMap.addAttribute("done", "Registration is done");
        return "login";
    }

    @RequestMapping(value = "/filter/{input}", method = RequestMethod.GET)
    public String printFilterPage(@PathVariable String input, ModelMap modelMap) {

        ArrayList<Lot> lotlist = new ArrayList<Lot>();

        for (Lot h : lotService.getAll()) {
            if (h.getLotName().startsWith(input)) {
                lotlist.add(h);
            }
        }

        int size = lotlist.size()%10 == 0 ? lotlist.size()/10 : (lotlist.size()/10) + 1;

        modelMap.addAttribute("size", size);

        return "main";
    }

    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.GET)
    public String findLot(@PathVariable String lotid) {

        Lot lot = lotService.getOne(Long.valueOf(lotid));

        //here put lot to view

        return "lotdetails";
    }

    private static String encrypt(String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return byteArrayToHexString(d.digest());
    }

    private static String byteArrayToHexString(byte[] b) {

        String result = "";

        for (byte aB : b) {
            result += Integer.toString((aB & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
