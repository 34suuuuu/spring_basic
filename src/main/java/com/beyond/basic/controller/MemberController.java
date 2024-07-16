package com.beyond.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.beyond.basic.domain.CommonResDto;
import com.beyond.basic.domain.MemberDetResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.service.MemberService;

// @RequiredArgsConstructor
@Controller
public class MemberController {

	// 의존성주입(DI) 방법1. 생성자 주입방식 (가장 많이 사용하는 방식)
	// 장점 1) final을 통해 상수로 사용가능 2) 다형성 구현가능 3) 순환 참조 방지
	// 생성자가 1개밖에 없는 경우에는 Autowired 생략 가능

	private final MemberService memberService;

	@Autowired
	public MemberController(MemberService memberService){
		this.memberService = memberService;
	}

	// // 의존성주입 방법2. 필드주입방식(Autowired만 사용) -> 다형성 구현이 안됨: 이 부분 짚고 넘어가기
	// @Autowired
	// private MemberService memberService;

	// 의존성주입 방법3. Annotation(@RequiredArgs)을 이용하는 방식
	// @RequiredArgsContructor:
	// @NonNull 어노테이션, final 키워드가 붙어있는 필드를 대상으로 생성자 생성
	// 생성자주입 방법1과 같은 결과
	// @NonNull
	// private MemberService memberService;
	// private final MemberService memberService;

	@GetMapping("/")
	public String home(){
		return "member/home";
	}

	// 회원목록조회
	@GetMapping("/member/list")
	public String memberList(Model model) {
		// repository에서 받는 List<Member>를 화면에 출력하기위해 필요한 Model객체
		List<MemberResDto> memberList = memberService.memberList();
		model.addAttribute("memberList", memberList);
		return "member/member-list";
	}

	// 	회원 상세 조회 : memberDetail
	// 	url(uri): member/1, member/2
	// 	화면명: member-detail
	@GetMapping("/member/detail/{id}")
	// int 또는 long 받을 경우 스프링에서 형변화(String -> Long)
	public String memberDetail(@PathVariable Long id, Model model) {
		MemberDetResDto member = memberService.memberDetail(id);
		model.addAttribute("member", member);
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
	public String memberCreatePost(MemberReqDto dto, Model model) {
		try{
			memberService.memberCreate(dto);
			// 화면 리턴이 아닌 url재호출
			// 화면 리턴을 하면 데이터가 없기 때문에 Model에 데이터를 넣어줘야해서
			return "redirect:/member/list";
		}catch(IllegalArgumentException e){
			model.addAttribute("error", e.getMessage());
			return "member/member-error";
		}
	}
}
