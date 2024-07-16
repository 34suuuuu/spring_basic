package com.beyond.basic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beyond.basic.domain.CommonErrorDto;
import com.beyond.basic.domain.CommonResDto;
import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberDetResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.domain.MemberUpdateDto;
import com.beyond.basic.service.MemberService;

@RestController
// RestController의 경우 모든 메서드 상단에 @ResponseBody가 붙는 효과
@RequestMapping("/rest")
public class MemberRestController {

	private final MemberService memberService;
	private final MemberController memberController;

	@Autowired
	public MemberRestController(MemberService memberService, MemberController memberController){
		this.memberService = memberService;
		this.memberController = memberController;
	}

	@GetMapping("/member/list")
	@ResponseBody
	// public List<MemberResDto> memberList() {
	// 	List<MemberResDto> memberList = memberService.memberList();
	// 	return memberList;
	// }
	public ResponseEntity<CommonResDto> memberList(Model model) {
		// repository에서 받는 List<Member>를 화면에 출력하기위해 필요한 Model객체
		List<MemberResDto> memberList = memberService.memberList();
		CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "memberList ok", memberList);
		return new ResponseEntity<>(commonResDto, HttpStatus.OK);
	}

	@GetMapping("/member/detail/{id}")
	// public MemberDetResDto memberDetail(@PathVariable Long id) {
	// 	return memberService.memberDetail(id);
	// }
	public ResponseEntity<Object> memberDetail(@PathVariable Long id) {
		try{
			MemberDetResDto memberDetResdto = memberService.memberDetail(id);
			CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "detail ok", memberDetResdto);
			return new ResponseEntity<>(commonResDto, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.NOT_FOUND, "detail not found");
			return new ResponseEntity<>(commonErrorDto, HttpStatus.NOT_FOUND);
		}
	}


	@PostMapping("/member/create")
	@ResponseBody
	// public String memberCreatePost(@RequestBody MemberReqDto dto) {
	// 	try{
	// 		memberService.memberCreate(dto);
	// 		return "OK";
	// 	}catch(IllegalArgumentException e){
	// 		e.printStackTrace();
	// 		return "error!!";
	// 	}
	// }
	public ResponseEntity<Object> memberCreatePost(@RequestBody MemberReqDto dto) {
		try{
			Member member = memberService.memberCreate(dto);
			CommonResDto commonResDto = new CommonResDto(HttpStatus.OK, "create ok", member);
			return new ResponseEntity<>(commonResDto, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			CommonErrorDto commonErrorDto = new CommonErrorDto(HttpStatus.BAD_REQUEST, "create error");
			return new ResponseEntity<>(commonErrorDto, HttpStatus.BAD_REQUEST);
		}
	}

// 	수정은 2가지 요청방식 : PUT, PATCH 요청
// 	PATCH 요청은 부분 수정, PUT 요청은 덮어쓰기
	@PatchMapping("/member/pw/update")
	public String memberList (@RequestBody MemberUpdateDto dto){
		memberService.pwUpdate(dto);
		return "OK";
	}

	@DeleteMapping("/member/delete/{id}")
	public String memberDelete(@PathVariable Long id){
		memberService.delete(id);
		return "OK";
	}
}

