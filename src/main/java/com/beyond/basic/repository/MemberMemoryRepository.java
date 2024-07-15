// package com.beyond.basic.repository;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
//
// import org.springframework.stereotype.Repository;
//
// import com.beyond.basic.domain.Member;
//
// // 클래스가 Repository 계층임을 표현함과 동시에 싱글톤객체로 생성(단하나의 객체만 만든다)
// @Repository
// public class MemberMemoryRepository implements MemberRepository{
//
// 	private final List<Member> memberList;
//
// 	MemberMemoryRepository(){
// 		memberList = new ArrayList<>();
// 	}
//
// 	@Override
// 	public Member save(Member member) {
// 		memberList.add(member);
// 		return member;
// 	}
//
// 	@Override
// 	public Optional<Member> findById(Long id) {
// 		return null;
// 	}
//
// 	@Override
// 	public List<Member> findAll() {
// 		return memberList;
// 	}
// }
