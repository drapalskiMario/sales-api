CREATE TABLE `clients` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(100)
);

CREATE TABLE `products` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `description` VARCHAR(100),
    `price` NUMERIC(20,2)
);

CREATE TABLE `orders` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `client_id` INTEGER REFERENCES `clients`(`id`),
    `order_date` TIMESTAMP,
    `total` NUMERIC(20,2)
);

CREATE TABLE `order_items` (
    `id` INTEGER PRIMARY KEY AUTO_INCREMENT,
    `order_id` INTEGER REFERENCES `orders`(`id`),
    `product_id` INTEGER REFERENCES `products`(`id`),
    `quantity` INTEGER
);