DROP TABLE IF EXISTS exercise_records;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    age INTEGER,
    gender INTEGER
);

CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    mets NUMERIC(4,1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE exercise_records (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    date DATE NOT NULL,
    time INTEGER NOT NULL,
    burn_calorie NUMERIC(6,1) NOT NULL,
    weight NUMERIC(5,1) NOT NULL,
    progress INTEGER DEFAULT 0,
    memo TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);
