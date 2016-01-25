package com.audev.common.Controller;

import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Model.SearchAjaxResponseBody;
import com.audev.common.Model.SearchCriteria;
import com.audev.common.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by cosxt on 25.11.2015.
 */
@RestController
public class RstController {

    @Autowired
    private LotService lotService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatService chatService;

    @Autowired
    private UserService userService;

    @JsonView(Lot.Public.class)
    @Transactional
    @RequestMapping(value = "/filter/{input}")
    public List<Lot> getFilterData(@PathVariable String input, @RequestBody String num) {

        ArrayList<Lot> result = new ArrayList<>();

        if (subCategoryService.getOneByName(input) != null) {
            List<Lot> lots = subCategoryService.getOneByName(input).getLots();
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            result.addAll(lots.subList(start -10, end));
            return result;
        }
        else {
            List<Lot> lots = lotService.getBySearch(input);
            int start = Integer.valueOf(num.replaceAll("[a-zA-Z ={}:\"]+", ""))*10;
            int end = start > lots.size() ? lots.size() : start;
            result.addAll(lots.subList(start- 10, end));
            return result;
        }
    }

    //TODO implement sec. here
    @JsonView(Message.PublicMessage.class)
    @RequestMapping(value = "/conversations/chat/{id}", method = RequestMethod.POST)
    public Callable<List<Message>> asyncMessage(@PathVariable String id, @RequestBody String size) throws Exception {

        return () -> {
            while (true) {
                Thread.sleep(400);
                if (messageService.getAllByChatId(Integer.valueOf(id)).size() > Integer.valueOf(size)) {
                    for (Message message : messageService.getAllUnreaded(getUserFromSession().getLogin(), Integer.valueOf(id))) {
                        message.setIsReaded(true);
                        messageService.saveOne(message);
                    }
                    return chatService.getOneById(Integer.valueOf(id)).getMessages();
                }
            }
        };
    }

    //TODO implement sec. here
    @RequestMapping(value = "/conversations/chat/{id}", method = RequestMethod.PUT)
    public void postMessage(@PathVariable String id, @RequestBody String incomeMessage) {
        Message message = new Message();

        Chat chat = chatService.getOneById(Integer.valueOf(id));
        message.setAuthor(getUserFromSession().getLogin());
        message.setIsReaded(false);
        message.setMessage(incomeMessage);
        message.setChat(chat);
        chat.getMessages().add(message);
        messageService.saveOne(message);
        chatService.saveOne(chat);
    }

    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.POST)
    public String newChat(@PathVariable String lotid, @RequestBody String message) {

        if (!lotid.isEmpty() && !message.isEmpty()) {
            Lot currentLot = lotService.getOne(Long.valueOf(lotid));
            User user = getUserFromSession();
            if (user.getLots().contains(currentLot)){
                return "Sorry, you cant write to yourself.";
            }
            if (!user.getLots().contains(currentLot)) {
                Chat chat = new Chat();
                Message message1 = new Message();

                message1.setAuthor(user.getLogin());
                message1.setIsReaded(false);
                message1.setMessage(message);
                message1.setChat(chat);

                chat.setLot(currentLot);
                chat.getMessages().add(message1);
                chatService.saveOne(chat);
                messageService.saveOne(message1);

                user.getLots().add(currentLot);
                userService.addUser(user);

                return "done";
            }
            else return "You already have active conversation";
        }
        return "Please, write something to do that.";
    }

    private User getUserFromSession() {
        UserDetails userDetails;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserByEmail(userDetails.getUsername());
        }
        else return null;
    }
}