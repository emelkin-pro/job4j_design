create table products
(
    id       serial primary key,
    name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function add_tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price * 1.2
        where id in (
			select id
			from inserted
		);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure add_tax();


create
or replace function add_tax_before()
    returns trigger as
$$
    BEGIN
        NEW.price := NEW.price * 1.2;
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger_before
    before insert
    on products
    for each row
    execute procedure add_tax_before();

create table history_of_price
(
    id    serial primary key,
    name  varchar(50),
    price integer,
    date  timestamp
);


create
or replace function add_to_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (name,price,date)
		values(NEW.name,NEW.price, NOW());
		return NEW;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history
	after insert 
	on products
	for each row
	execute procedure add_to_history();


INSERT INTO products (name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50), ('product_2', 'producer_1', 10, 50)
,('product_3', 'producer_1', 12, 50),('product_5', 'producer_2', 9, 50);

SELECT * FROM products;
SELECT * FROM history_of_price;
