CREATE TABLE places
(
    id    serial primary key,
    place int not null UNIQUE
);

CREATE TABLE accounts
(
    id           serial primary key,
    name         TEXT not null,
    phone_number int  not null,
    placeId     int  not null UNIQUE references places (id)
);