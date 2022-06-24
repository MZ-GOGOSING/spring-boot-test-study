package me.gogosing.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;
import me.gogosing.persistence.entity.SandboxEntity;

@Schema(description = "특정 게시물 정보 응답 모델")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class SandboxDto {

	@Schema(description = "식별자", example = "1", required = true)
	@EqualsAndHashCode.Include
	private Long id;

	@Schema(description = "이름", example = "이름", required = true)
	private String name;

	@Schema(description = "나이", example = "19", required = true)
	private Integer age;

	@Schema(description = "유형", example = "NORMAL", required = true)
	private SandboxCategory category;

	@Schema(description = "삭제여부", example = "false", required = true)
	private Boolean deleted;

	@Schema(description = "생성일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime createDate;

	@Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
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
