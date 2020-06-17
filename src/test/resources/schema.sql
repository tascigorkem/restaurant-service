drop table menu_food cascade;
drop table food cascade;
drop table menu cascade;
drop table restaurant cascade;
drop table company cascade;

-- Company Table
create table if not exists company
(
    id            uuid    not null
        constraint company_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    address       varchar(255),
    phone         varchar(30),
    email_address varchar(50),
    website_url   varchar(255)
);

alter table company
    owner to postgres;

-- Restaurant Table
create table if not exists restaurant
(
    id             uuid    not null
        constraint restaurant_pk
            primary key,
    creation_time  timestamp,
    update_time    timestamp,
    status_type    varchar(30),
    deleted        boolean not null,
    deletion_time  timestamp,
    name          varchar(255),
    address        varchar(255),
    phone          varchar(30),
    employee_count integer,
    company_id     uuid
        constraint restaurant_company_id_fk
            references company
            on update cascade on delete cascade
);

alter table restaurant
    owner to postgres;

-- Menu Table
create table menu
(
    id            uuid    not null
        constraint menu_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    name          varchar(255),
    menu_type     varchar(255),
    restaurant_id uuid
        constraint menu_restaurant_id_fk
            references restaurant
            on update cascade on delete cascade
);

alter table menu
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

-- Menu - Food Relation Table
create table menu_food
(
    id            uuid    not null
        constraint menu_food_pk
            primary key,
    creation_time timestamp,
    update_time   timestamp,
    status_type   varchar(30),
    deleted       boolean not null,
    deletion_time timestamp,
    menu_id       uuid
        constraint menu_food_menu_id_fk
            references menu,
    food_id       uuid
        constraint menu_food_food_id_fk
            references food
);

alter table menu_food
    owner to postgres;

