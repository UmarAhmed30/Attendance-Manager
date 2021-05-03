create database javaproject;
use javaproject;
create table user(email varchar(50),password varchar(500));

select * from user;
ALTER TABLE user
    ADD PRIMARY KEY (email);


create table subject(subjectCode varchar(50),staff varchar(50));
ALTER TABLE subject
    ADD PRIMARY KEY (subjectCode,staff);


select * from user;
create table registers(email varchar(50),attendedClasses int,missedClasses int,subjectName varchar(50),
subjectCode varchar(50),staff varchar(50));
ALTER TABLE registers
    ADD FOREIGN KEY (email) REFERENCES user(email);

ALTER TABLE registers
    ADD FOREIGN KEY (subjectCode,staff) REFERENCES subject(subjectCode,staff);


create table history(subjectCode varchar(50),email varchar(50),attendance int);
ALTER TABLE history
    ADD FOREIGN KEY (subjectCode) REFERENCES subject(subjectCode);
ALTER TABLE history
    ADD FOREIGN KEY (email) REFERENCES user(email);










