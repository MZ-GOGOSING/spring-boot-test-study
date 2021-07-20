package me.gogosing.persistence;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertWith;

import java.time.LocalDateTime;
import java.util.Set;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.persistence.repository.BoardRepository;
import me.gogosing.support.QueryDslRepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@QueryDslRepositoryTest
@DisplayName("BoardRepository Tests")
public class BoardRepositoryTests {

	@Autowired
	private BoardRepository boardRepository;

	@Test
	@DisplayName("BoardEntity 프로퍼티 존재 여부 확인")
	public void testHasProperties() {
		final var boardEntity = new BoardEntity();
		assertThat(boardEntity)
			.hasFieldOrProperty("boardId")
			.hasFieldOrProperty("boardTitle")
			.hasFieldOrProperty("boardUseYn")
			.hasFieldOrProperty("createDate")
			.hasFieldOrProperty("updateDate")
			.hasFieldOrProperty("contents")
			.hasFieldOrProperty("attachments");
	}

	@Test
	@DisplayName("BoardEntity 저장 동작 여부 확인")
	public void testSave() {
		// given
		final var boardEntity = BoardEntity.builder()
			.boardTitle("테스트 게시물 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();

		// when
		final var storedEntity = boardRepository.save(boardEntity);

		// then
		assertWith(storedEntity, entity -> {
			assertThat(entity).isNotNull();
			assertThat(entity.getBoardId()).isPositive();
		});
	}

	@Test
	@DisplayName("BoardEntity 단건 조회에 따른 연관 엔티티들의 fetch join 여부 확인")
	public void testFindByBoardId() {
		// when
		final var storedEntity = boardRepository.findByBoardId(LONG_ONE);

		// then
		assertThat(storedEntity).isPresent();
		assertWith(storedEntity.get(), entity -> {
			assertThat(entity.getBoardId()).isPositive();
			assertThat(entity.getContents()).isNotNull();
			assertThat(entity.getAttachments()).isNotEmpty();
		});
	}

	@Test
	@DisplayName("BoardEntity 다건 조회에 따른 연관 엔티티들의 fetch join 여부 확인")
	public void testFindAllByBoardIdInd() {
		// given
		final var boardIds = Set.of(1L, 2L, 3L, 4L, 5L);

		// when
		final var storedEntities = boardRepository.findAllByBoardIdIn(boardIds);

		// then
		assertThat(storedEntities).isNotEmpty();
		assertThat(storedEntities).hasSize(boardIds.size());
		assertThat(storedEntities)
			.extracting("contents")
			.isNotNull();
	}

	@Test
	@DisplayName("BoardEntity 페이징 정상 동작 여부 확인")
	public void testFindAllByCondition() {
		// given
		final var condition = BoardCondition.builder()
			.title("첫번째")
			.build();

		final var pageable = PageRequest
			.of(0, 10, Sort.by(Order.asc("boardId")));

		// when
		final var paginatedResult = boardRepository.findAllByCondition(condition, pageable);

		// then
		assertThat(paginatedResult).isNotEmpty();
		assertWith(paginatedResult, result -> {
			assertThat(result.getTotalElements()).isNotZero();
			assertThat(result.getTotalElements()).isEqualTo(LONG_ONE);
			assertThat(result.getContent())
				.extracting("boardTitle")
				.contains("첫번째 게시물 제목");
		});
	}
}
