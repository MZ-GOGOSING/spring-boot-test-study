package me.gogosing.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;
import me.gogosing.persistence.entity.SandboxEntity;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SandboxDto {

	/**
	 * 식별자.
	 */
	@EqualsAndHashCode.Include
	private Long id;

	/**
	 * 이름.
	 */
	private String name;

	/**
	 * 나이.
	 */
	private Integer age;

	/**
	 * 유형.
	 */
	private SandboxCategory category;

	/**
	 * 삭제여부.
	 */
	private Boolean deleted;

	/**
	 * 생성일.
	 */
	private LocalDateTime createDate;

	/**
	 * 최종 수정일.
	 */
	private LocalDateTime updateDate;


	@Builder
	@SuppressWarnings("unused")
	public SandboxDto(
		Long id,
		String name,
		Integer age,
		SandboxCategory category,
		Boolean deleted,
		LocalDateTime createDate,
		LocalDateTime updateDate
	) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.category = category;
		this.deleted = deleted;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	@QueryProjection
	public SandboxDto(final SandboxEntity entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.age = entity.getAge();
		this.category = entity.getCategory();
		this.deleted = entity.isDeleted();
		this.createDate = entity.getCreateDate();
		this.updateDate = entity.getUpdateDate();
	}
}
