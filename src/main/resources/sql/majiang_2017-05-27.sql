# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: majiang
# Generation Time: 2017-05-27 09:22:52 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table mj_order
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_order`;

CREATE TABLE `mj_order` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mj_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_product`;

CREATE TABLE `mj_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mj_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_user`;

CREATE TABLE `mj_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `userid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `level` int(1) DEFAULT NULL COMMENT '用户级别',
  `parant_level` int(1) DEFAULT NULL COMMENT '用户上级',
  `parent_userid` int(11) DEFAULT NULL COMMENT '上级ID',
  `balance` int(11) DEFAULT NULL COMMENT '用户余额',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信ID',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `recent_login` date DEFAULT NULL COMMENT '最近登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
