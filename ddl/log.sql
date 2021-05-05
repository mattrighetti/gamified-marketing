DROP TABLE IF EXISTS `log`;

CREATE TABLE `log` (
                       `user` VARCHAR(50) NOT NULL,
                       `access` TIMESTAMP NOT NULL,
                       PRIMARY KEY(`user`,`access`),
                       FOREIGN KEY(`user`) REFERENCES user(`username`) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;