--Authority

INSERT INTO authority (id, name) values (1, 'PATIENT');
INSERT INTO authority (id, name) values (2, 'DOCTOR');
INSERT INTO authority (id, name) values (3, 'NURSE');
INSERT INTO authority (id, name) values (4, 'CLINIC_ADMIN');
INSERT INTO authority (id, name) values (5, 'CLINIC_CENTER_ADMIN');

--Start end time
--Working hours
INSERT INTO start_end_time (time_id, start_time, end_time) values (-1, '07:00:00', '15:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-2, '10:00:00', '18:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-3, '12:00:00', '20:00:00');
--Checkup times
INSERT INTO start_end_time (time_id, start_time, end_time) values (-4, '09:00:00', '09:30:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-5, '09:40:00', '10:25:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-6, '10:30:00', '11:30:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-7, '11:40:00', '12:00:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-8, '12:10:00', '13:10:00');
INSERT INTO start_end_time (time_id, start_time, end_time) values (-9, '13:30:00', '13:45:00');

--Start end date

INSERT INTO start_end_date (dates_id, start_date, end_date) values (-1, '2020-03-05', '2020-03-15');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-2, '2020-03-08', '2020-03-18');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-3, '2020-03-01', '2020-03-19');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-4, '2020-04-03', '2020-04-07');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-5, '2020-03-20', '2020-03-28');
INSERT INTO start_end_date (dates_id, start_date, end_date) values (-6, '2020-03-25', '2020-04-03');

--Address

INSERT INTO address (address_id, city, country, number, postcode, street) values (-1, 'Novi Sad', 'Serbia', '27',
                                                                                  '21000', 'Jovana Ducica');
INSERT INTO address (address_id, city, country, number, postcode, street) values (-2, 'Novi Sad', 'Serbia', '20',
                                                                                  '21000', 'Janka Cmelika');
INSERT INTO address (address_id, city, country, number, postcode, street) values (-3, 'Novi Sad', 'Serbia', '22',
                                                                                  '21000', 'Narodnog Fronta');
INSERT INTO address (address_id, city, country, number, postcode, street) values (-4, 'Beograd', 'Serbia', '17',
                                                                                  '11000', 'Gospodara Vucica');
INSERT INTO address (address_id, city, country, number, postcode, street) values (-5, 'Krusevac', 'Serbia', '74',
                                                                                  '37000', 'Cara Lazar');
INSERT INTO address (address_id, city, country, number, postcode, street) values (-6, 'Aleksinac', 'Serbia', '12',
                                                                                  '18220', 'Marsala Tita');

--Clinic
INSERT INTO clinic (clinic_id, description, name, address, version) values (0, 'Praesent et tempus eros.', 'Medicino de clinico', -1, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-1, 'Vestibulum in condimentum felis', 'Healthier Group', -2, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-2, 'Nam iaculis, dui in viverra rutrum, purus nulla rhoncus magna, eget iaculis tortor justo in nisi.', 'ContraClinico', -3, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-3, 'Fusce non tristique dui.', 'La Havanna', -4, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-4, 'Aenean ullamcorper nisi eget eros porttitor, quis sollicitudin nisi convallis', 'Hospital Perfection', -5, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-5, 'Integer vel fermentum neque, at elementum leo.', 'El Paella', -6, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-6, 'Cras sed augue ipsum.', 'Lifeline', -1, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-7, 'Sed eleifend neque nulla, et faucibus leo suscipit eget. ', 'BlueCross United', -2, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-8, 'Sed a justo nisl.', 'Soy Medico', -3, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-9, 'Mauris laoreet vitae dui eu aliquet.', 'White Support', -4, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-10, 'Morbi id placerat purus.', 'Lets Hospitalize', -5, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-11, 'Aenean blandit sapien quis luctus sagittis.', 'MedGroup', -6, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-12, 'Aenean vulputate rhoncus purus', 'Emerald Dove', -1, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-13, 'Praesent eget sodales diam.', 'Crossroad miracle', -2, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-14, 'Maecenas faucibus lacus urna, ac euismod ipsum suscipit ac.', 'Grand Clinic', -3, 0);
INSERT INTO clinic (clinic_id, description, name, address, version) values (-15, 'In et ante aliquet, blandit eros a, efficitur lectus.', 'Refined emegency services', -4, 0);


--Checkup type

INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-1, 'Internal medicine', 45, 500,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-2, 'Pediatrics', 20, 200,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-3, 'Radiology', 30, 2000,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-4, 'Neurology', 50, 2500,-1);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-5, 'Dermatology', 15, 1500,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-6, 'Cardiology', 60, 700,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-7, 'Pathology', 45, 1050,0);
INSERT INTO checkup_type (checkuptype_id, name, duration, price, checkuptypes_clinic) values  (-8, 'Oncology', 60, 3000,0);


--Room
INSERT INTO room (room_id, rooms_clinic, name) values (-1,0,'R1');
INSERT INTO room (room_id, rooms_clinic, name) values (-2,0,'R2');
INSERT INTO room (room_id, rooms_clinic, name) values (-3,0,'R3');
INSERT INTO room (room_id, rooms_clinic, name) values (-4,0,'R4');

INSERT INTO room (room_id, rooms_clinic, name) values (-5,-1,'CCR1');
INSERT INTO room (room_id, rooms_clinic, name) values (-6,-1,'CCR2');
INSERT INTO room (room_id, rooms_clinic, name) values (-7,-1,'CCR3');
INSERT INTO room (room_id, rooms_clinic, name) values (-8,-1,'CCR4');

--Medicine

INSERT INTO medicine(medicine_id, code, description) values (-1, 'Andol', 'Lowers body temperature.');
INSERT INTO medicine(medicine_id, code, description) values (-2, 'Pycharm', 'Helps with snake venom.');
INSERT INTO medicine(medicine_id, code, description) values (-3, 'IS-A prototype', 'Enhances human senses.');
INSERT INTO medicine(medicine_id, code, description) values (-4, 'Sellenium', 'Lowers back pain.');
INSERT INTO medicine(medicine_id, code, description) values (-5, 'Brufen', 'Eases pain.');
INSERT INTO medicine(medicine_id, code, description) values (-6, 'Xanax', 'Makes life more bearable.');

--Diagnosis
INSERT INTO diagnosis(diagnosis_id, code, description) values (-1, '#22', 'AIDS');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-2, '#46', 'Food poisoning');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-3, '#13', 'Common cold');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-4, '#73', 'Infected wound');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-5, '#116', 'Testicular cancer');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-6, '#2', 'Eczema');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-7, '#89', 'Stroke');
INSERT INTO diagnosis(diagnosis_id, code, description) values (-8, '#103', 'Alzheimers disease');

--Medical record

INSERT INTO medical_record (m_record_id, age, allergies, blood_type, diopter, height, weight) values (-1, 22, 'Peanut' ,'A', '-1.5', 184, 85);
INSERT INTO medical_record (m_record_id, age, allergies, blood_type, diopter, height, weight) values (-2, 45, 'Pollen' ,'AB', '+3', 172, 73);
INSERT INTO medical_record (m_record_id, age, allergies, blood_type, diopter, height, weight) values (-3, 37, 'Penicilin' ,'0', '0', 165, 50);

--Person

--Patient

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, activated, medical_record) values ('PATIENT', -1,   'patient1@gmail.com', 'Marijan',
                                                                                          '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '06348232', 'Bijelic', '21832893131', -1,
                                                                                          '2020-02-02 09:00:01', true, -1);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, activated, medical_record) values ('PATIENT', -2,   'patient2@gmail.com', 'Janko',
                                                                                          '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '06523324', 'Veselinovic', '218212893131', -3,
                                                                                          '2020-02-02 09:00:01', true, -2);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, activated, medical_record) values ('PATIENT', -3,   'patient3@gmail.com', 'Neda',
                                                                                          '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '068276235', 'Mandic', '218334433131', -3,
                                                                                          '2020-02-02 09:00:01', true, -3);

--Doctor
INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, doctor_clinic, shift, specialization_checkuptype_id, must_change_pass) values ('DOCTOR', -4, 'doctor1@gmail.com', 'Vlada',
                                                                                                                                      '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '067213212', 'Marjanovic', -1,
                                                                                                                                      '2020-02-01 09:00:01', 0, -1, -1, true);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, doctor_clinic, shift, specialization_checkuptype_id) values ('DOCTOR', -5, 'doctor2@gmail.com', 'Dragoljub',
                                                                                                                    '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '067213212', 'Vesimirovic', -1,
                                                                                                                    '2020-02-01 09:00:01', 0, -2, -2);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, doctor_clinic, shift, specialization_checkuptype_id) values ('DOCTOR', -6, 'doctor3@gmail.com', 'Luka',
                                                                                                                    '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '067213212', 'Bajic', -1,
                                                                                                                    '2020-02-01 09:00:01', 0, -3, -3);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, doctor_clinic, shift, specialization_checkuptype_id) values ('DOCTOR', -7, 'doctor4@gmail.com', 'Vlada',
                                                                                                                    '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '067213212', 'Marjanovic', -2,
                                                                                                                    '2020-02-01 09:00:01', -1, -1, -4);

--Clinic admin

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', -8, 'clinic_admin@gmail.com', 'Aleksa',
                                                                             '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '05328312', 'Lukac', null, -2,
                                                                             '2019-12-01 09:00:01', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', -9, 'clinic_admin2@gmail.com', 'Borko',
                                                                             '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '064198324', 'Mandic', null, -2,
                                                                             '2019-12-01 09:00:01', -1);

--Clinical center admin
INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date) values ('CLINIC_CENTER_ADMIN', -10, 'admin@gmail.com', 'Dane',
                                                               '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '0643325333', 'Jovanovic', null, -3,
                                                               '2019-12-01 09:00:01');

--Nurse

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, nurse_clinic) values ('NURSE', -11, 'nurse1@gmail.com', 'Jana',
                                                                             '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '06521416', 'Stojanov', -4,
                                                                             '2019-12-01 09:00:01', 0);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname,
                    address, last_password_reset_date, nurse_clinic) values ('NURSE', -12, 'nurse2@gmail.com', 'Ana',
                                                                             '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '0532521416', 'Markovic', -3,
                                                                             '2019-12-01 09:00:01', 0);

--User authority

INSERT INTO user_authority (person_id, authority_id) values  (-1, 1);
INSERT INTO user_authority (person_id, authority_id) values  (-2, 1);
INSERT INTO user_authority (person_id, authority_id) values  (-3, 1);
INSERT INTO user_authority (person_id, authority_id) values  (-4, 2);
INSERT INTO user_authority (person_id, authority_id) values  (-5, 2);
INSERT INTO user_authority (person_id, authority_id) values  (-6, 2);
INSERT INTO user_authority (person_id, authority_id) values  (-7, 2);
INSERT INTO user_authority (person_id, authority_id) values  (-8, 4);
INSERT INTO user_authority (person_id, authority_id) values  (-9, 4);
INSERT INTO user_authority (person_id, authority_id) values  (-10, 5);
INSERT INTO user_authority (person_id, authority_id) values  (-11, 3);
INSERT INTO user_authority (person_id, authority_id) values  (-12, 3);

--Absences

INSERT INTO absences (person_id, dates_id) values (-4, -1);
INSERT INTO absences (person_id, dates_id) values (-5, -2);
INSERT INTO absences (person_id, dates_id) values (-6, -3);
INSERT INTO absences (person_id, dates_id) values (-7, -4);
INSERT INTO absences (person_id, dates_id) values (-4, -5);
INSERT INTO absences (person_id, dates_id) values (-5, -6);

--Predefined checkups
INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, date) values (-1,-1,-4,-1,0,-4, '2020-02-25');
INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, date) values (-2,-2,-5,-2,0,-4, '2020-02-29');
INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, date) values (-3,-3,-6,-3,0,-4, '2020-02-29');

--Prescription

INSERT INTO prescription(prescription_id, certified, by_who) values (-1, true , -11);
INSERT INTO prescription(prescription_id, certified, by_who) values (-2, false , null);
INSERT INTO prescription(prescription_id, certified, by_who) values (-3, true , -12);
INSERT INTO prescription(prescription_id, certified, by_who) values (-4, false , null);

--Medicine & Prescription
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-1, -1);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-1, -2);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-1, -5);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-2, -2);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-2, -6);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-3, -3);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-3, -4);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-4, -1);
INSERT INTO medicine_list_prescription(prescription_id, medicine_id) values (-4, -2);

--Report
INSERT INTO report(report_id, diagnosis_id, prescription_id, description) values (-1, -1, -1, 'Patient stable.');
INSERT INTO report(report_id, diagnosis_id, prescription_id, description) values (-2, -2, -2, 'Showing signs of improvement.');
INSERT INTO report(report_id, diagnosis_id, prescription_id, description) values (-3, -3, -3, 'Patient healthy.');
INSERT INTO report(report_id, diagnosis_id, prescription_id, description) values (-4, -4, -4, 'Visible distress.');

--Checkup
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-1,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-2,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-3,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, true, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-4,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, true, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-5,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');

INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date, report_id) values (-6,-2,-2,-1 ,0,-4, 218334433131, true , true , false, false, false, true, '2020-02-01', -1);
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date, report_id) values (-7,-2,-2,-1 ,0,-4, 218212893131, true , true , false, false, false, false, '2020-01-29', -2);

--Medical record & report
INSERT INTO m_record_report_mapping(m_record_id, report_id) values (-3, -1);
INSERT INTO m_record_report_mapping(m_record_id, report_id) values (-2, -2);

