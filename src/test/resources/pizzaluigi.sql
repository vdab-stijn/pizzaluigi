set names utf8mb4;
set charset utf8mb4;

drop database if exists pizzaluigi;
CREATE DATABASE pizzaluigi charset utf8mb4;
use pizzaluigi;


CREATE TABLE IF NOT EXISTS pizzas (
  id int NOT NULL AUTO_INCREMENT primary key,
  name varchar(45) DEFAULT NULL,
  price decimal(10,2) DEFAULT NULL,
  spicy tinyint DEFAULT NULL
);

INSERT INTO pizzas(name,price,spicy) VALUES ('Prosciutto',4.00,1),('Margheritta',5.00,0),('Calzone',4.00,0),('Funghi & Olive',5.00,0);

create user if not exists cursist identified by 'cursist';
grant select,insert,update,delete on pizzas to cursist;