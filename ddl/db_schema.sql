DROP DATABASE IF EXISTS gamified_marketing;
CREATE DATABASE gamified_marketing;
USE gamified_marketing;

DROP TABLE IF EXISTS `forbidden`;

CREATE TABLE `forbidden`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `word` VARCHAR(50) NOT NULL,
    PRIMARY KEY(`id`),
    UNIQUE KEY(`word`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `admin` BOOLEAN NOT NULL DEFAULT FALSE,
    `banned` BOOLEAN NOT NULL DEFAULT FALSE,
    `score` INT UNSIGNED NOT NULL DEFAULT 0,
    PRIMARY KEY(`id`),
    UNIQUE (`username`),
    UNIQUE (`email`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `image` VARCHAR(50) NOT NULL UNIQUE KEY,
    `thumbnail` VARCHAR(50) NOT NULL UNIQUE KEY,
    `date` INT UNSIGNED NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `input_type`;

CREATE TABLE `input_type` (
    `id` INT UNSIGNED NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`type`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `access` TIMESTAMP NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY(`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_header`;

CREATE TABLE `survey_header` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(50),
    `instructions` VARCHAR(255),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `product`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_section`;

CREATE TABLE `survey_section` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `survey_header_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `subheading` VARCHAR(70),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `survey_section_id` INT UNSIGNED NOT NULL,
    `input_type_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `subtext` VARCHAR(255),
    `required` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`survey_section_id`) REFERENCES `survey_section`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`input_type_id`) REFERENCES `input_type`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
)  ENGINE=InnoDB;

DROP TABLE IF EXISTS `option_group`;

CREATE TABLE `option_group`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_name` VARCHAR(50),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `option_choice`;

CREATE TABLE `option_choice`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`option_group_id`) REFERENCES `option_group`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `quesiton_option`;

CREATE TABLE `question_option`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `question_id` INT UNSIGNED NOT NULL,
    `option_choice_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`question_id`) REFERENCES `question`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`option_choice_id`) REFERENCES `option_choice`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `user_survey_sections`;

CREATE TABLE `user_survey_sections` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `survey_section_id` INT UNSIGNED NOT NULL,
    `completed_on` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`survey_section_id`) REFERENCES `survey_section`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `question_option_id` INT UNSIGNED NOT NULL,
    `answer_numeric` INT UNSIGNED,
    `answer_text` VARCHAR(255),
    `answer_yn` BOOLEAN,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`question_option_id`) REFERENCES `question_option`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

INSERT INTO `user`(`username`, `password`, `email`, `admin`, `banned`, `score`)
VALUES ("admin1", "secret", "admin1@gmail.com", 1, 0, 10),
       ("admin2", "secret", "admin2@gmail.com", 1, 0, 1000),
       ("admin3", "secret", "admin3@gmail.com", 1, 0, 854),
       ("matt", "secret", "matt@gmail.com", 0, 0, 130921),
       ("random", "secret", "random@gmail.com", 0, 0, 2139);