package com.beyond.basic.service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beyond.basic.domain.Member;
import com.beyond.basic.domain.MemberDetResDto;
import com.beyond.basic.domain.MemberReqDto;
import com.beyond.basic.domain.MemberResDto;
import com.beyond.basic.domain.MemberUpdateDto;
import com.beyond.basic.domain.Post;
import com.beyond.basic.repository.MyMemberRepository;

// input값의 검증 및 실질적인 비니지스 로직은 서비스 계층에서 수행
@Service    //서비스 계층임을 표현한과 동시에 싱긅톤 객체로 생성
// Transactional 어노테이션을 통해 모든 메서드에 트랜잭션을 적용하고, 만약 예외가 발생시 롤백처리 자동화(각 메서드마다 하나의 트랜잭션으로 묶는다는 뜻)
@Transactional(readOnly = true)
public class MemberService {
	private final MyMemberRepository memberRepository;

	@Autowired	//싱글톤 객체를 주입(DI) 받는다는 것을 의미
	public MemberService(MyMemberRepository memberMemoryRepository){
		this.memberRepository = memberMemoryRepository;
	}

	public void memberCreate(MemberReqDto dto){
		if (dto.getPassword().length() < 8) {
			throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
		}
		Member member = dto.toEntity();
		// 	Transactional 롤백처리 테스트
		// if(member.getName().equals("kim")){
		// 	throw new IllegalArgumentException("잘못된 입력값입니다.");
		// }
		if(memberRepository.findByEmail(dto.getEmail()).isPresent()){
			throw new IllegalArgumentException("이미 존재하는 이메일 입니다.");
		}
		memberRepository.save(member);
	}

	public MemberDetResDto memberDetail(Long id){
		Optional<Member> optMember = memberRepository.findById(id);
		// MemberDetailResDto resDto = new MemberDetailResDto();
		// 클라이언트에게 적절함 예외 메시지와 상태코드를 주는것이 주요 목적
		// 또한 예외를 강제 발생시킴으로서 적절한 롤백처리 하는 것도 주요 목적
		Member member = optMember.orElseThrow(() -> new EntityNotFoundException("없는 회원입니다."));
		System.out.println("글쓴이의 글쓴 갯수 = " + member.getPosts().size());
		for(Post post : member.getPosts()){
			System.out.println("글의 제목은 " + post.getTitle());
		}
		return member.detFromEntity();
	}

	public List<MemberResDto> memberList(){
		List<Member> memberList = memberRepository.findAll();
		List<MemberResDto> resDtoList = new ArrayList<MemberResDto>();
		for(Member member : memberList){
			resDtoList.add(member.listFromEntity());
		}
		return resDtoList;
	}

	public void pwUpdate(MemberUpdateDto dto){
		Member member = memberRepository.findById(dto.getId())
			.orElseThrow(() -> new EntityNotFoundException("member is not found"));
		// 기존 객체를 조회 후 수정한 다음에 save 진행시 jpa update 실행
		member.updatePw(dto.getPassword());
		memberRepository.save(member);
	}

	public void delete(Long id){
		Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("member is not found"));
		memberRepository.delete(member); // 완전 삭제
		// member.updateDelYn("Y");
		// memberRepository.save(member);
	}
}
