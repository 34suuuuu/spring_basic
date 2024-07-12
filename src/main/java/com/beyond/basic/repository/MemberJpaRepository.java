package com.beyond.basic.repository;

import java.util.List;

import com.beyond.basic.domain.Member;

public class MemberJpaRepository implements MemberRepository{
	@Override
	public Member save(Member member) {
		return null;
	}

	@Override
	public Member findById(Long id) {
		return null;
	}

	@Override
	public List<Member> findAll() {
		return List.of();
	}
}
