package me.gogosing.persistence.repository;

import static me.gogosing.support.query.QueryDslHelper.optionalWhen;

import com.querydsl.jpa.JPQLQuery;
import me.gogosing.persistence.CustomQuerydslRepositorySupport;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.persistence.dto.QBoardDto;
import me.gogosing.persistence.entity.BoardEntity;
import me.gogosing.persistence.entity.QBoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unused")
public class BoardRepositoryCustomImpl extends CustomQuerydslRepositorySupport
	implements BoardRepositoryCustom {

	private static final QBoardEntity BOARD_ENTITY = QBoardEntity.boardEntity;

	public BoardRepositoryCustomImpl() {
		super(BoardEntity.class);
	}

	@Override
	public Page<BoardDto> findAllByCondition(
		final BoardCondition condition,
		final Pageable pageable
	) {
		final var query = getPaginationDefaultQuery();

		applyPaginationWhereClause(query, condition);

		return applyPagination(pageable, query);
	}

	private JPQLQuery<BoardDto> getPaginationDefaultQuery() {
		return getQuerydsl()
			.createQuery()
			.select(new QBoardDto(BOARD_ENTITY))
			.from(BOARD_ENTITY)
			.innerJoin(BOARD_ENTITY.contents);
	}

	private void applyPaginationWhereClause(
		final JPQLQuery<BoardDto> query,
		final BoardCondition condition
	) {
		optionalWhen(condition.getTitle()).then(
			it -> query.where(BOARD_ENTITY.boardTitle.trim().containsIgnoreCase(it))
		);
		optionalWhen(condition.getContents()).then(
			it -> query.where(BOARD_ENTITY.contents.boardContents.trim().containsIgnoreCase(it))
		);
		optionalWhen(condition.getLastModifiedPeriod()).then(
			it -> query.where(
				BOARD_ENTITY.updateDate.goe(it.getStartDateTime()),
				BOARD_ENTITY.updateDate.loe(it.getEndDateTime())
			)
		);
	}
}
