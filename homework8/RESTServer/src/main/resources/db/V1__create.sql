CREATE TABLE firms(
    id SERIAL PRIMARY KEY,
    title VARCHAR(40) NOT NULL
);

CREATE TABLE products(
    id SERIAL PRIMARY KEY,
    title VARCHAR(40) NOT NULL,
    company INTEGER NOT NULL,
    amount INTEGER NOT NULL,

    FOREIGN KEY (company) REFERENCES firms(id) ON DELETE SET NULL
)