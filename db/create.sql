-- app_users Table
create table app_users(id int primary key auto_increment,
 name varchar(100) not null,
 mob_no bigint unique not null,
 password varchar(10) not null,
 role char(1) not null);
 
 select * from app_users;
 
 -- Student_Details Table
 create table student_details (
 s_no int primary key auto_increment,
 student_name varchar(50) not null,
 reg_no int not null unique,
 average int ,
 Grade char(1) );
 
 alter table student_details modify Grade char(1) ;

 select * from student_details;
 
-- student Mark Table
   create table student_marks (
	id int auto_increment,
	reg_no int not null, 
    subject_code varchar(100) not null, 
    marks int not null,
	foreign key (reg_no) references student_details(reg_no), 
	foreign key (subject_code) references subject(sub_code),	
    primary key(id) );
   
 -- student Grade Table
 create table student_grade (reg_no int unique not null,
 average float not null,
 grade char(1) not null,
 foreign key (reg_no) references student_details(reg_no));

-- Score Range Table
create table score_range ( grade char(1) primary key, min float not null, max float not null );
 
 -- Subject Table
 create table subject ( 
 id int primary key auto_increment,
 sub_code varchar(100) unique not null,
 subject_name varchar(20) not null );