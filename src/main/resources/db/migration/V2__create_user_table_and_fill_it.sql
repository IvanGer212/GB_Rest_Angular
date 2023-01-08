CREATE TABLE users (
                          id bigserial primary key,
                          name VARCHAR (80) not null,
                          surname VARCHAR (255) not null,
                          password VARCHAR (80) not null,
                          email VARCHAR(255) not null,
                          phone VARCHAR(80) not null,
                          created_at timestamp default current_timestamp,
                          updated_at timestamp default current_timestamp
);


INSERT INTO users (name, surname, password, email, phone) VALUES
                                          ('John', 'Philips', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm', 'JohnPilips73@gmail.com', '+79734657412'),
                                          ('Jack', 'Brown','$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm','JackBrown45@mail.ru', '+74591287469'),
                                          ('Peter', 'Murphy','$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm', 'PeterMurphy76@yandex.ru','+78526457825'),
                                          ('Liza','Smith', '$2a$12$Tuscacg3ftxmrpWSJwec5u7zb5Eq2XpvU7p6Xz/RNRkq4CndU0dvO', 'LizaSmith74@rambler.ru','+79641374816'),
                                          ('Mary','James', '$2a$12$I3l/oh2PS.e2rdWHhYuQ6.pirh6r2zCScoho7OoNe30uh86Wvaijm','MaryJames63@gmail.com','+77896523455');
