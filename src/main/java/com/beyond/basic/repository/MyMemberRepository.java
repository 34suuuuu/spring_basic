package com.beyond.basic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.beyond.basic.domain.Member;

@Repository
public interface MyMemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	List<Member> findByName(String name);

	// jpql문법을 통한 raw쿼리 작성시 컴파일타임에서 오류 체크
	// @Query("select m from Member m")
	// List<Member> myFindAll();
}