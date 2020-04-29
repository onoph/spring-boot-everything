INSERT INTO test_db.role (role_id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO test_db.role (role_id, name) VALUES (2, 'ROLE_USER');

INSERT INTO test_db.user (user_id, email, username, password)
VALUES (1, 'admin@admin.com', 'admin', '$2a$10$7cl09mWSRXUlc2IqEIhWh.TBOPbbodLvMb7vmksW1Jn//BMQTYAzu');

INSERT INTO test_db.user_role (user_id, role_id)
VALUES (1, 1);