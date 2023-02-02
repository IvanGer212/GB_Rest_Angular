CREATE TABLE category (
    id bigserial primary key,
    title varchar not null,
    parent_category bigint references category(id),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

INSERT INTO category (title) VALUES
                                    ('food'),
                                    ('electronics'),
                                    ('dress'),
                                    ('furniture');


CREATE TABLE products (
                         id bigserial primary key,
                         title varchar not null,
                         price int not null,
                         category_id bigint references category (id),
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp
);

INSERT INTO products (title, price) VALUES
                                       ('Product 1', 100),
                                       ('Product 2', 101),
                                       ('Product 3', 102),
                                       ('Product 4', 103),
                                       ('Product 5', 104),
                                       ('Product 6', 105),
                                       ('Product 7', 106),
                                       ('Product 8', 107),
                                       ('Product 9', 108),
                                       ('Product 10', 109);