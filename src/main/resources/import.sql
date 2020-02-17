insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Renan','Alexandre','81988989899','renan@gmail.com','1234', 'true');

insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Robson','Silva','819999999','robson@gmail.com','1234', 'true');

insert into user (user_name,user_surname,user_fone,user_email,user_password, user_status) values ('Lionel','Messi','81997777','messi@gmail.com','1234', 'true');


insert into contact (contact_name,contact_fone,user_id) values('Messi','81997777',1);
insert into contact (contact_name,contact_fone,user_id) values('CR7','8199888888',1);
insert into contact (contact_name,contact_fone,user_id) values('Neymar','8199809090',1);
insert into contact (contact_name,contact_fone,user_id) values('Iniesta','819967879',1);
insert into contact (contact_name,contact_fone,user_id) values('salah','81997788990',2);
insert into contact (contact_name,contact_fone,user_id) values('Marcelo','81999998870',2);
insert into contact (contact_name,contact_fone,user_id) values('Sergio','81992374899',2);
insert into contact (contact_name,contact_fone,user_id) values('Gabriel','81993457789',2);

insert into contact (contact_name,contact_fone,user_id) values('Renan','81988989899',3);

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg) values ('olá amigo !','81997777',1,'true');

insert into content_menssage (content_msg,id_user_contact,id_user_msg,status_msg) values ('Fala cara !','81988989899',3,'true');



