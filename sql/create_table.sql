-- create account table
DROP TABLE IF EXISTS `md_account`;
CREATE TABLE `md_account` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '注册邮箱',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT 'md5密码',
  `ctime` int(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `utime` int(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  `locked` tinyint(1) NOT NULL DEFAULT '1' COMMENT '账号是否被锁定',
  `access_token` varchar(150) NOT NULL COMMENT '访问网站的身份令牌',
  `expires` int(13) NOT NULL DEFAULT '0' COMMENT '身份令牌失效窗口，单位毫秒',
  `role` varchar(10) NOT NULL DEFAULT 'USER' COMMENT '用户角色',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=373 DEFAULT CHARSET=utf8;

--create profile table
DROP TABLE IF EXISTS `md_profile`;
CREATE TABLE `md_profile` (
  `profile_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '用户信息id',
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `nickname` varchar(50) NOT NULL COMMENT '用户名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别',
  `ctime` int(13) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `utime` int(13) NOT NULL DEFAULT '0' COMMENT '更新时间',
  PRIMARY KEY (`profile_id`),
  KEY `user_id_indx` (`user_id`),
  CONSTRAINT `profile_user_id` FOREIGN KEY (`user_id`) REFERENCES `md_account` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='用户信息表';
ALTER TABLE `md_profile`
ADD COLUMN `description` TEXT NULL DEFAULT NULL AFTER `nickname`;

-- create config table
DROP TABLE IF EXISTS `md_config`;
CREATE TABLE `md_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(20) DEFAULT NULL,
  `attribute_name` varchar(45) DEFAULT NULL,
  `attribute_value` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--create check table
DROP TABLE IF EXISTS `md_check`;
CREATE TABLE `md_check` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `answer` SMALLINT NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- create complete table
DROP TABLE IF EXISTS `md_completion`;
CREATE TABLE `md_completion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `answer` VARCHAR(128) NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- create single select table
DROP TABLE IF EXISTS `md_single_select`;
CREATE TABLE `md_single_select` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `a` VARCHAR(128) NOT NULL,
  `b` VARCHAR(128) NOT NULL,
  `c` VARCHAR(128) NOT NULL,
  `d` VARCHAR(128) NOT NULL,
  `answer` VARCHAR(128) NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- create multi select table
DROP TABLE IF EXISTS `md_multi_select`;
CREATE TABLE `md_multi_select` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `a` VARCHAR(128) NOT NULL,
  `b` VARCHAR(128) NOT NULL,
  `c` VARCHAR(128) NOT NULL,
  `d` VARCHAR(128) NOT NULL,
  `answer` VARCHAR(128) NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- create short answer table
DROP TABLE IF EXISTS `md_short_answer`;
CREATE TABLE `md_short_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `answer` text NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- create short answer table
DROP TABLE IF EXISTS `md_essay`;
CREATE TABLE `md_essay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) NOT NULL COMMENT '用户id',
  `content` text NOT NULL,
  `answer` text NOT NULL,
  `ctime` int(13) DEFAULT '0' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;