INSERT INTO sandbox (name, age, category) VALUES ('김지헌', 30, 'ADULT');
INSERT INTO sandbox (name, age, category) VALUES ('노수진', 29, 'ADULT');
INSERT INTO sandbox (name, age, category) VALUES ('조범진', 28, 'ADULT');
INSERT INTO sandbox (name, age, category) VALUES ('김양현', 19, 'ADULT');
INSERT INTO sandbox (name, age, category) VALUES ('박인수', 18, 'NORMAL');
INSERT INTO sandbox (name, age, category, deleted) VALUES ('정진범', 17, 'NORMAL', true);

/* 게시물 샘플 데이터 */
INSERT INTO board (board_id, board_title, board_use_yn, create_date, update_date)
VALUES (1, '첫번째 게시물 제목', true, current_timestamp(), current_timestamp());

INSERT INTO board (board_id, board_title, board_use_yn, create_date, update_date)
VALUES (2, '두번째 게시물 제목', true, current_timestamp(), current_timestamp());

INSERT INTO board (board_id, board_title, board_use_yn, create_date, update_date)
VALUES (3, '세번째 게시물 제목', true, current_timestamp(), current_timestamp());

INSERT INTO board (board_id, board_title, board_use_yn, create_date, update_date)
VALUES (4, '네번째 게시물 제목', true, current_timestamp(), current_timestamp());

INSERT INTO board (board_id, board_title, board_use_yn, create_date, update_date)
VALUES (5, '다섯번째 게시물 제목', true, current_timestamp(), current_timestamp());

/* 게시물 컨텐츠 샘플 데이터 */
INSERT INTO board_contents (board_id, board_contents) VALUES (1 , '첫번째 게시물 내용');
INSERT INTO board_contents (board_id, board_contents) VALUES (2 , '두번째 게시물 내용');
INSERT INTO board_contents (board_id, board_contents) VALUES (3 , '세번째 게시물 내용');
INSERT INTO board_contents (board_id, board_contents) VALUES (4 , '네번째 게시물 내용');
INSERT INTO board_contents (board_id, board_contents) VALUES (5 , '다섯번째 게시물 내용');

/* 게시물 첨부파일 샘플 데이터 */
INSERT INTO board_attachment (board_attachment_id, board_id, board_attachment_path, board_attachment_name)
VALUES (1, 1, 'https://woowahan-cdn.woowahan.com/resources/img/pc/', 'common-logo.png');

INSERT INTO board_attachment (board_attachment_id, board_id, board_attachment_path, board_attachment_name)
VALUES (2, 2, 'https://woowahan-cdn.woowahan.com/resources/img/pc/', 'common-logo.png');

INSERT INTO board_attachment (board_attachment_id, board_id, board_attachment_path, board_attachment_name)
VALUES (3, 3, 'https://woowahan-cdn.woowahan.com/resources/img/pc/', 'common-logo.png');

INSERT INTO board_attachment (board_attachment_id, board_id, board_attachment_path, board_attachment_name)
VALUES (4, 4, 'https://woowahan-cdn.woowahan.com/resources/img/pc/', 'common-logo.png');

INSERT INTO board_attachment (board_attachment_id, board_id, board_attachment_path, board_attachment_name)
VALUES (5, 5, 'https://woowahan-cdn.woowahan.com/resources/img/pc/', 'common-logo.png');