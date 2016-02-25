package com.audev.common.Controller;

import com.audev.common.Entity.Chat;
import com.audev.common.Entity.Lot;
import com.audev.common.Entity.User;
import com.audev.common.Service.ChatService;
import com.audev.common.Service.MessageService;
import com.audev.common.Service.UserService;
import com.audev.common.View.UnreadedMessageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Message processing
 *
 */
@Controller
@RequestMapping("/conversations")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    /**
     * Print conversations page with active conversations
     * @param modelMap -> ins. attributes
     * @return conversations page
     */
    @Transactional
    @RequestMapping
    public String printConversations(ModelMap modelMap) {

        if (getUserFromSession() != null) {
            List<Chat> chats = getUserFromSession().getChats();

            if (!chats.isEmpty())
                modelMap.addAttribute("chats", chats);
            else modelMap.addAttribute("nodata", "No active conversations founded");

            return "conversations";
        }
        else return "redirect:/";
    }

    /**
     * Print chat page by id
     * @param id -> chat id
     * @param modelMap -> ins. attributes
     * @return chat page
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "/chat/{id}")
    public String printChat(@PathVariable String id, ModelMap modelMap) {

        final User user = getUserFromSession();
        Chat chat = chatService.getOneById(Integer.valueOf(id));

        chat.getMessages().forEach(o -> {
            if(!o.getAuthor().equals(user.getLogin()) && !o.isReaded())
                o.setIsReaded(true);
            messageService.saveOne(o);
        });

        modelMap.addAttribute("messages", chat.getMessages());
        modelMap.addAttribute("user", chat.getLot().getUser());
        modelMap.addAttribute("lot", chat.getLot());

        return "chat";
    }

    /**
     * Return user from current session
     * @return User
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
