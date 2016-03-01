package com.audev.common.Controller;

import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Service.*;
import com.audev.common.View.UnreadedMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
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

    /**
     *
     * @param input -> input string (category select | search)
     * @param num
     * @return list with finded data
     */
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

    /**
     *
     * @param id -> chat id
     * @param size -> chat size
     * @return messages after chat update
     * @throws Exception
     */
    @PreAuthorize("!isAnonymous()")
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

    /**
     *
     * @return unread messages size
     */
    @Transactional
    @JsonView(UnreadedMessageView.main.class)
    @PreAuthorize("!isAnonymous()")
    @RequestMapping(value = "/conversations/unreaded", method = RequestMethod.GET)
    public UnreadedMessageView getUnreaded() {

        final User user = getUserFromSession();
        if (user != null && user.getChats() != null) {
            final List<Chat> chats = user.getChats();
            final int[] count = {0};
            chats.forEach(o -> o.getMessages().forEach(k -> {
                if (!Objects.equals(k.getAuthor(), user.getLogin()) && !k.isReaded())
                    count[0]++;
            }));
            UnreadedMessageView un = new UnreadedMessageView(); un.setSize(count[0]);
            return un;
        }
        return null;
    }

    /**
     * Insert new message
     * @param id - chat id
     * @param incomeMessage - > income message
     */
    @PreAuthorize("!isAnonymous()")
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

    /**
     * Creates new chat
     * @param lotid -> lot id
     * @param message -> income message
     * @return result
     */
    @PreAuthorize("!isAnonymous()")
    @RequestMapping(value = "/lot/{lotid}", method = RequestMethod.POST)
    public String newChat(@PathVariable String lotid, @RequestBody String message) {

        if (!lotid.isEmpty() && !message.isEmpty()) {
            Lot currentLot = lotService.getOne(Long.valueOf(lotid));
            User user = getUserFromSession();
            User autor = currentLot.getUser();
            if (user.getLots().contains(currentLot)){
                return "Sorry, you cant write to yourself.";
            }
            if (!user.getLots().contains(currentLot)) {
                Chat chat = new Chat();
                chat.getUsers().add(user);
                chat.getUsers().add(autor);

                Message message1 = new Message();
                message1.setAuthor(user.getLogin());
                message1.setIsReaded(false);
                message1.setMessage(message);
                message1.setChat(chat);

                currentLot.getChats().add(chat);
                chat.setLot(currentLot);
                chat.getMessages().add(message1);
                chatService.saveOne(chat);
                messageService.saveOne(message1);
                lotService.addOne(currentLot);

                //add this chat to lotAutor & writer
                autor.getChats().add(chat);
                user.getChats().add(chat);
                userService.addUser(user);

                return "done";
            }
            else return "You already have active conversation";
        }
        return "Please, write something to do that.";
    }

    /**
     *
     * @return user from current session
     */
    private User getUserFromSession() {
        UserDetails userDetails;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserByEmail(userDetails.getUsername());
        }
        else return null;
    }
}