import com.audev.common.Config.DataConfig;
import com.audev.common.Service.LotService;
import com.audev.common.Service.MessageService;
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
    @Autowired
    private MessageService messageService;

    @Test
    public void test() {

    }
}
