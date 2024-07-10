package com.beyond.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.beyond.basic.domain.Member;

@Controller
public class MemberController {

	// 회원목록조회
	@GetMapping("/member/list")
	public String memberList() {
		return "member/member-list";
	}

	// 	회원 상세 조회 : memberDetail
	// 	url(uri): member/1, member/2
	// 	화면명: member-detail
	@GetMapping("/member/{id}")
	// int 또는 long 받을 경우 스프링에서 형변화(String -> Long)
	public String memberDetail(@PathVariable Long id) {
		return "member/member-detail";
	}

	// 	회원가입화면 주고
	// 	url: member/create
	@GetMapping("/member/create")
	public String memberCreate() {
		return "member/member-create";
	}

	// 	회원 가입 데이터를 받는다
	// 	url: member/create
	// 	name, email, password
	@PostMapping("/member/create")
	public String memberCreatePost(Member member){
		System.out.println(member);
		return null;
	}
}
