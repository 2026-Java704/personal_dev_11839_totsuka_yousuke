INSERT INTO users(name, password, age, gender)
VALUES ('demo', 'demo', 20, 0);

INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), '縄跳び', 9.0);
INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), 'ブルガリアンスクワット', 7.0);
INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), 'スクワット', 5.0);
INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), 'プランク', 4.0);
INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), '腕立て伏せ', 4.0);
INSERT INTO events(user_id, name, mets)
VALUES ((SELECT id FROM users WHERE name = 'demo'), '腹筋', 4.0);

INSERT INTO exercise_records(user_id, event_id, date, time, burn_calorie, weight, progress, memo)
VALUES (
	(SELECT id FROM users WHERE name = 'demo'),
	(SELECT id FROM events WHERE user_id = (SELECT id FROM users WHERE name = 'demo') AND name = '腕立て伏せ'),
	'2025-12-31',
	30,
	283.5,
	60.0,
	2,
	'サンプルデータ'
);
