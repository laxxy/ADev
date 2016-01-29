package com.audev.common.Service;

import com.audev.common.Entity.Chat;
import com.audev.common.Repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Override
    public List<Chat> getAll() {
        return chatRepository.findAll();
    }

    @Override
    public List<Chat> getChatsByUserId(long id) {
        return chatRepository.getChatsByUserId(id);
    }

    @Override
    public void saveOne(Chat chat) {
        chatRepository.save(chat);
    }

    @Override
    public Chat getOneById(long id) {
        return chatRepository.findById(id);
    }
}
