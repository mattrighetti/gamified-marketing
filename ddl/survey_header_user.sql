CREATE TABLE `survey_header_user` (
    `survey_header_id` INT UNSIGNED NOT NULL ,
    `user_id` INT UNSIGNED NOT NULL ,
    `status` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`survey_header_id`,`user_id`),
    FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
      ON UPDATE CASCADE ON DELETE CASCADE ,
    FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
      ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;