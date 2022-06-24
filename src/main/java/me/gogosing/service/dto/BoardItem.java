package me.gogosing.service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.dto.BoardDto;

/**
 * 게시물 항목.
 */
@Schema(description = "게시판 항목 정보 응답 모델")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardItem {

	@Schema(description = "식별자", example = "1", required = true)
	@EqualsAndHashCode.Include
	private Long boardId;

	@Schema(description = "제목", example = "게시물 제목", required = true)
	private String boardTitle;

	@Schema(description = "사용여부", example = "true", required = true)
	private Boolean boardUseYn;

	@Schema(description = "등록일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime createDate;

	@Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime updateDate;

	@Builder
	@SuppressWarnings("unused")
	public BoardItem(
		Long boardId,
		String boardTitle,
		Boolean boardUseYn,
		LocalDateTime createDate,
		LocalDateTime updateDate
	) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardUseYn = boardUseYn;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public static BoardItem of(final BoardDto boardDto) {
		return BoardItem.builder()
			.boardId(boardDto.getBoardId())
			.boardTitle(boardDto.getBoardTitle())
			.boardUseYn(boardDto.getBoardUseYn())
			.createDate(boardDto.getCreateDate())
			.updateDate(boardDto.getUpdateDate())
			.build();
	}
}
