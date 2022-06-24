package me.gogosing.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 첨부파일 등록 DTO.
 */
@Schema(description = "게시물 첨부파일 등록 모델")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardAttachmentSource {

	/**
	 * 첨부파일경로.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	@Schema(description = "경로", example = "https://host.com/foo/bar/", required = true)
	private String boardAttachmentPath;

	/**
	 * 첨부파일명.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	@Schema(description = "파일명", example = "image.png", required = true)
	private String boardAttachmentName;
}
