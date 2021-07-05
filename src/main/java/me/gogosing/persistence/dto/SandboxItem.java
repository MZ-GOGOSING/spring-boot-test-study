package me.gogosing.persistence.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;

/**
 * 데이터 항목.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SandboxItem {

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
	 * 최종 수정일.
	 */
	private LocalDateTime updateDate;


	@Builder
	public SandboxItem(
		Long id,
		String name,
		Integer age,
		SandboxCategory category,
		LocalDateTime updateDate
	) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.category = category;
		this.updateDate = updateDate;
	}

	public static SandboxItem of(final SandboxDto dto) {
		return SandboxItem.builder()
			.id(dto.getId())
			.name(dto.getName())
			.age(dto.getAge())
			.category(dto.getCategory())
			.updateDate(dto.getUpdateDate())
			.build();
	}
}
