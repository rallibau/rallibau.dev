CREATE TABLE IF NOT EXISTS `tags` (
    `id` CHAR(36) NOT NULL,
    `numero` VARCHAR(255) NOT NULL,
    `clienteId` CHAR(36) NOT NULL,
    `tagoperador` INTEGER NOT NULL,
    `tagtipo` INTEGER NOT NULL,
    `tagcategoria` VARCHAR(255) NOT NULL,
    `tagstatus` INTEGER NOT NULL,
    `taghoraactualizacion` VARCHAR(255) NOT NULL,
    `tagfechaactualizacion` VARCHAR(255) NOT NULL,
    `tagsaldo` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);