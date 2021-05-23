DROP TABLE IF EXISTS `input_type`;

CREATE TABLE `input_type` (
    `id` INT UNSIGNED NOT NULL,
    `type` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`type`)
) ENGINE=InnoDB;