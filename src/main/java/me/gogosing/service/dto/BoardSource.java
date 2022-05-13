package me.gogosing.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardSource {

	@NotBlank
	@Schema(description = "제목")
	private String boardTitle;

	@NotNull
	@Schema(description = "사용여부")
	private Boolean boardUseYn;

	@NotBlank
	@Schema(description = "내용")
	private String boardContents;

	@Size(max = 3)
	@Builder.Default
	@Schema(description = "첨부파일 목록")
	private List<BoardAttachmentSource> attachments = Collections.emptyList();
}
