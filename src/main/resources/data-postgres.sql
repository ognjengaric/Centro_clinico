INSERT INTO address (address_id, city, country, number, postcode, street) values (1, 'Novi Sad', 'Serbia', '27',
'21000', 'Jovana Ducica');

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date) values ('PATIENT', 1, 'ognjengari@gmail.com', 'Ognjen',
'$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '0621409979', 'Garic', '2108379121', 1,
'2019-12-01 09:00:01');

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');

INSERT INTO user_authority (person_id, authority_id) values  (1, 1);