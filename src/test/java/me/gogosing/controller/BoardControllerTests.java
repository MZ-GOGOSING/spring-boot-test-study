package me.gogosing.controller;

import static org.apache.commons.lang3.math.NumberUtils.LONG_ONE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import me.gogosing.persistence.dto.BoardAttachmentDto;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardContentsDto;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.service.BoardService;
import me.gogosing.service.dto.BoardAttachmentSource;
import me.gogosing.service.dto.BoardItem;
import me.gogosing.service.dto.BoardSource;
import me.gogosing.support.dto.ApiResponseGenerator;
import me.gogosing.support.page.PageResponse;
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

	@Autowired
	private ObjectMapper objectMapper;

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
		final var expectedJsonString = objectMapper.writeValueAsString(
			ApiResponseGenerator.success(PageResponse.convert(expectedResult))
		);

		final var actualJsonString = mockMvc.perform(
			get("/v1/board")
				.accept(APPLICATION_JSON_VALUE)
				.param("page", String.valueOf(pageable.getPageNumber()))
				.param("title", condition.getTitle())
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString();

		assertThat(actualJsonString).isEqualTo(expectedJsonString);
	}

	@Test
	@DisplayName("특정 게시물 조회 테스트")
	public void testGetSandbox() throws Exception {
		// given
		final var boardId = LONG_ONE;

		// when
		final var expectedResult = BoardDto.builder()
			.boardId(boardId)
			.boardTitle("첫번째 테스트 제목")
			.boardUseYn(true)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();

		when(boardService.getBoard(boardId))
			.thenReturn(expectedResult);

		// then
		final var expectedJsonString = objectMapper
			.writeValueAsString(ApiResponseGenerator.success(expectedResult));

		final var actualJsonString = mockMvc.perform(
			get("/v1/board/{boardId}", boardId)
				.accept(APPLICATION_JSON_VALUE)
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString();

		assertThat(actualJsonString).isEqualTo(expectedJsonString);
	}

	@Test
	@DisplayName("특정 게시물 생성 테스트")
	public void testPostSandbox() throws Exception {
		// given
		final var boardAttachmentSources = List.of(
			BoardAttachmentSource.builder()
				.boardAttachmentPath("/foo/bar/")
				.boardAttachmentName("sample.png")
				.build());

		final var boardSource = BoardSource.builder()
			.boardTitle("첫번째 테스트 제목")
			.boardUseYn(true)
			.boardContents("첫번째 테스트 내용")
			.attachments(boardAttachmentSources)
			.build();

		// when
		final var expectedAttachments = List.of(
			BoardAttachmentDto.builder()
				.boardAttachmentId(1L)
				.boardId(1L)
				.boardAttachmentPath("/foo/bar/")
				.boardAttachmentName("sample.png")
				.build());

		final var expectedContents = BoardContentsDto.builder()
			.boardId(LONG_ONE)
			.boardContents("첫번째 테스트 내용")
			.build();

		final var expectedResult = BoardDto.builder()
			.boardId(LONG_ONE)
			.boardTitle("첫번째 테스트 제목")
			.boardUseYn(true)
			.contents(expectedContents)
			.attachments(expectedAttachments)
			.createDate(LocalDateTime.now())
			.updateDate(LocalDateTime.now())
			.build();

		when(boardService.insertBoard(boardSource))
			.thenReturn(expectedResult);

		// then
		final var expectedJsonString = objectMapper
			.writeValueAsString(ApiResponseGenerator.success(expectedResult));

		final var actualJsonString = mockMvc.perform(
			post("/v1/board")
				.contentType(APPLICATION_JSON)
				.accept(APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(boardSource))
		)
		.andDo(print())
		.andExpect(status().isOk())
		.andReturn()
		.getResponse()
		.getContentAsString();

		assertThat(actualJsonString).isEqualTo(expectedJsonString);
	}
}
