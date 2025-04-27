CREATE TABLE car_bodies (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE car_engines (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE car_transmissions (
  id INT PRIMARY KEY,
  name VARCHAR(255)
);

CREATE TABLE cars (
  id INT PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  body_id INT REFERENCES car_bodies(id),
  engine_id INT REFERENCES car_engines(id),
  transmission_id INT REFERENCES car_transmissions(id)
);

INSERT INTO car_bodies (id, name) VALUES
(1, 'Седан'),
(2, 'Хэтчбек'),
(3, 'Универсал'),
(4, 'Купе'),
(5, 'Кроссовер'),
(6, 'Минивэн'),
(7, 'Пикап'),
(8, 'Лифтбек'),
(9, 'Кабриолет'),
(10, 'Родстер');

INSERT INTO car_engines (id, name) VALUES
(1, 'Бензиновый 1.6L'),
(2, 'Бензиновый 2.0L'),
(3, 'Дизельный 2.2L'),
(4, 'Электрический мотор'),
(5, 'Гибридный 1.8L'),
(6, 'Бензиновый 3.0L'),
(7, 'Дизельный 3.0L'),
(8, 'Электрический 75 kWh'),
(9, 'Гибридный 2.5L'),
(10, 'Газовый 2.0L');

INSERT INTO car_transmissions (id, name) VALUES
(1, 'Механика 5-ступенчатая'),
(2, 'Автомат 6-ступенчатый'),
(3, 'Вариатор'),
(4, 'Робот DSG'),
(5, 'Электропередача'),
(6, 'Автомат 8-ступенчатый'),
(7, 'Механика 6-ступенчатая'),
(8, 'Автомат CVT'),
(9, 'Робот AMT'),
(10, 'Электрическая трансмиссия');


INSERT INTO cars (id, name, body_id, engine_id, transmission_id) VALUES
(1, 'Toyota Corolla', 1, 1, 2),
(2, 'Honda Civic', 2, 1, 1),
(3, 'Volkswagen Passat', 3, 2, 2),
(4, 'BMW 3 Series', 1, 2, 6),
(5, 'Audi A5', 4, 6, 6),
(6, 'Tesla Model S', 9, 4, 5),
(7, 'Nissan Leaf', 2, 4, 10),
(8, 'Ford Focus', 2, 1, 1),
(9, 'Subaru Outback', 3, 3, 2),
(10, 'Kia Sportage', 5, 3, 2),
(11, 'Mazda 6', 8, 2, 2),
(12, 'Chevrolet Tahoe', 7, 7, 6),
(13, 'Hyundai Solaris', 1, 1, 2),
(14, 'Lada Vesta', 1, 1, 1),
(15, 'Porsche 911', 4, 6, 4),
(16, 'Lexus RX', 5, 9, 2),
(17, 'Peugeot 3008', 5, 5, 2),
(18, 'Skoda Octavia', 8, 2, 2),
(19, 'BMW i3', 2, 8, NULL), 
(20, 'Audi e-tron', NULL, 8, 5); 

-- 1
SELECT c.id, c.name, cb.name, ce.name, ct.name
FROM cars c
LEFT JOIN car_bodies cb on c.body_id = cb.id
LEFT JOIN car_engines ce on c.engine_id = ce.id
LEFT JOIN car_transmissions ct on c.transmission_id = ct.id

--2 
SELECT cb.id, cb.name
FROM car_bodies cb
LEFT JOIN cars c on c.body_id = cb.id
WHERE c.body_id IS NULL

--3
SELECT ce.id, ce.name
FROM car_engines ce
LEFT JOIN cars c on c.engine_id = ce.id
WHERE c.engine_id IS NULL

--4
SELECT ct.id, ct.name
FROM car_transmissions ct
LEFT JOIN cars c on c.transmission_id = ct.id
WHERE c.engine_id IS NULL