-- phpMyAdmin SQL Dump
-- version 4.0.10.20
-- https://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2018-03-24 17:21:47
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
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=208 ;

--
-- 转存表中的数据 `msg`
--

INSERT INTO `msg` (`code`, `msg`) VALUES
(201, '密码错误'),
(202, '此邮箱已注册'),
(204, '此用户名已被使用'),
(205, '此邮箱未注册'),
(206, '找不到此用户'),
(207, '没有此分类');

-- --------------------------------------------------------

--
-- 表的结构 `type`
--

CREATE TABLE IF NOT EXISTS `type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- 转存表中的数据 `type`
--

INSERT INTO `type` (`id`, `name`) VALUES
(1, '手绘'),
(2, '风景'),
(3, '街拍'),
(4, '动漫'),
(5, '简约'),
(6, '自然');

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
  `qqSign` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `name`, `avatar`, `password`, `email`, `birth`, `sex`, `qqSign`) VALUES
(9, 'vivi', 'http://139.199.32.74/files/img/1521648852024.jpg', 'qqqqqq', '932942491@qq.com', '1970-01-18', 1, 'eJxFkF1vgjAUhv8Lt1tcW1oYS0wGzhFU5ohItt2QDooeP6BgUeey-z5sdLt9n-PmnOd8G-Fk1uNSQp5ylZpNbjwYyLjVsThKaETKCyWaLsaMMYLQle5Fs4Oq7ABBmGFiIvQPIRelggJ00TGJQwl18GNd97Jqe5nZwaKD4XA*CKInP9rMlXV3cOKQg1cyryD5ajxuLTV1n6P2Jkn2vmLR4PjpBgsvGMVLeqKvp83Hu4vIqLaHNU-Cl2Q2iSCjy-WbL93DinDUvy7L16nWPIvQ7lDbvnfwBSrYCi1IsG1b5p8Fz7KqLVWqvqTQf-n5BXiZWYY_'),
(10, 'jim', 'http://139.199.32.74/files/img/1521649505316.jpg', 'qqqqqq', '792585582@qq.com', '1970-01-18', 1, 'eJxFkF1vgjAUhv8LtyzaFmrbJUsWCTrjtuiQLXpDgBZWWMuHxWjM-vuQYHb7PufNOc*5WrvXYBLXteRRbCKn5dajBayHIRbnWrYiijMj2j6GGGMEwJ2eRHuUle4BAhBD5ADwDyUX2shMDkXCEKYYU-TcNJO0UuPMUeY9fPNDb7X1oC58xR2W2N56rUoNdZgs38v5zxJ*XoJCTB1-1tjsQx22qzzch8nG3lP-m7MXsmHFIgsImp7ZKSjnXepRd4E6dfhy8-LpvoyX0aB5E3H7QwmhDI7QSCUGQQQpJA6ajXmcplWnTWQutRj*8vsHG2RYtw__'),
(11, 'test', 'http://139.199.32.74/files/img/1521650486130.jpg', 'qqqqqq', 'test@qq.com', '1970-01-18', 1, 'eJxFkF1PgzAUQP8Lr5ilhRWGiYlkYQZniVLZ9IlU6MxV*zFayYzxv1sJi6-n5Obec7*Dxzu24MZA33LXxkMfXAYouJiwOBkYRMsPTgweY0JIhNDZjmKwoJUXEcIERzFC-xJ6oRwcYBp0wrrr43HRaTlrC6*e06JZl5vaxHWtnbZNI9fircb7MJRJgUkG*6pRCUanKt7malPlUOTmJtl2VArGUL8ry-GDuh3FBWNP1N5DSl9uH5ZgRx7S56vzsv69nQr-Gpb*xjRdZXiWDqSY2iK88n0omznvOv2pXOu*jJhe8vMLktxY-Q__');

-- --------------------------------------------------------

--
-- 表的结构 `work`
--

CREATE TABLE IF NOT EXISTS `work` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `type_id` int(11) NOT NULL,
  `url` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `work`
--

INSERT INTO `work` (`id`, `uid`, `type_id`, `url`) VALUES
(1, 9, 1, 'http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg'),
(2, 9, 1, 'http://a.hiphotos.baidu.com/zhidao/pic/item/adaf2edda3cc7cd9f595fcf03d01213fb80e915b.jpg');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
