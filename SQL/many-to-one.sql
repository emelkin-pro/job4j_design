DROP TABLE IF EXISTS clients CASCADE;
DROP TABLE IF EXISTS metrica_visit CASCADE;

create table clients(
    client_id serial primary key,
    user_agent varchar(255),
	date_first_visit timestamp,
	count_of_visit int,
	average_visits_time int
);

create table metrica_visit(
    visit_id serial primary key,
	ip_adress cidr,
	utm_source varchar(255),
	utm_medium varchar(255),
	visit_time timestamp,
	client_id int references clients(client_id)
);
