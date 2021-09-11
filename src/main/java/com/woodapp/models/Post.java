package com.woodapp.models;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="posts")
@NoArgsConstructor

public class Post {

	//Need to map the USERS table and the Comments table using their ids**
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "post_id", updatable=false)
    private Integer id;
	
	@NotNull
	@Column(name="post_author_name", nullable=false)
    private String name;
	
	@NotNull
	@Column(name="post_content", nullable=false)
    private String post_content;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "post_id")
	private List<User> users;

	private LocalDateTime timeStamp = LocalDateTime.now();

	public Post(@NotNull String name, @NotNull String post_content, LocalDateTime timeStamp) {
		super();
		this.name = name;
		this.post_content = post_content;
		this.timeStamp = timeStamp;
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
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Post{" +
				"name='" + name + '\'' +
				", post_content='" + post_content + '\'' +
				", users=" + users +
				", timeStamp=" + timeStamp +
				'}';
	}
}