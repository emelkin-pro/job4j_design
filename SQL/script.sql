create table procurement(
	id serial primary key,
	purchase_ date,
	name text,
	count integer
);

insert into procurement(name, count, purchase_date) values('Яблоки', '10', '2025-05-01');

update procurement set name = 'Яблоки'

delete from procurement;

SELECT * 
FROM procurement

