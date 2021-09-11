package com.woodapp.controllers;

import java.util.List;

import com.woodapp.models.Post;
import com.woodapp.repositories.PostRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//This should be used for any comment box we have on the site - especially for the Water Cooler component
@RequestMapping("/api/post")
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

	PostRepository dao;
	
	@GetMapping("/find/all")
	public List<Post> getPosts() {
		List<Post> foundPosts = dao.findAll();
        return foundPosts;
	}

	 @GetMapping("/find/{id}")
	 public ResponseEntity<Post> getPost(@PathVariable("id") Integer id) {
	    Post foundPost = dao.findById(id).orElse(null);
	    if(foundPost == null) {
	    	return ResponseEntity.notFound().header("Post","Nothing found with that id").build();
	        }
	        return ResponseEntity.ok(foundPost);
	    }

	 @PostMapping("/add")
	 public ResponseEntity<Post> postOnePost(@RequestBody Post post) {
	        Post createdPost = dao.save(post);
	        return ResponseEntity.ok(createdPost);
	    }
	 
	 @PutMapping("/update/{id}")
		public Post updatePost(@PathVariable("id") Integer id, @RequestBody Post post)
				throws Exception {
			Post foundPost = dao.findById(id).orElse(null);
			foundPost.setName(post.getName());
			foundPost.setPost_content(post.getPost_content());
			foundPost.setTimeStamp(post.getTimeStamp());
			dao.save(foundPost);
			return foundPost;
		}

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Post> deletePost(@PathVariable("id") Integer id) {
	        Post foundPost = dao.findById(id).orElse(null);

	        if(foundPost == null) {
	            return ResponseEntity.notFound().header("Post","Nothing found with that id").build();
	        }else {
	            dao.delete(foundPost);
	        }
	        return ResponseEntity.ok().build();
	    } 
}
