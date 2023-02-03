----------------Tables for project--------------

--Users
create table users (
id serial primary key,
first_name VARCHAR(100) not null,
last_name VARCHAR(100) not null,
username VARCHAR(100) not null unique,
password VARCHAR (100) not null,
roles_id INTEGER references roles(id)
);
truncate users 

select id from roles
-- Roles
create table roles (
id serial primary key,
access_ VARCHAR(50) not null
);
insert into roles (access_) values 
('Manager'),
('Employee');


-- Ticket Info
create table tickets (
id serial primary key,
amount numeric not null,
description VARCHAR (500) not null,
categories_id integer references categories(id) not null,
status_id INTEGER references status(id not null,
users_id INTEGER references users(id) not null
);
insert into tickets (id, amount, description, categories_id, status_id, users_id) values (1, 25, 'Motong', 2, 2, 1);
insert into tickets (id, amount, description, categories_id, status_id, users_id) values (2, 25, 'Tubajon', 4, 1, 5);
insert into tickets (id, amount, description, categories_id, status_id, users_id) values (3, 7.1, 'Daqiao', 4, 1, 3);
insert into tickets (id, amount, description, categories_id, status_id, users_id) values (4, 25, 'El Viejo', 4, 1, 4);
insert into tickets (id, amount, description, categories_id, status_id, users_id) values (5, 25, 'Mohelnice', 1, 2, 1);





-- Categories
create table categories (
id serial primary key,
category VARCHAR(50)not null
);

insert into categories (category) values 
('Food'),
('Travel'),
('Equipment'),
('Other');

-- Ticket Status
create table status (
id serial primary key,
ticket_status VARCHAR (50) not null
);
insert into status (ticket_status) values
('Pending'),
('Approved'),
('Denied');


-------------Quick Queries-----------

--get total amount of approved reimbursements
select get_total_amount_reimbursements() as total_reimbursements 
--See all ticket info and user first and last name for managers
select tickets.amount, tickets.description, categories.category, status.ticket_status, users.first_name, users.last_name  from tickets join categories on tickets.categories_id = categories.id join status on status_id = status.id
join users on tickets.users_id = users.id
--See all ticket info and user first and last name for employees
select tickets.amount, tickets.description, categories.category, status.ticket_status, users.first_name, users.last_name  from tickets join categories on tickets.categories_id = categories.id join status on status_id = status.id
join users on tickets.users_id = users.id where

----------------Functions and Procedures--------------------
create or replace function get_total_amount_reimbursements()
returns numeric 
language plpgsql
as 
$$
declare
	total_amount numeric;
begin
	select sum(amount) into total_amount from tickets where status_id = 2;

	return total_amount;
end;
$$;

