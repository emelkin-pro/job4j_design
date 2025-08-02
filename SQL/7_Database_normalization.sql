drop table IF EXISTS orders CASCADE;
drop table IF EXISTS clients CASCADE;
drop table IF EXISTS films CASCADE;

create table clients 
(
	id	serial primary key,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	adres VARCHAR(100),
	sex VARCHAR(50)
);

create table films 
(
	id	serial primary key,
	film_name VARCHAR(50)
);

create table orders
(
	id serial primary key,
	id_film int references films(id),
	id_clients int references clients(id)
	
);

INSERT INTO clients (first_name, last_name, adres, sex) VALUES
('Ольга', 'Егорова', 'ул. 1-й казанский переулок', 'женский'),
('Сергей', 'Иванов', 'ул. Центральная', 'мужской');

INSERT INTO films (film_name) VALUES
('Пираты карибского моря'),
('Матрица: Революция'),
('Человек, который изменил все'),
('Интерстелар');

INSERT INTO orders (id_film, id_clients) VALUES
(1, 1),  -- Ольга — Пираты карибского моря
(2, 1),  -- Ольга — Матрица: Революция
(3, 2),  -- Сергей — Человек, который изменил все
(4, 2),  -- Сергей — Интерстелар
(2, 2);  -- Сергей — Матрица: Революция (повторный заказ)


SELECT 
		CONCAT(c.first_name, ' ', c.last_name) AS "Полное Имя",
		c.adres AS "Адрес",
		STRING_AGG(f.film_name, '; ') AS "СписокФильмовВзаказе",
		c.sex AS "Пол"

FROM orders AS o
JOIN clients c ON o.id_clients = c.id
JOIN films f ON o.id_film = f.id

GROUP BY
	c.first_name, c.last_name, c.adres, c.sex

