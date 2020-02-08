INSERT INTO start_end_time (time_id, start_time, end_time) values (-1, '07:00:00', '15:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-2, '09:00:00', '10:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-3, '09:00:00', '10:00:00');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-1, '2020-01-25', '2020-01-30');

INSERT INTO address (address_id, city, country, number, postcode, street) values (-1, 'Novi Sad', 'Serbia', '27',
'21000', 'Jovana Ducica');

INSERT INTO address (address_id, city, country, number, postcode, street) values (0, 'Novi Sad', 'Serbia', '20',
'21000', 'Janka Cmelika');

INSERT INTO clinic (clinic_id, description, name, address) values (0, 'Opis klinike', 'Klinika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-1, 'Apis klinike', 'Linika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-2, 'Cpis klinike', 'Medica', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-3, 'Dpis klinike', 'Hoga', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-4, 'Rpis klinike', 'Bontia', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-5, 'Npis klinike', 'Komotno', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-6, 'Qpis klinike', 'Jes', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-41, 'Opis klinike', 'Klinika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-21, 'Apis klinike', 'Linika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-22, 'Cpis klinike', 'Medica', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-32, 'Dpis klinike', 'Hoga', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-42, 'Rpis klinike', 'Bontia', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-52, 'Npis klinike', 'Komotno', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-62, 'Qpis klinike', 'Jes', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-72, 'Opis klinike', 'Klinika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-8, 'Apis klinike', 'Linika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-9, 'Cpis klinike', 'Medica', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-10, 'Dpis klinike', 'Hoga', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-11, 'Rpis klinike', 'Bontia', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-12, 'Npis klinike', 'Komotno', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-13, 'Qpis klinike', 'Jes', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-14, 'Opis klinike', 'Klinika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-15, 'Apis klinike', 'Linika', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-16, 'Cpis klinike', 'Medica', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-17, 'Dpis klinike', 'Hoga', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-18, 'Rpis klinike', 'Bontia', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-19, 'Npis klinike', 'Komotno', 0);
INSERT INTO clinic (clinic_id, description, name, address) values (-20, 'Qpis klinike', 'Jes', 0);


INSERT INTO checkup_type (checkuptype_id, name, duration, price) values  (-1, 'Dermatologija', 30, 500);
INSERT INTO checkup_type (checkuptype_id, name, duration, price) values  (-2, 'Neurologija', 50, 400);
INSERT INTO checkup_type (checkuptype_id, name, duration, price) values  (-3, 'Pedijatrija', 20, 350);
INSERT INTO checkup_type (checkuptype_id, name, duration, price) values  (-4, 'Onkologija', 60, 200);

INSERT INTO room (room_id, rooms_clinic, name) values (-1,0,'Room-1');
INSERT INTO medical_record (m_record_id, age, allergies, blood_type, diopter, height, weight) values (-1, 22, 'Peanut' ,'A', '-1.5', 189, 85);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, activated, medical_record) values ('PATIENT', -2, 'ognjengari@gmail.com', 'Ognjen',
'$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '0621409979', 'Garic', '2108379121', -1,
'2019-12-01 09:00:01', true, -1);


INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, doctor_clinic, avgrating, shift, specialization_checkuptype_id) values ('DOCTOR', -1, 'doctor@gmail.com', 'Prvi',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Doktor', null, 0,
'2019-12-01 09:00:01', 0, 1.5, -1, -1);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, doctor_clinic, avgrating, shift, specialization_checkuptype_id) values ('DOCTOR', -5, 'doctor2@gmail.com', 'Drugi',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Doktor', null, 0,
'2019-12-01 09:00:01', 0, 1.5, -1, -2);

INSERT INTO absences (person_id, dates_id) values (-1, -1);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', 0, 'clinic_admin@gmail.com', 'Aleksa',
'$2y$12$7dK59rknCeqTXmxU8vszremre2A5F.XScnFOeSuJPtdhRaifoIrlO', '0643325333', 'Lukac', null, 0,
'2019-12-01 09:00:01', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date) values ('CLINIC_CENTER_ADMIN', -3, 'admin@gmail.com', 'a',
'$2y$12$2ZStaKA9GlgA2wDAbEivwuHukYAiQMCfH7XNE4KK8GPh6nBFJW6iq', '0643325333', 'a', null, 0,
'2019-12-01 09:00:01');

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date) values ('NURSE', -4, 'nurse@gmail.com', 'a',
'$2a$10$tFUmGgo2vd/aWIol9dGtEe66c1AXfGlm485IPv1rIYbjiDqU21IVu', '0643325332', 'a', null, 0,
'2019-12-01 09:00:01');

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');


INSERT INTO user_authority (person_id, authority_id) values  (-3, 5);
INSERT INTO user_authority (person_id, authority_id) values  (0, 4);
INSERT INTO user_authority (person_id, authority_id) values  (-2, 1);
INSERT INTO user_authority (person_id, authority_id) values  (-1, 2);

INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, date) values (-1,-2,-2,-1 ,0,-1, 2108379121, true , true , false, '2020-01-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, date) values (-2,-1,-2,-1 ,0,-1, 2108379121, true , true , false, '2020-01-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, date) values (-3,-2,-2,-1 ,0,-5, 2108379121, true , true , false, '2020-01-30');
--INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, date) values (-1,-1,-2,-1,0,-1, 2108379121, true, true , true, '2020-01-25');

INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, date) values (-1,-1,-2,-1,0,0, 2108379121, '2020-02-25');
INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, date) values (-2,-2,-1,-1,0,0, 2108379121, '2020-02-29');

--Medicine
INSERT INTO medicine(medicine_id, code, description) values (-1, 'Anadol', 'Snizava temperaturu');
--Prescription
INSERT INTO prescription(prescription_id, certified, by_who) values (-1, true , -5);
--Prescription & medicine
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-1, -1);
--Diagnosis
INSERT INTO diagnosis(diagnosis_id, code, description) values (-1, '#22', 'Prehlada sa temperaturom');
--Report
INSERT INTO report(report_id, description, diagnosis_id, prescription_id) values (-1, 'Pregled prosao lagano', -1, -1);
--Medical record & report
INSERT INTO m_record_report_mapping(m_record_id, report_id) values (-1  , -1);

--Ratings

--INSERT INTO patient_doctor_rating(doctor_id, patient_id, rating) values (-5, -2, 5);