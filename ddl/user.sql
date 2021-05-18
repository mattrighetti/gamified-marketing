DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `username` VARCHAR(50) NOT NULL,
    `password` VARCHAR(50) NOT NULL,
    `email` VARCHAR(50) NOT NULL,
    `admin` BOOL NOT NULL DEFAULT FALSE,
    `banned` BOOL NOT NULL DEFAULT FALSE,
    `score` INT NOT NULL DEFAULT 0,
    PRIMARY KEY(`username`)
) ENGINE=InnoDB;
