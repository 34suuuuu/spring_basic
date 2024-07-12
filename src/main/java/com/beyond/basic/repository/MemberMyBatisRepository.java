package com.beyond.basic.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.beyond.basic.domain.Member;

@Repository
// 해당 레포지토리를 mybatis기술을 쓰겠다는 표현
@Mapper
public interface MemberMyBatisRepository extends MemberRepository{

}
