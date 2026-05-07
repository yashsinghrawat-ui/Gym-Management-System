
CREATE DATABASE gymdb;

USE gymdb;

CREATE TABLE members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    phone VARCHAR(20)
);
