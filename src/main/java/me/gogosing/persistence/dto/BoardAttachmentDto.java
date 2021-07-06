package me.gogosing.persistence.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.entity.BoardAttachmentEntity;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardAttachmentDto {

	/**
	 * 게시물 첨부파일 번호.
	 */
	@EqualsAndHashCode.Include
	private Long boardAttachmentId;

	/**
	 * 게시물 일련번호.
	 */
	@NotNull
	@EqualsAndHashCode.Include
	private Long boardId;

	/**
	 * 첨부파일경로.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	private String boardAttachmentPath;

	/**
	 * 첨부파일명.
	 */
	@NotBlank
	@EqualsAndHashCode.Include
	private String boardAttachmentName;


	@Builder
	@SuppressWarnings("unused")
	public BoardAttachmentDto(
		Long boardAttachmentId,
		Long boardId,
		String boardAttachmentPath,
		String boardAttachmentName
	) {
		this.boardAttachmentId = boardAttachmentId;
		this.boardId = boardId;
		this.boardAttachmentPath = boardAttachmentPath;
		this.boardAttachmentName = boardAttachmentName;
	}

	public BoardAttachmentDto(final BoardAttachmentEntity entity) {
		this.boardAttachmentId = entity.getBoardAttachmentId();
		this.boardId = entity.getBoard().getBoardId();
		this.boardAttachmentPath = entity.getBoardAttachmentPath();
		this.boardAttachmentName = entity.getBoardAttachmentName();
	}
}
