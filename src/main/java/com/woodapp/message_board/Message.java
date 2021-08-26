package com.woodapp.message_board;

import javax.persistence.*;

@Entity
@Table(name="messages")
public class Message {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;
    private String name;
    private String post_content;
    private String date_created;
    
    public Integer getPostId() {
		return post_id;
	}
	public void setPostId(Integer post_id) {
		this.post_id = post_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
	}
    
}