package com.beyond.basic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberDetailResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.repository.MemberJdbcRepository;
import com.beyond.basic.repository.MemberMemoryRepository;
import com.beyond.basic.repository.MemberMyBatisRepository;
import com.beyond.basic.repository.MemberRepository;

// input값의 검증 및 실질적인 비니지스 로직은 서비스 계층에서 수행
@Service    //서비스 계층임을 표현한과 동시에 싱긅톤 객체로 생성
public class MemberService {
	private final MemberRepository memberRepository;

	@Autowired	//싱글톤 객체를 주입(DI) 받는다는 것을 의미
	public MemberService(MemberMyBatisRepository memberMemoryRepository){
		this.memberRepository = memberMemoryRepository;
	}

	public void memberCreate(MemberReqDto dto){
		if (dto.getPassword().length() < 8) {
			throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
		}
		Member member = new Member();
		member.setName(dto.getName());
		member.setPassword(dto.getPassword());
		member.setEmail(dto.getEmail());
		memberRepository.save(member);
	}

	public MemberDetailResDto memberDetail(Long id){
		Member member = memberRepository.findById(id);
		MemberDetailResDto resDto = new MemberDetailResDto();
		resDto.setId(member.getId());
		resDto.setName(member.getName());
		resDto.setEmail(member.getEmail());
		resDto.setPassword(member.getPassword());
		System.out.println(resDto.toString());
		return resDto;
	}

	public List<MemberResDto> memberList(){
		List<Member> memberList = memberRepository.findAll();
		List<MemberResDto> resDtoList = new ArrayList<MemberResDto>();

		for(Member member : memberList){
			MemberResDto resDto = new MemberResDto();
			resDto.setId(member.getId());
			resDto.setName(member.getName());
			resDto.setEmail(member.getEmail());
			resDtoList.add(resDto);
		}
		return resDtoList;
	}
}
