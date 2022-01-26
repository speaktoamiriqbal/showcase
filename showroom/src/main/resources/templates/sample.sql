create table cars
(
    id                      integer not null,
    active                  char(1),
    created_at              timestamp,
    description             varchar(50),
    modified_at             timestamp,
    registration_date       date,
    registration_id         varchar(255),
    manufacturing_detail_id integer not null,
    primary key (id)
)