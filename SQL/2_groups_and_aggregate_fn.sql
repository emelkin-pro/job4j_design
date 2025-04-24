create table devices
(
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people
(
    id   serial primary key,
    name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(id,name,price) 
values (1,'macbook ', 200000), (2,'HP probook',100000), (3,'Digma notebook',1000)
, (4,'Apple display',100000), (5,'HP display',20000), (6,'Digma display',1000);


insert into people(id,name) 
values (1,'Олег '), (2,'Андрей '), (3,'Анастасия ')
, (4,'Алексей '), (5,'Лео '), (6,'Рома ');

insert into devices_people(id,device_id,people_id) 
values (1,1,1), (2,2,2), (3,3,3), (4,4,4), (5,5,5), (6,6,6)
,(7,1,1), (8,6,2), (9,3,3), (10,2,4), (11,4,5), (12,5,6);

select avg(price) from devices

select p.name, avg(d.price)
FROM devices_people dp
JOIN devices d on dp.device_id = d.id
JOIN people p on dp.people_id = p.id
GROUP by p.name

select p.name, avg(d.price)
FROM devices_people dp
JOIN devices d on dp.device_id = d.id
JOIN people p on dp.people_id = p.id
GROUP by p.name
HAVING avg(d.price) > 5000

