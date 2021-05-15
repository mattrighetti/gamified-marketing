DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
                            `id` INT NOT NULL AUTO_INCREMENT,
                            `user` VARCHAR(50) NOT NULL,
                            `product` INT UNSIGNED NOT NULL,
                            `question_text` VARCHAR(100) NOT NULL,
                            `mandatory` BOOL NOT NULL DEFAULT FALSE,
                            `answer` VARCHAR(100),
                            PRIMARY KEY(`id`,`user`,`product`),
                            FOREIGN KEY(`user`) REFERENCES user(`username`) ON UPDATE CASCADE ON DELETE CASCADE,
                            FOREIGN KEY(`product`) REFERENCES product(`id`)  ON UPDATE CASCADE ON DELETE CASCADE
)  ENGINE=InnoDB;