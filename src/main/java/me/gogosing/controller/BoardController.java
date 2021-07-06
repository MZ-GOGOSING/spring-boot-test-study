package me.gogosing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

@Api(value = "게시물 관리 샘플 API", tags = "[샘플] BOARD")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/board")
public class BoardController {

	private final BoardService boardService;

	@ApiOperation("페이징 처리된 목록 조회")
	@GetMapping
	public ApiResponse<PageResponse<BoardItem>> getPaginatedItem(
		final BoardCondition condition,
		final @PageableDefault(
			size = 25,
			sort = "boardId",
			direction = Direction.ASC
		) Pageable pageable
	) {
		final var paginatedResult = boardService
			.getPaginatedBoard(condition, pageable);

		return ApiResponseGenerator.success(PageResponse.convert(paginatedResult));
	}

	@ApiOperation("특정 게시물 조회")
	@GetMapping("/{boardId}")
	public ApiResponse<BoardDto> getSandbox(final @PathVariable @Min(1) Long boardId) {
		return ApiResponseGenerator.success(boardService.getBoard(boardId));
	}

	@ApiOperation("특정 게시물 저장")
	@PostMapping
	public ApiResponse<BoardDto> postBoard(final @RequestBody @Valid BoardSource source) {
		return ApiResponseGenerator.success(boardService.insertBoard(source));
	}

	@ApiOperation("특정 게시물 수정")
	@PutMapping("/{boardId}")
	public ApiResponse<BoardDto> putBoard(
		final @PathVariable @Min(1) Long boardId,
		final @RequestBody @Valid BoardSource source
	) {
		return ApiResponseGenerator.success(boardService.updateBoard(boardId, source));
	}

	@ApiOperation("특정 게시물 삭제")
	@DeleteMapping("/{boardId}")
	public ApiResponse<BoardDto> deleteBoard(final @PathVariable @Min(1) Long boardId) {
		return ApiResponseGenerator.success(boardService.deleteBoard(boardId));
	}
}
