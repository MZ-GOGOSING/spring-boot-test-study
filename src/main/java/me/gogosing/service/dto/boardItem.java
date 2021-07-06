package me.gogosing.service.dto;

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
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class boardItem {

	@EqualsAndHashCode.Include
	private Long boardId;

	private String boardTitle;

	private LocalDateTime createDate;

	private LocalDateTime updateDate;

	@Builder
	@SuppressWarnings("unused")
	public boardItem(
		Long boardId,
		String boardTitle,
		LocalDateTime createDate,
		LocalDateTime updateDate
	) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}

	public static boardItem of(final BoardDto boardDto) {
		return boardItem.builder()
			.boardId(boardDto.getBoardId())
			.boardTitle(boardDto.getBoardTitle())
			.createDate(boardDto.getCreateDate())
			.updateDate(boardDto.getUpdateDate())
			.build();
	}
}