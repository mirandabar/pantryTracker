CREATE TABLE IF NOT EXISTS users (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_name TEXT NOT NULL UNIQUE,
    email TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    id integer,
    category varchar(255),
    expiration_date varchar(255),
    price float,
    product_name varchar(255),
    purchase_date varchar(255),
    quantity integer,
    user_name varchar(255), user_id bigint,
    primary key (id)
);
        