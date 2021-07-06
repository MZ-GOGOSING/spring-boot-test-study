package me.gogosing.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 첨부파일 Entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "board_attachment")
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class BoardAttachmentEntity extends BaseEntity {

	/**
	 * 게시물 첨부파일 번호.
	 */
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_attachment_id", nullable = false)
	private long boardAttachmentId;

	/**
	 * 게시물 일련번호.
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id")
	private BoardEntity board;

	/**
	 * 첨부파일경로.
	 */
	@EqualsAndHashCode.Include
	@Column(name = "board_attachment_path", nullable = false)
	private String boardAttachmentPath;

	/**
	 * 첨부파일명.
	 */
	@EqualsAndHashCode.Include
	@Column(name = "board_attachment_name", nullable = false)
	private String boardAttachmentName;


	@Builder
	@SuppressWarnings("unused")
	public BoardAttachmentEntity(
		long boardAttachmentId,
		BoardEntity board,
		String boardAttachmentPath,
		String boardAttachmentName
	) {
		this.boardAttachmentId = boardAttachmentId;
		this.board = board;
		this.boardAttachmentPath = boardAttachmentPath;
		this.boardAttachmentName = boardAttachmentName;
	}
}
