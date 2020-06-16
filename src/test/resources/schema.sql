-- Food Table
drop table food;

create table food
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
    vegetable     boolean
);

alter table food
    owner to postgres;

INSERT INTO public.food (id, creation_time, update_time, status_type, deleted, deletion_time, name, vegetable) VALUES ('e304ab71-ae13-4224-a021-0c216ac2af76', '2020-06-15 23:00:55.504000', '2020-06-15 23:00:55.504000', 'CREATED', false, null, 'Som Tam', true);