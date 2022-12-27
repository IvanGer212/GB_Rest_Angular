
CREATE TABLE role (
    id bigserial primary key,
    name VARCHAR (50) not null
);

CREATE TABLE  customer_role (
    customer_id integer REFERENCES customer(id),
    role_id integer REFERENCES role(id),
    primary key (customer_id, role_id)
    );

INSERT INTO role (name) VALUES
('ROLE_USER'),
('ROLE_ADMIN');


INSERT INTO customer_role (customer_id, role_id) VALUES
(1,1),
(2,1),
(3,1),
(4,2),
(5,1);
