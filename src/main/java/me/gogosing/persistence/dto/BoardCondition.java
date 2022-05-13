package me.gogosing.persistence.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Getter
@Setter
@Builder
@ParameterObject
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardCondition {

	@Schema(description = "제목")
	private String title;

	@Schema(description = "내용")
	private String contents;

	@Schema(description = "최종 수정일 범위")
	private FilterLocalDateRange lastModifiedPeriod;
}
