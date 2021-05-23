DROP TABLE IF EXISTS `user_survey_sections`;

CREATE TABLE `user_survey_sections` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `user_id` INT UNSIGNED NOT NULL,
    `survey_section_id` INT NOT NULL,
    `completed_on` INT NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`) ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`survey_section_id`) REFERENCES `survey_section`(`id`) ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;