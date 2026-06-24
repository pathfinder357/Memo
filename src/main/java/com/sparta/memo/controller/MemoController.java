package com.sparta.memo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.service.MemoService;

@RestController
@RequestMapping("/api")
public class MemoController {

	private final MemoService memoService;

	// public MemoController(MemoService memoService)는 auto-wired 할수 없다고 뜸(그러한 Bean타입을 찾을수없다)
	// 의문점? 외부에서 미리 만든 객체를 주입하는 DI패턴이기 때문에 기존 코드를 엎엇음
	// 도대체 서비스, 컨트롤러, 레퍼지토리는 메인 메서드에 아무것도 적지 않았는데 어디서 주입시킨다는거?
	// bean은 스프링이 관리하는 객체 & Ioc Container는 빈을 모아둔 하나의 container
	public MemoController(MemoService memoService) {
		this.memoService = memoService;
	}

	@PostMapping("/memos")
	public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto) {

		// 객체간 이동(다른 클래스의 메서드를 호출)
		// 하기 위해서는 객체를 만들어야함(instance화)
		 return memoService.createMemo(requestDto);
	}

	@GetMapping("/memos")
	public List<MemoResponseDto> getMemos() {

		return memoService.getMemos();
	}

	@PutMapping("/memos/{id}")
	public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {

		return memoService.updateMemos(id, requestDto);
		// 해당 메모가 DB에 존재하는지 확인

	}

	@DeleteMapping("/memos/{id}")
	public Long deleteMemo(@PathVariable Long id) {
		return memoService.deleteMemo(id);
	}


}