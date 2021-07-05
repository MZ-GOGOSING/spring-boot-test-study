package me.gogosing.persistence.repository;

import java.util.Optional;
import me.gogosing.persistence.entity.SandboxEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SandboxRepository extends
	JpaRepository<SandboxEntity, Long>,
	SandboxRepositoryCustom {

	Optional<SandboxEntity> findByIdAndDeletedFalse(final Long id);
}
