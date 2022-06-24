package me.gogosing.persistence.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.gogosing.persistence.code.SandboxCategory;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 데이터 Entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "sandbox")
public class SandboxEntity {

	/**
	 * 식별자.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private long id;

	/**
	 * 이름.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * 나이.
	 */
	@Column(name = "age", nullable = false)
	private int age;

	/**
	 * 유형.
	 */
	@Enumerated(value = EnumType.STRING)
	@Column(name = "category", nullable = false)
	private SandboxCategory category;

	/**
	 * 삭제여부.
	 */
	@Column(name = "deleted", nullable = false)
	private boolean deleted;

	/**
	 * 생성일.
	 */
	@CreatedDate
	@Column(name = "create_date", nullable = false, updatable = false)
	private LocalDateTime createDate;

	/**
	 * 최종 수정일.
	 */
	@LastModifiedDate
	@Column(name = "update_date", nullable = false)
	private LocalDateTime updateDate;


	@Builder
	@SuppressWarnings("unused")
	public SandboxEntity(
		long id,
		String name,
		int age,
		SandboxCategory category,
		boolean deleted,
		LocalDateTime createDate,
		LocalDateTime updateDate
	) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.category = category;
		this.deleted = deleted;
		this.createDate = createDate;
		this.updateDate = updateDate;
	}
}
