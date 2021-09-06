package com.woodapp.post_comment;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//This should be used for any comment box we have on the site - especially for the Water Cooler component
@RequestMapping("/api")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	CommentRepository dao;
	
	@GetMapping("/comment")
	public List<Comment> getComments() {
		List<Comment> foundComments = dao.findAll();
        return foundComments;
	}

	 @GetMapping("/comment/{id}")
	 public ResponseEntity<Comment> getComment(@PathVariable("id") Integer id) {
	    Comment foundComment = dao.findById(id).orElse(null);

	    if(foundComment == null) {
	    	return ResponseEntity.notFound().header("Comment","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundComment);
	    }

	 @PostMapping("/comment")
	 public ResponseEntity<Comment> postComment(@RequestBody Comment comment) {
	        Comment createdComment = dao.save(comment);
	        return ResponseEntity.ok(createdComment);
	    }
	 
	 @PutMapping("/comment/{id}")
		public Comment updateComment(@PathVariable("id") Integer id, @RequestBody Comment comment)

				throws Exception {
			Comment foundComment = dao.findById(id).orElse(null);
			foundComment.setName(comment.getName());
			foundComment.setPost_content(comment.getPost_content());
			foundComment.setTimeStamp(comment.getTimeStamp());
			dao.save(foundComment);

			return foundComment;
		}

	    @DeleteMapping("/comment/{id}")
	    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Integer id) {
	        Comment foundComment = dao.findById(id).orElse(null);


	        if(foundComment == null) {
	            return ResponseEntity.notFound().header("Comment","Nothing found with that id").build();
	        }else {
	            dao.delete(foundComment);
	        }
	        return ResponseEntity.ok().build();
	    } 
}
