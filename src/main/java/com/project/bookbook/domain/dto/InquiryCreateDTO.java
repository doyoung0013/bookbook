package com.project.bookbook.domain.dto;

import java.time.LocalDateTime;

import com.project.bookbook.domain.entity.QNAEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InquiryCreateDTO {
	
	
	private long qnaNum;
	private String title; //답변 제목
	private String content; //답변 내용
}
