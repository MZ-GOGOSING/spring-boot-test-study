package me.gogosing.persistence.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.support.dto.filter.FilterLocalDateRange;

/**
 * 게시물 필터.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "게시물 목록 조회 조건 모델")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardCondition {

	@ApiModelProperty("제목")
	private String title;

	@ApiModelProperty("내용")
	private String contents;

	@ApiModelProperty("최종 수정일 범위")
	private FilterLocalDateRange lastModifiedPeriod;
}
