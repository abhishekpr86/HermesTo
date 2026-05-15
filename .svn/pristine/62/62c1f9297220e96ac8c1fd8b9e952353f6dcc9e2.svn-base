-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2016 at 09:08 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hermes_onboarding`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `emp_id` int(10) NOT NULL,
  `corp_id` varchar(10) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `grade_id` int(10) NOT NULL,
  `primary_technology` varchar(30) NOT NULL,
  `joining_date` date NOT NULL,
  `end_date` date NOT NULL,
  `contact` varchar(12) DEFAULT NULL,
  `entry_date` date DEFAULT NULL,
  `created_by` varchar(30) DEFAULT NULL,
  `mgr_id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `corp_id`, `first_name`, `last_name`, `email`, `grade_id`, `primary_technology`, `joining_date`, `end_date`, `contact`, `entry_date`, `created_by`, `mgr_id`) VALUES
(1000, 'harinder', 'Harinder', 'Singh', 'harinder.singh@capgemini.com', 11, 'Java', '2016-04-12', '2016-11-25', '1234567890', NULL, 'System', NULL),
(2000, 'bhnakt', 'Bhakti', 'Nakti', 'bhakti.nakti@capgemini.com', 4, 'SAP', '2016-01-11', '2016-01-12', '546565645', NULL, NULL, 1000),
(34534, 'trtan', 'Trupti', 'Tan', 'trupti.tan@capgemini.com', 7, 'LAMP', '2016-01-11', '2016-01-12', '4554545', NULL, NULL, 68722),
(68722, 'miprabhu', 'Milind', 'Prabhu', 'milind.prabhu@capgemini.com', 9, 'Java', '2016-01-01', '2017-01-31', '9820948339', NULL, NULL, 1000),
(90821, 'dgupta13', 'Deepak', 'Gupta', 'deepak.n.gupta@capgemini.com', 2, 'Java', '2016-01-11', '2016-01-11', '9820801863', NULL, NULL, 68722),
(91254, 'nitinkad', 'Nitn', 'Kadam', 'nitin.c.kadam@capgemini.com', 9, 'Java', '2016-01-16', '2016-01-16', '983648576345', NULL, NULL, 1000);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`emp_id`),
  ADD UNIQUE KEY `UNQ_EMP_ID` (`emp_id`),
  ADD UNIQUE KEY `UNQ_CORP_ID` (`corp_id`),
  ADD UNIQUE KEY `UNQ_EMAIL` (`email`),
  ADD KEY `mgr_id` (`mgr_id`),
  ADD KEY `grade_id` (`grade_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_employee_ibfk_1` FOREIGN KEY (`mgr_id`) REFERENCES `employee` (`emp_id`),
  ADD CONSTRAINT `employee_grade_ibfk_1` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
