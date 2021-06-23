CREATE TABLE `survey_header_user` (
      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
      `survey_header_id` INT UNSIGNED NOT NULL ,
      `user_id` INT UNSIGNED NOT NULL ,
      PRIMARY KEY (`id`),
      FOREIGN KEY (`survey_header_id`) REFERENCES `survey_header`(`id`)
          ON UPDATE CASCADE ON DELETE CASCADE ,
      FOREIGN KEY (`user_id`) REFERENCES `user`(`id`)
          ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;