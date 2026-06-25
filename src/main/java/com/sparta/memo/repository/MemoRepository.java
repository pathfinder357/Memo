package com.sparta.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.sparta.memo.entity.Memo;


public interface MemoRepository extends JpaRepository<Memo, Long> {
	//JpaRepository에서 simpleJpaRepository 클래스를 스프링 시작할때 생성
	//그때 이렇게 작성한 아래의 메서드들을 모두 자동으로 구현함

	// memo 테이블에 요청하고자 하는 sql을 메서드이름으로 만드는 예시
	List<Memo> findAllByOrderByModifiedAtDesc();

	// where조건문 뒤에서 값을 동적으로 처리 할수 있는 방법은 query Method 파라미터에 값을 넣어주면 됨
	List<Memo> findAllByContentsContainsOrderByModifiedAtDesc(String contents);

}
