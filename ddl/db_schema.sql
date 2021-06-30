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
    `image` VARCHAR(500) NOT NULL,
    `date` INT UNSIGNED NOT NULL,
    `description` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`id`)
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

DROP TABLE IF EXISTS `survey_header_user`;

CREATE TABLE `survey_header_user` (
    `survey_header_id` INT UNSIGNED NOT NULL ,
    `user_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`survey_header_id`,`user_id`),
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_section`;

CREATE TABLE `survey_section` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `subheading` VARCHAR(70),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_header_survey_section`;

CREATE TABLE `survey_header_survey_section`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `survey_header_id` INT UNSIGNED NOT NULL,
    `survey_section_id` INT UNSIGNED NOT NULL,
    `section_order` INT UNSIGNED NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`survey_section_id`) REFERENCES `survey_section`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `option_group`;

CREATE TABLE `option_group`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_name` VARCHAR(50),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group` INT UNSIGNED,
    `name` VARCHAR(100) NOT NULL,
    `subtext` VARCHAR(255),
    `input_type` VARCHAR(30) NOT NULL,
    `required` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`option_group`) REFERENCES `option_group`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
)  ENGINE=InnoDB;

DROP TABLE IF EXISTS `survey_section_question`;

CREATE TABLE `survey_section_question`(
    `survey_section_id` INT UNSIGNED NOT NULL,
    `question_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`survey_section_id`,question_id),
    FOREIGN KEY (`survey_section_id`) REFERENCES survey_section(`id`)
      ON UPDATE CASCADE  ON DELETE CASCADE,
    FOREIGN KEY (`question_id`) REFERENCES `question`(`id`)
      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `option_choice`;

CREATE TABLE `option_choice`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_id` INT UNSIGNED NOT NULL,
    `option_choice_name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`option_group_id`) REFERENCES `option_group`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `quesiton_option`;

DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `question_id` INT UNSIGNED,
    `survey_header_id` INT UNSIGNED,
    `option_choice_id` INT UNSIGNED,
    `answer_text` VARCHAR(255),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`question_id`) REFERENCES `question`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`option_choice_id`) REFERENCES `option_choice`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT UNSIGNED NOT NULL,
    `user_id` INT UNSIGNED NOT NULL,
    `review_text` VARCHAR(511),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `product`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

-- SQL DATA INSERTION

INSERT INTO `user`(`username`, `password`, `email`, `admin`, `banned`, `score`)
VALUES ("admin1", "secret", "admin1@gmail.com", 1, 0, 10),
       ("admin2", "secret", "admin2@gmail.com", 1, 0, 1000),
       ("admin3", "secret", "admin3@gmail.com", 1, 0, 854),
       ("matt", "secret", "matt@gmail.com", 0, 0, 130921),
       ("random", "secret", "random@gmail.com", 0, 0, 2139);

INSERT INTO `product`(`name`, `image`, `date`, `description`)
VALUE ("iPad 12.9\"", "https://johnlewis.scene7.com/is/image/JohnLewis/238667158?$rsp-pdp-port-1440$", 1622106066, "This is the new iPad 12.9 inches with the new M1 chip"),
      ("iPhone 12\"", "https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.apple.com%2Fit%2Fshop%2Fbuy-iphone%2Fiphone-12-pro&psig=AOvVaw0F9zeDBzOck__7nGRYLMU3&ust=1624488071565000&source=images&cd=vfe&ved=0CAoQjRxqFwoTCMDao9HCrPECFQAAAAAdAAAAABAD", 1620106066, "This is the new iPhone 12 ");


INSERT INTO `survey_header`(`name`, `product_id`, `instructions`)
VALUE ("Quality Control", 1, "Answer to all the required fields to gain points"),
    ("Quality Control", 2, "Answer to all the required fields to gain points");

INSERT INTO `survey_section`(`name`, `title`, `subheading`)
VALUES ("Quality questions", "Quality", "This section asks general questions about the product's quality"),
       ("Statistics questions", "Statistichs", "This section will ask stuff about you");

INSERT INTO `survey_header_survey_section`(`survey_header_id`,`survey_section_id`, `section_order`)
VALUES  (1,1,1),
        (1,2,2),
        (2,1,1),
        (2,2,2);

INSERT INTO `option_group`(`option_group_name`)
VALUES ("Sex options"),
       ("Age range");

INSERT INTO `option_choice`(`option_group_id`, `option_choice_name`)
VALUES (1, "Male"),
       (1, "Female"),
       (2, "15-30"),
       (2, "30-45"),
       (2, "45-60"),
       (2, ">60");

INSERT INTO `question`(`option_group`, `name`, `subtext`,`input_type` ,`required`)
VALUES ( null, "Review the product", "Write a small review of the product: what you did like, what you did not like etc.","text" ,true),
       (null, "Would you recommend this product to a friend?", null, "text", true),
       (null, "What would you buy next?", null, "text", true),
       ( 1, "Sex", null, "radio", false),
       (2, "Age", null, "radio", false);

INSERT INTO `survey_section_question`(`survey_section_id`,`question_id`)
VALUES(1,1),
       (1,2),
       (1,3),
       (2,4),
       (2,5);

INSERT INTO `forbidden`(`word`)
VALUES ("cazzo"), ("culo"), ("merda"), ("coglione"), ("imbecille"), ("ritardato");

INSERT INTO `review`(`product_id`, `user_id`, `review_text`)
VALUES (1, 1, "Best product ever!"),
       (1, 3, "Android is better!");

INSERT INTO `survey_header_user`(`survey_header_id`,`user_id`)
VALUES (2,2);

INSERT INTO `answer`(`user_id`,`question_id`,`survey_header_id`,`option_choice_id`,`answer_text`)
VALUES (2,1,2,null,"a"),
        (2,2,2,null,"b"),
        (2,3,2,null,"c"),
       (2,4,2,1,null),
       (2,5,2,3,null);



DELIMITER //

CREATE TRIGGER `addUsersScore`
    AFTER INSERT ON `answer`
    FOR EACH ROW
BEGIN
    UPDATE `user`
    SET user.`score` = (SELECT SUM(shss.`section_order`)
                        FROM `survey_section_question` AS sq
                                 INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                 INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                        WHERE NEW.`question_id` = sq.`question_id`
                       ) + user.`score`
    WHERE `user_id` = NEW.`user_id`;
END;//

CREATE TRIGGER `subtractUsersScore`
AFTER DELETE ON `answer`
FOR EACH ROW
UPDATE `user`
SET user.`score` = user.`score` - (SELECT SUM(shss.`section_order`)
                                   FROM `survey_section_question` AS sq
                                            INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                            INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                                   WHERE OLD.`question_id` = sq.`question_id`
)
WHERE `user_id` = OLD.`user_id`;//