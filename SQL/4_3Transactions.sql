create table products
(
    id    serial primary key,
    name  varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, count, price)
VALUES ('product_1', 3, 50);
insert into products (name, count, price)
VALUES ('product_2', 15, 32);
insert into products (name, count, price)
VALUES ('product_3', 8, 115);

drop table products;

set session transaction isolation level read uncommitted;
begin
select * from products
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
rollback
end


begin transaction;
select * from products
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
commit;

begin transaction isolation level repeatable read;
select * from products
insert into products (name, count, price) VALUES ('product_4', 11, 64);
delete from products where price = 115;
update products set price = 75 where name = 'product_1';
commit


set session transaction isolation level serializable;
begin
select sum(count) from products;
update products set count = 26 where name = 'product_1';
commit
