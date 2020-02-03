INSERT INTO start_end_time (time_id, start_time, end_time) values (-1, '07:00:00', '15:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-2, '09:00:00', '10:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-3, '09:00:00', '10:00:00');

INSERT INTO address (address_id, city, country, number, postcode, street) values (-1, 'Novi Sad', 'Serbia', '27',
'21000', 'Jovana Ducica');

INSERT INTO address (address_id, city, country, number, postcode, street) values (0, 'Novi Sad', 'Serbia', '20',
'21000', 'Janka Cmelika');

INSERT INTO clinic (clinic_id, description, name, address) values (0, 'Opis klinike', 'Klinika', 0);

INSERT INTO room (room_id, rooms_clinic, name) values (-1,0,'Room-1');

INSERT INTO checkup_type(checkuptype_id, duration, checkuptypes_clinic, name, price) values (-1,15, 0, 'ORL', 0);


INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, activated) values ('PATIENT', -2, 'patient@gmail.com', 'Ognjen',
'$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '0621409979', 'Garic', '2108379121', -1,
'2019-12-01 09:00:01', true);


INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, doctor_clinic, avgrating, specialization_checkuptype_id, shift) values ('DOCTOR', -1, 'doctor@gmail.com', 'Prvi',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Doktor', null, 0,
'2019-12-01 09:00:01', 0, 1.5, -1, -1);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', 0, 'clinic_admin@gmail.com', 'Aleksa',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Lukac', null, 0,
'2019-12-01 09:00:01', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date) values ('CLINIC_CENTER_ADMIN', -3, 'admin@gmail.com', 'a',
'$2y$12$2ZStaKA9GlgA2wDAbEivwuHukYAiQMCfH7XNE4KK8GPh6nBFJW6iq', '0643325333', 'a', null, 0,
'2019-12-01 09:00:01');


INSERT INTO checkup (checkup_id, start_end_time, checkup_room, checkup_clinic, checkup_doctor, is_approved, is_ended, is_started, date, checkup_type) values (-1,-2,null,0,-1, false, false, false, '2020-01-25',-1);
INSERT INTO checkup (checkup_id, start_end_time, checkup_room, checkup_clinic, checkup_doctor, is_approved, is_ended, is_started, date, checkup_type) values (-2,-3,null,0,-1, false, false, false, '2020-01-25',-1);

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');


INSERT INTO user_authority (person_id, authority_id) values  (-3, 5);
INSERT INTO user_authority (person_id, authority_id) values  (0, 4);
INSERT INTO user_authority (person_id, authority_id) values  (-2, 1);
INSERT INTO user_authority (person_id, authority_id) values  (-1, 2);
