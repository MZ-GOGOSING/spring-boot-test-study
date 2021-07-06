package me.gogosing.persistence;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;

import com.querydsl.jpa.JPQLQuery;
import java.util.Objects;
import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class CustomQuerydslRepositorySupport extends QuerydslRepositorySupport implements
	InitializingBean {

	public CustomQuerydslRepositorySupport(Class<?> domainClass) {
		super(domainClass);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(getQuerydsl(), "The QueryDsl must not be null.");
	}

	@Nonnull
	@Override
	public Querydsl getQuerydsl() {
		return Objects.requireNonNull(super.getQuerydsl());
	}

	@Nonnull
	@Override
	public EntityManager getEntityManager() {
		return Objects.requireNonNull(super.getEntityManager());
	}

	@Override
	@PersistenceContext
	public void setEntityManager(
		@Nonnull EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

	@Nonnull
	public <T> Page<T> applyPagination(final Pageable pageable, final JPQLQuery<T> query) {
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
