create table students
(
    id   serial primary key,
    name varchar(50)
);

insert into students (name)
values ('Иван Иванов');
insert into students (name)
values ('Петр Петров');


create table authors
(
    id   serial primary key,
    name varchar(50)
);

insert into authors (name)
values ('Александр Пушкин');
insert into authors (name)
values ('Николай Гоголь');


create table books
(
    id serial primary key,
    name varchar(200),
    author_id integer references authors (id)
);

insert into books (name, author_id)
values ('Евгений Онегин', 1);
insert into books (name, author_id)
values ('Капитанская дочка', 1);
insert into books (name, author_id)
values ('Дубровский', 1);
insert into books (name, author_id)
values ('Мертвые души', 2);
insert into books (name, author_id)
values ('Вий', 2);

create table orders
(
    id serial primary key,
    active boolean default true,
    book_id integer references books (id),
    student_id integer references students (id)
);

insert into orders (book_id, student_id)
values (1, 1);
insert into orders (book_id, student_id)
values (3, 1);
insert into orders (book_id, student_id)
values (5, 2);
insert into orders (book_id, student_id)
values (4, 1);
insert into orders (book_id, student_id)
values (2, 2);



select s.name, count(a.name), a.name
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;

create view show_students_with_2_or_more_books
as
select s.name as student, count(a.name), a.name as author
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;


select * from show_students_with_2_or_more_books;


CREATE VIEW absurdly_heavy_request
AS
SELECT DISTINCT 
    s.id AS student_id,
    s.name AS student_name,
    b.id AS book_id,
    b.name AS book_name,
    a.id AS author_id,
    a.name AS author_name,
    o.id AS order_id,
    CASE
        WHEN o.active = true THEN 'АКТИВНЫЙ'
        ELSE 'НЕАКТИВНЫЙ'
    END AS order_status,
    CASE 
        WHEN b.name LIKE '%дочка%' THEN 'Книга про дочку'
        WHEN b.name LIKE '%души%' THEN 'Книга про души'
		WHEN b.name LIKE '%Онегин%' THEN 'Книга про Женька'
		WHEN b.name LIKE '%Вий%' THEN 'Книга про круги на полу'
        ELSE 'Другое произведение'
    END AS book_topic,
    LENGTH(b.name) AS book_name_length
FROM 
    students s
INNER JOIN orders o ON s.id = o.student_id
INNER JOIN books b ON o.book_id = b.id
INNER JOIN authors a ON b.author_id = a.id
WHERE 
    (s.name LIKE '%Иван%' OR s.name LIKE '%Петр%')
    AND (a.name NOT LIKE '%Толстой%')
    AND (b.name IS NOT NULL)
    AND (o.book_id IS NOT NULL)
GROUP BY 
    s.id, s.name, b.id, b.name, a.id, a.name, o.id, o.active
ORDER BY 
    student_name ASC,
    author_name DESC,
    book_name_length DESC
LIMIT 500;


SELECT * FROM absurdly_heavy_request;