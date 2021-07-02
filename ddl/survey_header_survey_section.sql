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