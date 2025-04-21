insert into roles(id,name) values(1,'admin');
insert into roles(id,name) values(2,'accountant');
insert into roles(id,name) values(3,'manager');

insert into rules(id,name) values(1,'admin panel');
insert into rules(id,name) values(2,'check');
insert into rules(id,name) values(3,'CRM');

insert into roules_rules(id,role_id,rule_id) values(1,1,1);
insert into roules_rules(id,role_id,rule_id) values(2,1,2);
insert into roules_rules(id,role_id,rule_id) values(3,1,3);
insert into roules_rules(id,role_id,rule_id) values(4,2,2);
insert into roules_rules(id,role_id,rule_id) values(5,3,3);



insert into users(id,name,role_id) values(1,'Oleg',1);
insert into users(id,name,role_id) values(2,'Boris',2);
insert into users(id,name,role_id) values(3,'Anna',3);

insert into categories(id,name) values(1,'to ask');
insert into categories(id,name) values(2,'to buy');
insert into categories(id,name) values(3,'spam');

insert into states(id,name) values(1,'To start');
insert into states(id,name) values(2,'In progress');
insert into states(id,name) values(3,'Finished');

insert into items(id,name,user_id,item) values(1,'i want to buy',3,2,1);
insert into items(id,name,user_id,category_id,state_id) values(2,'spam',1,3,3);
insert into items(id,name,user_id,category_id,state_id) values(3,'to pay',2,1,2);

insert into attachs(id,name,item_id) values(1,'url1',1);
insert into attachs(id,name,item_id) values(2,'url2',2);
insert into attachs(id,name,item_id) values(3,'url3',3);

insert into comments(id,name,item_id) values(1,'lol',1);
insert into comments(id,name,item_id) values(2,'kek',2);
insert into comments(id,name,item_id) values(3,'lolkek',3);