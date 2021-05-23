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