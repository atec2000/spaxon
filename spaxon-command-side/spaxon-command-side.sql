use mysql;
update user set host = '%' where user ='root';
flush privileges;

create database spaxon;

use spaxon;

create table categories (
	id int primary key auto_increment, 
	name varchar(20) 
);

create table products (
	id int primary key auto_increment, 
	name varchar(20), 
	is_saleable int
);

create table category_products (
	id int primary key auto_increment, 
	category_id int not null, 
	product_id int not null
);

create table product_images (
	id int primary key auto_increment, 
	product_id int not null,
	url varchar(50)	not null
);
