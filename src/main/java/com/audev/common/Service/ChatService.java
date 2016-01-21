package com.audev.common.Service;

import com.audev.common.Entity.Chat;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface ChatService {
    public List<Chat> getAll();
    public void saveOne(Chat chat);
    public Chat getOneById(long id);
}
