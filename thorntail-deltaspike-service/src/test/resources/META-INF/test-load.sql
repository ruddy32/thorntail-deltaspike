-- INSERT INTO sample.role(id, name, description) VALUES (default, 'Test', 'Unit test role');
INSERT INTO sample.role(id, name, description) VALUES (1, 'Test', 'Unit test role');
-- INSERT INTO sample.role(name, description) VALUES ('Test', 'Unit test role');
-- INSERT INTO sample.role(id, name, description) VALUES (nextval('sample.role_id_seq'), 'Test', 'Unit test role');
ALTER SEQUENCE sample.role_id_seq RESTART WITH 2;

-- INSERT INTO sample.user(id, uid, name) VALUES (default, 'test@domain.fr', 'Testeur');
INSERT INTO sample.user(id, uid, name) VALUES (1, 'test@domain.fr', 'Testeur');
-- INSERT INTO sample.user(uid, name) VALUES ('test@domain.fr', 'Testeur');
-- INSERT INTO sample.user(id, uid, name) VALUES (nextval('sample.role_id_seq'), 'test@domain.fr', 'Testeur');
ALTER SEQUENCE sample.user_id_seq RESTART WITH 2;

INSERT INTO sample.user_role_rel(user_id, role_id) VALUES (1, 1);