insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Renan','Alexandre','81988989899','renan@gmail.com','1234', 'true');

insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Robson','Silva','819999999','robson@gmail.com','1234', 'true');

insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Lionel','Messi','81997777','messi@gmail.com','1234', 'true');


insert into contact (contact_name,id_contact_user,user_id) values('Messi','3',1);
--insert into contact (contact_name,id_contact_user,user_id) values('CR7','2',1);
--insert into contact (contact_name,id_contact_user,user_id) values('Neymar','8199809090',1);
--insert into contact (contact_name,id_contact_user,user_id) values('Iniesta','819967879',1);
--insert into contact (contact_name,id_contact_user,user_id) values('salah','81997788990',2);
--insert into contact (contact_name,id_contact_user,user_id) values('Marcelo','81999998870',2);
--insert into contact (contact_name,id_contact_user,user_id) values('Sergio','81992374899',2);
--insert into contact (contact_name,id_contact_user,user_id) values('Gabriel','81993457789',2);

insert into contact (contact_name,id_contact_user,user_id) values('Renan','1',3);

insert into contact (contact_name,id_contact_user,user_id) values('Renan','1',2);
insert into contact (contact_name,id_contact_user,user_id) values('Robson','2',1);

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg,status_send,status_recipient) values ('olá amigo !',3,1,'true','true','true');

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg,status_send,status_recipient) values ('Fala cara !',1,3,'true','true','true');

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg,status_send,status_recipient) values ('Como vai amg !',1,2,'true','true','true');

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg,status_send,status_recipient) values ('vou bem !',2,1,'true','true','true');




