CREATE TABLE customer (
                          id bigserial primary key,
                          name VARCHAR (80) not null,
                          password VARCHAR (80) not null
);

CREATE TABLE  product_customer (
                                   product_id integer REFERENCES product(id),
                                   customer_id integer REFERENCES customer(id)
);

INSERT INTO customer (name, password) VALUES
                                          ('John', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm'),
                                          ('Jack', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm'),
                                          ('Pit', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm'),
                                          ('Liza', '$2a$12$Tuscacg3ftxmrpWSJwec5u7zb5Eq2XpvU7p6Xz/RNRkq4CndU0dvO'),
                                          ('Mary', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm');

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