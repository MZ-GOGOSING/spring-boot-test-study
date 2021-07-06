package me.gogosing.persistence.repository;

import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SandboxRepositoryCustom {

	Page<SandboxDto> findAllByCondition(
		final SandboxCondition condition,
		final Pageable pageable
	);
}
