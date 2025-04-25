create table type(
	id serial primary key,
	name varchar(255)
);

create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price int
);


insert into type(id,name) values(1,'СЫР'),(2,'МОЛОКО'),(3,'КОФЕ')
,(4,'ХЛЕБ'),(5,'МОРОЖЕНОЕ');

insert into product(id,name,type_id,expired_date,price) 
values(1,'Сыр плавленный',1,'2025-04-24',100),(2,'Сыр моцарелла',1,'2025-07-24',200)
,(3,'Сыр Чедр',1,'2025-05-24',900),(4,'Сыр фета',1,'2025-03-24',120);

insert into product(id,name,type_id,expired_date,price) 
values(5,'Молоко суздальское',2,'2025-04-24',90),(6,'Молоко Ополье',2,'2025-07-24',80)
,(7,'Молоко Простоквашено',2,'2025-01-24',110),(8,'Молоко веселый молочник',2,'2025-02-24',120);

insert into product(id,name,type_id,expired_date,price) 
values(9,'Кофе жардин',3,'2025-08-24',300),(10,'Кофе Черная карта',3,'2025-07-24',200)
,(11,'Кофе Живой',3,'2025-05-24',330),(12,'Кофе Tasty',3,'2025-03-24',900);

insert into product(id,name,type_id,expired_date,price) 
values(13,'Хлеб Черный',4,'2025-04-30',55),(14,'Хлеб Белый',4,'2025-04-24',50)
,(15,'Хлеб Серый',4,'2025-05-24',60),(16,'Хлеб бездрожевой',4,'2025-04-24',80);

insert into product(id,name,type_id,expired_date,price) 
values(17,'Мороженое Грильяж',5,'2025-10-30',100),(18,'Мороженое Ванильное',5,'2025-11-24',90)
,(19,'Мороженое Шоколадное',5,'2025-12-24',110),(20,'Мороженое безлактозное',5,'2025-04-20',200)
,(21,'Фруктоный лед апельсиновый',5,'2025-04-20',200),(22,'мороженое вкусное',5,'2025-04-20',200)
,(23,'Фруктоный лед ягодный',5,'2025-04-20',200),(24,'Фруктоный лед вишневый',5,'2025-04-20',200)
,(25,'Фруктоный лед водяной',5,'2025-04-20',200),(26,'Фруктоный лед арбузный',5,'2025-04-20',200)
,(27,'Мороженое кек',5,'2025-04-20',200),(28,'мороженое искимо',5,'2025-04-20',200);


SELECT *
FROM product p
JOIN type t on p.type_id = t.id
WHERE t.name = 'СЫР';

SELECT *
FROM product p
JOIN type t on p.type_id = t.id
WHERE t.id = 1;

SELECT *
FROM product p
WHERE p.expired_date < NOW();

SELECT p.name, p.price
FROM product p
WHERE p.price = (
  SELECT MAX(price)
  FROM product
);

SELECT t.name, count(*)
FROM product p
JOIN type t on p.type_id = t.id
GROUP by t.name;

SELECT *
FROM product p
JOIN type t on p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО';

SELECT *
FROM product p
JOIN type t on p.type_id = t.id
WHERE t.id = 1 OR t.id = 2;

SELECT t.name, count(*)
FROM product p
JOIN type t on p.type_id = t.id
GROUP by t.name
HAVING count(*) < 10;

SELECT *
FROM product p
JOIN type t on p.type_id = t.id;
