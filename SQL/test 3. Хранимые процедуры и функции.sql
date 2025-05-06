create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);


create
or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql'
as $$
    BEGIN
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    END
$$;

create
or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql'
as $$
    BEGIN
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
        end if;
        if
            tax > 0 THEN
            update products
            set price = price + price * tax;
        end if;
    END;
$$;

create
or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql'
as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

create
or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql'
as
$$
    declare
        result integer;
    begin
        if u_count > 0 THEN
            update products
            set count = count - u_count
            where id = u_id;
            select into result count
            from products
            where id = u_id;
        end if;
        if tax > 0 THEN
            update products
            set price = price + price * tax;
            select into result sum(price)
            from products;
        end if;
        return result;
    end;
$$;

call insert_data('product_2', 'producer_2', 15, 32);
call update_data(10, 0, 1);
call insert_data('product_1', 'producer_1', 3, 50);
call insert_data('product_3', 'producer_3', 8, 115);
call update_data(0, 0.2, 0);

select f_insert_data('product_1', 'producer_1', 25, 50);
select f_update_data(10, 0, 1);
select f_insert_data('product_2', 'producer_2', 15, 32);
select f_insert_data('product_3', 'producer_3', 8, 115);

create
or replace procedure decrease_data(u_count integer, u_id integer)
language 'plpgsql'
as $$
    BEGIN
		update products	
		set count = count - u_count
		where id = u_id; 
        if (select count from products where id = u_id) <= 0 THEN
			delete from products
			where id = u_id; 
        end if;
    END;
$$;

call decrease_data(100, 1)
SELECT * from products


create
or replace function f_decrease_data(u_count integer, u_id integer)
returns int
language 'plpgsql'
as $$
    declare
        result integer;
    BEGIN
		update products	
		set count = count - u_count
		where id = u_id; 
		select into result count
		from products
		where id = u_id;
        if result <= 0 THEN
			delete from products
			where id = u_id; 
        end if;
		return result;
    END;
$$;

select f_decrease_data(100, 2
SELECT * FROM products