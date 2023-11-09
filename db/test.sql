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