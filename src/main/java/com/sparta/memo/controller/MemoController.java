package com.sparta.memo.controller;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
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

	public MemoController(JdbcTemplate jdbcTemplate) {
		this.memoService = new MemoService(jdbcTemplate);
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