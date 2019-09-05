
/*DROP SCHEMA IF EXISTS shoppingcart_schema;*/
CREATE SCHEMA IF NOT EXISTS shoppingcart_schema;
SET SCHEMA shoppingcart_schema;


CREATE TABLE `authorities` (
  `username` varchar(60) NOT NULL,
  `authority` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`username`)
);


CREATE TABLE `products` (
  `productId` int(11) NOT NULL AUTO_INCREMENT,
  `productAuthor` varchar(255) DEFAULT NULL,
  `productName` varchar(255) DEFAULT NULL,
  `productPrice` int(11) DEFAULT NULL,
  PRIMARY KEY (`productId`)
);



CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
);



CREATE TABLE `orderdetails` (
  `orderId` int(11) NOT NULL AUTO_INCREMENT,
  `orderDate` datetime DEFAULT NULL,
  `orderprice` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  FOREIGN KEY (`username`) REFERENCES `users` (`username`)
);


CREATE TABLE `productdetails` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productQuantity` int(11) DEFAULT NULL,
  `productTotalPrice` int(11) DEFAULT NULL,
  `productId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`productId`) REFERENCES `products` (`productId`)
);


CREATE TABLE `order_product` (
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  UNIQUE (`product_id`),
  FOREIGN KEY (`order_id`) REFERENCES `orderdetails` (`orderId`),
  FOREIGN KEY (`product_id`) REFERENCES `productdetails` (`id`)
);
