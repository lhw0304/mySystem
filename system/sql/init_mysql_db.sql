-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema mode
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mode
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mode` DEFAULT CHARACTER SET utf8mb4 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `mode` ;

-- -----------------------------------------------------
-- Table `mode`.`md_account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_account` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_account` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'User id',
  `username` VARCHAR(50) NOT NULL COMMENT 'User name',
  `password` VARCHAR(100) NOT NULL DEFAULT '' COMMENT 'MD5 password',
  `email` VARCHAR(100) NULL COMMENT 'User email',
  `mobile` VARCHAR(30) NULL COMMENT 'User mobile number',
  `access_token` VARCHAR(255) NULL DEFAULT NULL COMMENT 'User access token',
  `expire_time` BIGINT(13) NULL DEFAULT NULL COMMENT 'Access token expire time',
  `role` VARCHAR(50) NOT NULL DEFAULT 'USER' COMMENT 'User role: USER, CRITIC, ADMIN, MERCHANT, STYLIST',
  `status` TINYINT(2) NOT NULL DEFAULT '0' COMMENT 'User account status',
  `activate_key` INT(10) NULL COMMENT 'User sign up activate key',
  `reset_password_key` INT(10) NULL COMMENT 'User reset password key',
  `source` VARCHAR(50) NOT NULL DEFAULT 'MODE',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_idx` (`username` ASC),
  UNIQUE INDEX `email_idx` (`email` ASC),
  UNIQUE INDEX `mobile_idx` (`mobile` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_ad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_ad` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_ad` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Indicates the ad type, e.g. article, item, collection, modelook and so on',
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `display_page` VARCHAR(50) NULL DEFAULT NULL COMMENT 'Indicates where to display the ad, e.g. startup page, discovery page',
  `default_image` VARCHAR(255) NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `status` TINYINT(2) NULL DEFAULT '1',
  `country_code` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Country codes divided by ‘,’',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_appsflyer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_appsflyer` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_appsflyer` (
  `appsflyer_device_id` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
  `app_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `platform` VARCHAR(45) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `click_time` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `install_time` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `agency` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `media_source` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `campaign` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_campaign_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_campaign_name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_adset_name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_adset_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_adgroup_name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `fb_adgroup_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_siteid` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `cost_per_install` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `country_code` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `city` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `ip` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `wifi` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `language` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `customer_user_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `idfa` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `idfv` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `mac` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `device_name` VARCHAR(100) CHARACTER SET 'utf8mb4' NULL DEFAULT NULL,
  `device_type` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `os_version` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `sdk_version` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `app_version` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `event_type` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `event_name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `event_value` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `currency` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `event_time` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_sub1` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_sub2` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_sub3` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_sub4` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `af_sub5` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `click_url` TEXT CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `attribution_type` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `http_referrer` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `operator` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `advertising_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `android_id` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `imei` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `device_brand` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `device_model` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  `user_id` INT(10) NULL DEFAULT '0',
  `uuid` VARCHAR(255) CHARACTER SET 'utf8' NULL DEFAULT NULL,
  PRIMARY KEY (`appsflyer_device_id`),
  UNIQUE INDEX `user_id_idx` (`user_id` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_stylist`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_stylist` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_stylist` (
  `user_id` INT(10) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL DEFAULT '',
  `logo` VARCHAR(255) NOT NULL DEFAULT '',
  `description` TEXT NULL DEFAULT NULL,
  `status` TINYINT(3) NOT NULL DEFAULT '1',
  `country_code` VARCHAR(500) NULL DEFAULT NULL,
  `followers` INT(10) NOT NULL DEFAULT '0' COMMENT 'Brand likes count',
  `articles` INT(10) NOT NULL DEFAULT '0',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`user_id`),
  INDEX `brand_name_idx` (`name` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_config`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_config` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_config` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(50) NULL DEFAULT NULL,
  `attribute_name` VARCHAR(50) NULL DEFAULT NULL,
  `attribute_value` TEXT NULL DEFAULT NULL,
  `country_code` VARCHAR(500) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_feedback`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_feedback` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_feedback` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(40) NULL DEFAULT NULL,
  `sender` INT(10) NULL DEFAULT NULL,
  `receiver` INT(10) NULL DEFAULT NULL,
  `content` VARCHAR(500) NULL DEFAULT NULL,
  `status` TINYINT(2) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_feed`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_feed` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_feed` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(30) NULL DEFAULT NULL COMMENT 'Type : article, collection',
  `author_id` INT(10) NULL DEFAULT NULL,
  `default_image` VARCHAR(255) NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `content` TEXT NULL DEFAULT NULL COMMENT 'The content of the headline news',
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `section` VARCHAR(20) NULL DEFAULT NULL,
  `status` TINYINT(2) NULL DEFAULT '0' COMMENT 'Status',
  `likes` INT(10) NULL DEFAULT '0',
  `saves` INT(10) NULL DEFAULT '0',
  `comments` INT(10) NULL DEFAULT '0',
  `shares` INT(10) NULL DEFAULT '0',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`),
  INDEX `author_id` (`author_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_feed_country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_feed_country` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_feed_country` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `feed_id` INT(10) NOT NULL COMMENT 'The id of the md_feed',
  `country_code` VARCHAR(2) CHARACTER SET 'utf8' NOT NULL COMMENT 'The country code',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  PRIMARY KEY (`id`),
  INDEX `feed_id_idx` (`feed_id` ASC),
  CONSTRAINT `feed_id_fk`
    FOREIGN KEY (`feed_id`)
    REFERENCES `mode`.`md_feed` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_feed_draft`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_feed_draft` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_feed_draft` (
  `id` INT(10) NOT NULL,
  `type` VARCHAR(20) NULL DEFAULT NULL COMMENT 'The type of the feed draft, e.g article, collection',
  `author_id` INT(10) NOT NULL COMMENT 'The id of the author',
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NULL DEFAULT NULL COMMENT 'The content of the feed draft',
  `description` VARCHAR(500) NULL DEFAULT NULL,
  `status` TINYINT(2) NULL DEFAULT NULL COMMENT 'The status of the feed draft',
  `comment` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Comment of the feed draft',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_image_taggify`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_image_taggify` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_image_taggify` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `taggified_image` TEXT NULL DEFAULT NULL,
  `author_id` INT(10) NULL DEFAULT NULL,
  `draft_id` INT(10) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `draft_id_fk_idx` (`draft_id` ASC),
  CONSTRAINT `draft_id_fk`
    FOREIGN KEY (`draft_id`)
    REFERENCES `mode`.`md_feed_draft` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_item` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_item` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` TINYINT(2) NULL DEFAULT '0' COMMENT 'The type of the item',
  `item_name` VARCHAR(100) NOT NULL DEFAULT '',
  `brand_id` INT(10) NOT NULL,
  `default_image` VARCHAR(255) NOT NULL DEFAULT '',
  `extra_image` VARCHAR(255) NULL DEFAULT '' COMMENT 'The first extra image URL of this item.',
  `description` TEXT NULL DEFAULT NULL,
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  `on_sale` TINYINT(1) NULL DEFAULT '0' COMMENT '0 - 没有促销，1 - 促销中',
  `sale_time` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Sale end time',
  `sale_price` DECIMAL(10,2) NULL DEFAULT NULL,
  `sale_percent` TINYINT(3) NULL DEFAULT NULL,
  `sku` VARCHAR(255) NULL DEFAULT NULL,
  `size` VARCHAR(200) NULL DEFAULT NULL,
  `color` VARCHAR(200) NULL DEFAULT NULL,
  `product_link` VARCHAR(2000) NULL DEFAULT NULL,
  `status` TINYINT(2) NOT NULL DEFAULT '1',
  `views` INT(10) NULL DEFAULT '0' COMMENT 'Times the item viewed',
  `saves` INT(10) NULL,
  `shares` INT(10) NULL DEFAULT '0' COMMENT 'Times the item shared',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `items_event_id_idx` (`on_sale` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_item_country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_item_country` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_item_country` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `item_id` INT(10) NOT NULL,
  `country_code` VARCHAR(2) NOT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `item_id_idx` (`item_id` ASC),
  CONSTRAINT `item_id_fk`
    FOREIGN KEY (`item_id`)
    REFERENCES `mode`.`md_item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_lucky_wheel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_lucky_wheel` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_lucky_wheel` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `prize_detail` TEXT NULL DEFAULT NULL COMMENT 'Jason format of the prize detail',
  `level` TINYINT(1) NULL DEFAULT NULL COMMENT 'The level of the prize',
  `base` INT(10) NULL DEFAULT NULL COMMENT 'The base of the probability for the prize',
  `probability` INT(10) NULL DEFAULT NULL COMMENT 'The probability of the prize',
  `start_range` INT(10) NULL DEFAULT NULL COMMENT 'The start range of the probability',
  `end_range` INT(10) NULL DEFAULT NULL COMMENT 'The end range of the probability',
  `status` TINYINT(2) NULL DEFAULT NULL COMMENT 'The status of the prize: 1 enabled; 0 disabled',
  `country_code` VARCHAR(500) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_notification`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_notification` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_notification` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NULL DEFAULT NULL COMMENT 'The type of the message, e.g : event, system ',
  `target` VARCHAR(30) NULL DEFAULT NULL,
  `user_id` INT(10) NULL DEFAULT NULL,
  `title` VARCHAR(255) NULL DEFAULT NULL COMMENT 'The title of the message',
  `content` TEXT NULL DEFAULT NULL,
  `description` VARCHAR(500) NULL DEFAULT NULL COMMENT 'The description of the message',
  `default_image` VARCHAR(255) NULL DEFAULT NULL COMMENT 'The Image of the message',
  `status` TINYINT(2) NULL DEFAULT '0',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_notification_country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_notification_country` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_notification_country` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `notification_id` INT(10) NOT NULL,
  `country_code` VARCHAR(2) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `notification_id_idx` (`notification_id` ASC),
  CONSTRAINT `notification_id_fk`
    FOREIGN KEY (`notification_id`)
    REFERENCES `mode`.`md_user_notification` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_profile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_profile` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_profile` (
  `user_id` INT(10) NOT NULL COMMENT 'User id',
  `gender` VARCHAR(10) NULL DEFAULT NULL COMMENT 'Gender',
  `nickname` VARCHAR(100) NULL DEFAULT NULL COMMENT 'Nick name',
  `level` TINYINT(2) NULL DEFAULT '1' COMMENT 'User level',
  `avatar` VARCHAR(255) NULL DEFAULT '' COMMENT 'Avatar',
  `birthday` VARCHAR(10) NULL DEFAULT '0' COMMENT 'Birthday',
  `description` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Description',
  `payment` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Paypal account',
  `credits` INT(10) NULL DEFAULT '0' COMMENT 'Credits',
  `address` TEXT NULL COMMENT 'Address in json format',
  `usd` FLOAT NOT NULL DEFAULT '0' COMMENT 'US dollar',
  `invite_by` INT(10) NULL DEFAULT '0' COMMENT 'Invited by',
  `default_image` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Default image',
  `saves` INT(10) NULL DEFAULT '0' COMMENT 'Saves count',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`user_id`),
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id`)
    REFERENCES `mode`.`md_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_credit_zone`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_credit_zone` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_credit_zone` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `prize_detail` TEXT NULL DEFAULT NULL COMMENT 'Jason format of the prize',
  `status` TINYINT(2) NULL DEFAULT NULL COMMENT 'The status of the prize: 1 enabled; 0 disabled',
  `country_code` VARCHAR(500) NULL DEFAULT NULL,
  `sort_order` INT(10) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_task` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_task` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'The id of the task',
  `group` VARCHAR(20) NOT NULL DEFAULT 'daily' COMMENT 'Group: daily, special',
  `type` VARCHAR(20) NULL DEFAULT NULL COMMENT 'The type of the task, e.g daily, activity',
  `name` VARCHAR(100) CHARACTER SET 'utf8' NULL DEFAULT NULL COMMENT 'The name of the task',
  `description` VARCHAR(500) CHARACTER SET 'utf8' NULL DEFAULT NULL COMMENT 'The description of the task',
  `tips` VARCHAR(500) CHARACTER SET 'utf8' NULL DEFAULT NULL COMMENT 'The tips of the task',
  `object_type` VARCHAR(30) NOT NULL COMMENT 'Type: article, item, collection etc.',
  `action` VARCHAR(30) NULL DEFAULT NULL COMMENT 'Action: view, share, like, save.',
  `rule` VARCHAR(500) CHARACTER SET 'utf8' NULL DEFAULT NULL COMMENT 'The json format rule of the task',
  `start_time` BIGINT(13) NULL DEFAULT NULL COMMENT 'The start time',
  `end_time` BIGINT(13) NULL DEFAULT NULL COMMENT 'The end time',
  `priority` INT(3) NULL DEFAULT '0' COMMENT 'The priority of the task',
  `status` TINYINT(2) NULL DEFAULT '1' COMMENT 'Turn on or off the task',
  `country_code` VARCHAR(500) NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'The create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'The update time',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_transaction` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_transaction` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL,
  `type` VARCHAR(30) NULL DEFAULT NULL,
  `amount` FLOAT NOT NULL DEFAULT '0',
  `unit` VARCHAR(10) NOT NULL DEFAULT 'usd',
  `status` TINYINT(2) NULL DEFAULT '1',
  `former_balance` FLOAT NULL DEFAULT NULL,
  `comment` VARCHAR(500) NULL DEFAULT NULL,
  `payment_channel` VARCHAR(50) NULL,
  `payment_account` VARCHAR(100) NULL,
  `payment_transaction` TEXT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_action_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_action_log` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_action_log` (
  `id` BIGINT(13) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL COMMENT 'The user id',
  `source_type` VARCHAR(30) NULL DEFAULT NULL COMMENT 'Source type.',
  `source_value` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Source Id.',
  `action` VARCHAR(30) NULL DEFAULT NULL COMMENT 'Action: view, share, like, save.',
  `object_type` VARCHAR(30) NOT NULL COMMENT 'Type: article, item, collection etc.',
  `object_value` VARCHAR(255) NOT NULL COMMENT 'The id of headline, user, item, collection etc.',
  `extra_value` VARCHAR(255) NULL DEFAULT NULL COMMENT 'Extra stored value.',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  PRIMARY KEY (`id`),
  INDEX `user_action_log_idx` (`user_id` ASC, `object_value` ASC, `object_type` ASC, `action` ASC),
  INDEX `user_action_log_ctime_idx` (`ctime` ASC),
  INDEX `user_action_log_object_type_idx` (`object_type` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_user_comment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_comment` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_comment` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NULL DEFAULT NULL,
  `object_type` VARCHAR(20) NULL DEFAULT NULL,
  `object_id` INT(10) NULL DEFAULT NULL,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `likes` INT(10) NULL,
  `ctime` BIGINT(13) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_credit_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_credit_log` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_credit_log` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL COMMENT 'The user id',
  `task_id` INT(10) NOT NULL COMMENT 'The id of the task',
  `type` VARCHAR(20) NOT NULL COMMENT 'The type of the credit: + / -',
  `credit` INT(10) NOT NULL COMMENT 'Credit number',
  `balance` INT(10) NULL DEFAULT NULL,
  `remarks` TEXT NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  PRIMARY KEY (`id`),
  INDEX `user_id_task_id_idx` (`user_id` ASC, `task_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_user_login_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_login_log` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_login_log` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL DEFAULT '0',
  `action` VARCHAR(10) NULL DEFAULT NULL,
  `device_type` VARCHAR(10) NULL DEFAULT NULL,
  `phone_number` VARCHAR(20) NULL DEFAULT NULL,
  `phone_name` VARCHAR(50) NULL DEFAULT NULL,
  `device_token` VARCHAR(255) NULL DEFAULT NULL,
  `system_version` VARCHAR(50) NULL DEFAULT NULL,
  `app_version` VARCHAR(50) NULL DEFAULT NULL,
  `uuid` VARCHAR(255) NULL DEFAULT NULL,
  `imsi` VARCHAR(50) NULL DEFAULT NULL,
  `model` VARCHAR(50) NULL DEFAULT NULL,
  `simserialnum` VARCHAR(100) NULL DEFAULT NULL,
  `pixel` VARCHAR(30) NULL DEFAULT NULL,
  `ip_address` VARCHAR(100) NULL DEFAULT NULL,
  `mac_address` VARCHAR(100) NULL DEFAULT NULL,
  `latitude` DOUBLE NULL DEFAULT '0',
  `longitude` DOUBLE NULL DEFAULT '0',
  `language` VARCHAR(50) NULL DEFAULT NULL,
  `country` VARCHAR(50) NULL DEFAULT NULL,
  `time_zone` VARCHAR(50) NULL DEFAULT NULL COMMENT 'The time zone',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `ctime_idx` (`ctime` ASC),
  INDEX `user_id_idx` (`user_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_user_prize`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_prize` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_prize` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(30) NOT NULL COMMENT 'The type of the prize: luckyDraw or redeem',
  `user_id` INT(10) NOT NULL COMMENT 'user Id',
  `source_id` INT(10) NULL DEFAULT NULL COMMENT 'The id of the prize: md_lucky_draw.id or md_redeem.id',
  `usd` INT(10) NULL DEFAULT NULL COMMENT 'Redeem usd amount',
  `address` VARCHAR(255) NULL DEFAULT NULL COMMENT 'the address of the user',
  `comment` VARCHAR(500) NULL DEFAULT NULL COMMENT 'comment',
  `status` TINYINT(2) NULL DEFAULT NULL COMMENT 'status: 1 new, 2 processing, 3 dispatched, 4 closed',
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  `utime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Update time',
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `prize_id_idx` (`source_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_task`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_task` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_task` (
  `id` INT(10) NOT NULL AUTO_INCREMENT COMMENT 'The primary key, unique id',
  `user_id` INT(10) NULL DEFAULT NULL COMMENT 'The user id',
  `task_id` INT(10) NULL DEFAULT NULL COMMENT 'The unique id of the task',
  `status` INT(10) NULL DEFAULT NULL COMMENT 'The status of the task of the user',
  `invite_code` VARCHAR(50) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0' COMMENT 'Create time',
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `task_id_idx` (`task_id` ASC),
  CONSTRAINT `task_id_fk`
    FOREIGN KEY (`task_id`)
    REFERENCES `mode`.`md_task` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_user_shopping_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_shopping_log` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_shopping_log` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL,
  `item_id` INT(10) NULL DEFAULT NULL,
  `feed_id` INT(10) NULL DEFAULT NULL,
  `url` VARCHAR(2000) NULL DEFAULT NULL COMMENT '用户点击的链接',
  `price` DECIMAL(10,2) NULL DEFAULT NULL,
  `navigation_type` INT(1) NULL DEFAULT NULL COMMENT '用户点击浏览的类型',
  `source` VARCHAR(50) NULL DEFAULT NULL COMMENT '用户从哪里点击进入第三方网站',
  `browse_time` BIGINT(13) NOT NULL DEFAULT '0' COMMENT '用户浏览网页的时间',
  PRIMARY KEY (`id`),
  INDEX `browse_time_idx` (`browse_time` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_editor_recommendation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_editor_recommendation` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_editor_recommendation` (
  `id` INT(10) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NULL DEFAULT NULL,
  `Item_id` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Item ids divided by ‘,’',
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `default_image` VARCHAR(255) NULL DEFAULT NULL,
  `status` TINYINT(2) NULL DEFAULT NULL,
  `author` VARCHAR(100) NULL DEFAULT NULL,
  `country_code` VARCHAR(500) NULL DEFAULT NULL COMMENT 'Country code divided by ‘,’',
  `sort_order` TINYINT(3) NULL DEFAULT NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  `utime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mode`.`md_user_engagement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_user_engagement` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_user_engagement` (
  `id` BIGINT(13) NOT NULL AUTO_INCREMENT,
  `user_id` INT(10) NOT NULL,
  `action` VARCHAR(30) NOT NULL COMMENT 'Actio: follow, like, dislike, save, share',
  `object_type` VARCHAR(30) NULL COMMENT 'Type: article, collection, item, user, comment',
  `object_id` INT(10) NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mode`.`md_api_log`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mode`.`md_api_log` ;

CREATE TABLE IF NOT EXISTS `mode`.`md_api_log` (
  `id` BIGINT(13) NOT NULL AUTO_INCREMENT,
  `api` VARCHAR(30) NOT NULL,
  `http_method` VARCHAR(10) NOT NULL COMMENT 'Method: get, post',
  `http_status` VARCHAR(10) NULL,
  `ip` VARCHAR(30) NULL,
  `user_id` INT(10) NULL,
  `source` VARCHAR(50) NULL,
  `ctime` BIGINT(13) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  INDEX `api_idx` (`api` ASC),
  INDEX `user_id_idx` (`user_id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
