package com.audev.common.Controller;

import com.audev.common.Entity.Enums.UserRole;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.User;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.SubCategoryService;
import com.audev.common.Service.UserService;
import org.jets3t.service.S3Service;
import org.jets3t.service.acl.AccessControlList;
import org.jets3t.service.acl.GroupGrantee;
import org.jets3t.service.acl.Permission;
import org.jets3t.service.impl.rest.httpclient.RestS3Service;
import org.jets3t.service.model.S3Bucket;
import org.jets3t.service.model.S3Object;
import org.jets3t.service.security.AWSCredentials;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import sun.awt.image.ImageFormatException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Main controller
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

    /**
     *
     * @param model -> ins. attributes
     * @return start page
     */
    @RequestMapping(value = "/")
    public String printMain(Model model) {

        model.addAttribute("category", categoryService.getAll());
        model.addAttribute("recommended1", lotService.getLastSix().subList(0,3));
        //model.addAttribute("recommended2", lotService.getLastSix().subList(2,5));
        return "index";
    }

    /**
     *
     * @param modelMap -> ins. attributes
     * @return user panel
     */
    @RequestMapping(value = "/panel")
    public String printPanel(ModelMap modelMap) {

        final User user = getUserFromSession();
        //TODO rewrite, ret. panel?
        if (user == null) {
            return "panel";
        }
        modelMap.addAttribute("lots", user.getLots());
        return "panel";
    }

    /**
     *
     * @param model -> ins. attributes
     * @return login page
     */
    @RequestMapping(value = "/login")
    public String printLogin(Model model) {

        model.addAttribute(new User());

        return "login";
    }

    /**
     *
     * @param user -> user with filed data
     * @param bindingResult -> BindingResult
     * @param modelMap- > ins. attributes
     * @return login page
     * @throws Exception
     */
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

    /**
     *
     * @param input -> contains search words or category name
     * @param modelMap -> ins. attributes
     * @return founded results page
     */
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
        // 10 fields per page
        int size = lotlist.size()%10 == 0 ? lotlist.size()/10 : (lotlist.size()/10) + 1;

        if (size <= 0) {
            modelMap.addAttribute("noData", "No Data Founded");
        }

        modelMap.addAttribute("size", size);

        return "main";
    }

    /**
     *
     * @param lotid -> lot id
     * @param modelMap -> ins. attributes
     * @return -> page with lot details(by id)
     */
    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.GET)
    public String findLot(@PathVariable String lotid, ModelMap modelMap) {

        Lot lot = lotService.getOne(Long.valueOf(lotid));
        lot.incrementViewCounter(); //TODO write val/ to db after method
        modelMap.addAttribute("lot", lot);

        return "lotdetails";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String newLot(Model model) {

        model.addAttribute(new Lot());
        model.addAttribute("categories", categoryService.getAll());
        model.addAttribute("user", getUserFromSession());

        return "newlot";
    }


    @Transactional
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String postNew(@Valid Lot lot,  BindingResult bindingResult,
                          HttpServletRequest httpServletRequest,
                          @RequestParam(value = "image1", required = false)MultipartFile image1,
                          @RequestParam(value = "image2", required = false)MultipartFile image2,
                          @RequestParam(value = "image3", required = false)MultipartFile image3) {

        if (bindingResult.hasErrors())
            return "newlot";

        ArrayList<String> images = new ArrayList<>();

        if (!image1.isEmpty() && !image2.isEmpty() && !image3.isEmpty()) {

            try {
                validateImage(image1);
                validateImage(image2);
                validateImage(image3);
            } catch (ImageFormatException e) {
                bindingResult.reject(e.getMessage());
                return "newlot";
            }
            images.add(saveImage(image1, image1.getOriginalFilename()));
            images.add(saveImage(image2, image2.getOriginalFilename()));
            images.add(saveImage(image3, image3.getOriginalFilename()));
        }
        //TODO this throws Data truncation: Data too long for column 'images' at row 1
        //but test work(
        lot.setImages(images);
        lot.setSubCategory(subCategoryService.getOneByName(httpServletRequest.getParameter("subname")));
        lot.setUser(getUserFromSession());
        lotService.addOne(lot);
        return "newlot";
    }

    /**
     *
     * @param x
     * @return
     * @throws Exception
     */
    private static String encrypt(String x) throws Exception {
        java.security.MessageDigest d;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return byteArrayToHexString(d.digest());
    }

    /**
     *
     * @param b
     * @return
     */
    private static String byteArrayToHexString(byte[] b) {

        String result = "";

        for (byte aB : b) {
            result += Integer.toString((aB & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }

    /**
     *
     * @return current user
     */
    private User getUserFromSession() {
        UserDetails userDetails;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserByEmail(userDetails.getUsername());
        }
        else return null;
    }

    /**
     *
     * @param image
     * @param filename
     * @return file path
     */
    private String saveImage(MultipartFile image, String filename) {
        String rez = null;
        try {
            AWSCredentials credentials = new AWSCredentials("",
                    "nWC3/");
            S3Service s3 = new RestS3Service(credentials);

            S3Bucket imgB = s3.getBucket("rezb");
            S3Object imgO = new S3Object(filename);


            imgO.setDataInputStream(new ByteArrayInputStream(image.getBytes()));
            imgO.setContentLength(image.getBytes().length);
            imgO.setContentType("image/jpeg");

            AccessControlList ac1 = new AccessControlList();
            ac1.setOwner(imgB.getOwner());
            ac1.grantPermission(GroupGrantee.ALL_USERS, Permission.PERMISSION_READ);
            imgO.setAcl(ac1);

            s3.putObject(imgB, imgO);
            rez = "https://s3.eu-central-1.amazonaws.com/rezb/" + filename;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return rez;
    }

    /**
     *
     * @param image ->
     * @throws ImageFormatException
     */
    private void validateImage(MultipartFile image) throws ImageFormatException {

        if (!image.getContentType().equals("image/jpeg")){
            throw new ImageFormatException("Wrong format");
        }
    }
}
