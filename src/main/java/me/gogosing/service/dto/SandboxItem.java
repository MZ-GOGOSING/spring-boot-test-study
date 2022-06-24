package me.gogosing.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;
import me.gogosing.persistence.dto.SandboxDto;

/**
 * 데이터 항목.
 */
@Schema(description = "게시판 항목 정보 응답 모델")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SandboxItem {

	@Schema(description = "식별자", example = "1", required = true)
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(description = "이름", example = "이름", required = true)
	private String name;

	@Schema(description = "나이", example = "19", required = true)
	private Integer age;

	@Schema(description = "유형", example = "NORMAL", required = true)
	private SandboxCategory category;

	@Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime updateDate;


	@Builder
	@SuppressWarnings("unused")
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
