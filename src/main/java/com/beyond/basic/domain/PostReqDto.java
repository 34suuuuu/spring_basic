package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReqDto {
	private String title;

	public Post toEntity(){
		Post post = new Post(this.title);
		return post;
	}
}
