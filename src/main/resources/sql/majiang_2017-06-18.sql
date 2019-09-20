# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.17)
# Database: majiang
# Generation Time: 2017-06-18 13:57:11 +0000
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
	(1,0,'admin','admin','admin','0','admin',0,'2017-06-18 00:00:00','2017-06-18 00:00:00',NULL,NULL,NULL),
	(2,10000002,'清雨','800006','888888','0','admin',1,'2017-06-18 21:43:55','2017-06-18 21:43:55',NULL,NULL,NULL),
	(3,10000003,'晴天','800007','888888','0','admin',1,'2017-06-18 21:44:27','2017-06-18 21:44:27',NULL,NULL,NULL),
	(4,10000004,'雨天','800018','888888','0','admin',1,'2017-06-18 21:50:23','2017-06-18 21:50:23',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_manager` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_manager_balance
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_manager_balance`;

CREATE TABLE `mj_manager_balance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `manager_number` int(11) DEFAULT NULL COMMENT '管理员编号',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '管理者名称',
  `manager_username` varchar(32) DEFAULT NULL COMMENT '登陆账号',
  `level` int(11) DEFAULT NULL COMMENT '代理等级',
  `parant_number` int(11) DEFAULT NULL COMMENT '上级代理编号',
  `parant_name` varchar(32) DEFAULT NULL COMMENT '上级代理名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `cost` int(11) DEFAULT NULL COMMENT '累计消费',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `mj_manager_balance` WRITE;
/*!40000 ALTER TABLE `mj_manager_balance` DISABLE KEYS */;

INSERT INTO `mj_manager_balance` (`id`, `manager_number`, `manager_name`, `manager_username`, `level`, `parant_number`, `parant_name`, `product_number`, `product_name`, `balance`, `cost`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,0,'admin','admin',0,0,'admin',100002,'顺手麻将',99999968,32,NULL,NULL,NULL),
	(2,0,'admin',NULL,0,0,'admin',100003,'反手麻将',99999988,12,NULL,NULL,NULL),
	(3,10000004,'雨天','800018',1,0,'admin',100002,'顺手麻将',12,0,NULL,NULL,NULL),
	(4,10000004,'雨天','800018',1,0,'admin',100003,'反手麻将',12,0,NULL,NULL,NULL),
	(5,10000003,'晴天','800007',1,0,'admin',100002,'顺手麻将',20,0,NULL,NULL,NULL);

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
	(1,1,'一级代理编号',10000004,NULL,NULL,NULL),
	(2,2,'二级代理编号',20000000,NULL,NULL,NULL),
	(3,3,'产品编号',100003,NULL,NULL,NULL),
	(4,4,'用户编号',10000000,NULL,NULL,NULL),
	(5,5,'用户ID',800026,NULL,NULL,NULL);

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
	(1,'顺手麻将',100002,'2017-06-18 16:06:07','2017-06-18 16:06:07',NULL,NULL,NULL),
	(2,'反手麻将',100003,'2017-06-18 21:38:33','2017-06-18 21:38:33',NULL,NULL,NULL);

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
	(1,0,'admin',100002,'顺手麻将',1,10000004,'雨天',12,'2017-06-18 21:51:00',NULL,NULL,NULL),
	(2,0,'admin',100003,'反手麻将',1,10000004,'雨天',12,'2017-06-18 21:51:14',NULL,NULL,NULL),
	(3,0,'admin',100002,'顺手麻将',1,10000003,'晴天',10,'2017-06-18 21:51:29',NULL,NULL,NULL),
	(4,0,'admin',100002,'顺手麻将',1,10000003,'晴天',10,'2017-06-18 21:51:33',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_recharge` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table mj_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `mj_user`;

CREATE TABLE `mj_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL COMMENT '金额(金币  钻石  房卡)',
  `cost` int(11) DEFAULT NULL COMMENT '累计消费',
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

INSERT INTO `mj_user` (`id`, `user_id`, `balance`, `cost`, `wxid`, `product_name`, `product_number`, `sex`, `nick_name`, `create_time`, `recent_login`, `exdata1`, `exdata2`, `exdata3`)
VALUES
	(1,10000001,10,0,'wxid001','顺手麻将',100002,1,'顺手玩家1','2017-06-18 00:00:00','2017-06-18 00:00:00',NULL,NULL,NULL),
	(2,10000002,10,0,'wxid002','反手麻将',100003,0,'反手玩家1','2017-06-18 00:00:00','2017-06-18 00:00:00',NULL,NULL,NULL);

/*!40000 ALTER TABLE `mj_user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
