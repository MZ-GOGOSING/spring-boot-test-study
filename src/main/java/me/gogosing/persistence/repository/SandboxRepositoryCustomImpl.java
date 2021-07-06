package me.gogosing.persistence.repository;

import static me.gogosing.support.query.QueryDslHelper.optionalWhen;

import com.querydsl.jpa.JPQLQuery;
import me.gogosing.persistence.CustomQuerydslRepositorySupport;
import me.gogosing.persistence.dto.QSandboxDto;
import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxCondition;
import me.gogosing.persistence.entity.QSandboxEntity;
import me.gogosing.persistence.entity.SandboxEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unused")
public class SandboxRepositoryCustomImpl extends CustomQuerydslRepositorySupport implements SandboxRepositoryCustom {

	private static final QSandboxEntity SANDBOX_ENTITY = QSandboxEntity.sandboxEntity;

	public SandboxRepositoryCustomImpl() {
		super(SandboxEntity.class);
	}

	@Override
	public Page<SandboxDto> findAllByCondition(
		final SandboxCondition condition,
		final Pageable pageable
	) {
		final var query = getPaginationDefaultQuery();

		applyPaginationWhereClause(query, condition);

		return applyPagination(pageable, query);
	}

	private JPQLQuery<SandboxDto> getPaginationDefaultQuery() {
		return getQuerydsl()
			.createQuery()
			.select(new QSandboxDto(SANDBOX_ENTITY))
			.from(SANDBOX_ENTITY);
	}

	private void applyPaginationWhereClause(
		final JPQLQuery<SandboxDto> query,
		final SandboxCondition condition
	) {
		optionalWhen(condition.getName()).then(
			it -> query.where(SANDBOX_ENTITY.name.trim().containsIgnoreCase(it))
		);
		optionalWhen(condition.getAge()).then(
			it -> query.where(
				SANDBOX_ENTITY.age.goe(it.getMin()),
				SANDBOX_ENTITY.age.loe(it.getMax())
			)
		);
		optionalWhen(condition.getCategory()).then(
			it -> query.where(SANDBOX_ENTITY.category.eq(it))
		);
	}
}
