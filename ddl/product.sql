DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
                           `id` INT(4) UNSIGNED NOT NULL AUTO_INCREMENT,
                           `name` VARCHAR(50) NOT NULL,
                           `image` VARCHAR(50) NOT NULL UNIQUE KEY,
                           `thumbnail` VARCHAR(50) NOT NULL UNIQUE KEY,
                           `date` INT(11) NOT NULL,
                           `description` VARCHAR(100) NOT NULL,
                           PRIMARY KEY (id)
) ENGINE=InnoDB;
