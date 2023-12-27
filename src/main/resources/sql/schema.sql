create database SIHINA;

use SIHINA;

create table user (
    user_id    varchar(50) primary key,
    First_Name varchar(55) not null,
    Last_Name  varchar(55) not null,
    Email      varchar(50) not null,
    NIC        varchar(15) not null,
    User_Name  varchar(20) not null,
    Password   varchar(10) unique
);

create table Student (
    Stu_id     varchar(50) primary key,
    Name       varchar(55) not null,
    Email      varchar(50) not null,
    Address    varchar(50) not null,
    D_O_B      date        not null,
    Gender     varchar(20) not null,
    Contact    varchar(10) not null,
    Class      varchar(25) not null,
    subjects   varchar(50) not null,
    image      LONGBlob,
    user_id    varchar(50),
    constraint foreign key (user_id) references user (user_id) on UPDATE cascade on DELETE cascade
);


create table Guardian (
    Guard_id   varchar(50) primary key,
    Guard_Name varchar(55) not null,
    contactNo  varchar(12) not null,
    Email      varchar(50) not null,
    occupation varchar(50) not null,
    Stu_id     varchar(50),
    constraint foreign key (Stu_id) references Student (Stu_id) on UPDATE cascade on DELETE cascade
);

create table Payment (
    Pay_id    varchar(50) primary key,
    Stu_id    varchar(50) not null,
    Type      varchar(50) not null,
    Stu_Class varchar(50) not null,
    Subject   varchar(50) not null,
    Pay_Month varchar(15) not null,
    date      DATE        not null,
    time      TIME        not null,
    Amount double (15,2),
    constraint foreign key (Stu_id) references Student (Stu_id) on UPDATE cascade on DELETE cascade
);

create table Class (
    class_id varchar(50) primary key,
    Name     varchar(50) not null
);

create table Registration (
    Stu_id   varchar(50) not null,
    Pay_id   varchar(50) not null,
    Stu_Name varchar(50) not null,
    class_id varchar(50) not null,
    Reg_Fee double (10,2) not null,
    date     DATE        not null,
    time     TIME        not null,
    constraint foreign key (Stu_id) references Student (Stu_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (Pay_id) references Payment (Pay_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (class_id) references Class (class_id) on UPDATE cascade on DELETE cascade
);

create table Attendance (
    Att_id    varchar(50) primary key,
    Stu_id    varchar(50) not null,
    Stu_Class varchar(50) not null,
    Subject   VARCHAR(50) not null,
    Month     VARCHAR(50) not null,
    date      DATE        not null,
    time      TIME        not null,
    type      VARCHAR(50) not null,
    constraint foreign key (Stu_id) references Student (Stu_id) on UPDATE cascade on DELETE cascade
);


create table Subject (
    Sub_id         varchar(50) primary key,
    Sub_Name       varchar(30) not null,
    AvailableClass varchar(45) not null,
    teacherName    varchar(40) not null,
    MonthlyAmount  double (10,2)
);

create table SubjectDetail (
    Sub_id     varchar(50) not null,
    class_id   varchar(50) not null,
    date       DATE        not null,
    Start_Time TIME        not null,
    End_Time   TIME        not null,
    constraint foreign key (Sub_id) references Subject (Sub_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (class_id) references Class (class_id) on UPDATE cascade on DELETE cascade
);

create table Exam (
    Exam_id     varchar(50) primary key,
    date        DATE        not null,
    Start_time  TIME        not null,
    End_time    TIME        not null,
    Description varchar(55) not null,
    class_id    varchar(50) not null,
    Sub_id      varchar(50) not null,
    constraint foreign key (Sub_id) references Subject (Sub_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (class_id) references Class (class_id) on UPDATE cascade on DELETE cascade
);

create table Teacher (
    Teacher_id varchar(50) primary key,
    Name       varchar(50) not null,
    Address    varchar(60) not null,
    Email      varchar(50) not null,
    Contact    varchar(12) not null,
    Subject    varchar(55) not null,
    image      LONGBLOB
);

create table Schedule (
    class_id   varchar(50) not null,
    Sub_id     varchar(50) not null,
    Teacher_id varchar(50) not null,
    Class_day  varchar(50) not null,
    Start_Time TIME        not null,
    End_Time   TIME        not null,
    constraint foreign key (class_id) references Class (class_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (Sub_id) references Subject (Sub_id) on UPDATE cascade on DELETE cascade,
    constraint foreign key (Teacher_id) references Teacher (Teacher_id) on UPDATE cascade on DELETE cascade
);