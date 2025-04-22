create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name,avg_age,discovery_date)
values ('fish',10,'1990-09-20');
insert into fauna(name,avg_age,discovery_date)
values ('cat',12,'900-01-01');
insert into fauna(name,avg_age,discovery_date)
values ('dog',20,'0090-02-02');
insert into fauna(name,avg_age,discovery_date)
values ('cool lizard',15000,'2020-08-20');
insert into fauna(name,avg_age,discovery_date)
values ('Teenage Mutant Turtles',20000,'1994-02-17');
insert into fauna(name,avg_age)
values ('lion',30);
insert into fauna(name,avg_age)
values ('snakes',5);

SELECT * 
FROM fauna
WHERE name like '%fish%'

SELECT * 
FROM fauna
WHERE avg_age between '10000' and '21000'

SELECT * 
FROM fauna
WHERE avg_age between '10000' and '21000'


SELECT * 
FROM fauna
WHERE discovery_date is NULL


SELECT * 
FROM fauna
WHERE discovery_date < '1950-01-01'


