/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50018
Source Host           : 127.0.0.1:3306
Source Database       : teach

Target Server Type    : MYSQL
Target Server Version : 50018
File Encoding         : 65001

Date: 2018-03-12 23:48:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL auto_increment,
  `account` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `creater` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', '1', '2018-03-12 21:16:26');

-- ----------------------------
-- Table structure for classes
-- ----------------------------
DROP TABLE IF EXISTS `classes`;
CREATE TABLE `classes` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '班级名称',
  `major` int(11) NOT NULL COMMENT '所在专业',
  `term` int(11) NOT NULL COMMENT '学期，因为大学每个学期可能学生都不一样',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of classes
-- ----------------------------

-- ----------------------------
-- Table structure for class_for_teacher
-- ----------------------------
DROP TABLE IF EXISTS `class_for_teacher`;
CREATE TABLE `class_for_teacher` (
  `id` int(11) NOT NULL auto_increment,
  `score` int(11) NOT NULL,
  `teacher` int(11) NOT NULL,
  `class` int(11) NOT NULL,
  `evaluate_grade` decimal(10,0) default NULL,
  `evaluate_count` int(11) default NULL,
  `question_grade` decimal(10,0) default NULL,
  `question_count` int(11) default NULL,
  `assessment_grade` decimal(10,0) default NULL,
  `assessment_count` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_for_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for evaluate
-- ----------------------------
DROP TABLE IF EXISTS `evaluate`;
CREATE TABLE `evaluate` (
  `id` int(11) NOT NULL auto_increment,
  `class_for_teacher` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  `text` text NOT NULL,
  `result` varchar(64) NOT NULL,
  `creater` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `creater_type` varchar(32) NOT NULL,
  `flag` tinyint(1) NOT NULL COMMENT '是否匿名标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluate
-- ----------------------------

-- ----------------------------
-- Table structure for for_question
-- ----------------------------
DROP TABLE IF EXISTS `for_question`;
CREATE TABLE `for_question` (
  `id` int(11) NOT NULL auto_increment,
  `question` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  `text` text NOT NULL,
  `creater` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `creater_type` varchar(32) NOT NULL,
  `flag` tinyint(1) NOT NULL COMMENT '是否匿名标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of for_question
-- ----------------------------

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `number` varchar(32) NOT NULL COMMENT '专业编号',
  `name` varchar(64) NOT NULL COMMENT '专业名称',
  `schooling` tinyint(4) NOT NULL COMMENT '学制',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('1', '01', '软件工程', '4');
INSERT INTO `major` VALUES ('2', '02', '土木工程', '4');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `creater` int(11) NOT NULL,
  `creater_type` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for question
-- ----------------------------
DROP TABLE IF EXISTS `question`;
CREATE TABLE `question` (
  `id` int(11) NOT NULL auto_increment,
  `class_for_teacher` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  `text` text NOT NULL,
  `result` varchar(64) default NULL,
  `creater` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `creater_type` varchar(32) NOT NULL,
  `flag` tinyint(1) NOT NULL COMMENT '是否匿名标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question
-- ----------------------------

-- ----------------------------
-- Table structure for question_bank
-- ----------------------------
DROP TABLE IF EXISTS `question_bank`;
CREATE TABLE `question_bank` (
  `id` int(11) NOT NULL auto_increment,
  `type` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `remarks` text,
  `content_A` varchar(255) NOT NULL,
  `result_A` decimal(10,0) NOT NULL,
  `content_B` varchar(255) NOT NULL,
  `result_B` decimal(10,0) NOT NULL,
  `content_C` varchar(255) default NULL,
  `result_C` decimal(10,0) default NULL,
  `content_D` varchar(255) default NULL,
  `result_D` decimal(10,0) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_bank
-- ----------------------------

-- ----------------------------
-- Table structure for question_template
-- ----------------------------
DROP TABLE IF EXISTS `question_template`;
CREATE TABLE `question_template` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `question_list` varchar(255) NOT NULL,
  `creater` int(11) NOT NULL,
  `creater_type` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `updater` int(11) default NULL,
  `updater_type` varchar(64) default NULL,
  `update_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_template
-- ----------------------------

-- ----------------------------
-- Table structure for question_type
-- ----------------------------
DROP TABLE IF EXISTS `question_type`;
CREATE TABLE `question_type` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of question_type
-- ----------------------------

-- ----------------------------
-- Table structure for receive_message
-- ----------------------------
DROP TABLE IF EXISTS `receive_message`;
CREATE TABLE `receive_message` (
  `id` int(11) NOT NULL auto_increment,
  `message` int(11) NOT NULL,
  `recipient` int(11) NOT NULL,
  `recipient_type` varchar(64) NOT NULL,
  `flag` tinyint(1) NOT NULL COMMENT '已读或未读标记',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of receive_message
-- ----------------------------

-- ----------------------------
-- Table structure for register
-- ----------------------------
DROP TABLE IF EXISTS `register`;
CREATE TABLE `register` (
  `id` int(11) NOT NULL auto_increment,
  `number` varchar(32) NOT NULL,
  `name` varchar(64) NOT NULL,
  `password` varchar(16) NOT NULL,
  `header` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of register
-- ----------------------------
INSERT INTO `register` VALUES ('1', '020934', '德信', '123456', null);

-- ----------------------------
-- Table structure for report
-- ----------------------------
DROP TABLE IF EXISTS `report`;
CREATE TABLE `report` (
  `id` int(11) NOT NULL auto_increment,
  `reported_id` int(11) NOT NULL,
  `reported_type` varchar(64) NOT NULL,
  `reason` varchar(255) NOT NULL,
  `reporter_id` int(11) NOT NULL,
  `reporter_type` varchar(64) NOT NULL,
  `creater` int(11) NOT NULL,
  `creater_type` varchar(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `handler` int(11) default NULL,
  `handle_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of report
-- ----------------------------

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(64) NOT NULL,
  `major` int(11) NOT NULL,
  `term` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `result` decimal(10,0) default NULL COMMENT '成绩',
  `credit` decimal(10,0) default NULL COMMENT '学分',
  `hours` decimal(10,0) default NULL COMMENT '学时 单位：课时',
  `test_way` varchar(64) default NULL COMMENT '考核方式',
  `remarks` varchar(255) default NULL COMMENT '备注',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score
-- ----------------------------

-- ----------------------------
-- Table structure for score_type
-- ----------------------------
DROP TABLE IF EXISTS `score_type`;
CREATE TABLE `score_type` (
  ` id` int(11) NOT NULL auto_increment,
  `name` varchar(64) NOT NULL,
  PRIMARY KEY  (` id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of score_type
-- ----------------------------
INSERT INTO `score_type` VALUES ('1', '专业必修');
INSERT INTO `score_type` VALUES ('2', '专业选修');
INSERT INTO `score_type` VALUES ('3', '通识必修');
INSERT INTO `score_type` VALUES ('4', '通识选修');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `stu_id` varchar(32) NOT NULL COMMENT '学号',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `sex` tinyint(1) NOT NULL COMMENT '性别',
  `id_card` char(18) NOT NULL COMMENT '身份证',
  `header` varchar(255) default NULL COMMENT '头像',
  `password` varchar(16) NOT NULL COMMENT '密码',
  `time_of_enrollment` datetime NOT NULL COMMENT '入学时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '20144206068', '欧文惠', '1', '430404199612112037', null, '102037', '2014-09-15 22:21:36');

-- ----------------------------
-- Table structure for stu_for_class
-- ----------------------------
DROP TABLE IF EXISTS `stu_for_class`;
CREATE TABLE `stu_for_class` (
  `id` int(11) NOT NULL auto_increment,
  `stu_id` int(11) NOT NULL COMMENT '学生编号',
  `class_id` int(11) NOT NULL COMMENT '班级编号',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of stu_for_class
-- ----------------------------

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` int(11) NOT NULL auto_increment,
  `number` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `password` varchar(16) NOT NULL,
  `major` int(11) NOT NULL,
  `business` varchar(64) default NULL COMMENT '职称',
  `email` varchar(128) default NULL,
  `phone` varchar(16) default NULL,
  `QQ` varchar(32) default NULL,
  `wechat` varchar(64) default NULL,
  `header` varchar(255) default NULL,
  `evaluate` varchar(255) default NULL COMMENT '总评',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1', '010943', '欧辰', '1', '123456', '1', '讲师', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for term
-- ----------------------------
DROP TABLE IF EXISTS `term`;
CREATE TABLE `term` (
  `id` int(11) NOT NULL auto_increment COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '学期名称 例如2014—2015下',
  `start_time` datetime NOT NULL COMMENT '学期开始时间',
  `end_time` datetime NOT NULL COMMENT '学期结束时间',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of term
-- ----------------------------
