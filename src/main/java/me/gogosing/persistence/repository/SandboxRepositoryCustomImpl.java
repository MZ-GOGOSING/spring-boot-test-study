package me.gogosing.persistence.repository;

import static java.util.Collections.emptyList;
import static me.gogosing.support.query.QueryDslHelper.optionalWhen;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

import com.querydsl.jpa.JPQLQuery;
import me.gogosing.persistence.CustomQuerydslRepositorySupport;
import me.gogosing.persistence.dto.QSandboxDto;
import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxFilter;
import me.gogosing.persistence.entity.QSandboxEntity;
import me.gogosing.persistence.entity.SandboxEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@SuppressWarnings("unused")
public class SandboxRepositoryCustomImpl extends CustomQuerydslRepositorySupport implements SandboxRepositoryCustom {

	private static final QSandboxEntity SANDBOX_ENTITY = QSandboxEntity.sandboxEntity;

	public SandboxRepositoryCustomImpl() {
		super(SandboxEntity.class);
	}

	@Override
	public Page<SandboxDto> getPaginatedSandbox(
		final SandboxFilter filter,
		final Pageable pageable
	) {
		final var query = getPaginationDefaultQuery();

		applyPaginationWhereClause(query, filter);

		return executePaginationFetchQuery(query, pageable);
	}

	private JPQLQuery<SandboxDto> getPaginationDefaultQuery() {
		return getQuerydsl()
			.createQuery()
			.select(new QSandboxDto(SANDBOX_ENTITY))
			.from(SANDBOX_ENTITY);
	}

	private void applyPaginationWhereClause(
		final JPQLQuery<SandboxDto> query,
		final SandboxFilter filter
	) {
		optionalWhen(filter.getName()).then(
			it -> query.where(SANDBOX_ENTITY.name.trim().containsIgnoreCase(it))
		);
		optionalWhen(filter.getAge()).then(
			it -> query.where(
				SANDBOX_ENTITY.age.goe(it.getMin()),
				SANDBOX_ENTITY.age.loe(it.getMax())
			)
		);
		optionalWhen(filter.getCategory()).then(
			it -> query.where(SANDBOX_ENTITY.category.eq(it))
		);
	}

	private <T> Page<T> executePaginationFetchQuery(
		final JPQLQuery<T> query,
		final Pageable pageable
	) {
		final var totalCount = query.fetchCount();
		if (totalCount < LONG_ONE) {
			return new PageImpl<>(emptyList(), pageable, totalCount);
		}

		final var content = getQuerydsl()
			.applyPagination(pageable, query)
			.fetch();

		return new PageImpl<>(content, pageable, totalCount);
	}
}
