CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Olga', 'Egorova', 32, 'Russia'),
('Sergey', 'Ivanov', 40, 'Russia'),
('Anna', 'Petrova', 28, 'Russia'),
('Dmitry', 'Sokolov', 35, 'Russia'),
('Marina', 'Kuznetsova', 45, 'Russia'),
('Alexey', 'Smirnov', 22, 'Russia'),
('Elena', 'Popova', 30, 'Russia'),
('Pavel', 'Lebedev', 38, 'Russia'),
('Irina', 'Morozova', 27, 'Russia'),
('Nikolay', 'Kovalev', 50, 'Russia');

INSERT INTO customers (first_name, last_name, age, country) VALUES
('Ivan',   'Petrov',    22, 'Russia'),
('Mikhail','Orlov',     22, 'Russia'),
('Katya',  'Vasilyeva', 22, 'Russia'),
('Sofia',  'Nikolaeva', 22, 'Russia'),
('Roman',  'Zhukov',    22, 'Russia');


INSERT INTO orders (amount, customer_id) VALUES
(1200, 1),
(800, 2),
(1500, 3),
(600, 4),
(2300, 5),
(450, 6),
(980, 7),
(1250, 8),
(700, 9),
(3100, 10);

INSERT INTO orders (amount, customer_id) VALUES
  (500, 1),
  (750, 1),
  (1200, 1),
  (300, 1),
  (999, 1);


SELECT *
FROM customers
WHERE age = (SELECT MIN(age) FROM customers);

SELECT *
FROM customers
WHERE customers.id NOT IN (SELECT customer_id FROM orders GROUP BY customer_id);




