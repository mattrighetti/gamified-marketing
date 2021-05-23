DROP TABLE IF EXISTS `survey_section`;

CREATE TABLE `survey_section` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `survey_header_id` INT NOT NULL,
    `name` VARCHAR(50) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `subheading` VARCHAR(70),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;