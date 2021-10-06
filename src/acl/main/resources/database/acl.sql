CREATE TABLE IF NOT EXISTS `user` (
    `id` CHAR(36) NOT NULL,
    `user_name` VARCHAR(255) NOT NULL,
    `user_password` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);
