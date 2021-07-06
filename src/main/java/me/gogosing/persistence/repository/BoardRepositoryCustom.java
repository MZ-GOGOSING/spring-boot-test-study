package me.gogosing.persistence.repository;

import me.gogosing.persistence.dto.BoardCondition;
import me.gogosing.persistence.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardRepositoryCustom {

	Page<BoardDto> findAllByCondition(
		final BoardCondition condition,
		final Pageable pageable
	);
}
