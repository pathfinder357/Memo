package com.sparta.memo.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;

@RestController
@RequestMapping("/api")
public class MemoController {

	// 아직 강의 단계가 db연결 단계가 아니기 때문에 Map자료구조로 db와 유사한 형태를 만듦
	private final Map<Long, Memo> memoList = new HashMap<>();

	// 메모 생성하기 API
	@PostMapping("/memos")
	// 데이터가 바디 형태의 JSON으로 넘어옴 -> 그럼 어떻게 받지? requestBody로
	// requestDto로 Body의 데이터를 받음 -> createMemo는 requestDto를 받아서 어떤 로직을 수행하는 함수
	// MemoResponseDto는 반환타입
	public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
		// requestDto -> Entity(DB와 소통하는 클래스)
		// memo 엔티티에 뭘 넣어야하지? requestBody로 부터 받아온 데이터를 담는
		// requestDto(손님의 주문서)를 하나의 객체에 넣어야하지.
		// 따라서 Memo엔티티에는 이미 붕어빵틀이 있으니 그걸 붕어빵으로 만들려면 밀가루
		// 즉 생성자를 생성하는 것이 당연한거 ㅇㅇ
		// 즉 밑의 코드는 받은 데이터(팥)을 담아서 내용물을 채우는 코드
		Memo memo = new Memo(requestDto);

		// Memo Max ID Check
		Long maxId = memoList.size() > 0  ? Collections.max(memoList.keySet()) + 1 : 1;
		memo.setId(maxId);

		// DB 저장
		// ID값도 maxId를 통해서 구했으니 DB(임의로 현재는 Map)에 Id(key) memo(val)를 넣어줌
		// 진짜 완성된 붕어빵이 손님 봉투에 들어감
		memoList.put(memo.getId(), memo);

		// Entity -> ResponseDto
		// 반환 타입인 responseDto(붕어빵과 그걸 담는 봉투)를 이제 포장하는 단계
		// 현재 포장이 완성되었고 봉투는 각각 일련번호가 있어서 주문한 손님에 맞게 딱딱 배분됨
		MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

		return memoResponseDto;
	}

	@GetMapping("/memos")
	// List인 이유? Memo(MemoResponseDto)는 당연히 여러개일 가능성이 높으니깐
	public List<MemoResponseDto> getMemos(){
		// Map to List
		List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

		return responseList;
	}
}
