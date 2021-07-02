DROP TABLE IF EXISTS `survey_header`;

CREATE TABLE `survey_header` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_id` INT UNSIGNED NOT NULL,
    `name` VARCHAR(50),
    `instructions` VARCHAR(255),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `product`(`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;