CREATE TABLE orders (
                        id bigserial primary key,
                        user_id bigint not null references users(id),
                        cost numeric(8,2) not null,
                        address varchar(255),
                        phone varchar(255),
                        created_at timestamp default current_timestamp,
                        updated_at timestamp default current_timestamp
);


CREATE TABLE  order_items (
                             id bigserial primary key,
                             product_id bigint not null references products(id),
                             order_id bigint not null references orders(id),
                             quantity int not null,
                             price_per_product numeric(8,2) not null,
                             cost numeric(8,2) not null,
                             created_at timestamp default current_timestamp,
                             updated_at timestamp default current_timestamp
);