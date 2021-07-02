DROP TABLE IF EXISTS `option_choice`;

CREATE TABLE `option_choice`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_id` INT UNSIGNED NOT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (`option_group_id`) REFERENCES `option_group`(`id`)
        ON UPDATE CASCADE ON DELETE NO ACTION
) ENGINE=InnoDB;