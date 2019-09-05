INSERT INTO shoppingcart_schema.authorities (username,authority) VALUES ('UserA','ROLE_USER');
INSERT INTO shoppingcart_schema.authorities (username,authority) VALUES ('UserB','ROLE_USER');
INSERT INTO shoppingcart_schema.authorities (username,authority) VALUES ('UserC','ROLE_USER');
INSERT INTO shoppingcart_schema.authorities (username,authority) VALUES ('UserD','ROLE_EMPLOYEE');
INSERT INTO shoppingcart_schema.authorities (username,authority) VALUES ('UserE','ROLE_MANAGER');

INSERT INTO shoppingcart_schema.users (username, email, enabled, firstname, lastname, password) VALUES ('UserA', 'Ram@gmail.com', '1', 'Ram', 'Baswa', '$2a$10$UdbJd6ermap8IBLaBP.5TuDzrrcBby8oMlhD0Y9SJ5t6odbNeFQvy');
INSERT INTO shoppingcart_schema.users (username, email, enabled, firstname, lastname, password) VALUES ('UserB', 'kal@gmail.com', '1', 'Kalpana', 'Chit', '$2a$10$v/YdglGQ3mre2RG1jNygIOv5FfLwnSplqnkijJRGbe5mg6e9P.kNa');
INSERT INTO shoppingcart_schema.users (username, email, enabled, firstname, lastname, password) VALUES ('UserC', 'Ravan@gmail.com', '1', 'Ravan', 'Karva', '$2a$10$s.szt/5INcSW3ctrRNf5buTKTrB5NM1YMchCYofPzektcAdA97j1W');
INSERT INTO shoppingcart_schema.users (username, email, enabled, firstname, lastname, password) VALUES ('UserD', 'sdsdf@gmail.com', '1', 'Shirish', 'Nanu', '$2a$10$vB.6jIYtvv.CUMCzTeZWtu.gJRxuhclMJaPXauNdrSy92.a8jutuO');
INSERT INTO shoppingcart_schema.users (username, email, enabled, firstname, lastname, password) VALUES ('UserE', 'dsfds@gmail.com', '1', 'Nitin', 'Baba', '$2a$10$xvmMWIpaC74kOpkFLMlCiuU4rVZghX.6L42EL9F0NGqxxKfVcwG/K');


INSERT INTO  shoppingcart_schema.products (productId, productAuthor, productName, productPrice) VALUES ('6', 'Ram Tripathi', 'Core Java', '100');
INSERT INTO  shoppingcart_schema.products (productId, productAuthor, productName, productPrice) VALUES ('7', 'Tata Bajari', 'J2EE Basics', '200');
INSERT INTO  shoppingcart_schema.products (productId, productAuthor, productName, productPrice) VALUES ('8', 'Rushi Kapoor', 'Java Design Patterns', '300');


INSERT INTO  shoppingcart_schema.productdetails (id, productId, productQuantity, productTotalPrice) VALUES ('1','6', '3', '300');
INSERT INTO  shoppingcart_schema.productdetails (id, productId, productQuantity, productTotalPrice) VALUES ('2','7', '4', '800');
INSERT INTO  shoppingcart_schema.productdetails (id, productId, productQuantity, productTotalPrice) VALUES ('3','8', '2', '600');


INSERT INTO  shoppingcart_schema.orderdetails (orderId,orderDate, orderprice, username) VALUES ('1','2019-02-09 09:06:35', '1700', 'UserD');

INSERT INTO  shoppingcart_schema.order_product (order_id,product_id) VALUES ('1','1');
INSERT INTO  shoppingcart_schema.order_product (order_id,product_id) VALUES ('1','2');
INSERT INTO  shoppingcart_schema.order_product (order_id,product_id) VALUES ('1','3');