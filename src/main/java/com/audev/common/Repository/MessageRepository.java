package com.audev.common.Repository;

import com.audev.common.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("select h from Message h where h.chat.id like :id")
    List<Message> getByChatId(@Param("id") long id);

    @Query("select h from Message h where h.author not like :autor and h.isReaded = false and h.chat.id like :id")
    List<Message> getAllUnreaded(@Param("autor") String autor, @Param("id") long id);

}
