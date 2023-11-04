-- init.sql

-- Create and use the `gtt` database
CREATE DATABASE IF NOT EXISTS gtt;
USE gtt;

-- Table structure for table employees
CREATE TABLE IF NOT EXISTS employees(
    id_employee INT(10) NOT NULL AUTO_INCREMENT PRIMARY KEY,
    f_name VARCHAR(30) NOT NULL,
    l_name VARCHAR(30) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(30) NOT NULL,
    user_role ENUM('0', '1'),
);

-- Table structure for table reports
CREATE TABLE IF NOT EXISTS reports(
    Id INT(11) NOT NULL AUTO_INCREMENT,
    Date DATE , 
    id_employee INT(10) NOT NULL, 
    PRIMARY KEY(Id), FOREIGN KEY(id_employee) REFERENCES employees(id_employee));

-- Table structure for table time
CREATE TABLE IF NOT EXISTS times(
    id INT(15) NOT NULL AUTO_INCREMENT,
    id_employee INT(15) NOT NULL,
    DATE DATE, 
    start_time TIME NOT NULL, 
    end_time TIME NOT NULL, 
    mood_form TINYINT(1) NOT NULL DEFAULT 0, 
    PRIMARY KEY(id), 
    FOREIGN KEY(id_employee) REFERENCES employees(id_employee)
);

-- Table structure for table pauses
CREATE TABLE IF NOT EXISTS pauses(
    id INT(11) NOT NULL AUTO_INCREMENT,
    id_time INT(11) NOT NULL,
    pause ENUM('1', '2', '3') DEFAULT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_time) REFERENCES times(id)
    );

-- Table structure for table humeurs
    CREATE TABLE IF NOT EXISTS humeurs(
        id_humeur INT(11) NOT NULL AUTO_INCREMENT,
        humeur ENUM('1', '2', '3') NOT NULL,
        PRIMARY KEY(id_humeur)
    );

-- Insert data for table employees
INSERT INTO employees ( f_name, l_name, email, password, user_role)
VALUES ( 'Oumayma', 'Yakoubi', 'ya.oumayma@gmail.com', '123456789', '0'),
       ( 'Mohamed Alhabib', 'Fatehi', 'mohamedfatehialhabib@gmail.com', '6341', '1');

-- Insert data for table humeurs
INSERT INTO humeurs ( humeur)
VALUES ( '1'),
       ( '2'),
       ( '2'),
       ( '2'),
       ( '3'),
       ( '3');

-- Insert data for table time
INSERT INTO times ( id_employee, date, start_time, end_time, mood_form)
VALUES ( 2, '2023-10-29', '09:00:00', '17:00:00', 0),
       ( 2, '2023-10-31', '09:00:00', '17:00:00', 0),
       ( 2, '2023-11-29', '09:00:00', '17:00:00', 0),
       ( 2, '2023-11-01', '09:00:00', '18:00:00', 0),
       ( 1, '2023-11-01', '09:00:00', '17:00:00', 0),
       ( 1, '2023-11-02', '09:00:00', '17:00:00', 0),
       ( 1, '2023-11-03', '09:00:00', '17:00:00', 0),
       ( 1, '2023-11-04', '09:00:00', '17:00:00', 0),
       ( 2, '2023-10-31', '09:00:00', '17:46:00', 0),
       ( 1, '2023-10-23', '09:00:00', '18:00:00', 0),
       ( 1, '2023-10-24', '09:30:00', '17:30:00', 0),
       ( 1, '2023-10-25', '09:00:00', '17:30:00', 0),
       ( 1, '2023-10-26', '10:30:00', '17:00:00', 0),
       ( 1, '2023-10-27', '09:00:00', '17:00:00', 0),
       ( 2, '2023-10-30', '09:00:00', '18:30:00', 0),
       ( 2, '2023-10-31', '09:00:00', '17:00:00', 0),
       ( 2, '2023-11-01', '09:00:00', '17:30:00', 0),
       ( 2, '2023-11-02', '09:30:00', '17:00:00', 0);

-- Insert data for table pauses
INSERT INTO pauses ( id_time, pause)
VALUES ( 1, '2'),
       ( 1, '1'),
       ( 3, '2'),
       ( 4, '3'),
       ( 4, '1'),
       ( 5, '2'),
       ( 5, '3'),
       ( 5, '1'),
       ( 6, '2'),
       ( 1, '1'),
       ( 9, '2'),
       ( 12, '3'),
       ( 11, '2'),
       ( 13, '2'),
       ( 12, '2'),
       ( 13, '2'),
       ( 14, '1'),
       ( 14, '2'),
       ( 17, '2');

-- Insert data for table reports
INSERT INTO reports ( Date, id_employee)
VALUES ( '2023-11-01', 2),
       ( '2023-11-01', 1),
       ( '2023-11-01', 2),
       ( '2023-11-01', 1),
       ( '2023-11-01', 2),
       ( '2023-11-01', 1),
       ( '2023-11-01', 2),
       ( '2023-11-01', 1),
       ( '2023-11-01', 2),
       ( '2023-11-01', 1),
       ( '2023-11-03', 2),
       ( '2023-11-03', 1);
