package com.audev.common.Controller;

import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.Message;
import com.audev.common.Entity.User;
import com.audev.common.Service.ChatService;
import com.audev.common.Service.MessageService;
import com.audev.common.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by laxxy on 21.01.16.
 */

@Controller
@RequestMapping("/conversations")
public class ChatController {

    private final ConcurrentHashMap<DeferredResult<List<Message>>, Integer> chatRequests =
            new ConcurrentHashMap<>();

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping
    public String printConversations(ModelMap modelMap) {

        if (getUserFromSession() != null) {
            List<Chat> chats = new ArrayList<>();
            List<Lot> lots = getUserFromSession().getLots();
            for (Lot lot : lots) {
                chats.addAll(lot.getChats());
            }

            if (!chats.isEmpty())
                modelMap.addAttribute("chats", chats);
            else modelMap.addAttribute("nodata", "No active conversations founded");

            return "conversations";
        }
        else return "redirect:/";
    }

    @RequestMapping(value = "/conversations/{input}",method= RequestMethod.GET)
    @ResponseBody
    public DeferredResult<List<Message>> getMessages(@PathVariable String input, @RequestParam int messageIndex) {

        final DeferredResult<List<Message>> deferredResult = new DeferredResult<>(null, Collections.emptyList());
        this.chatRequests.put(deferredResult, messageIndex);

        deferredResult.onCompletion(() -> chatRequests.remove(deferredResult));

        List<Message> messages = this.chatService.getOneById(Integer.valueOf(input)).getMessages();
        if (!messages.isEmpty()) {
            deferredResult.setResult(messages);
        }

        return deferredResult;
    }

    @RequestMapping(value = "/conversations/{input}",method= RequestMethod.POST)
    @ResponseBody
    public void postMessage(@PathVariable String input, @RequestParam String message) {

        Message messagein = new Message();
        messagein.setAuthor(getUserFromSession().getLogin());
        messagein.setChat(chatService.getOneById(Integer.valueOf(input)));
        messagein.setIsReaded(false);
        messagein.setMessage(message);
        messageService.saveOne(messagein);

        Chat chat = chatService.getOneById(Integer.valueOf(input));
        chat.getMessages().add(messagein);
        chatService.saveOne(chat);

        // Update all chat requests as part of the POST request
        // See Redis branch for a more sophisticated, non-blocking approach

        for (Map.Entry<DeferredResult<List<Message>>, Integer> entry : this.chatRequests.entrySet()) {
            List<Message> messages = getAll(entry.getValue(), Integer.valueOf(input));
            entry.getKey().setResult(messages);
        }
    }

    private User getUserFromSession() {
        UserDetails userDetails;
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return userService.getUserByEmail(userDetails.getUsername());
        }
        else return null;
    }

    public List<Message> getAll(int index, int id) {

        if (this.chatService.getOneById(id).getMessages().isEmpty()) {
            return Collections.<Message> emptyList();
        }
        List<Message> messages = this.chatService.getOneById(id).getMessages();
        Assert.isTrue((index >= 0) && (index <= messages.size()), "Invalid message index");
        return messages.subList(index, messages.size());
    }
}
