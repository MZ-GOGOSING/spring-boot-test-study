package me.gogosing.service.dto;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
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
	private String boardTitle;

	@NotNull
	private Boolean boardUseYn;

	@NotBlank
	private String boardContents;

	@Size(max = 3)
	private List<BoardAttachmentSource> attachments = Collections.emptyList();
}
