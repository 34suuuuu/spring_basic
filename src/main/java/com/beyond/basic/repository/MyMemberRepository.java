package com.beyond.basic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beyond.basic.domain.Member;

@Repository
public interface MyMemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByEmail(String email);

	List<Member> findByName(String name);
}