package me.gogosing.persistence.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.entity.BoardEntity;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardContentsDto {

	/**
	 * 게시물 번호.
	 */
	@EqualsAndHashCode.Include
	private Long boardId;

	/**
	 * 게시물 컨텐츠 내용.
	 */
	private String boardContents;


	@Builder
	@SuppressWarnings("unused")
	public BoardContentsDto(
		Long boardId,
		String boardContents
	) {
		this.boardId = boardId;
		this.boardContents = boardContents;
	}

	public BoardContentsDto(final BoardEntity boardEntity) {
		final var boardContentsEntity = boardEntity.getContents();
		this.boardId = boardContentsEntity.getBoardId();
		this.boardContents = boardContentsEntity.getBoardContents();
	}
}
