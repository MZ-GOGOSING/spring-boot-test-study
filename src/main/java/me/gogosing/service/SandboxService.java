package me.gogosing.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.gogosing.persistence.dto.SandboxDto;
import me.gogosing.persistence.dto.SandboxCondition;
import me.gogosing.service.dto.SandboxItem;
import me.gogosing.persistence.repository.SandboxRepository;
import me.gogosing.support.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class SandboxService {

	private final SandboxRepository sandboxRepository;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Page<SandboxItem> getPaginatedSandbox(
		final SandboxCondition condition,
		final Pageable pageable
	) {
		return sandboxRepository
			.findAllByCondition(condition, pageable)
			.map(SandboxItem::of);
	}

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public SandboxDto getSandbox(final Long id) {
		final var storedEntity = sandboxRepository
			.findByIdAndDeletedFalse(id)
			.orElseThrow(() -> new EntityNotFoundException(String.format("[%d]는 존재하지 않거나, 삭제된 상태입니다.", id)));

		return new SandboxDto(storedEntity);
	}
}
