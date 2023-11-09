GRANT ALL PRIVILEGES ON test1.* TO 'test1'@'%';

SELECT CURRENT_TIMESTAMP();

CREATE DATABASE edumon;

USE edumon;

CREATE TABLE test1(no int PRIMARY KEY AUTO_INCREMENT,
id VARCHAR(20), com VARCHAR(2000));

INSERT INTO test1 VALUES (DEFAULT, 'kim', '오늘 처음 스프링부트');
INSERT INTO test1 VALUES (DEFAULT, 'lee', '스프링 부트 더미 테스트2');

COMMIT;

SELECT * FROM test1;

CREATE TABLE tb_authorities (
  auth_idx int NOT NULL AUTO_INCREMENT COMMENT '인덱스',
  user_id varchar(45) DEFAULT NULL COMMENT '사용자 아이디',
  authority varchar(45) DEFAULT NULL COMMENT '권한',
  PRIMARY KEY (auth_idx)
);

CREATE TABLE tb_code (
  code_idx int NOT NULL AUTO_INCREMENT COMMENT '인덱스',
  code_no varchar(45) NOT NULL COMMENT '코드번호',
  code_name varchar(45) DEFAULT NULL,
  code_exp varchar(45) DEFAULT NULL COMMENT '코드명',
  PRIMARY KEY (code_idx,code_no)
);

CREATE TABLE tb_grp_code (
  grp_code_idx int NOT NULL AUTO_INCREMENT COMMENT '인덱스',
  grp_cd_no varchar(20) DEFAULT NULL COMMENT '그룹코드 번호',
  grp_cd_exp varchar(45) DEFAULT NULL COMMENT '그룹 코드명',
  PRIMARY KEY (grp_code_idx)
);

CREATE TABLE tb_users (
  user_idx int NOT NULL AUTO_INCREMENT COMMENT '인덱스',
  user_id varchar(45) NOT NULL COMMENT '사용자 아이디',
  passwd varchar(100) NOT NULL COMMENT '패스워드',
  user_name varchar(20) DEFAULT NULL COMMENT '사용자 이름',
  email varchar(45) DEFAULT NULL COMMENT '이메일',
  tel varchar(45) DEFAULT NULL COMMENT '핸드폰번호',
  use_yn varchar(45) DEFAULT NULL COMMENT '사용여부',
  regdate DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '가입일시',
  pt INT DEFAULT 0, 
  PRIMARY KEY (user_idx)
);

INSERT INTO tb_grp_code VALUES (DEFAULT, 200, '권한');

INSERT INTO tb_code VALUES (DEFAULT, '200', 'ADMIN', '관리자');
INSERT INTO tb_code VALUES (DEFAULT, '200', 'USER', '사용자');

##################################################

USE edumon;

CREATE TABLE kuser(id int PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(20) NOT NULL,
password VARCHAR(300) NOT NULL,
username VARCHAR(50) NOT NULL,
email VARCHAR(100) NOT NULL,
address VARCHAR(300),
tel VARCHAR(20),
regdate DATETIME DEFAULT CURRENT_TIMESTAMP,
lev VARCHAR(20) DEFAULT 'USER',
act VARCHAR(20) DEFAULT 'JOIN', 
CONSTRAINT key_name UNIQUE(NAME)
);

-- 더미 데이터
INSERT INTO kuser VALUES (DEFAULT, 'kim', '1234', '김', 'kim@gmail.com', '가산동', '010-1004-1004', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO kuser VALUES (DEFAULT, 'lee', '1234', '오', 'oh@gmail.com', '신림동', '010-7979-2848', DEFAULT, DEFAULT, DEFAULT);
INSERT INTO kuser VALUES (DEFAULT, 'admin', '1234', '박세훈', 'park@gmail.com', '구로', '010-2424-7942', DEFAULT, DEFAULT, DEFAULT);

UPDATE kuser SET lev='ADMIN' WHERE NAME='admin';
UPDATE kuser SET lev='EMP' WHERE NAME='lee';

SELECT * FROM kuser;

UPDATE kuser SET PASSWORD='$2a$10$N4HrCSDECM/wNWqBGhzDMOrLN1Aw9WRHtmEqxuBK9sWJ3K97Jqau6' WHERE PASSWORD='1234';

COMMIT;



DROP TABLE kuser;

DESC kuser;


-- 게시판
DROP TABLE board;

CREATE TABLE board(
	id int PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL,
	title VARCHAR(100) NOT NULL,
	content VARCHAR(1000),
	cnt INT DEFAULT 0,
	resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(name) REFERENCES kuser(name) ON DELETE 		
		CASCADE
);

INSERT INTO board values(DEFAULT, "admin", "admin1", "내용1", default, DEFAULT);
INSERT INTO board values(DEFAULT, "admin", "admin2", "내용2", default, DEFAULT);
INSERT INTO board values(DEFAULT, "admin", "admin3", "내용3", default, DEFAULT);
INSERT INTO board values(DEFAULT, "admin", "admin4", "내용4", default, DEFAULT);
INSERT INTO board values(DEFAULT, "admin", "admin5", "내용5", default, DEFAULT);
INSERT INTO board values(DEFAULT, "lee1", "lee1", "내용1", default, DEFAULT);
INSERT INTO board values(DEFAULT, "lee1", "lee2", "내용2", default, DEFAULT);
INSERT INTO board values(DEFAULT, "lee1", "lee3", "내용3", default, DEFAULT);
INSERT INTO board values(DEFAULT, "lee1", "lee4", "내용4", default, DEFAULT);
INSERT INTO board values(DEFAULT, "lee1", "lee5", "내용5", default, DEFAULT);
INSERT INTO board values(DEFAULT, "kim1", "kim1", "내용1", default, DEFAULT);
INSERT INTO board values(DEFAULT, "kim1", "kim2", "내용2", default, DEFAULT);
INSERT INTO board values(DEFAULT, "kim1", "kim3", "내용3", default, DEFAULT);
INSERT INTO board values(DEFAULT, "kim1", "kim4", "내용4", default, DEFAULT);
INSERT INTO board values(DEFAULT, "kim1", "kim5", "내용5", default, DEFAULT);

-- 게시판 댓글
CREATE TABLE board_com(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NAME VARCHAR(20) NOT NULL,
	CONTENT VARCHAR(300) NOT NULL,
	resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	par INT,
	FOREIGN KEY(name) REFERENCES kuser(name) ON DELETE 		
		CASCADE
);

-- 중고 상품 테이블
DROP TABLE product;

CREATE TABLE product(
	no INT PRIMARY KEY AUTO_INCREMENT,
	id VARCHAR(20) NOT NULL,
	title VARCHAR(100),
	content VARCHAR(2000),
	cnt INT DEFAULT 0,
	resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(id) REFERENCES kuser(name) ON DELETE 		
		CASCADE
);
	
-- 자료실 db
CREATE TABLE fileobj (
	no int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	pno INT NOT NULL, -- 상품 테이블 번호
	savefolder VARCHAR(400),
	originfile VARCHAR(400),
	savefile VARCHAR(800),
	filesize LONG,
	uploaddate VARCHAR(100)
);

-- 채팅방
CREATE TABLE chatroom(
	room_id VARCHAR(200) PRIMARY key,
	NAME VARCHAR(200),
	buyer VARCHAR(20),
	seller VARCHAR(20),
	FOREIGN KEY(buyer) REFERENCES kuser(name) ON DELETE CASCADE,
	FOREIGN KEY(seller) REFERENCES kuser(name) ON DELETE CASCADE
);
	
-- 채팅 메세지
CREATE TABLE chatmsg(
	no BIGINT PRIMARY KEY AUTO_INCREMENT,
	room_id VARCHAR(200) NOT NULL,
	mtype VARCHAR(10) NOT NULL,
	sender VARCHAR(20) NOT NULL,
	message VARCHAR(1000) NOT NULL,
	resdate DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY(sender) REFERENCES kuser(name) ON DELETE CASCADE,
	FOREIGN KEY(room_id) REFERENCES chatroom(room_id) ON DELETE CASCADE
);
	