create database javaproject;
use javaproject;
create table user(email varchar(50),password varchar(500));

select * from user;
ALTER TABLE user
    ADD PRIMARY KEY (email);


create table subject(subjectCode varchar(50),staff varchar(50),subjectName varchar(50));
ALTER TABLE subject
    ADD PRIMARY KEY (subjectCode,staff);


select * from user;
create table registers(email varchar(50),attendedClasses int,missedClasses int,subjectName varchar(50),
subjectCode varchar(50),staff varchar(50));
ALTER TABLE registers
    ADD FOREIGN KEY (email) REFERENCES user(email);

ALTER TABLE registers
    ADD FOREIGN KEY (subjectCode,staff) REFERENCES subject(subjectCode,staff);


create table history(subjectName varchar(50),attendance varchar(50));

select * from history;

select * from registers;

create table bio(name varchar(50), year varchar(50), college varchar(50), filepath varchar(50));

select * from bio;











