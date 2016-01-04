import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Category;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.util.Date;

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

    @Before
    public void setup() {

    }

    @Test
    public void tetstcc() {

        /*
        //add data for test
        for (int i = 0; i < 4; i++) {
            Category category = new Category();
            category.setName("Test: " + i);
            SubCategory subCategory;
            for (int j = 0; j < 10; j++) {
                subCategory = new SubCategory(category);

                subCategory.setName("inner test" + i);

                category.getSubCategories().add(subCategory);

                categoryService.addOne(category);

                subCategoryService.saveOne(subCategory);
            }
        }*/

        for (SubCategory subCategory : categoryService.getAll().get(0).getSubCategories()){
            System.out.println(subCategory.getName());
        }

    }
}
