CREATE TABLE customer (
    id serial primary key,
    name text not null
);

CREATE TABLE  product_customer (
    product_id integer REFERENCES product(id),
    customer_id integer REFERENCES customer(id)
    );

INSERT INTO customer (name) VALUES
('John'),
('Jack'),
('Pit'),
('Liza'),
('Mary');

INSERT INTO product_customer (product_id, customer_id) VALUES
(1,1),
(2,1),
(2,2),
(3,1),
(3,4),
(4,5),
(4,3),
(5,1),
(6,3),
(7,5),
(8,2),
(9,4),
(10,2);
