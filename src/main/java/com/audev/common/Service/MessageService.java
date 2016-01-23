package com.audev.common.Service;

import com.audev.common.Entity.Message;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface MessageService {
    void getAll();
    void saveOne(Message message);
    List<Message> getAllByChatId(long id);
}
