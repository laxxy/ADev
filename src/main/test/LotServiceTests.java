import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.SubCategory;
import com.audev.common.Entity.User;
import com.audev.common.Service.CategoryService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.SubCategoryService;
import com.audev.common.Service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by cosxt on 01.12.2015.
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class LotServiceTests {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private LotService lotService;
    @Autowired
    private UserService userService;

    private SubCategory subCategory;

    private User user;

    @Before
    @Transactional
    public void setup() {
        subCategory = subCategoryService.getOnyById(1);
        user = userService.getUserByEmail("cosxtgx@gmail.com");
    }


    @Test
    @Rollback(value = false)
    @Transactional
    public void saveMoreThenOne() {
        //add data for test
        for (int i = 0; i < 20; i++) {
            Lot lots = new Lot(subCategory);
            ArrayList<String> imgss = new ArrayList<>();
            lots.setLotName("test# " + i);
            lots.setBidCurrent(10);
            lots.setUser(user);
            //test -> upload test img
            imgss.add("https://s3.eu-central-1.amazonaws.com/rezb/empty_cart.png");
            lots.setImages(imgss);
            lotService.addOne(lots);
            subCategory.getLots().add(lots);
            subCategoryService.saveOne(subCategory);
        }
    }

    @Test
    @Rollback(value = false)
    @Transactional
    public void saveOne() {
        //save with images list
        ArrayList<String> imgs = new ArrayList<>();
        Lot lot = new Lot(subCategory);
        lot.setLotName("test");
        lot.setBidCurrent(10);
        lot.setUser(user);
        imgs.add("https://s3.eu-central-1.amazonaws.com/rezb/acer_nx_g6jeu_005_images_1417056832.jpg");
        imgs.add("https://s3.eu-central-1.amazonaws.com/rezb/acer_nx_g6jeu_005_images_1417056898.jpg");
        imgs.add("https://s3.eu-central-1.amazonaws.com/rezb/acer_nx_g6jeu_005_images_1417056976.jpg");
        lot.setImages(imgs);
        lotService.addOne(lot);
        subCategory.getLots().add(lot);
        subCategoryService.saveOne(subCategory);
    }

    @Test
    @Transactional
    public void getTest() {
        //System.out.println(lotService.getOne(0).getId());
        //System.out.println(lotService.getOneByName(""));
        lotService.getLastSix().forEach(h -> System.out.println(h.getId()));
    }
}
