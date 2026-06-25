package com.sparta.memo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // jpa entity 클래스들이 해당 출생 클래스를 상속할 경우
// createdAt modifiedAt과 같은 추상 클래스에 선언한 맴버 변수를 컬럼으로 인식
// 무슨말이냐? 타임 스탬프를 상속하는 엔티티 클래스들은  위에 서술한 맴버 변수들을 칼럼으로 가짐
// 그냥 클래스로 해도 되는데 왜 굳이 추상 클래스?
// ex)timestamped 엔티티(클래스)는 어차피 다른 클래스들을 상속하기 위해 만들어진 클래스
//  이 엔티티를 객체로 생성하지는 않음(필요가 없기때문에) 그래서 추상으로 함
@EntityListeners(AuditingEntityListener.class) // 자동으로 시간을 넣어주는 기능
public abstract class Timestamped {

	@CreatedDate
	@Column(updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@LastModifiedDate //조회한 엔티티 객체의 값을 변경할때 자동으로 해당 변경 시간으로 업데이트
	@Column
	@Temporal(TemporalType.TIMESTAMP) // 자바의 데이트 페키지에 있는 타입과 같은 날짜데이터를 맵핑할때 사용
	private LocalDateTime modifiedAt;
	// 마지막으로 JPA auditing 기능을 사용 하려면 메인 함수위에 @EnableJpaAuditing 어노테이션 달아야함
}