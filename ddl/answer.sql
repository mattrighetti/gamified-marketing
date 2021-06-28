DROP TABLE IF EXISTS `answer`;

CREATE TABLE `answer` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `question_id` INT UNSIGNED,
    `survey_header_id` INT UNSIGNED,
    `option_choice_id` INT UNSIGNED,
    `answer_numeric` INT UNSIGNED,
    `answer_text` VARCHAR(255),
    `answer_yn` BOOLEAN,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`question_id`) REFERENCES `question`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`option_choice_id`) REFERENCES `option_choice`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;