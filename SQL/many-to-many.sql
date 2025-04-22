drop table IF EXISTS contacts CASCADE;
drop table IF EXISTS deals CASCADE;
drop table IF EXISTS deals_contacts CASCADE;

create table contacts (
    contact_id serial primary key,
	email varchar(255),
	phone int,
	name varchar(255)
);

create table deals (
    deal_id serial primary key,
	product_id int,
	amount_rub int,
	date_to_purhase timestamp
);

create table deals_contacts(
	id serial primary key,
    deal_id int references deals(deal_id),
    contact_id int references contacts(contact_id)
);