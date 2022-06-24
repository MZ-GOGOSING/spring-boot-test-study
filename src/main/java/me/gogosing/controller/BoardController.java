package me.gogosing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import me.gogosing.service.BoardService;
import me.gogosing.service.dto.BoardItem;
import me.gogosing.service.dto.BoardSource;
import me.gogosing.support.dto.ApiResponse;
import me.gogosing.support.dto.ApiResponseGenerator;
import me.gogosing.support.page.PageResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BOARD", description = "게시물 관리 샘플 API")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {

	private final BoardService boardService;

	@Operation(summary = "페이징 처리된 게시물 목록 조회", description = "페이징 처리된 게시물 목록을 조회할 수 있습니다.")
	@GetMapping
	public ApiResponse<PageResponse<BoardItem>> getPaginatedItem(
		final @Valid BoardCondition condition,
		final @ParameterObject @PageableDefault(
			size = 25,
			sort = "boardId",
			direction = Direction.ASC
		) Pageable pageable
	) {
		final var paginatedResult = boardService
			.getPaginatedBoard(condition, pageable);

		return ApiResponseGenerator.success(PageResponse.convert(paginatedResult));
	}

	@Operation(summary = "특정 게시물 조회", description = "특정 게시물을 조회할 수 있습니다.")
	@GetMapping("/{boardId}")
	public ApiResponse<BoardDto> getSandbox(
		@Parameter(description = "게시물 ID", required = true)
		final @PathVariable @Min(1) Long boardId
	) {
		return ApiResponseGenerator.success(boardService.getBoard(boardId));
	}

	@Operation(summary = "게시물 생성", description = "특정 게시물을 생성할 수 있습니다.")
	@PostMapping
	public ApiResponse<BoardDto> postBoard(final @RequestBody @Valid BoardSource source) {
		return ApiResponseGenerator.success(boardService.insertBoard(source));
	}

	@Operation(summary = "특정 게시물 수정", description = "특정 게시물을 수정할 수 있습니다.")
	@PutMapping("/{boardId}")
	public ApiResponse<BoardDto> putBoard(
		@Parameter(description = "게시물 ID", required = true)
		final @PathVariable @Min(1) Long boardId,
		final @RequestBody @Valid BoardSource source
	) {
		return ApiResponseGenerator.success(boardService.updateBoard(boardId, source));
	}

	@Operation(summary = "특정 게시물 삭제")
	@DeleteMapping("/{boardId}")
	public ApiResponse<BoardDto> deleteBoard(
		@Parameter(description = "게시물 ID", required = true)
		final @PathVariable @Min(1) Long boardId
	) {
		return ApiResponseGenerator.success(boardService.deleteBoard(boardId));
	}
}
