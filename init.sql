-- 创建数据库
CREATE DATABASE IF NOT EXISTS mo_db;
USE mo_db;

-- 创建顾客表
DROP TABLE IF EXISTS customers;
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    wechat_openid VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(30),
    password VARCHAR(255) NOT NULL,
    avatar_url VARCHAR(255),
    balance DECIMAL(10, 2) DEFAULT 0.00,
    createTime TIMESTAMP,
    updateTime TIMESTAMP
);

-- 创建商家表
DROP TABLE IF EXISTS merchants;
CREATE TABLE merchants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    number VARCHAR(20),
    address VARCHAR(255),
    createTime TIMESTAMP,
    updateTime TIMESTAMP
);

-- 创建管理员表
DROP TABLE IF EXISTS admins;
CREATE TABLE admins (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role ENUM('root', 'admin') DEFAULT 'admin',
    createTime TIMESTAMP,
    updateTime TIMESTAMP
);

-- 创建店员表
DROP TABLE IF EXISTS employees;
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(255) NOT NULL,
    createTime TIMESTAMP,
    updateTime TIMESTAMP,
    merchant_id INT,
    FOREIGN KEY (merchant_id) REFERENCES merchants(id) ON DELETE CASCADE
);

-- 插入root管理员
INSERT INTO admins (username, password, role) VALUES ('root', 'root', 'root');