CREATE TABLE customer
(
    first_name text,
    last_name  text,
    status text
);

CREATE TABLE employee
(
    first_name text,
    last_name  text,
    emp_status text
);
CREATE TABLE referrer
(
    first_name text,
    last_name  text
);


INSERT INTO customer
VALUES ('Иван', 'Иванов', 'Active'),
       ('Петр', 'Сергеев', 'Active'),
       ('Ирина', 'Бросова', 'Active'),
       ('Анна', 'Опушкина', 'Active'),
       ('Потап', 'Потапов', 'Passive');

INSERT INTO employee
VALUES ('Кристина', 'Позова', 'Current'),
       ('Михаил', 'Кругов', 'Current'),
       ('Анна', 'Опушкина', 'Current'),
       ('Иван', 'Иванов', 'Current'),
       ('Сергей', 'Петров', 'Current');

INSERT INTO referrer
VALUES ('Евгений', 'Онегин'),
       ('Петр', 'Сергеев'),
       ('Александр', 'Ожегов'),
       ('Анна', 'Опушкина'),
       ('Михаил', 'Кругов');


SELECT first_name, last_name
FROM customer
WHERE status = 'Active'
UNION
SELECT first_name, last_name
FROM employee
WHERE emp_status = 'Current'
ORDER BY first_name, last_name;

SELECT first_name, last_name
FROM customer
UNION ALL
SELECT first_name, last_name
FROM employee;


SELECT first_name, last_name
FROM customer
EXCEPT
SELECT first_name, last_name
FROM employee;


SELECT first_name, last_name
FROM customer
UNION
SELECT first_name, last_name
FROM employee
UNION
SELECT first_name, last_name
FROM referrer
ORDER BY first_name, last_name;