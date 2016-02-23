import com.audev.common.Config.DataConfig;
import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Service.ChatService;
import com.audev.common.Service.LotService;
import com.audev.common.Service.MessageService;
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

import java.util.Date;
import java.util.List;

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

    private User user;
    private User anotherOne;
    private Lot lot;

    @Before
    public void setup() {
        user = userService.getUserByEmail("cosxtgx@gmail.com");
        anotherOne = userService.getUserByEmail("asd@asd");
        lot = lotService.getOneByName("test# 0");
    }

    @Transactional
    @Rollback(value = false)
    @Test
    public void fillAndSave() {

        Chat chat = new Chat();
        chat.setLot(lot);
        chat.setStartDate(new Date());

        chat.getUsers().add(user);
        chat.getUsers().add(anotherOne);

        for (int i = 0; i < 10; i++) {
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
        }

        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setMessage("Bsd for Test");
            message.setIsReaded(false);
            lot.getChats().add(chat);
            chat.getMessages().add(message);
            chat.setLot(lot);
            message.setChat(chat);
            message.setAuthor(anotherOne.getLogin());
            lotService.addOne(lot);
            chatService.saveOne(chat);
            messageService.saveOne(message);
        }

        user.getChats().add(chat);
        anotherOne.getChats().add(chat);
        userService.addUser(user);
        userService.addUser(anotherOne);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    public void get() {
       List<Chat> list = userService.getUserByEmail("cosxtgx@gmail.com").getChats();
        list.forEach(h -> {
            System.out.println("Inner chat: |||||||||");
            h.getMessages().forEach(k -> {
                System.out.print(k.getMessage());
                System.out.println(k.getAuthor());
                System.out.println();
            });
        });

        List<Chat> list2 = userService.getUserByEmail("asd@asd").getChats();
        list2.forEach(h -> {
            System.out.println("Inner chat: ||||||||");
            h.getMessages().forEach(k -> {
                System.out.print(k.getMessage());
                System.out.println(" " + k.getAuthor());
                System.out.println();
            });
        });
    }
 }
