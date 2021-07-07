package me.gogosing.persistence.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;
import me.gogosing.support.dto.filter.FilterNumericRange;

/**
 * 데이터 필터.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "목록 조회 조건 모델")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SandboxCondition {

	/**
	 * 이름.
	 */
	@ApiModelProperty("이름")
	private String name;

	/**
	 * 나이.
	 */
	@ApiModelProperty("나이 범위")
	private FilterNumericRange age;

	/**
	 * 유형.
	 */
	@NotNull
	@ApiModelProperty("카테고리")
	private SandboxCategory category;
}
