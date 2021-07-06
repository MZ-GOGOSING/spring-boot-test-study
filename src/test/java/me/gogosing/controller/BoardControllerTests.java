package me.gogosing.controller;

import static me.gogosing.support.code.ErrorCode.SUCCESS;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.service.BoardService;
import me.gogosing.service.dto.BoardItem;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BoardController.class)
@Import(HttpEncodingAutoConfiguration.class)
@DisplayName("BoardController Tests")
public class BoardControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BoardService boardService;

	@Test
	@DisplayName("페이징 처리된 목록 조회 테스트")
	public void testGetPaginatedItem() throws Exception {
		// given
		final var condition = BoardCondition.builder()
			.title("첫번째")
			.build();

		final var pageable = PageRequest
			.of(0, 25, Sort.by(Order.asc("boardId")));

		// when
		final var boardItem = BoardItem.builder()
			.boardId(1L)
			.boardTitle("첫번째 테스트 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();
		final var content = Collections.singletonList(boardItem);
		final var expectedResult = PageableExecutionUtils
			.getPage(content, pageable, () -> (long) content.size());

		when(boardService.getPaginatedBoard(condition, pageable))
			.thenReturn(expectedResult);

		// then
		mockMvc.perform(
			get("/v1/board")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON_VALUE)
				.param("page", String.valueOf(pageable.getPageNumber()))
				.param("title", condition.getTitle())
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$").exists())
		.andExpect(jsonPath("$.code").value(SUCCESS.getCode()))
		.andExpect(jsonPath("$.message").value(SUCCESS.getDefaultMessage()))
		.andExpect(jsonPath("$.data").exists())
		.andExpect(jsonPath("$.data.pageSize").value(pageable.getPageSize()))
		.andExpect(jsonPath("$.data.pageNumber").value(1))
		.andExpect(jsonPath("$.data.totalPageNumber").value(1))
		.andExpect(jsonPath("$.data.totalSize").value(1))
		.andExpect(jsonPath("$.data.list").exists())
		.andExpect(jsonPath("$.data.list", hasSize(1)))
		.andExpect(jsonPath("$.data.list[0].boardTitle").value(boardItem.getBoardTitle()));
	}
}
