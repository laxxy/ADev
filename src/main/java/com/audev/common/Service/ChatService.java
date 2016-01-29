package com.audev.common.Service;

import com.audev.common.Entity.Chat;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface ChatService {
    List<Chat> getAll();
    List<Chat> getChatsByUserId(long id);
    void saveOne(Chat chat);
    Chat getOneById(long id);


}
