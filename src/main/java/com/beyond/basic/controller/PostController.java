package com.beyond.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beyond.basic.domain.PostResDto;
import com.beyond.basic.service.PostService;

@Controller
public class PostController {

	@Autowired
	private final PostService postService ;

	PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/post/list")
	@ResponseBody
	public List<PostResDto> postList() {
		List<PostResDto> postList = postService.postList();
		return postList;
	}
}
