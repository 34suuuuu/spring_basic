package com.beyond.basic.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
	@Id
	private Long id;
	private String title;
	// 1:1의 경우 OneToOne을 설정하고, unique=true로 설정.
	@ManyToOne
	@JoinColumn(name = "member_id")
	// JPA의 영속성(Persistance) 컨텍스트에 의해 Member객체가 관리
	private Member member;

	Post(String title) {
		this.title = title;
	}

	public PostResDto fromEntity(){
		return new PostResDto(this.id, this.title);
	}
}
