CREATE TABLE `products` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`name` varchar(255) NOT NULL,
	`price` int,
	`count` int,
	PRIMARY KEY (`id`)
);

CREATE TABLE `categories` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nameCategory` varchar(255) NOT NULL,
	`parentId` bigint NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `baskets` (
	`id_client` bigint NOT NULL,
	`id_product` bigint NOT NULL,
	`name` varchar(255) NOT NULL,
	`price` int NOT NULL,
	`count` int NOT NULL
);

CREATE TABLE `clients` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`lastName` varchar(255) NOT NULL,
	`firstName` varchar(255) NOT NULL,
	`patronymic` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`postalAddress` varchar(255) NOT NULL,
	`phoneNumber` varchar(255) NOT NULL,
	`login` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `admins` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`lastName` varchar(255) NOT NULL,
	`firstName` varchar(255) NOT NULL,
	`patronymic` varchar(255) NOT NULL,
	`position` varchar(255) NOT NULL,
	`login` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `products_categories` (
	`id_product` bigint NOT NULL,
	`id_category` bigint NOT NULL
);

CREATE TABLE `deposits` (
	`id_client` bigint NOT NULL,
	`summ` int
);

CREATE TABLE `purchases` (
	`id_client` bigint NOT NULL,
	`id_product` bigint NOT NULL,
	`name` varchar(255) NOT NULL,
	`price` int NOT NULL,
	`count` int NOT NULL
);

ALTER TABLE `categories` ADD CONSTRAINT `categories_fk0` FOREIGN KEY (`parentId`) REFERENCES `categories`(`id`) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `baskets` ADD CONSTRAINT `baskets_fk0` FOREIGN KEY (`id_client`) REFERENCES `clients`(`id`);

ALTER TABLE `baskets` ADD CONSTRAINT `baskets_fk1` FOREIGN KEY (`id_product`) REFERENCES `products`(`id`);

ALTER TABLE `products_categories` ADD CONSTRAINT `products_categories_fk0` FOREIGN KEY (`id_product`) REFERENCES `products`(`id`);

ALTER TABLE `products_categories` ADD CONSTRAINT `products_categories_fk1` FOREIGN KEY (`id_category`) REFERENCES `categories`(`id`)ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE `deposits` ADD CONSTRAINT `deposits_fk0` FOREIGN KEY (`id_client`) REFERENCES `clients`(`id`);

ALTER TABLE `purchases` ADD CONSTRAINT `purchases_fk0` FOREIGN KEY (`id_client`) REFERENCES `clients`(`id`);

ALTER TABLE `purchases` ADD CONSTRAINT `purchases_fk1` FOREIGN KEY (`id_product`) REFERENCES `products`(`id`);
