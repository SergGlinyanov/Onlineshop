CREATE TABLE `products` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nameProduct` varchar(255) NOT NULL,
	`count` int,
	`price` int,
	`id_category` int,
	PRIMARY KEY (`id`)
);

CREATE TABLE `categories` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`nameCategory` varchar(255) NOT NULL,
	`id_parent_category` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `baskets` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`id_user` int NOT NULL,
	`id_product` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `users` (
	`id` bigint NOT NULL AUTO_INCREMENT,
	`lastName` varchar(255) NOT NULL,
	`firstName` varchar(255) NOT NULL,
	`patronymic` varchar(255) NOT NULL,
	`email` varchar(255) NOT NULL,
	`postalAddress` varchar(255) NOT NULL,
	`phoneNumber` varchar(255) NOT NULL,
	`login` varchar(255) NOT NULL,
	`password` varchar(255) NOT NULL,
	`deposit` int NOT NULL,
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

ALTER TABLE `products` ADD CONSTRAINT `products_fk0` FOREIGN KEY (`id_category`) REFERENCES `categories`(`id`);

ALTER TABLE `categories` ADD CONSTRAINT `categories_fk0` FOREIGN KEY (`id_parent_category`) REFERENCES `categories`(`id`);

ALTER TABLE `baskets` ADD CONSTRAINT `baskets_fk0` FOREIGN KEY (`id_user`) REFERENCES `users`(`id`);

ALTER TABLE `baskets` ADD CONSTRAINT `baskets_fk1` FOREIGN KEY (`id_product`) REFERENCES `products`(`id`);
