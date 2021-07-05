package me.gogosing.persistence.repository;

import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SandboxRepositoryCustom {

	Page<SandboxDto> getPaginatedSandbox(
		final SandboxFilter filter,
		final Pageable pageable
	);
}
