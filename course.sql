/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : course

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2019-12-21 16:29:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cert
-- ----------------------------
DROP TABLE IF EXISTS `cert`;
CREATE TABLE `cert` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `certName` varchar(20) DEFAULT NULL COMMENT '菜单名称',
  `certUrl` varchar(20) DEFAULT NULL COMMENT '菜单点击路径',
  `certPic` varchar(255) DEFAULT NULL COMMENT '菜单显示图片',
  `parentId` int(11) DEFAULT NULL COMMENT '父亲菜单id,-1表示根',
  `isLeaf` int(11) DEFAULT NULL COMMENT '是否是叶子节点0-是，1-不是',
  `type` tinyint(4) DEFAULT NULL COMMENT '权限1-管理员，2-老师，3-学生',
  `flag` tinyint(4) DEFAULT NULL,
  `createTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cert
-- ----------------------------
INSERT INTO `cert` VALUES ('1', '学生管理', '', 'fa-info', '-1', '1', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('2', '老师管理', '', 'fa-info', '-1', '1', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('3', '留言管理', '', 'fa-info', '-1', '1', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('4', '学生信息查询', '/student', 'fa-info', '1', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('5', '学生信息增加', '/student', 'fa-info', '1', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('7', '老师信息查询', '/teacher', 'fa-info', '2', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('8', '老师信息增加', '/teacher', 'fa-info', '2', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('9', '查看留言', '/liuyan', 'fa-info', '3', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('10', '开设课程', null, 'fa-info', '-1', '1', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('11', '学生选课', null, 'fa-info', '-1', '1', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('12', '课程增加', '/test', 'fa-info', '10', '0', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('13', '所有课程', '/test', 'fa-info', '10', '0', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('14', '学生选课查询', '/test', 'fa-info', '11', '0', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('15', '学生留言', '', 'fa-info', '-1', '1', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('16', '学生留言查看', '/test', 'fa-info', '15', '0', '2', '1', '201912202130');
INSERT INTO `cert` VALUES ('17', '选课中心', '/ss', 'fa-info', '-1', '1', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('18', '在线选课', '/ss', 'fa-info', '17', '0', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('19', '查看所有选课', '/ss', 'fa-info', '17', '0', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('20', '我的留言', '/ss', 'fa-info', '-1', '1', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('21', '在线留言', '/ss', 'fa-info', '20', '0', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('22', '查看所有留言', '/ss', 'fa-info', '20', '0', '3', '1', '201912202135');
INSERT INTO `cert` VALUES ('23', '菜单管理', null, 'fa-info', '-1', '1', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('24', '菜单新增', '/s', 'fa-info', '23', '0', '1', '1', '201912202226');
INSERT INTO `cert` VALUES ('25', '菜单修改', '/s', 'fa-info', '23', '0', '1', '1', '201912202226');

-- ----------------------------
-- Table structure for permison
-- ----------------------------
DROP TABLE IF EXISTS `permison`;
CREATE TABLE `permison` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `permison` varchar(20) DEFAULT NULL,
  `context` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of permison
-- ----------------------------
INSERT INTO `permison` VALUES ('1', '1', 'admin:*', '所有权限');
INSERT INTO `permison` VALUES ('2', '2', 'cource:teacher:*', '课程所有的权限');
INSERT INTO `permison` VALUES ('3', '3', 'test', '课程查看的权限');

-- ----------------------------
-- Table structure for picture
-- ----------------------------
DROP TABLE IF EXISTS `picture`;
CREATE TABLE `picture` (
  `id` varchar(20) NOT NULL,
  `number` varchar(20) DEFAULT NULL COMMENT '编号',
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `forurl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of picture
-- ----------------------------
INSERT INTO `picture` VALUES ('1', 'admin', '管理员图片', '/image/guanliyuan.png', '管理员的照片', null);
INSERT INTO `picture` VALUES ('2', '079155', '老师照片', '/image/laoshione.png', '老师照片', null);
INSERT INTO `picture` VALUES ('3', '7023116096', '学生照片', '/image/xueshengone.png', '学生照片', null);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `name` varchar(20) DEFAULT NULL COMMENT '角色名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '1', '管理员');
INSERT INTO `role` VALUES ('2', '2', '老师');
INSERT INTO `role` VALUES ('3', '3', '学生');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', 'zms');
INSERT INTO `test` VALUES ('2', '曾豪明');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(22) NOT NULL COMMENT '用户主键',
  `number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户编号',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `realname` varchar(20) DEFAULT NULL COMMENT '用户真名',
  `createtime` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `logintime` varchar(30) DEFAULT NULL COMMENT '登录时间',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话号码',
  `address` varchar(50) DEFAULT NULL COMMENT '用户地址',
  `sex` tinyint(4) DEFAULT NULL COMMENT '用户性别，1-男，0-女',
  `ip` varchar(20) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '状态 1-有用，0-已删除',
  `type` tinyint(4) DEFAULT NULL COMMENT '1-管理员 2-老师 3-用户',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number-index` (`number`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '7023116096', '0b719675c9f661e8d454e4a3553721e2', '周淼森', '2019-11-24', '20191221025201', '13970501291', '青山湖区', '1', '127.0.0.1', '1', '3');
INSERT INTO `user` VALUES ('2', '0795', '95d1c328be7f53dad3b412a988adf3b9', '毛毛', '2019-11-24', '20191221011352', '13970502234', '南昌市', '0', '192.168.7.10', '1', '2');
INSERT INTO `user` VALUES ('3', 'admin', '3fed7a346e430ea4c2aa10250928f4de', '管理员', '2019-11-24', '20191221042530', '110110110', '中国', '1', '15679301291', '1', '1');
