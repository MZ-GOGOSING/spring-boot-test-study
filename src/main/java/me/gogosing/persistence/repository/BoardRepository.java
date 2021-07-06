package me.gogosing.persistence.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import me.gogosing.persistence.entity.BoardEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends
	JpaRepository<BoardEntity, Long>,
	BoardRepositoryCustom {

	@EntityGraph(attributePaths = {"contents", "attachments"}, type = EntityGraphType.FETCH)
	Optional<BoardEntity> findByBoardId(final Long boardId);

	@EntityGraph(attributePaths = {"contents"}, type = EntityGraphType.FETCH)
	List<BoardEntity> findAllByBoardIdIn(final Collection<Long> boardIds);
}
