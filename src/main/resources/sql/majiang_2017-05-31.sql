/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : majiang

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-05-31 22:53:23
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `mj_developer`
-- ----------------------------
DROP TABLE IF EXISTS `mj_developer`;
CREATE TABLE `mj_developer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `name` varchar(32) DEFAULT NULL COMMENT '名称',
  `state` int(11) DEFAULT NULL COMMENT '状态',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_developer
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_manager`
-- ----------------------------
DROP TABLE IF EXISTS `mj_manager`;
CREATE TABLE `mj_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `username` varchar(255) DEFAULT NULL COMMENT '登录名',
  `passward` varchar(255) DEFAULT NULL COMMENT '密码',
  `parant_id` int(11) DEFAULT NULL COMMENT '上级代理id',
  `level` int(11) DEFAULT NULL COMMENT '代理等级',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `mj_product`
-- ----------------------------
DROP TABLE IF EXISTS `mj_product`;
CREATE TABLE `mj_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '名称',
  `developer_id` int(11) DEFAULT NULL COMMENT '开发者id',
  `number` int(11) DEFAULT NULL COMMENT '编号',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '修改时间',
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
  `manager_id` int(11) DEFAULT NULL COMMENT '管理者id',
  `manager_name` varchar(32) DEFAULT NULL COMMENT '管理者名称',
  `product_id` int(11) DEFAULT NULL COMMENT '产品id',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(32) DEFAULT NULL COMMENT '玩家名称',
  `balance` int(11) DEFAULT NULL COMMENT '金额',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
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
  `balance` int(11) DEFAULT NULL COMMENT '金额(金币  钻石  房卡)',
  `wxid` varchar(32) DEFAULT NULL COMMENT '微信id',
  `sex` int(1) DEFAULT NULL COMMENT '性别',
  `nickname` varchar(32) DEFAULT NULL COMMENT '昵称',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `province` varchar(32) DEFAULT NULL COMMENT '省份',
  `city` varchar(32) DEFAULT NULL COMMENT '城市',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像url地址',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `recent_login` date DEFAULT NULL COMMENT '最近登录',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mj_user
-- ----------------------------
