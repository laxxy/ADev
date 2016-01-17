import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Service.ChatService;
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
public class ChatTest {

    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private LotService lotService;

    @Test
    public void testIn() {
        User user = userService.getUserByEmail("cosxtgx@gmail.com");
        Lot lot = lotService.getOneByName("Name1");
        user.getLots().add(lot);
        lot.setUser(user);
        lotService.addOne(lot);
        userService.addUser(user);

        Chat chat = new Chat();

        Message message = new Message();
        message.setMessage("Asd for Test");
        message.setIsReaded(false);
        lot.getChats().add(chat);
        chat.getMessages().add(message);
        chat.setLot(lot);
        message.setChat(chat);
        message.setAuthor(user.getLogin());
        lotService.addOne(lot);
        chatService.saveOne(chat);
        messageService.saveOne(message);

        //get

    }

 }
