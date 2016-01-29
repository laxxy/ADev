import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Service.ChatService;
import com.audev.common.Service.MessageService;
import com.audev.common.Service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by cosxt on 24.01.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class eqtest {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @Test
    public void testeq() {

        User user = userService.getUserByName("laxxy");
        System.out.println("user " + user.getId() + " : name" + user.getLogin());


        //System.out.println(chatService.getChatsByUserId(1).size());
    }
}
