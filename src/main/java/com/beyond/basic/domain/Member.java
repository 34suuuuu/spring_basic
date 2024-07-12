package com.beyond.basic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
// entity어노테이션을 통해 엔티티 매니저에게 객체관리를 위엄
// 해당 클래스명으로 테이블 및 컬럼을 자동생성하고 각종 설정정보 위임
@Entity
public class Member {
	@Id	// pk설정
	// Identity: auto_increment설정
	// auto: jpa 자동으로 적절한 전략을 선택하도록 맡기는 것.
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	// Long은 Bigint로 변환
	// String은 Varchar(255)로 변환. name변수명이 name컬럼명으로 변환
	private String name;
	// nullable=false: not null제약조건
	@Column(nullable = false, length = 50, unique = true)
	private String email;
	// @Column(name="pw") 이렇게 할 수 있으나, 컬럼명과 변수명을 일치시키는것이 혼선을 줄일 수 있음
	private String password;
}
