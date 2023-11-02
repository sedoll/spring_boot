-- test07
CREATE DATABASE tsherpa;

USE tsherpa;

-- 권한 관련 테이블
CREATE TABLE ROLE(
	role_id INT PRIMARY KEY AUTO_INCREMENT, -- id
	ROLE VARCHAR(255) DEFAULT NULL -- 속성값
)

-- DROP TABLE userinfo;

-- user
CREATE TABLE userinfo(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	ACTIVE INT DEFAULT 0,
	login_id VARCHAR(255) NOT NULL,
	pw VARCHAR(330) NOT NULL,
	user_name VARCHAR(255) NOT NULL,
	CONSTRAINT key_name UNIQUE(login_id)
);

-- user + role	
CREATE table user_role(
	user_id INT NOT NULL,
	role_id INT NOT NULL
);
	