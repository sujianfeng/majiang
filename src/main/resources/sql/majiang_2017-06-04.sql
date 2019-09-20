/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : majiang

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-06-04 17:19:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mj_config`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_config
-- ----------------------------
INSERT INTO `mj_config` VALUES ('1', '1', '初始房卡', '1', null, null, null);

-- ----------------------------
-- Table structure for `mj_consumption`
-- ----------------------------
DROP TABLE IF EXISTS `mj_consumption`;
CREATE TABLE `mj_consumption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` date DEFAULT NULL,
  `extdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_consumption
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_manager`
-- ----------------------------
DROP TABLE IF EXISTS `mj_manager`;
CREATE TABLE `mj_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `username` varchar(255) DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `parant_number` varchar(16) DEFAULT NULL COMMENT '上级代理编号',
  `parant_name` varchar(255) DEFAULT NULL COMMENT '上级代理名称',
  `level` int(11) DEFAULT NULL COMMENT '代理等级',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_manager_balance`
-- ----------------------------
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

-- ----------------------------
-- Records of mj_manager_balance
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_number`
-- ----------------------------
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_number
-- ----------------------------
INSERT INTO `mj_number` VALUES ('1', '1', '一级代理编号', '100000', null, null, null);
INSERT INTO `mj_number` VALUES ('2', '2', '二级代理编号', '200000', null, null, null);
INSERT INTO `mj_number` VALUES ('3', '3', '产品编号', '2000000', null, null, null);
INSERT INTO `mj_number` VALUES ('4', '4', '用户编号', '10000000', null, null, null);

-- ----------------------------
-- Table structure for `mj_product`
-- ----------------------------
DROP TABLE IF EXISTS `mj_product`;
CREATE TABLE `mj_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_product
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_recharge`
-- ----------------------------
DROP TABLE IF EXISTS `mj_recharge`;
CREATE TABLE `mj_recharge` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `manager_number` int(11) DEFAULT NULL COMMENT '管理者编号',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '管理者名称',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品名称',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `exdata1` varchar(255) DEFAULT NULL,
  `exdata2` varchar(255) DEFAULT NULL,
  `exdata3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_recharge
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_user`
-- ----------------------------
DROP TABLE IF EXISTS `mj_user`;
CREATE TABLE `mj_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL COMMENT '金额(金币  钻石  房卡)',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信id',
  `product_name` varchar(32) DEFAULT NULL COMMENT '产品',
  `product_number` int(11) DEFAULT NULL COMMENT '产品编号',
  `sex` int(1) DEFAULT NULL,
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `recent_login` date DEFAULT NULL COMMENT '最近登录',
  PRIMARY KEY (`id`),
  KEY `idx_userid` (`user_id`),
  KEY `idx_wxid_productnumber` (`user_id`,`product_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_user
-- ----------------------------
