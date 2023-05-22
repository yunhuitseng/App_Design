-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-05-22 16:02:52
-- 伺服器版本： 10.4.22-MariaDB
-- PHP 版本： 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫: `appdesign`
--
CREATE DATABASE IF NOT EXISTS `appdesign` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `appdesign`;

-- --------------------------------------------------------

--
-- 資料表結構 `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE `photo` (
  `imgtitle` varchar(50) NOT NULL,
  `account` varchar(50) NOT NULL,
  `upload_date` datetime DEFAULT NULL,
  `content` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- 傾印資料表的資料 `photo`
--

INSERT INTO `photo` (`imgtitle`, `account`, `upload_date`, `content`) VALUES
('1000.jpg', 'student_1', '2023-05-21 10:13:46', '在線西拍的夕陽'),
('1001.jpg', 'student_1', '2023-05-08 10:13:46', '風車'),
('1002.jpg', 'student_2', '2023-05-17 10:13:46', '景色真美'),
('1003.jpg', 'student_2', '2023-05-11 10:13:46', '採蚵的人');

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`imgtitle`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
