package com.beyond.basic.repository;

import java.util.List;

import com.beyond.basic.domain.Member;

public interface MemberRepository {

	Member save(Member member);

	List<Member> findAll();	// 	select * from ;

	Member findById(Long id);  // select * from where id = id


}
