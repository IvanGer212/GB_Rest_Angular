CREATE TABLE orders (
                        id bigserial primary key,
                        customer_id bigint not null references customer(id),
                        cost int not null,
                        address varchar(255),
                        phone varchar(255),
                        created_at timestamp default current_timestamp,
                        updated_at timestamp default current_timestamp
);


CREATE TABLE  order_item (
                             id bigserial primary key,
                             product_id bigint not null references product(id),
                             order_id bigint not null references orders(id),
                             quantity int not null,
                             price_per_product int not null,
                             cost int not null,
                             created_at timestamp default current_timestamp,
                             updated_at timestamp default current_timestamp
);