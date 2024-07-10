package com.beyond.basic.domain;

import java.util.List;

import lombok.Data;

@Data
public class Student {
	private String name;
	private String email;
	private List<Student.Grade>grades;

	@Data
	static class Grade {
		// 변수명 정확히 html과 같게 해줘야함
		private String className;
		private String point;
	}
}

