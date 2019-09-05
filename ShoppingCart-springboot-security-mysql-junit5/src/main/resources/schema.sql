
/*DROP SCHEMA IF EXISTS shoppingcart_schema;*/
CREATE SCHEMA IF NOT EXISTS shoppingcart_schema;
SET SCHEMA shoppingcart_schema;


CREATE TABLE IF NOT EXISTS `authorities` (
  `username` varchar(60) PRIMARY KEY NOT NULL,
  `authority` varchar(45) DEFAULT NULL,
  /*PRIMARY KEY (`username`)*/
);


CREATE TABLE IF NOT EXISTS `products` (
  `productId` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
  `productAuthor` varchar(255) DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productPrice` int(11) DEFAULT NULL,
  /*PRIMARY KEY (`productId`)*/
);



CREATE TABLE IF NOT EXISTS `users` (
  `username` varchar(255) PRIMARY KEY NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  /*PRIMARY KEY (`username`)*/
);



CREATE TABLE IF NOT EXISTS `orderdetails` (
  `orderId` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL ,
  `orderDate` datetime DEFAULT NULL,
  `orderprice` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  /*PRIMARY KEY (`orderId`),*/
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);


CREATE TABLE IF NOT EXISTS `productdetails` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL ,
  `productQuantity` int(11) DEFAULT NULL,
  `productTotalPrice` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  /*PRIMARY KEY (`id`),*/
  FOREIGN KEY (`productId`) REFERENCES `products` (`productId`)
);


CREATE TABLE IF NOT EXISTS `order_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  UNIQUE (`product_id`),
  FOREIGN KEY (`order_id`) REFERENCES `orderdetails` (`orderId`),
  FOREIGN KEY (`product_id`) REFERENCES `productdetails` (`id`)
);
