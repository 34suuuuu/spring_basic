package com.beyond.basic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.basic.domain.Post;
import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private final PostRepository postRepository;

	PostService(PostRepository postRepository){
		this.postRepository = postRepository;
	}

	public List<PostResDto> postList(){
		List<Post> postList = postRepository.findAll();
		List<PostResDto> postResDtoList = new ArrayList<PostResDto>();
		for (Post post : postList) {
			postResDtoList.add(post.fromEntity());
			System.out.println("저자의 이름은: " + post.getMember().getEmail());
		}
		return postResDtoList;
	}
}
