package com.sparta.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.memo.dto.MemoRequestDto;
import com.sparta.memo.dto.MemoResponseDto;
import com.sparta.memo.entity.Memo;
import com.sparta.memo.repository.MemoRepository;

// component 어노테이션을 사용함으로써 스프링에서 MemoService를 Bean으로 등록함
@Service
public class MemoService {

	private final MemoRepository memoRepository;

	// MemoService가 생성될때 파라미터 JdbcTemplate를 받아서
	// 근데 위의 주석처럼 되면 의존성이 강해짐
	public MemoService(MemoRepository memoRepository) {
		// 여기서 memoRepository를 딱 하나 만드는 것
		this.memoRepository = memoRepository;
	}


	public List<MemoResponseDto> getMemos() {

		// stream()에서 memo가 하나씩 빠져나가면서 .map()에 의해 변환이 됨
		// MemoResponseDto의 생성자 중에서 memo를 파라미터로 가지고있는 생성자 호출
		// 하나 씩 변환하는 호출 된 객체를 list 타입으로 바꿔줌

		return memoRepository.findAll().stream().map(MemoResponseDto::new).toList();

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

	@Transactional
	public Long updateMemos(Long id, MemoRequestDto requestDto) {

		Memo memo = findMemo(id);
			// memo 내용 수정
			memo.update(requestDto);

			return id;


	}



	public Long deleteMemo(Long id) {
		// 해당 메모가 DB에 존재하는지 확인
		Memo memo = findMemo(id);

		// memo 삭제
		memoRepository.delete(memo);
		return id;

	}

	private Memo findMemo(Long id) {
		return memoRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("Memo not found"));
	}
}
