create database if not exists lr20190024_ps;

use lr20190024_ps;

create table if not exists clients (
	id bigint unsigned not null auto_increment,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null,
	phone varchar(20) not null,
	address varchar(255) not null,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp,
	primary key (id)
);

create table if not exists brokers (
	id bigint unsigned not null auto_increment,
	first_name varchar(100) not null,
	last_name varchar(100) not null,
	email varchar(100) not null,
	phone varchar(20) not null,
        password varchar(255) not null,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp,
	
	primary key (id)
);

create table if not exists products (
	id bigint unsigned not null auto_increment,
	title varchar(255) not null,
	
	primary key (id)
);


create table if not exists product_features (
	id bigint unsigned not null auto_increment,
	product_id bigint unsigned not null,
	title varchar(255) not null,
	val text not null,
	foreign key (product_id) references products(id) on delete cascade,
	
	primary key (id)
);

create table if not exists deals (
	id bigint unsigned not null auto_increment,
	client_id bigint unsigned not null,
	broker_id bigint unsigned not null,
	product_id bigint unsigned not null,
	deal_value double unsigned,
	deal_status varchar(10) not null,
	description text,
	status_updated_at timestamp,
	created_at timestamp not null default current_timestamp,
	updated_at timestamp not null default current_timestamp,
	foreign key (client_id) references clients(id) on delete restrict,
	foreign key (broker_id) references brokers(id) on delete restrict,
	foreign key (product_id) references products(id) on delete restrict,
	
	primary key (id)
);


insert into products(title) values('Usluga 1');
insert into products(title) values('Usluga 2');
insert into products(title) values('Usluga 3');
insert into products(title) values('Usluga 4');
insert into products(title) values('Usluga 5');

insert into product_features(product_id,title,val) values(1, 'Karakteristika 1', 'Opis karakteristike 1');
insert into product_features(product_id,title,val) values(1, 'Karakteristika 2', 'Opis karakteristike 2');
insert into product_features(product_id,title,val) values(1, 'Karakteristika 3', 'Opis karakteristike 3');
insert into product_features(product_id,title,val) values(2, 'Karakteristika 1', 'Opis karakteristike 1');
insert into product_features(product_id,title,val) values(2, 'Karakteristika 2', 'Opis karakteristike 2');
insert into product_features(product_id,title,val) values(2, 'Karakteristika 3', 'Opis karakteristike 3');
insert into product_features(product_id,title,val) values(3, 'Karakteristika 1', 'Opis karakteristike 1');
insert into product_features(product_id,title,val) values(3, 'Karakteristika 2', 'Opis karakteristike 2');
insert into product_features(product_id,title,val) values(3, 'Karakteristika 3', 'Opis karakteristike 3');
insert into product_features(product_id,title,val) values(4, 'Karakteristika 1', 'Opis karakteristike 1');
insert into product_features(product_id,title,val) values(4, 'Karakteristika 2', 'Opis karakteristike 2');
insert into product_features(product_id,title,val) values(4, 'Karakteristika 3', 'Opis karakteristike 3');
insert into product_features(product_id,title,val) values(5, 'Karakteristika 1', 'Opis karakteristike 1');
insert into product_features(product_id,title,val) values(5, 'Karakteristika 2', 'Opis karakteristike 2');
insert into product_features(product_id,title,val) values(5, 'Karakteristika 3', 'Opis karakteristike 3');
