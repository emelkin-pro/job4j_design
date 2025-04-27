--1 
CREATE TABLE departments (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE employees (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  department_id INT REFERENCES departments(id)
);


INSERT INTO departments (id, name) VALUES
(1, 'Отдел продаж'),
(2, 'Бухгалтерия'),
(3, 'Отдел маркетинга'),
(4, 'Юридический отдел'),
(5, 'IT-отдел'),
(6, 'Отдел кадров'),
(7, 'Служба поддержки'),
(8, 'Производственный отдел'),
(9, 'Отдел логистики'),
(10, 'Отдел закупок');

INSERT INTO employees (id, name, department_id) VALUES
(1, 'Иван Иванов', 1),
(2, 'Петр Петров', 1),
(3, 'Светлана Смирнова', 2),
(4, 'Мария Кузнецова', 3),
(5, 'Алексей Соколов', 4),
(6, 'Елена Попова', 5),
(7, 'Дмитрий Лебедев', 5),
(8, 'Анна Новикова', 6),
(9, 'Сергей Морозов', 7),
(10, 'Ольга Васильева', 8);

-- 2
SELECT * 
FROM employees e
LEFT JOIN departments d on e.department_id = d.id

SELECT * 
FROM employees e
RIGHT JOIN departments d on e.department_id = d.id

SELECT * 
FROM employees e
FULL JOIN departments d on e.department_id = d.id

SELECT * 
FROM employees e 
CROSS JOIN departments d

-- 3
SELECT * 
FROM departments d
LEFT JOIN employees e ON d.id = e.department_id 
WHERE e.department_id IS NULL

-- 4
SELECT * 
FROM employees e
LEFT JOIN departments d on e.department_id = d.id

SELECT * 
FROM employees e
RIGHT JOIN departments d on e.department_id = d.id
WHERE e.department_id IS NOT NULL

--5
CREATE TABLE teens (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255),
  gender VARCHAR(255)
);

INSERT INTO teens (name, gender) VALUES
('Вася', 'М'),
('Петя', 'М'),
('Коля', 'М'),
('Игорь', 'М'),
('Андрей', 'М'),
('Маша', 'Ж'),
('Оля', 'Ж'),
('Катя', 'Ж'),
('Аня', 'Ж'),
('Таня', 'Ж');

SELECT *
FROM teens t1
CROSS JOIN teens t2
WHERE t1.gender = 'М'
AND t2.gender = 'Ж'
