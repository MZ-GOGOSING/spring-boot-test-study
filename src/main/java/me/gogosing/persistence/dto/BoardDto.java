package me.gogosing.persistence.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.entity.BoardEntity;

@Schema(description = "특정 게시물 정보 응답 모델")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardDto {

	@Schema(description = "식별자", example = "1", required = true)
	@EqualsAndHashCode.Include
	private Long boardId;

	@Schema(description = "제목", example = "게시물 제목", required = true)
	private String boardTitle;

	@Schema(description = "사용여부", example = "true", required = true)
	private Boolean boardUseYn;

	@Schema(description = "생성일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime createDate;

	@Schema(description = "수정일", example = "yyyy-MM-dd", required = true)
	private LocalDateTime updateDate;

	@Schema(description = "내용", example = "게시물 내용", required = true)
	private BoardContentsDto contents;

	@Schema(description = "첨부파일 목록")
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
