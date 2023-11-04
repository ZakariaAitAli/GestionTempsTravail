-- init.sql

-- Create and use the `gtt` database
CREATE DATABASE IF NOT EXISTS gtt;
USE gtt;

-- Table structure for table `employees`
CREATE TABLE IF NOT EXISTS `employees`
(
    `id_employee` int(10)     NOT NULL AUTO_INCREMENT,
    `f_name`      varchar(30) NOT NULL,
    `l_name`      varchar(30) NOT NULL,
    `email`       varchar(50) NOT NULL,
    `password`    varchar(30) NOT NULL,
    PRIMARY KEY (`id_employee`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- Insert data for table `employees`
INSERT INTO `employees` (`id_employee`, `f_name`, `l_name`, `email`, `password`)
VALUES (1, 'Oumayma', 'Yakoubi', 'ya.oumayma@gmail.com', '123456789'),
       (3, 'Mohamed Alhabib', 'Fatehi', 'mohamedfatehialhabib@gmail.com', '6341');

-- Table structure for table `humeurs`
CREATE TABLE IF NOT EXISTS `humeurs`
(
    `id_humeur` int(11)            NOT NULL AUTO_INCREMENT,
    `humeur`    enum ('1','2','3') NOT NULL,
    PRIMARY KEY (`id_humeur`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- Insert data for table `humeurs`
INSERT INTO `humeurs` (`id_humeur`, `humeur`)
VALUES (1, '1'),
       (2, '2'),
       (3, '2'),
       (4, '2'),
       (5, '3'),
       (6, '3');

-- Table structure for table `pauses`
CREATE TABLE IF NOT EXISTS `pauses`
(
    `id`      int(11) NOT NULL AUTO_INCREMENT,
    `id_time` int(11) NOT NULL,
    `pause`   enum ('1','2','3') DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `pauses_ibfk_1` (`id_time`),
    CONSTRAINT `pauses_ibfk_1` FOREIGN KEY (`id_time`) REFERENCES `time` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- Insert data for table `pauses`
INSERT INTO `pauses` (`id`, `id_time`, `pause`)
VALUES (1, 1, '2'),
       (2, 1, '1'),
       (5, 15, '2'),
       (6, 15, '3'),
       (7, 17, '1'),
       (8, 17, '2'),
       (9, 17, '3'),
       (10, 18, '1'),
       (11, 18, '2'),
       (12, 19, '1'),
       (13, 19, '2'),
       (14, 19, '3'),
       (15, 20, '2'),
       (16, 21, '2'),
       (17, 22, '2'),
       (18, 23, '2'),
       (19, 24, '1'),
       (20, 24, '2'),
       (21, 25, '2');

-- Table structure for table `reports`
CREATE TABLE IF NOT EXISTS `reports`
(
    `Id`          int(11) NOT NULL AUTO_INCREMENT,
    `Date`        date    NOT NULL DEFAULT current_timestamp(),
    `id_employee` int(10) NOT NULL,
    PRIMARY KEY (`Id`),
    KEY `FK_ForeignKey2` (`id_employee`),
    CONSTRAINT `FK_ForeignKey2` FOREIGN KEY (`id_employee`) REFERENCES `employees` (`id_employee`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- Insert data for table `reports`
INSERT INTO `reports` (`Id`, `Date`, `id_employee`)
VALUES (1, '2023-11-01', 3),
       (2, '2023-11-01', 1),
       (3, '2023-11-01', 3),
       (4, '2023-11-01', 1),
       (5, '2023-11-01', 3),
       (6, '2023-11-01', 1),
       (7, '2023-11-01', 3),
       (8, '2023-11-01', 1),
       (9, '2023-11-01', 3),
       (10, '2023-11-01', 1),
       (11, '2023-11-03', 3),
       (12, '2023-11-03', 1);

-- Table structure for table time
CREATE TABLE IF NOT EXISTS time
(
    id          int(15)    NOT NULL AUTO_INCREMENT,
    id_employee int(15)    NOT NULL,
    date        date       NOT NULL DEFAULT current_timestamp(),
    start_time  time       NOT NULL,
    end_time    time       NOT NULL,
    mood_form   tinyint(1) NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    KEY id_employee (id_employee),
    CONSTRAINT time_ibfk_1 FOREIGN KEY (id_employee) REFERENCES employees (id_employee) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci;

-- Insert data for table time
INSERT INTO time (id, id_employee, date, start_time, end_time, mood_form)
VALUES (1, 3, '2023-10-29', '09:00:00', '17:00:00', 0),
       (2, 3, '2023-10-31', '09:00:00', '17:00:00', 0),
       (3, 3, '2023-11-29', '09:00:00', '17:00:00', 0),
       (4, 3, '2023-11-01', '09:00:00', '18:00:00', 0),
       (5, 1, '2023-11-01', '09:00:00', '17:00:00', 0),
       (6, 1, '2023-11-02', '09:00:00', '17:00:00', 0),
       (7, 1, '2023-11-03', '09:00:00', '17:00:00', 0),
       (8, 1, '2023-11-04', '09:00:00', '17:00:00', 0),
       (15, 3, '2023-10-31', '09:00:00', '17:46:00', 0),
       (17, 1, '2023-10-23', '09:00:00', '18:00:00', 0),
       (18, 1, '2023-10-24', '09:30:00', '17:30:00', 0),
       (19, 1, '2023-10-25', '09:00:00', '17:30:00', 0),
       (20, 1, '2023-10-26', '10:30:00', '17:00:00', 0),
       (21, 1, '2023-10-27', '09:00:00', '17:00:00', 0),
       (22, 3, '2023-10-30', '09:00:00', '18:30:00', 0),
       (23, 3, '2023-10-31', '09:00:00', '17:00:00', 0),
       (24, 3, '2023-11-01', '09:00:00', '17:30:00', 0),
       (25, 3, '2023-11-02', '09:30:00', '17:00:00', 0);
