CREATE DATABASE chat_room;
USE chat_room;

DROP TABLE IF EXISTS user;
CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
INSERT INTO user VALUES(NULL, "zzq", "123");
SELECT * FROM user;

DROP TABLE IF EXISTS chat;
CREATE TABLE chat (
    time VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL,
    chat VARCHAR(500) NOT NULL
);
SELECT * FROM chat;
