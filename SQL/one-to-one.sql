DROP TABLE IF EXISTS person CASCADE;
DROP TABLE IF EXISTS profiles CASCADE;

create table person(
    person_id serial primary key,
	name varchar(255),
	biometrics_data varchar(255),
	eye_color varchar(255),
	childs int
);

create table profiles(
    profile_id serial primary key,
	phone_number int,
    email varchar(255),
	inn int,
	person_id int references person(person_id) unique
);



