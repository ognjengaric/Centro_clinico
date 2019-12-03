INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');

INSERT INTO address (address_id, city, country, number, postcode, street) values (0, 'Novi Sad', 'Serbia', '20',
'21000', 'Janka Cmelika');

INSERT INTO clinic (clinic_id, description, name, address) values (0, 'Klinika broj 1', 'Klinika1', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', 0, 'alecar97@gmail.com', 'Aleksa',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Lukac', null, 0,
'2019-12-01 09:00:01', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, doctor_clinic, avgrating) values ('DOCTOR', 1, 'a@gmail.com', 'a',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'a', null, 0,
'2019-12-01 09:00:01', 0, 1.5);

INSERT INTO user_authority (person_id, authority_id) values  (0, 4);