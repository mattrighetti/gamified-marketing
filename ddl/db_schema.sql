DROP DATABASE IF EXISTS gamified_marketing;
CREATE DATABASE gamified_marketing;
USE gamified_marketing;

DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `log`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `product`;

CREATE TABLE `user` (
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `admin` BOOL NOT NULL DEFAULT FALSE,
    `banned` BOOL NOT NULL DEFAULT FALSE,
    `score` INT NOT NULL DEFAULT 0,
    PRIMARY KEY(`username`)
) ENGINE=InnoDB;

CREATE TABLE `product` (
    `id` INT(4) UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `image` VARCHAR(50) NOT NULL UNIQUE KEY,
    `thumbnail` VARCHAR(50) NOT NULL UNIQUE KEY,
    `date` INT(11) NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE `question` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `product` INT UNSIGNED NOT NULL,
    `question_text` VARCHAR(100) NOT NULL,
    `mandatory` BOOL NOT NULL DEFAULT FALSE, 
    `answer` VARCHAR(100),
    PRIMARY KEY(`id`,`product`),
    FOREIGN KEY(`product`)
        REFERENCES product(`id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE `answer` (
    `user` VARCHAR(50) NOT NULL,
    `question` INT UNSIGNED NOT NULL,
    `text` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`user`,`question`),
    FOREIGN KEY(`user`)
        REFERENCES user(`username`)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY(`user`)
        REFERENCES question(`id`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE=InnoDB;


CREATE TABLE `log` (
    `user` VARCHAR(50) NOT NULL,
    `access` TIMESTAMP NOT NULL,
    PRIMARY KEY(`user`,`access`),
    FOREIGN KEY(`user`)
        REFERENCES user(`username`)
        ON UPDATE CASCADE
        ON DELETE CASCADE
) ENGINE=InnoDB;
