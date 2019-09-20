# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: majiang
# Generation Time: 2017-06-17 15:03:56 +0000
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
	(1,1,'初始房卡','10',NULL,NULL,NULL);

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
	(3,1002,'test1','test1','111111','1001','admin',1,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(4,2001,'test2','test2','111111','1002','test1',2,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(5,1003,'test3','test3','111111','1001','admin',1,'2017-06-16 00:00:00','2017-06-16 00:00:00',NULL,NULL,NULL),
	(7,100002,'测试','mytest','111111','1001','admin',1,'2017-06-17 16:23:03','2017-06-17 16:23:03',NULL,NULL,NULL),
	(8,100003,'我是一个代理','woshidaili','111111','1001','admin',1,'2017-06-17 17:00:37','2017-06-17 17:00:37',NULL,NULL,NULL),
	(9,200001,'1001创建的代理','1011','111111','1002','test1',2,'2017-06-17 21:31:52','2017-06-17 21:31:52',NULL,NULL,NULL);

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

LOCK TABLES `mj_manager_balance` WRITE;
/*!40000 ALTER TABLE `mj_manager_balance` DISABLE KEYS */;

INSERT INTO `mj_manager_balance` (`id`, `manager_number`, `manager_name`, `level`, `parant_number`, `parant_name`, `product_number`, `product_name`, `balance`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,1001,'admin',0,0,'admin',1001,'江湖不再见',0,NULL,NULL,NULL),
	(2,1001,'admin',0,0,'admin',1002,'游戏2',198759,NULL,NULL,NULL),
	(3,1001,'admin',0,0,'admin',1003,'游戏3',0,NULL,NULL,NULL),
	(4,1001,'admin',0,0,'admin',1004,'游戏4',399900,NULL,NULL,NULL),
	(5,100003,'我是一个代理',1,1001,'admin',1001,'江湖不再见',360,NULL,NULL,NULL),
	(6,100003,'我是一个代理',1,1001,'admin',1002,'游戏2',280,NULL,NULL,NULL),
	(7,100002,'测试',1,1001,'admin',1002,'游戏2',960,NULL,NULL,NULL),
	(8,100002,'测试',1,1001,'admin',1004,'游戏4',160,NULL,NULL,NULL),
	(9,100002,'测试',1,1001,'admin',1003,'游戏3',300060,NULL,NULL,NULL),
	(10,1003,'test3',1,1001,'admin',1001,'江湖不再见',99760,NULL,NULL,NULL),
	(11,1001,'admin',0,1001,'admin',2000008,'223',99990000,NULL,NULL,NULL),
	(12,1002,'test1',1,1001,'admin',2000008,'223',9590,NULL,NULL,NULL),
	(13,200001,'1001创建的代理',2,1002,'test1',2000008,'223',350,NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_manager_balance` ENABLE KEYS */;
UNLOCK TABLES;


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
	(1,1,'一级代理编号',100003,NULL,NULL,NULL),
	(2,2,'二级代理编号',200001,NULL,NULL,NULL),
	(3,3,'产品编号',2000008,NULL,NULL,NULL),
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
	(1,'江湖不再见',1001,'2017-06-16 10:10:10','2017-06-17 14:59:51',NULL,NULL,NULL),
	(2,'游戏2',1002,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(3,'游戏3',1003,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(4,'游戏4',1004,'2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(5,'游戏5',2000001,'2017-06-17 14:42:02','2017-06-17 14:42:02',NULL,NULL,NULL),
	(6,'12121',2000002,'2017-06-17 14:44:41','2017-06-17 14:44:41',NULL,NULL,NULL),
	(7,'江湖再见',2000003,'2017-06-17 14:58:29','2017-06-17 14:58:29',NULL,NULL,NULL),
	(8,'龙在江湖',2000004,'2017-06-17 15:03:51','2017-06-17 15:03:51',NULL,NULL,NULL),
	(9,'龙不在江湖',2000005,'2017-06-17 15:04:01','2017-06-17 15:04:01',NULL,NULL,NULL),
	(10,'再战江湖',2000006,'2017-06-17 15:04:17','2017-06-17 15:04:17',NULL,NULL,NULL),
	(11,'不战江湖1',2000007,'2017-06-17 15:04:31','2017-06-17 21:10:26',NULL,NULL,NULL),
	(12,'223',2000008,'2017-06-17 21:10:43','2017-06-17 21:10:43',NULL,NULL,NULL);

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
	(3,1002,'test',1003,'游戏3',2,11111,'测试2',10,'2017-06-16 12:12:12',NULL,NULL,NULL),
	(4,1001,'admin',1001,'江湖不再见',1,100003,'我是一个代理',NULL,'2017-06-17 18:28:10',NULL,NULL,NULL),
	(5,1001,'admin',1001,'江湖不再见',1,100003,'我是一个代理',NULL,'2017-06-17 18:28:16',NULL,NULL,NULL),
	(6,1001,'admin',1002,'游戏2',1,100003,'我是一个代理',NULL,'2017-06-17 18:28:31',NULL,NULL,NULL),
	(7,1001,'admin',1002,'游戏2',1,100002,'测试',NULL,'2017-06-17 18:32:47',NULL,NULL,NULL),
	(8,1001,'admin',1002,'游戏2',1,100002,'测试',NULL,'2017-06-17 18:33:01',NULL,NULL,NULL),
	(9,1001,'admin',1004,'游戏4',1,100002,'测试',NULL,'2017-06-17 18:33:47',NULL,NULL,NULL),
	(10,1001,'admin',1003,'游戏3',1,100002,'测试',NULL,'2017-06-17 18:34:05',NULL,NULL,NULL),
	(11,1001,'admin',1001,'江湖不再见',1,1003,'test3',99700,'2017-06-17 18:37:31',NULL,NULL,NULL),
	(12,1001,'admin',1002,'游戏2',1,100002,'测试',500,'2017-06-17 19:03:36',NULL,NULL,NULL),
	(13,1001,'admin',1002,'游戏2',2,22222,'测试2',1,'2017-06-17 20:10:47',NULL,NULL,NULL),
	(14,1001,'admin',1002,'游戏2',2,22222,'测试2',100,'2017-06-17 20:12:14',NULL,NULL,NULL),
	(15,1001,'admin',1002,'游戏2',1,100003,'我是一个代理',20,'2017-06-17 21:11:59',NULL,NULL,NULL),
	(16,1001,'admin',1002,'游戏2',2,22222,'测试2',10,'2017-06-17 21:13:01',NULL,NULL,NULL),
	(17,1001,'admin',1002,'游戏2',2,22222,'测试2',10,'2017-06-17 21:13:06',NULL,NULL,NULL),
	(18,1001,'admin',2000008,'223',1,1002,'test1',10000,'2017-06-17 21:23:40',NULL,NULL,NULL),
	(19,1002,'test1',2000008,'223',1,200001,'1001创建的代理',100,'2017-06-17 21:33:40',NULL,NULL,NULL),
	(20,1002,'test1',2000008,'223',1,200001,'1001创建的代理',300,'2017-06-17 21:33:57',NULL,NULL,NULL),
	(21,1002,'test1',2000008,'223',2,6666,'666',10,'2017-06-17 22:59:25',NULL,NULL,NULL),
	(22,200001,'1001创建的代理',2000008,'223',2,6666,'666',50,'2017-06-17 23:03:15',NULL,NULL,NULL);

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
	(1,11111,112,'1212121212','游戏1',1001,1,'测试1','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(2,22222,333,'12121','游戏2',1002,0,'测试2','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(3,333333,112,'121212121','游戏3',1003,1,'测试3','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(4,444,112,'11111','游戏4',1004,0,'测试4','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(5,5555,212,'1113333','游戏1',1001,1,'测试5','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL),
	(6,6666,70,'12121','223',2000008,1,'666','2017-06-16 10:10:10','2017-06-16 10:10:10',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
