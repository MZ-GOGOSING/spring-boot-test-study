package me.gogosing.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
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
public class BoardDto {

	/**
	 * 일련번호.
	 */
	@EqualsAndHashCode.Include
	private Long boardId;

	/**
	 * 제목.
	 */
	private String boardTitle;

	/**
	 * 사용여부.
	 */
	private Boolean boardUseYn;

	/**
	 * 등록일시.
	 */
	private LocalDateTime createDate;

	/**
	 * 수정일시.
	 */
	private LocalDateTime updateDate;

	/**
	 * 게시물 컨텐츠.
	 */
	private BoardContentsDto contents;

	/**
	 * 게시물 첨부파일 목록.
	 */
	private List<BoardAttachmentDto> attachments;


	@Builder
	@SuppressWarnings("unused")
	public BoardDto(
		Long boardId,
		String boardTitle,
		Boolean boardUseYn,
		LocalDateTime createDate,
		LocalDateTime updateDate,
		BoardContentsDto contents,
		List<BoardAttachmentDto> attachments
	) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardUseYn = boardUseYn;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.contents = contents;
		this.attachments = attachments;
	}

	@QueryProjection
	public BoardDto(final BoardEntity boardEntity) {
		this.boardId = boardEntity.getBoardId();
		this.boardTitle = boardEntity.getBoardTitle();
		this.boardUseYn = boardEntity.isBoardUseYn();
		this.createDate = boardEntity.getCreateDate();
		this.updateDate = boardEntity.getUpdateDate();
	}

	public static BoardDto withContents(final BoardEntity boardEntity) {
		final var boardDto = new BoardDto(boardEntity);
		final var boardContentsDto = new BoardContentsDto(boardEntity);

		boardDto.setContents(boardContentsDto);

		return boardDto;
	}

	public static BoardDto withContentsAndAttachments(final BoardEntity boardEntity) {
		final var boardDto = withContents(boardEntity);
		final var boardAttachmentDtoList = collectAttachmentDtoList(boardEntity);

		boardDto.setAttachments(boardAttachmentDtoList);

		return boardDto;
	}

	private static List<BoardAttachmentDto> collectAttachmentDtoList(final BoardEntity boardEntity) {
		return boardEntity.getAttachments()
			.stream()
			.map(BoardAttachmentDto::new)
			.distinct()
			.collect(Collectors.toList());
	}
}
