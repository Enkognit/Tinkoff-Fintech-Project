--liquibase formatted sql

--changeset bot:1
create table chats
(
    id     serial not null primary key,
    name   text not null
);

--changeset bot:2
create table links
(
    id     serial not null primary key,
    chat_id int  not null references chats(id),
    link    text not null
);