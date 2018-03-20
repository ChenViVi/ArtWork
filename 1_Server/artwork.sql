-- phpMyAdmin SQL Dump
-- version 4.0.10.20
-- https://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2018-03-20 22:43:29
-- 服务器版本: 5.7.21
-- PHP 版本: 5.3.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `artwork`
--

-- --------------------------------------------------------

--
-- 表的结构 `msg`
--

CREATE TABLE IF NOT EXISTS `msg` (
  `code` int(11) NOT NULL AUTO_INCREMENT,
  `msg` varchar(20) NOT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=207 ;

--
-- 转存表中的数据 `msg`
--

INSERT INTO `msg` (`code`, `msg`) VALUES
(201, '密码错误'),
(202, '此邮箱已注册'),
(204, '此用户名已被使用'),
(205, '此邮箱未注册'),
(206, '找不到此用户');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `avatar` text NOT NULL,
  `password` varchar(20) NOT NULL,
  `email` varchar(20) NOT NULL,
  `birth` date NOT NULL,
  `sex` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `name`, `avatar`, `password`, `email`, `birth`, `sex`) VALUES
(1, 'vivi', 'http://owtt2jsve.bkt.clouddn.com/def_avatar.png', 'qqqqqq', '932942491@qq.com', '1970-01-18', 1);

-- --------------------------------------------------------

--
-- 表的结构 `work`
--

CREATE TABLE IF NOT EXISTS `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
