package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor	// 전부 다 알고있는 내용이니깐 쓸 수 있음
@NoArgsConstructor
public class MemberDetResDto {
	private Long id;
	private String name;
	private String email;
	private String password;
	private String createdTime;
}