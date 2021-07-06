package me.gogosing.persistence.entity;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 게시물 Entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "board")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardEntity extends BaseEntity {

	/**
	 * 일련번호.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id", nullable = false)
	private long boardId;

	/**
	 * 제목.
	 */
	@Column(name = "board_title", nullable = false)
	private String boardTitle;

	/**
	 * 사용여부.
	 */
	@Column(name = "board_use_yn", nullable = false)
	private boolean boardUseYn;

	/**
	 * 등록일시.
	 */
	@CreatedDate
	@Column(name = "create_date", nullable = false, updatable = false)
	private LocalDateTime createDate;

	/**
	 * 수정일시.
	 */
	@LastModifiedDate
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	/**
	 * 게시물 컨텐츠.
	 */
	@OneToOne(
		mappedBy = "board",
		fetch = FetchType.LAZY,
		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}
	)
	private BoardContentsEntity contents;

	/**
	 * 게시물 첨부파일 목록.
	 */
	@OneToMany(
		mappedBy = "board",
		fetch = FetchType.LAZY,
		cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
		orphanRemoval = true
	)
	private Set<BoardAttachmentEntity> attachments = new LinkedHashSet<>();


	@Builder
	@SuppressWarnings("unused")
	public BoardEntity(
		long boardId,
		String boardTitle,
		boolean boardUseYn,
		LocalDateTime createDate,
		LocalDateTime updateDate,
		BoardContentsEntity contents
	) {
		this.boardId = boardId;
		this.boardTitle = boardTitle;
		this.boardUseYn = boardUseYn;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.contents = contents;
	}
}
