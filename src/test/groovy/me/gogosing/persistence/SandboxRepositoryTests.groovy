package me.gogosing.persistence

import me.gogosing.persistence.repository.SandboxRepository
import me.gogosing.support.QueryDslRepositoryTest
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Specification

@QueryDslRepositoryTest
class SandboxRepositoryTests extends Specification {

    @Autowired
    SandboxRepository sandboxRepository

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
