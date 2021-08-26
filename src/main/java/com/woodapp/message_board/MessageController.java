package com.woodapp.message_board;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class MessageController {
	
	@Autowired
	MessageRepository dao;
	
	@GetMapping("/message-board")
	public List<Message> getMessages() {
		List<Message> foundMessages = dao.findAll();
        return foundMessages;
	}
	
	 @GetMapping("/message-board/{post_id}")
	 public ResponseEntity<Message> getMessage(@PathVariable("post_id") Integer post_id) {
	    Message foundMessage = dao.findById(post_id).orElse(null);

	    if(foundMessage == null) {
	    	return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundMessage);
	    }

	 @PostMapping("/message-board")
	 public ResponseEntity<Message> postMessage(@RequestBody Message message) {
	        Message createdMessage = dao.save(message);
	        return ResponseEntity.ok(createdMessage);
	    }
	 
	 @PutMapping("/message-board/{post_id}")
		public Message updateMessage(@PathVariable("post_id") Integer post_id, @RequestBody Message message)
				throws Exception {
			Message foundMessage = dao.findById(post_id).orElse(null);
			foundMessage.setName(message.getName());
			foundMessage.setPost_content(message.getPost_content());
			foundMessage.setDate_created(message.getDate_created());
			dao.save(foundMessage);

			return foundMessage;
		}

	    @DeleteMapping("/message-board/{id}")
	    public ResponseEntity<Message> deleteMessage(@PathVariable("post_id") Integer post_id) {
	        Message foundMessage = dao.findById(post_id).orElse(null);

	        if(foundMessage == null) {
	            return ResponseEntity.notFound().header("Message","Nothing found with that id").build();
	        }else {
	            dao.delete(foundMessage);
	        }
	        return ResponseEntity.ok().build();
	    } 
}