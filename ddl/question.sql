DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `survey_section_id` INT UNSIGNED NOT NULL,
    `input_type_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(100) NOT NULL,
    `subtext` VARCHAR(255),
    `required` BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`survey_section_id`) REFERENCES `survey_section`(`id`) ON UPDATE CASCADE ON DELETE NO ACTION,
    FOREIGN KEY (`input_type_id`) REFERENCES `input_type`(`id`) ON UPDATE CASCADE ON DELETE NO ACTION
)  ENGINE=InnoDB;