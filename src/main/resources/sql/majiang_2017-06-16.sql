# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: majiang
# Generation Time: 2017-06-16 12:42:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table mj_config
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_config`;

CREATE TABLE `mj_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '类型1表示初始房卡',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `config_value` varchar(255) DEFAULT NULL COMMENT '值',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_config` WRITE;
/*!40000 ALTER TABLE `mj_config` DISABLE KEYS */;

INSERT INTO `mj_config` (`id`, `type`, `description`, `config_value`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,1,'初始房卡','1',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_config` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_consumption
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_consumption`;

CREATE TABLE `mj_consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL,
  `extdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mj_manager
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_manager`;

CREATE TABLE `mj_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `user_name` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `parant_number` varchar(16) DEFAULT NULL COMMENT '上级代理编号',
  `parant_name` varchar(255) DEFAULT NULL COMMENT '上级代理名称',
  `level` int(11) DEFAULT NULL COMMENT '代理等级',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_manager` WRITE;
/*!40000 ALTER TABLE `mj_manager` DISABLE KEYS */;

INSERT INTO `mj_manager` (`id`, `number`, `name`, `user_name`, `password`, `parant_number`, `parant_name`, `level`, `create_time`, `update_time`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,1001,'admin','admin','admin','0',NULL,0,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(3,1002,'test1','test1','test1','1001','admin',1,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(4,2001,'test2','test2','test2','1002','test1',2,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(5,1003,'test3','test3','test3','1001','admin',1,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_manager` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_manager_balance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_manager_balance`;

CREATE TABLE `mj_manager_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manager_number` int(11) DEFAULT NULL COMMENT '管理员编号',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '管理者名称',
  `level` int(11) DEFAULT NULL COMMENT '代理等级',
  `parant_number` int(11) DEFAULT NULL COMMENT '上级代理编号',
  `parant_name` varchar(32) DEFAULT NULL COMMENT '上级代理名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table mj_number
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_number`;

CREATE TABLE `mj_number` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '1 一级代理编号 2二级代理编号 3产品编号  4用户编号',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `number_value` int(11) DEFAULT NULL,
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_number` WRITE;
/*!40000 ALTER TABLE `mj_number` DISABLE KEYS */;

INSERT INTO `mj_number` (`id`, `type`, `description`, `number_value`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,1,'一级代理编号',100000,NULL,NULL,NULL),
	(2,2,'二级代理编号',200000,NULL,NULL,NULL),
	(3,3,'产品编号',2000000,NULL,NULL,NULL),
	(4,4,'用户编号',10000000,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_number` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_product
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_product`;

CREATE TABLE `mj_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_product` WRITE;
/*!40000 ALTER TABLE `mj_product` DISABLE KEYS */;

INSERT INTO `mj_product` (`id`, `name`, `number`, `create_time`, `update_time`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,'游戏1',1001,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(2,'游戏2',1002,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(3,'游戏3',1003,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(4,'游戏4',1004,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_product` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_recharge
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_recharge`;

CREATE TABLE `mj_recharge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `manager_number` int(11) DEFAULT NULL COMMENT '管理者编号',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '管理者名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `recharge_type` int(11) DEFAULT NULL COMMENT '重置类型 1代理 2用户',
  `recharge_id` int(11) DEFAULT NULL COMMENT '充值id',
  `recharge_name` varchar(32) DEFAULT NULL COMMENT '充值名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_recharge` WRITE;
/*!40000 ALTER TABLE `mj_recharge` DISABLE KEYS */;

INSERT INTO `mj_recharge` (`id`, `manager_number`, `manager_name`, `product_number`, `product_name`, `recharge_type`, `recharge_id`, `recharge_name`, `balance`, `create_time`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,1001,'admin',1001,'游戏1',1,1002,'test',10,'2017-06-16 12:12:02',NULL,NULL,NULL),
	(2,1001,'admin',1002,'游戏2',2,11111,'测试2',10,'2017-06-16 12:12:22',NULL,NULL,NULL),
	(3,1002,'test',1003,'游戏3',2,11111,'测试2',10,'2017-06-16 12:12:12',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_recharge` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_user`;

CREATE TABLE `mj_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL COMMENT '金额(金币  钻石  房卡)',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信id',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `sex` int(1) DEFAULT NULL,
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `recent_login` datetime DEFAULT NULL COMMENT '最近登录',
  `exdata1` varchar(128) DEFAULT NULL,
  `exdata2` varchar(128) DEFAULT NULL,
  `exdata3` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`user_id`),
  KEY `idx_wxid_productnumber` (`user_id`,`product_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_user` WRITE;
/*!40000 ALTER TABLE `mj_user` DISABLE KEYS */;

INSERT INTO `mj_user` (`id`, `user_id`, `balance`, `wxid`, `product_name`, `product_number`, `sex`, `nick_name`, `create_time`, `recent_login`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,11111,100,'1212121212','游戏1',1001,1,'测试1','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(2,22222,200,'12121','游戏2',1002,0,'测试2','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(3,333333,100,'121212121','游戏3',1003,1,'测试3','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(4,444,100,'11111','游戏4',1004,0,'测试4','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(5,5555,200,'1113333','游戏1',1001,1,'测试5','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
