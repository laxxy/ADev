package com.audev.common.Repository;

import com.audev.common.Entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by cosxt on 12.01.2016.
 */
public interface ChatRepository extends JpaRepository<Chat, Long> {
}
