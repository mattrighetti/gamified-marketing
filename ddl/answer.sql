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