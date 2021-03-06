package me.gogosing.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.SandboxCondition;
import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.service.SandboxService;
import me.gogosing.service.dto.SandboxItem;
import me.gogosing.support.dto.ApiResponse;
import me.gogosing.support.dto.ApiResponseGenerator;
import me.gogosing.support.page.PageResponse;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "SANDBOX", description = "샘플 관리 API")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sandbox")
public class SandboxController {

	private final SandboxService sandboxService;

	@Operation(summary = "페이징 처리된 게시물 목록 조회", description = "페이징 처리된 게시물 목록을 조회할 수 있습니다.")
	@GetMapping
	public ApiResponse<PageResponse<SandboxItem>> getPaginatedItem(
		final @Valid SandboxCondition condition,
		final @ParameterObject @PageableDefault(
			size = 25,
			sort = "name",
			direction = Direction.ASC
		) Pageable pageable
	) {
		final var paginatedResult = sandboxService
			.getPaginatedSandbox(condition, pageable);

		return ApiResponseGenerator.success(PageResponse.convert(paginatedResult));
	}

	@Operation(summary = "특정 게시물 조회", description = "특정 게시물을 조회할 수 있습니다.")
	@GetMapping("/{id}")
	public ApiResponse<SandboxDto> getSandbox(
		@Parameter(description = "게시물 ID", required = true)
		final @PathVariable @Min(1) Long id
	) {
		return ApiResponseGenerator.success(sandboxService.getSandbox(id));
	}
}
