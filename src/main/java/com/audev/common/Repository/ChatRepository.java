package com.audev.common.Repository;

import com.audev.common.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {

    Chat findById(long id);

    @Query("select h from Chat h where h.lot.user.id like :id")
    List<Chat> getChatsByUserId(@Param("id") long id);
}
