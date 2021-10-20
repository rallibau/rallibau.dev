CREATE TABLE IF NOT EXISTS `page` (
    `id` CHAR(36) NOT NULL,
    `page_id_user` CHAR(36) NOT NULL,
    `page_title` VARCHAR(255) NOT NULL,
    `page_body` MEDIUMBLOB NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `user` (
    `id` CHAR(36) NOT NULL,
    `user_name` VARCHAR(255) NOT NULL,
    `page_count` INT NOT NULL,
    PRIMARY KEY (`id`)
);
