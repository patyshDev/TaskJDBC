
#select * from user;
#select * from user where name = 'Steve';
#update user set name = 'Steve', lastname = 'Jobs', age = 55 where id = 2;
#delete from user where id = 4;
#select * from user;
#create table if not exists users
#(
#    id bigint auto_increment primary key,
#    name varchar(45) not null,
#    lastname varchar(45) not null,
#    age tinyint not null
#);
#insert into users (name, lastname, age) values ('Steve', 'JOB', 55);
#insert into users (name, lastname, age) values ('Steve', 'JOB', 55);
##insert into users (name, lastname, age) values ('Steve', 'JOB', 55);
#insert into users (name, lastname, age) values ('Steve', 'JOB', 55);
#delete from users where id = 5;
#drop table if exists users;