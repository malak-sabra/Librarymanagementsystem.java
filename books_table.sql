create database library;
create table books
(
   id varchar(255) PRIMARY KEY
);
alter table books 
add Author varchar (255);
select * from books