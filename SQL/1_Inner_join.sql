create table clients(
	id serial primary key,
	name text,
	phone text,
	email text
);

create table deals(
	id serial primary key,
	name text,
	amount int,
	client_id int
);

insert into clients (id,name,phone,email) values (1,'Oleg','79000000001','Oleg@test.ru');
insert into clients (id,name,phone,email) values (2,'Alexei','79000000002','Alexei@test.ru');
insert into clients (id,name,phone,email) values (3,'Petr','79000000003','Petr@test.ru');

insert into deals (name,amount,client_id) values ('Oleg Deal',1000,1);
insert into deals (name,amount,client_id) values ('Alexei Deal',2000,2);
insert into deals (name,amount,client_id) values ('Petr Deal',70000,3);


Select c.id as "ИД клиента", d.id as "ИД Сделки"
, c.name as "Имя Клиента", d.amount "Сумма"
FROM deals d
JOIN clients c on d.client_id = c.id

Select d.name as "Название сделки", c.name as "Имя Клиента для обзвона", d.amount "Сумма"
, c.phone "Телефон"
FROM deals d
JOIN clients c on d.client_id = c.id
WHERE d.amount > 1500

Select c.name as "Имя для рассылки писем", d.amount "Сумма"
, c.email as "Email для рассылки писем"
FROM deals d
JOIN clients c on d.client_id = c.id
WHERE d.amount < 10000
