package com.beyond.basic.domain;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

@Getter
// 기본적으로 Entity는 상속관계가 불가능하여, 해당 annotation을 붙여야 상속관계 성립 가능
@MappedSuperclass
public abstract class BaseEntity {
	@CreationTimestamp
	private LocalDateTime createdTime;
	@UpdateTimestamp
	private LocalDateTime updateTime;
}
