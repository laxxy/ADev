import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.User;
import com.audev.common.Model.SearchCriteria;
import com.audev.common.Service.LotService;
import com.audev.common.Service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by cosxt on 14.01.2016.
 */

@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;

    @Test
    public void test() {
        SearchCriteria searchCriteria = new SearchCriteria();
        searchCriteria.setSearchString("Name0");
        System.out.println(lotService.getBySearch(searchCriteria.getSearchString()));
        System.out.println(lotService.getBySearch(searchCriteria.getSearchString()).size());
        //lotService.getBySearch(searchCriteria.getSearchString()).forEach(h -> System.out.println(h.getLotName()));
    }
}
