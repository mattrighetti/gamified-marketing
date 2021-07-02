DROP TABLE IF EXISTS `review`;

CREATE TABLE `review`(
    `id`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    'product_id'  INT UNSIGNED NOT NULL,
    `user_id`     INT UNSIGNED NOT NULL,
    `review_text` VARCHAR(511),
    PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES `user` (`id`)
        ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE = InnoDB;
