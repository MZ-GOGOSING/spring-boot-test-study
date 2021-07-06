package me.gogosing.persistence.dto;

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
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardCondition {

	private String title;

	private String contents;

	private FilterLocalDateRange lastModifiedPeriod;
}