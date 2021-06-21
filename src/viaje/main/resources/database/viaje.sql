CREATE TABLE IF NOT EXISTS `viajes` (
    `id` CHAR(36) NOT NULL,
    `ruta` VARCHAR(255) NOT NULL,
    `estado` VARCHAR(255) NOT NULL,
    `tagNumero` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `viajes_tags` (
    `id` CHAR(36) NOT NULL,
    `numero` VARCHAR(255) NOT NULL,
    `clienteId` CHAR(36) NOT NULL,
    PRIMARY KEY (`id`)
);
CREATE TABLE IF NOT EXISTS `aclaracion` (
    `id` CHAR(36) NOT NULL,
    `estado` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`)
);