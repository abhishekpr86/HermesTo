-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2016 at 09:10 AM
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
-- Table structure for table `training_grade`
--

CREATE TABLE `training_grade` (
  `training_id` int(10) NOT NULL,
  `grade_id` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `training_grade`
--

INSERT INTO `training_grade` (`training_id`, `grade_id`) VALUES
(11, 1),
(11, 2),
(11, 3),
(11, 4),
(11, 5),
(12, 1),
(12, 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `training_grade`
--
ALTER TABLE `training_grade`
  ADD PRIMARY KEY (`training_id`,`grade_id`),
  ADD KEY `training_id` (`training_id`),
  ADD KEY `grade_id` (`grade_id`),
  ADD KEY `training_id_2` (`training_id`),
  ADD KEY `grade_id_2` (`grade_id`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `training_grade`
--
ALTER TABLE `training_grade`
  ADD CONSTRAINT `training_grade_ibfk_1` FOREIGN KEY (`training_id`) REFERENCES `training` (`training_id`),
  ADD CONSTRAINT `training_grade_ibfk_2` FOREIGN KEY (`grade_id`) REFERENCES `grade` (`grade_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
