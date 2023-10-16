create table user
(
    id                                    varchar(36) not null,
    email                                 varchar(255) unique,
    password                              varchar(255),
    primary key (id)
);

INSERT INTO user
(id,
 email,
 password)
VALUES ('3d81dcf8-5170-4d2e-88e3-4a39686b2314',
        'admin@ehsan.io',
        '$2a$10$zfs7M/Aurm2oZizbtzldoe9DB9D06xSc5S50RdvnPn7NSkzsbg1Oq');