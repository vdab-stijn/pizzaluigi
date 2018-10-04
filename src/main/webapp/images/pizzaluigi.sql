set names utf8mb4;
set charset utf8mb4;

drop database if exists pizzaluigi;
CREATE DATABASE pizzaluigi charset utf8mb4;
use pizzaluigi;


CREATE TABLE pizzas (
  id int NOT NULL AUTO_INCREMENT primary key,
  naam varchar(45) DEFAULT NULL,
  prijs decimal(10,2) DEFAULT NULL,
  pikant tinyint DEFAULT NULL
);

INSERT INTO pizzas(naam,prijs,pikant) VALUES ('Prosciutto',4.00,1),('Margheritta',5.00,0),('Calzone',4.00,0),('Fungi & Olive',5.00,0);

create user if not exists cursist identified by 'cursist';
grant select,insert,update,delete on pizzas to cursist;