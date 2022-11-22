CREATE TABLE providers(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    TIN VARCHAR(15) NOT NULL,
    payment_account VARCHAR(20) NOT NULL
);

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    title VARCHAR(50) NOT NULL,
    variety VARCHAR(50) NOT NULL
);

CREATE TABLE invoice_info(
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    provider INTEGER,

    FOREIGN KEY (provider) REFERENCES providers(id) ON DELETE SET NULL
);

CREATE TABLE invoice_data(
    id SERIAL PRIMARY KEY,
    invoice_id INTEGER,
    product INTEGER,
    price INTEGER NOT NULL,
    amount INTEGER NOT NULL,

    FOREIGN KEY (invoice_id) REFERENCES invoice_info(id) ON DELETE SET NULL,
    FOREIGN KEY (product) REFERENCES products(id) ON DELETE SET NULL
);
