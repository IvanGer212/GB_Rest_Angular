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
                         price numeric(8,2) not null,
                         category_id bigint references category (id),
                         created_at timestamp default current_timestamp,
                         updated_at timestamp default current_timestamp
);

INSERT INTO products (title, price, category_id) VALUES
                                       ('Product 1', 100.00, 1),
                                       ('Product 2', 101.00, 2),
                                       ('Product 3', 102.00, 3),
                                       ('Product 4', 103.00, 4),
                                       ('Product 5', 104.00, 1),
                                       ('Product 6', 105.00, 1),
                                       ('Product 7', 106.00, 2),
                                       ('Product 8', 107.00, 4),
                                       ('Product 9', 108.00, 4),
                                       ('Product 10', 109.00, 3);