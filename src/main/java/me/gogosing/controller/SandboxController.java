package me.gogosing.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxCondition;
import me.gogosing.service.dto.SandboxItem;
import me.gogosing.service.SandboxService;
import me.gogosing.support.dto.ApiResponse;
import me.gogosing.support.dto.ApiResponseGenerator;
import me.gogosing.support.page.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "데이터 관리 샘플 API", tags = "[샘플] SANDBOX")
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/sandbox")
public class SandboxController {

	private final SandboxService sandboxService;

	@ApiOperation("페이징 처리된 목록 조회")
	@GetMapping
	public ApiResponse<PageResponse<SandboxItem>> getPaginatedItem(
		final SandboxCondition condition,
		final @PageableDefault(
			size = 25,
			sort = "name",
			direction = Direction.ASC
		) Pageable pageable
	) {
		final var paginatedResult = sandboxService
			.getPaginatedSandbox(condition, pageable);

		return ApiResponseGenerator.success(PageResponse.convert(paginatedResult));
	}

	@ApiOperation("ID를 이용한 단건 조회")
	@GetMapping("/{id}")
	public ApiResponse<SandboxDto> getSandbox(final @PathVariable @Min(1) Long id) {
		return ApiResponseGenerator.success(sandboxService.getSandbox(id));
	}
}
