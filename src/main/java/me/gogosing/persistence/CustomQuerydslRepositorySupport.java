package me.gogosing.persistence;

import java.util.Objects;
import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
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
}
