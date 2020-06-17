-- drop table food;
-- drop table menu;
-- drop table restaurant;
-- drop table company;

-- Food Table
create table if not exists food
(
    id            uuid    not null
        constraint food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    vegetable     boolean,
    price         numeric,
    image_url     varchar(255)
);

alter table food
    owner to postgres;

-- Food Table
create table if not exists food
(
    id            uuid    not null
        constraint food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    vegetable     boolean,
    price         numeric,
    image_url     varchar(255)
);

alter table food
    owner to postgres;

-- Menu Table
create table if not exists menu
(
    id            uuid    not null
        constraint food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    vegetable     boolean,
    price         numeric,
    image_url     varchar(255)
);

alter table food
    owner to postgres;

-- Restaurant Table
create table if not exists restaurant
(
    id            uuid    not null
        constraint food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    vegetable     boolean,
    price         numeric,
    image_url     varchar(255)
);

alter table food
    owner to postgres;

-- Company Table
create table if not exists company
(
    id            uuid    not null
        constraint food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    vegetable     boolean,
    price         numeric,
    image_url     varchar(255)
);

alter table company
    owner to postgres;

INSERT INTO public.food (id, creation_time, update_time, status_type, deleted, deletion_time, name, vegetable)
VALUES ('e304ab71-ae13-4224-a021-0c216ac2af76', '2020-06-15 23:00:55.504000', '2020-06-15 23:00:55.504000', 'CREATED',
        false, null, 'Som Tam', true);