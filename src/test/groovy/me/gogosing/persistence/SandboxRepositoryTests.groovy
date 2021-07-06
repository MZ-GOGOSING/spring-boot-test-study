package me.gogosing.persistence

import me.gogosing.QueryDslSupportTestConfiguration
import me.gogosing.persistence.repository.SandboxRepository
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

@DataJpaTest
@AutoConfigureTestDatabase
@Import(QueryDslSupportTestConfiguration.class)
@DisplayName("SandboxRepository Tests")
class SandboxRepositoryTests extends Specification {

    @Autowired
    private SandboxRepository sandboxRepository;

    def "ID를 통한 단건 조회 테스트"() {
        given:
        def id = 1L

        when:
        def actualResult = sandboxRepository.findByIdAndDeletedFalse(id)

        then:
        actualResult != null
        actualResult.get().getId() == id
    }
}
