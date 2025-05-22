create database bankmanagementsystem;

show databases;

use bankmanagementsystem;

create table signup(formno varchar(20), name varchar(20), father_name varchar(20), dob varchar(20), gender varchar(20), email varchar(100), marital_status varchar(20), address varchar(40), city varchar(25), pincode varchar(20), state varchar(25));
show tables;
select * from signup;

create table signuptwo(formno varchar(20), religion varchar(20), category varchar(20), income varchar(20), education varchar(20), occupation varchar(20), pan varchar(20), aadhar varchar(40), seniorcitizen varchar(25), existingaccount varchar(20));
select * from signuptwo;

create table signupthree(formno varchar(20), accountType varchar(40), cardnumber varchar(25), pin varchar(10), facilities varchar(100));
select * from signupthree;

create table login(formno varchar(20), cardnumber varchar(25), pin varchar(10));
select * from login;

drop table bank;

create table bank(pin varchar(10), date varchar(50), type varchar(20), amount varchar(20));

select * from bank;
