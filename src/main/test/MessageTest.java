import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Message;
import com.audev.common.Service.ChatService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.MessageService;
import com.audev.common.Service.UserService;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.concurrent.Callable;

/**
 * Created by cosxt on 23.01.2016.
 */
@DirtiesContext
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DataConfig.class)
@WebAppConfiguration
public class MessageTest {

    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private ChatService chatService;

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Test
    public void testGet() throws Exception {

        Chat chat = chatService.getOneById(1);

        //int before = messageService.getAllUnreaded("laxxy").size();
        //System.out.println("before"+before);


/*
        final Callable siz = () -> {
            Thread.sleep(10000);
                Message message = new Message();
                message.setAuthor("laxxy");
                message.setIsReaded(false);
                message.setMessage("asdasdasd");
                message.setChat(chat);
                chat.getMessages().add(message);
                messageService.saveOne(message);
                chatService.saveOne(chat);
            System.out.println("1 more added");
            return null;
        };

        final Callable sizw = () -> {
            while (true) {
                Thread.sleep(4000);
                if (messageService.getAllByChatId(1).size() > before)
                    System.out.println("size changed" + messageService.getAllByChatId(1).size() );
            }
        };

        siz.call();
        sizw.call();

        Message message = new Message();
        message.setAuthor("laxxy");
        message.setIsReaded(false);
        message.setMessage("asdasdasd");
        message.setChat(chat);
        chat.getMessages().add(message);
        messageService.saveOne(message);
        chatService.saveOne(chat);

        System.out.println("after"+messageService.getAllByChatId(1).size());*/


    }
}
