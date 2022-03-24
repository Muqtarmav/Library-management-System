create database library_db;
create user 'library_user'@'localhost' identified by 'pass_123';
grant all privileges on library_db. * to 'library_user'@'localhost';
flush privileges;
