INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
address, last_password_reset_date, activated, medical_record) values ('PATIENT', -1,   'patient1@gmail.com', 'Marijan',
'$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '06348232', 'Bijelic', '21832893131', -1,
'2020-02-02 09:00:01', true, -1);

INSERT INTO person (discriminator, person_id, email, name, password, phone_number, surname, social_security_number,
                    address, last_password_reset_date, admin_clinic) values ('CLINIC_ADMIN', -8, 'clinic_admin@gmail.com', 'Aleksa',
                                                                             '$2y$12$0CKdvcFlnIVz81ywVD9n/eVIl5hDLP7CdiTkT8eo9d6axuKxZW14q', '05328312', 'Lukac', null, -2,
                                                                             '2019-12-01 09:00:01', 0);
INSERT INTO room (room_id, rooms_clinic, name) values (-1,0,'R1');

INSERT INTO predefined_checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, date) values (-1,-1,-4,-1,0,-4, '2020-02-25');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-1,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-2,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-3,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, true, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-4,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, true, '2020-04-29');
INSERT INTO checkup (checkup_id, checkup_type, start_end_time, checkup_room, checkup_clinic, checkup_doctor, patient_ssn, is_approved, is_ended, is_started, is_pending_patient, is_updated, is_operation, date) values (-5,-3,-4,null ,0,-6, 21832893131, false , false , false, false, false, false, '2020-04-29');
