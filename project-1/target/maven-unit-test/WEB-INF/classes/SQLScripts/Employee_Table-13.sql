drop table if exists employee;

create table employee (
	id serial primary key,
	first_name varchar(50),
	last_name varchar(50),
	username varchar(50),
	pass_word varchar(50)
)

INSERT into employee (first_name, last_name, username, pass_word)
	values('Timmy', 'Maxoff', 'tmax', 'secret');
