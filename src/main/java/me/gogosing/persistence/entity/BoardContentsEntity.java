package me.gogosing.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 컨텐츠 Entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "board_contents")
public class BoardContentsEntity extends BaseEntity {

	/**
	 * 게시물 번호.
	 */
	@Id
	@Column(name = "board_id", nullable = false)
	private long boardId;

	/**
	 * 게시물 컨텐츠 내용.
	 */
	@Column(name = "board_contents", nullable = false, columnDefinition = "TEXT")
	private String boardContents;

	/**
	 * 게시물 정보.
	 */
	@MapsId
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_id")
	private BoardEntity board;


	@Builder
	@SuppressWarnings("unused")
	public BoardContentsEntity(
		String boardContents,
		BoardEntity board
	) {
		this.boardContents = boardContents;
		this.board = board;
	}
}
