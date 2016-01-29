package com.audev.common.Controller;

import com.audev.common.Entity.Enums.UserRole;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.User;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.SubCategoryService;
import com.audev.common.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

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
    public String printMain(Model model) {

        model.addAttribute("category", categoryService.getAll());

        return "index";
    }

    @RequestMapping(value = "/panel")
    public String printPanel(ModelMap modelMap) {

        if (getUserFromSession() == null) {
            return "panel";
        }

        List<Lot> lots = getUserFromSession().getLots();
        modelMap.addAttribute("lots", lots);
        return "panel";
    }

    @RequestMapping(value = "/panel/chat")
    public String printPanelChat() {

        return "chat";
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

    @Transactional
    @RequestMapping(value = "/filter/{input}", method = RequestMethod.GET)
    public String printFilterPage(@PathVariable String input, ModelMap modelMap) {

        List<Lot> lotlist; //TODO rewrite to sql q., change to int (size)

        if (subCategoryService.getOneByName(input) != null) {
            lotlist = subCategoryService.getOneByName(input).getLots();//TODO rewrite to .getLost().size()
        }
        else {
            lotlist = lotService.getBySearch(input);
        }

        int size = lotlist.size()%10 == 0 ? lotlist.size()/10 : (lotlist.size()/10) + 1;

        if (size <= 0) {
            modelMap.addAttribute("noData", "No Data Founded");
        }

        modelMap.addAttribute("size", size);

        return "main";
    }

    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.GET)
    public String findLot(@PathVariable String lotid, ModelMap modelMap) {

        Lot lot = lotService.getOne(Long.valueOf(lotid));
        lot.incrementViewCounter(); //TODO write val/ to db after method
        modelMap.addAttribute("lot", lot);

        return "lotdetails";
    }

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLot(Model model) {

        model.addAttribute(new Lot());
        model.addAttribute("user", getUserFromSession());

        return "newlot";
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

    private User getUserFromSession() {
        UserDetails userDetails;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserByEmail(userDetails.getUsername());
        }
        else return null;
    }
}
