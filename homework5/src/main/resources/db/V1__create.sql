CREATE TABLE providers(
    title VARCHAR(50) PRIMARY KEY,
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
    provider VARCHAR(50) NOT NULL,

    FOREIGN KEY (provider) REFERENCES providers(title)
);

CREATE TABLE invoice_data(
    id SERIAL PRIMARY KEY,
    invoice_id INTEGER NOT NULL,
    product INTEGER NOT NULL,
    price INTEGER NOT NULL,
    amount INTEGER NOT NULL,

    FOREIGN KEY (invoice_id) REFERENCES invoice_info(id),
    FOREIGN KEY (product) REFERENCES products(id)
);
