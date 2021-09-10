CREATE TABLE IF NOT EXISTS `task` (
    `id` CHAR(36) NOT NULL,
    `task_node_id` CHAR(36) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `story` (
    `id` CHAR(36) NOT NULL,
    `name` VARCHAR(255) NOT NULL,
    `process_id` CHAR(36) NOT NULL,
    PRIMARY KEY (`id`)
);