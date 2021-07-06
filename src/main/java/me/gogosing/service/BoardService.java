package me.gogosing.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.persistence.repository.BoardRepository;
import me.gogosing.service.dto.BoardItem;
import me.gogosing.service.dto.BoardSource;
import me.gogosing.service.helper.BoardEntityGenerateComponent;
import me.gogosing.support.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

	private final BoardEntityGenerateComponent boardEntityGenerateComponent;

	private final BoardRepository boardRepository;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<BoardItem> getPaginatedBoard(
		final BoardCondition condition,
		final Pageable pageable
	) {
		return boardRepository
			.findAllByCondition(condition, pageable)
			.map(BoardItem::of);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public BoardDto getBoard(final Long boardId) {
		final var storedEntity = getBoardEntityById(boardId);

		return BoardDto.withContentsAndAttachments(storedEntity);
	}

	public BoardDto insertBoard(final BoardSource source) {
		final var boardEntity = boardEntityGenerateComponent.buildBoardEntity(source);

		final var boardContentsEntity = boardEntityGenerateComponent
			.buildBoardContentsEntity(boardEntity, source.getBoardContents());

		final var boardAttachmentEntities = boardEntityGenerateComponent
			.buildBoardAttachmentEntities(boardEntity, source.getAttachments());

		boardEntity.setContents(boardContentsEntity);
		boardEntity.setAttachments(boardAttachmentEntities);

		return BoardDto.withContentsAndAttachments(boardRepository.save(boardEntity));
	}

	public BoardDto updateBoard(
		final Long boardId,
		final BoardSource source
	) {
		final var boardEntity = getBoardEntityById(boardId);
		final var boardAttachmentEntities = boardEntityGenerateComponent
			.buildBoardAttachmentEntities(boardEntity, source.getAttachments());

		boardEntity.setBoardTitle(source.getBoardTitle());
		boardEntity.setBoardUseYn(source.getBoardUseYn());
		boardEntity.setUpdateDate(LocalDateTime.now());

		boardEntity.getContents().setBoardContents(source.getBoardContents());
		boardEntity.getAttachments().clear();
		boardEntity.getAttachments().addAll(boardAttachmentEntities);

		return BoardDto.withContentsAndAttachments(boardRepository.save(boardEntity));
	}

	public BoardDto deleteBoard(final Long boardId) {
		final var boardEntity = getBoardEntityById(boardId);

		boardRepository.delete(boardEntity);

		return BoardDto.withContentsAndAttachments(boardEntity);
	}

	private BoardEntity getBoardEntityById(final Long boardId) {
		return boardRepository
			.findByBoardId(boardId)
			.orElseThrow(() -> new EntityNotFoundException(String.format("[%d]는 존재하지 않거나, 삭제된 상태입니다.", boardId)));
	}
}
