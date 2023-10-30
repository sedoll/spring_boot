CREATE DATABASE edumon;

USE edumon;

-- varchar는 최대 64kb 까지 저장 가능 64만글자?까지 가능
CREATE TABLE test1(
	NO INT PRIMARY KEY AUTO_INCREMENT, -- 번호, 기본키
	id VARCHAR(20), -- 아이디
	com VARCHAR(2000) -- 코멘트
);

INSERT INTO test1 VALUES(DEFAULT, 'kim', '안녕하세요');
INSERT INTO test1 VALUES(DEFAULT, 'oh', '안녕하세요');
INSERT INTO test1 VALUES(DEFAULT, 'kang', '안녕하세요');
INSERT INTO test1 VALUES(DEFAULT, 'hong', '안녕하세요');
INSERT INTO test1 VALUES(DEFAULT, 'lee', '안녕하세요');