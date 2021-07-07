package me.gogosing.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.gogosing.persistence.dto.SandboxCondition
import me.gogosing.persistence.dto.SandboxDto
import me.gogosing.service.SandboxService
import me.gogosing.service.dto.SandboxItem
import me.gogosing.support.dto.ApiResponseGenerator
import me.gogosing.support.page.PageResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.mock.DetachedMockFactory

import java.time.LocalDateTime

import static me.gogosing.persistence.code.SandboxCategory.NORMAL
import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [SandboxController])
@Import(HttpEncodingAutoConfiguration)
class SandboxControllerTests extends Specification {

    @Autowired
    MockMvc mockMvc

    @Autowired
    ObjectMapper objectMapper

    @Autowired
    SandboxService sandboxService

    @TestConfiguration
    static class StubConfig {
        DetachedMockFactory detachedMockFactory = new DetachedMockFactory()

        @Bean
        SandboxService sandboxService() {
            return detachedMockFactory.Stub(SandboxService)
        }
    }

    def "페이징 처리된 목록 조회 테스트"() {
        given:
        def condition = SandboxCondition.builder()
            .name("진범")
            .build()

        def pageable = PageRequest
            .of(0, 25, Sort.by(Sort.Order.asc("name")))

        when:
        def sandBoxItem = SandboxItem.builder()
            .id(1L)
            .name("정진범")
            .age(20)
            .category(NORMAL)
            .updateDate(LocalDateTime.now())
            .build()

        def content = Collections.singletonList(sandBoxItem);
        def expectedResult = PageableExecutionUtils
            .getPage(content, pageable, { -> (long) content.size() })

        sandboxService.getPaginatedSandbox(condition, pageable) >> PageableExecutionUtils.getPage(
                content,
                pageable,
                { -> (long) content.size() }
        )

        then:
        def expectedJsonString = objectMapper.writeValueAsString(
            ApiResponseGenerator.success(PageResponse.convert(expectedResult))
        )

        def actualJsonString = mockMvc.perform(
            get("/v1/sandbox")
                .accept(APPLICATION_JSON_VALUE)
                .param("page", String.valueOf(pageable.getPageNumber()))
                .param("name", condition.getName())
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString()

        actualJsonString == expectedJsonString
    }

    def "특정 게시물 조회 테스트"() {
        given:
        def id = LONG_ONE

        when:
        def expectedResult = SandboxDto.builder()
            .id(id)
            .name("정진범")
            .age(20)
            .category(NORMAL)
            .deleted(false)
            .createDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build()

        sandboxService.getSandbox(id) >> expectedResult

        then:
        def expectedJsonString = objectMapper
            .writeValueAsString(ApiResponseGenerator.success(expectedResult))

        def actualJsonString = mockMvc.perform(
            get("/v1/sandbox/{id}", id)
                .accept(APPLICATION_JSON_VALUE)
        )
        .andDo(print())
        .andExpect(status().isOk())
        .andReturn()
        .getResponse()
        .getContentAsString()

        actualJsonString == expectedJsonString
    }
}
