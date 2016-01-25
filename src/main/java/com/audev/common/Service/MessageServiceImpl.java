package com.audev.common.Service;

import com.audev.common.Entity.Message;
import com.audev.common.Repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public void getAll() {
        messageRepository.findAll();
    }

    @CacheEvict(value = "Message", allEntries = true)
    @Override
    public void saveOne(Message message) {
        messageRepository.save(message);
    }

    @Cacheable(value = "Message")
    @Override
    public List<Message> getAllByChatId(long id) {
        return messageRepository.getByChatId(id);
    }

    @Override
    public List<Message> getAllUnreaded(String userLogin, long chatId) {
        return messageRepository.getAllUnreaded(userLogin, chatId);
    }
}
