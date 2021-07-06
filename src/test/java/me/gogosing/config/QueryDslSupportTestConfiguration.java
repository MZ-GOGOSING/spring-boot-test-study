package me.gogosing.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * DataJpaTest annotation 을 이용한 Slice 테스트 환경에서는 QueryDsl 에 대한 AutoConfiguration 이 이루어 지지 않기 때문에,
 * Custom 하게 QueryDsl 의 JPAQueryFactory 를 생성해 주는 TestConfiguration 클래스를 구현함.
 */
@TestConfiguration
public class QueryDslSupportTestConfiguration {

	@PersistenceContext
	private EntityManager entityManager;

	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new JPAQueryFactory(entityManager);
	}
}
