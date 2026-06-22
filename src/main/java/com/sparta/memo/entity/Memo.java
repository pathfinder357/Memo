package com.sparta.memo.entity;

import com.sparta.memo.dto.MemoRequestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Memo {
	private Long id;
	private String username;
	private String contents;

	// 생성자 만드는 이유 : 클라이언트에서 받아온 데이터(requestDto)를 위에 선언한 id,username,contetns에 데이터를 넣어주기 위함

	public Memo(MemoRequestDto requestDto) {
		this.username = requestDto.getUsername();
		this.contents = requestDto.getContents();
	}
}