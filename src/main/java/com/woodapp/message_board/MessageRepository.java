package com.woodapp.message_board;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woodapp.message_board.Message;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}