import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Enums.UserRole;
import com.audev.common.Entity.SubCategory;
import com.audev.common.Entity.User;
import com.audev.common.Service.LotService;
import com.audev.common.Service.MessageService;
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
import org.springframework.transaction.annotation.Transactional;

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

    private User user;

    @Before
    public void setup() {
        user = userService.getUserByEmail("cosxtgx@gmail.com");
    }

    @Test
    public void create() {
        User user1 = new User();
        User user2 = new User();

        user1.setEmail("cosxtgx@gmail.com");
        user1.setPassword("6422f4c40534db400e0278f7c62a79bfc40f3566");
        user1.setLogin("laxxy");
        user1.setUserRole(UserRole.USER);

        user2.setEmail("asd@asd");
        user2.setPassword("6422f4c40534db400e0278f7c62a79bfc40f3566");
        user2.setLogin("inner");
        user2.setUserRole(UserRole.USER);

        userService.addUser(user1);
        userService.addUser(user2);
    }

    @Transactional
    @Test
    public void get() {
        System.out.println(userService.getUserByEmail("cosxtgx@gmail.com").getEmail());
        System.out.println(userService.getUserByEmail("asd@asd").getEmail());

        //get chats
        user.getChats().forEach(h -> System.out.println(h.getId()));
    }
}
