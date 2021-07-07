package me.gogosing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.persistence.repository.BoardRepository;
import me.gogosing.service.dto.BoardItem;
import me.gogosing.service.dto.BoardSource;
import me.gogosing.service.helper.BoardRelationshipMappingComponent;
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

	private final BoardRelationshipMappingComponent boardRelationshipMappingComponent;

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
		final var boardEntity = boardRelationshipMappingComponent.buildForInsert(source);
		final var storedEntity = boardRepository.save(boardEntity);

		return BoardDto.withContentsAndAttachments(storedEntity);
	}

	public BoardDto updateBoard(final Long boardId, final BoardSource source) {
		final var boardEntity = boardRelationshipMappingComponent.buildForUpdate(getBoardEntityById(boardId), source);
		final var storedEntity = boardRepository.save(boardEntity);

		return BoardDto.withContentsAndAttachments(storedEntity);
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
