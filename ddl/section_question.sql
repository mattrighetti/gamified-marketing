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