package me.gogosing.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "게시물 첨부파일 모델")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardAttachmentSource {

	/**
	 * 첨부파일경로.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	@ApiModelProperty("첨부파일 경로")
	private String boardAttachmentPath;

	/**
	 * 첨부파일명.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	@ApiModelProperty("첨부파일 이름")
	private String boardAttachmentName;
}
