package com.woodapp.message_board;

import lombok.*;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity(name="Message")
@Table(name="messages")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Message {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", updatable=false)
    private Integer id;
	
	@NotNull
	@Column(name="post_author_name", nullable=false, columnDefinition="TEXT")
    private String name;
	
	@NotNull
	@Column(name="post_content", nullable=false, columnDefinition="TEXT")
    private String post_content;

	private LocalDateTime timeStamp = LocalDateTime.now();

	public Message(Integer id, @NotNull String name, @NotNull String post_content, LocalDateTime timeStamp) {
		super();
		this.id = id;
		this.name = name;
		this.post_content = post_content;
		this.timeStamp = timeStamp;
	}

	public Integer getId() {
		return id;
	}
	public void setPostId(Integer id) {
		this.id = id;
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
		return "Message{" +
				"id=" + id +
				", name='" + name + '\'' +
				", post_content='" + post_content + '\'' +
				", timeStamp=" + timeStamp +
				'}';
	}
}