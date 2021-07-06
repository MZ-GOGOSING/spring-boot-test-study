package me.gogosing.service.dto;

import java.util.Collections;
import java.util.List;
import javax.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 등록 DTO.
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardSource {

	private String boardTitle;

	private Boolean boardUseYn;

	private String boardContents;

	@Size(max = 3)
	private List<BoardAttachmentSource> attachments = Collections.emptyList();
}
