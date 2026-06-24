package com.sparta.memo.service;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;

public class MemoService {

	private final MemoRepository memoRepository;

	// MemoService가 생성될때 파라미터 JdbcTemplate를 받아서
	public MemoService(JdbcTemplate jdbcTemplate) {
		// 여기서 memoRepository를 딱 하나 만드는 것
		this.memoRepository = new MemoRepository(jdbcTemplate);
	}


	public List<MemoResponseDto> getMemos() {

		return memoRepository.findAll();

	}

	public MemoResponseDto createMemo(MemoRequestDto requestDto) {
		// RequestDto -> Entity
		Memo memo = new Memo(requestDto);

		// DB 저장

		Memo saveMemo = memoRepository.save(memo);

		// Entity -> ResponseDto
		MemoResponseDto memoResponseDto = new MemoResponseDto(memo);

		return memoResponseDto;
	}

	public Long updateMemos(Long id, MemoRequestDto requestDto) {



		Memo memo = memoRepository.findById(id);
		if(memo != null) {
			// memo 내용 수정
			memoRepository.update(id, requestDto);

			return id;
		} else {
			throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
		}
	}



	public Long deleteMemo(Long id) {
		// 해당 메모가 DB에 존재하는지 확인
		Memo memo = memoRepository.findById(id);
		if(memo != null) {
			// memo 삭제
			memoRepository.delete(id);
			return id;
		} else {
			throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
		}
	}
}
