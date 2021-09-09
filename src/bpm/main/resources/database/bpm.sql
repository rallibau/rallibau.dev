CREATE TABLE IF NOT EXISTS `process` (
    `id` CHAR(36) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `nodes` JSON     NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `node` (
    `id` CHAR(36) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `type` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `connection` (
    `id` CHAR(36) NOT NULL,
    `node_id_owner` CHAR(36) NOT NULL,
    `node_id_target` CHAR(36) NULL,
    PRIMARY KEY (`id`)
);