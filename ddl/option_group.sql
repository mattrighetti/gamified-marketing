DROP TABLE IF EXISTS `option_group`;

CREATE TABLE `option_group`(
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `option_group_name` VARCHAR(50),
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;