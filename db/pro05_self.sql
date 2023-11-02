CREATE DATABASE edumon;

USE edumon;

DROP TABLE userinfo;

-- 테이블
CREATE TABLE userinfo (
	no INT PRIMARY KEY AUTO_INCREMENT, -- 고유번호
	id VARCHAR(20) NOT NULL, -- 아이디
	pw VARCHAR(300) NOT NULL, -- 비밀번호
	NAME VARCHAR(50) NOT NULL, -- 이름
	email VARCHAR(100), -- 이메일
	addr VARCHAR(300), -- 주소
	tel VARCHAR(20), -- 전화번호
	regdate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(), -- 가입일
	lev VARCHAR(20) DEFAULT 'USER', -- 레벨
	act VARCHAR(20) DEFAULT 'JOIN', -- 활성, 비활성 상태
	pt INT DEFAULT 0, -- 포인트
	CONSTRAINT key_name UNIQUE(id), -- id 중복 방지
	CONSTRAINT key_name UNIQUE(email) -- 이메일 중복 방지
);

-- ALTER TABLE userinfo MODIFY COLUMN tel VARCHAR(100);


-- 더미데이터
-- 관리자
insert into userinfo values(DEFAULT, 'admin', '1234', '관리자', 'admin@edu.com', '서울 구로구', '010-1234-5678', DEFAULT, 'ADMIN', default, DEFAULT);

-- 직원
insert into userinfo values(DEFAULT, 'hong', '1234', '홍길동', 'hong@edu.com', '서울 양천구', '010-4444-5678', default, 'EMP', default, DEFAULT);

-- 회원
insert into userinfo values(DEFAULT, 'kang', '1234', '강감찬', 'kang@edu.com', '서울 동작구', '010-1234-1234', default, default, default, DEFAULT);
insert into userinfo values(DEFAULT, 'kim', '1234', '김유신', 'kim@edu.com', '서울 은평구', '010-4242-5858', default, default, default, DEFAULT);

-- 비밀번호 1234 spring 암호화 버전
UPDATE userinfo SET pw='$2a$10$3zl8fmNyd1IsP1Ru0TNVee9AMtpM9E7yz5ZR9Qiofbj8zqqjJiqIi';

##############################################