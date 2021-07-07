package me.gogosing.service.helper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import me.gogosing.persistence.entity.BoardAttachmentEntity;
import me.gogosing.persistence.entity.BoardContentsEntity;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.service.dto.BoardAttachmentSource;
import me.gogosing.service.dto.BoardSource;
import org.springframework.stereotype.Component;

@Component
public class BoardRelationshipMappingComponent {

	public BoardEntity buildForInsert(final BoardSource boardSource) {
		final var boardEntity = buildBoardEntity(boardSource);
		final var boardContentsEntity = buildBoardContentsEntity(boardEntity, boardSource.getBoardContents());
		final var boardAttachmentEntities = buildBoardAttachmentEntities(boardEntity, boardSource.getAttachments());

		boardEntity.setContents(boardContentsEntity);
		boardEntity.setAttachments(boardAttachmentEntities);

		return boardEntity;
	}

	public BoardEntity buildForUpdate(final BoardEntity boardEntity, final BoardSource boardSource) {
		final var boardAttachmentEntities = buildBoardAttachmentEntities(boardEntity, boardSource.getAttachments());

		boardEntity.setBoardTitle(boardSource.getBoardTitle());
		boardEntity.setBoardUseYn(boardSource.getBoardUseYn());
		boardEntity.setUpdateDate(LocalDateTime.now());

		boardEntity.getContents().setBoardContents(boardSource.getBoardContents());
		boardEntity.getAttachments().clear();
		boardEntity.getAttachments().addAll(boardAttachmentEntities);

		return boardEntity;
	}

	private BoardEntity buildBoardEntity(final BoardSource boardSource) {
		return BoardEntity.builder()
			.boardTitle(boardSource.getBoardTitle())
			.boardUseYn(boardSource.getBoardUseYn())
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();
	}

	private BoardContentsEntity buildBoardContentsEntity(
		final BoardEntity boardEntity,
		final String contents
	) {
		return BoardContentsEntity.builder()
			.board(boardEntity)
			.boardContents(contents)
			.build();
	}

	private Set<BoardAttachmentEntity> buildBoardAttachmentEntities(
		final BoardEntity boardEntity,
		final List<BoardAttachmentSource> attachments
	) {
		return attachments
			.stream()
			.map(attachment -> BoardAttachmentEntity.builder()
				.board(boardEntity)
				.boardAttachmentPath(attachment.getBoardAttachmentPath())
				.boardAttachmentName(attachment.getBoardAttachmentName())
				.build())
			.collect(Collectors.toSet());
	}
}
