
create sequence address_seq start with 1 increment by 50;
create table address
(
    id bigint not null primary key,
    street varchar(50)
);
create sequence phone_seq start with 1 increment by 50;
create table phone
(
    id bigint not null primary key,
    number varchar(50),
    client_id bigint
);

alter table client add column address_id bigint
CONSTRAINT fk_address_id REFERENCES address(id);
