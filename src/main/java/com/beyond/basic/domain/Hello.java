package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// lombok 라이브러리를 통해 getter, setter 어노테이션 사용
// @Getter
// @Setter
@Data // getter, setter, toString 등을 포함한다.
// @NoArgsConstructor	: 기본생성자를 만드는 어노테이션
// @AllArgsConstructor : 모든 매개변수를 사용한 생성자를 만드는 어노테이션
public class Hello {
	private String name;
	private String email;
	private String password;

	// @Override
	// public String toString() {
	// 	return "name : " + this.name + "/nemail : " + this.email + "\npassword : " + this.password;
	// }
}