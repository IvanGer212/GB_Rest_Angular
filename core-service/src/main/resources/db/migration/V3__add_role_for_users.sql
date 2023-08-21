CREATE TABLE roles (
                      id bigserial primary key,
                      name VARCHAR (50) not null,
                      created_at timestamp default current_timestamp,
                      updated_at timestamp default current_timestamp

);

CREATE TABLE  user_role (
                                user_id integer REFERENCES users(id),
                                role_id integer REFERENCES roles(id),
                                primary key (user_id, role_id)
);

INSERT INTO roles (name) VALUES
                            ('ROLE_USER'),
                            ('ROLE_ADMIN');


INSERT INTO user_role (user_id, role_id) VALUES
                                                     (1,1),
                                                     (2,1),
                                                     (3,1),
                                                     (4,2),
                                                     (5,1);