-- Food Table
drop table food;

create table food
(
    id uuid not null
        constraint food_pk
            primary key,
	creation_time timestamp,
	update_time timestamp,
	status_type varchar(30),
	deleted boolean not null,
    deletion_time timestamp,
    name varchar(255),
    vegetable boolean
);

alter table food owner to postgres;

