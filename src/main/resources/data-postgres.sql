INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');


INSERT INTO address (address_id, city, country, number, postcode, street) values (0, 'Novi Sad', 'Serbia', '20',
'21000', 'Janka Cmelika');


INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date) values ('CLINIC_CENTER_ADMIN', -2, 'admin@gmail.com', 'a',
'$2y$12$2ZStaKA9GlgA2wDAbEivwuHukYAiQMCfH7XNE4KK8GPh6nBFJW6iq', '0643325333', 'a', null, 0,
'2019-12-01 09:00:01');

INSERT INTO user_authority (person_id, authority_id) values  (-2, 5);
