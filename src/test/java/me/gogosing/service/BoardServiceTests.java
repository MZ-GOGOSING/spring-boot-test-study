package me.gogosing.service;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.persistence.entity.BoardContentsEntity;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.persistence.repository.BoardRepository;
import me.gogosing.service.dto.BoardItem;
import me.gogosing.service.dto.BoardSource;
import me.gogosing.service.helper.BoardEntityRelationshipMappingComponent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("BoardService Tests")
public class BoardServiceTests {

	@MockBean
	private BoardRepository boardRepository;

	private BoardService boardService;

	@BeforeEach
	public void init() {
		boardService = new BoardService(new BoardEntityRelationshipMappingComponent(), boardRepository);
	}

	@Test
	@DisplayName("페이징 처리된 목록 조회 테스트")
	public void testGetPaginatedBoard() {
		// given
		final var condition = BoardCondition.builder()
			.title("첫번째")
			.build();

		final var pageable = PageRequest
			.of(0, 25, Sort.by(Order.asc("boardId")));

		final var boardDto = BoardDto.builder()
			.boardId(1L)
			.boardTitle("첫번째 테스트 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();

		final var content = Collections.singletonList(boardDto);
		final var expectedResult = PageableExecutionUtils
			.getPage(content, pageable, content::size);

		when(boardRepository.findAllByCondition(condition, pageable))
			.thenReturn(expectedResult);

		// when
		final var actualResult = boardService
			.getPaginatedBoard(condition, pageable);

		// then
		assertThat(actualResult).isNotEmpty();
		assertWith(actualResult, result -> {
			assertThat(result.getTotalElements()).isNotZero();
			assertThat(result.getTotalElements()).isEqualTo(LONG_ONE);
			assertThat(result.getContent())
				.hasOnlyElementsOfType(BoardItem.class)
				.extracting("boardTitle")
				.contains("첫번째 테스트 제목");
		});
	}

	@Test
	@DisplayName("특정 게시물 조회 테스트")
	public void testGetBoard() {
		// given
		final var boardId = LONG_ONE;

		final var expectedBoardContentsEntity = BoardContentsEntity.builder()
			.boardContents("테스트 게시물 내용")
			.build();

		final var expectedBoardEntity = BoardEntity.builder()
			.boardId(boardId)
			.boardTitle("테스트 게시물 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.contents(expectedBoardContentsEntity)
			.build();

		expectedBoardEntity.setContents(expectedBoardContentsEntity);

		final var expectedResult = Optional.of(expectedBoardEntity);

		when(boardRepository.findByBoardId(boardId))
			.thenReturn(expectedResult);

		// when
		final var actualResult = boardService.getBoard(boardId);

		// then
		assertThat(actualResult)
			.isNotNull()
			.isInstanceOf(BoardDto.class)
			.extracting("boardId")
			.isEqualTo(boardId);
	}

	@Test
	@DisplayName("특정 게시물 생성 테스트")
	public void testInsertBoard() {
		// given
		final var boardSource = BoardSource.builder()
			.boardTitle("테스트 게시물 제목")
			.boardUseYn(true)
			.boardContents("테스트 게시물 내용")
			.attachments(emptyList())
			.build();

		final var boardContentsEntity = BoardContentsEntity.builder()
			.boardContents(boardSource.getBoardContents())
			.build();

		final var boardEntity = BoardEntity.builder()
			.boardId(LONG_ONE)
			.boardTitle(boardSource.getBoardTitle())
			.boardUseYn(boardSource.getBoardUseYn())
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.contents(boardContentsEntity)
			.build();

		boardEntity.setContents(boardContentsEntity);
		boardEntity.setAttachments(emptySet());

		when(boardRepository.save(any())).thenReturn(boardEntity);

		final var expectedResult = BoardDto
			.withContentsAndAttachments(boardEntity);

		// when
		final var actualResult = boardService.insertBoard(boardSource);

		// then
		assertThat(actualResult)
			.isNotNull()
			.isInstanceOf(BoardDto.class)
			.extracting("boardId")
			.isEqualTo(expectedResult.getBoardId());
	}

	@Test
	@DisplayName("특정 게시물 수정 테스트")
	public void testUpdateBoard() {
		// given
		final var boardId = LONG_ONE;
		final var boardSource = BoardSource.builder()
			.boardTitle("테스트 게시물 제목")
			.boardUseYn(true)
			.boardContents("테스트 게시물 내용")
			.attachments(emptyList())
			.build();

		final var boardContentsEntity = BoardContentsEntity.builder()
			.boardContents(boardSource.getBoardContents())
			.build();

		final var boardEntity = BoardEntity.builder()
			.boardId(LONG_ONE)
			.boardTitle(boardSource.getBoardTitle())
			.boardUseYn(boardSource.getBoardUseYn())
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.contents(boardContentsEntity)
			.build();

		boardEntity.setContents(boardContentsEntity);
		boardEntity.setAttachments(emptySet());

		when(boardRepository.findByBoardId(boardId)).thenReturn(Optional.of(boardEntity));

		when(boardRepository.save(any())).thenReturn(boardEntity);

		final var expectedResult = BoardDto
			.withContentsAndAttachments(boardEntity);

		// when
		final var actualResult = boardService.updateBoard(boardId, boardSource);

		// then
		assertThat(actualResult)
			.isNotNull()
			.isInstanceOf(BoardDto.class)
			.extracting("boardId")
			.isEqualTo(expectedResult.getBoardId());
	}

	@Test
	@DisplayName("특정 게시물 삭제 테스트")
	public void testDeleteBoard() {
		// given
		final var boardId = LONG_ONE;

		final var boardContentsEntity = BoardContentsEntity.builder()
			.boardContents("테스트 게시물 내용")
			.build();

		final var boardEntity = BoardEntity.builder()
			.boardId(boardId)
			.boardTitle("테스트 게시물 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.contents(boardContentsEntity)
			.build();

		boardEntity.setContents(boardContentsEntity);
		boardEntity.setAttachments(emptySet());

		final var storedEntity = Optional.of(boardEntity);

		when(boardRepository.findByBoardId(boardId))
			.thenReturn(storedEntity);

		doNothing()
			.when(boardRepository).delete(storedEntity.get());

		// when
		final var actualResult = boardService.deleteBoard(boardId);

		// then
		assertThat(actualResult)
			.isNotNull()
			.isInstanceOf(BoardDto.class)
			.extracting("boardId")
			.isEqualTo(storedEntity.get().getBoardId());

		verify(boardRepository, times(1))
			.delete(storedEntity.get());
	}
}
