-- ユーザーテーブル
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    password VARCHAR(255) NOT NULL,
    age INTEGER,
    gender INTEGER,
    weight INTEGER
);

-- 運動種目テーブル
CREATE TABLE events (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    name VARCHAR(50) NOT NULL,
    mets NUMERIC(4,1) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

-- 運動記録テーブル
CREATE TABLE exercise_records (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    date DATE NOT NULL,
    time INTEGER NOT NULL,
    burn_calorie INTEGER NOT NULL,
    memo TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (event_id) REFERENCES events(id)
);