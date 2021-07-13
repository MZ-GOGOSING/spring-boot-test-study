package me.gogosing.service.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 등록 DTO.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "게시물 등록/수정 모델")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardSource {

	@NotBlank
	@ApiModelProperty("제목")
	private String boardTitle;

	@NotNull
	@ApiModelProperty("사용여부")
	private Boolean boardUseYn;

	@NotBlank
	@ApiModelProperty("내용")
	private String boardContents;

	@Size(max = 3)
	@Builder.Default
	@ApiModelProperty("첨부파일 목록")
	private List<BoardAttachmentSource> attachments = Collections.emptyList();
}
