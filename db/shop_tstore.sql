CREATE DATABASE tstore;

USE tstore;

CREATE TABLE book(
	seq BIGINT PRIMARY KEY AUTO_INCREMENT,
	title VARCHAR(255) NOT NULL,
	creator VARCHAR(255) NOT NULL,
	publisher VARCHAR(255) NOT NULL,
	pubDate DATE
);
	
select * from book;