CREATE TABLE IF NOT EXISTS `page` (
    `id` CHAR(36) NOT NULL,
    `page_title` VARCHAR(255) NOT NULL,
    `page_body` MEDIUMBLOB NOT NULL,
    PRIMARY KEY (`id`)
);
