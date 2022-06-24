package me.gogosing.persistence.dto;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.support.dto.filter.FilterLocalDateRange;
import org.springdoc.api.annotations.ParameterObject;

/**
 * 게시물 필터.
 */
@Schema(description = "게시판 목록 조회 조건 정보 모델")
@ParameterObject
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardCondition {

	@Parameter(description = "제목")
	private String title;

	@Parameter(description = "내용")
	private String contents;

	@Valid
	@Parameter(description = "최종 수정일 범위")
	private FilterLocalDateRange lastModifiedPeriod;
}
