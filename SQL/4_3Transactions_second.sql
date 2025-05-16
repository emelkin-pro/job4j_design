set session transaction isolation level serializable;
begin
select sum(count) from products;
update products set count = 26 where name = 'product_2';
commit

begin transaction isolation level repeatable read;
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
commit

select * from products;
