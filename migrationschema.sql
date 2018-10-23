-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: halyeninstance
-- Source Schemata: halyeninstance
-- Created: Tue Oct 23 18:29:09 2018
-- Workbench Version: 6.3.10
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Schema halyeninstance
-- ----------------------------------------------------------------------------
DROP SCHEMA IF EXISTS `halyeninstance` ;
CREATE SCHEMA IF NOT EXISTS `halyeninstance` ;

-- ----------------------------------------------------------------------------
-- Table halyeninstance.Exercise
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyeninstance`.`Exercise` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `statement` TEXT NOT NULL,
  `modelResponse` TEXT NOT NULL,
  `tests` TEXT NOT NULL,
  `code` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyeninstance.Movement
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyeninstance`.`Movement` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `exerciseId` BIGINT(20) NOT NULL,
  `userId` BIGINT(20) NOT NULL,
  `codeUsed` TEXT NULL DEFAULT NULL,
  `mark` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 47
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyeninstance.Solution
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyeninstance`.`Solution` (
  `idSolution` INT(11) NOT NULL AUTO_INCREMENT,
  `test` VARCHAR(255) NULL DEFAULT NULL,
  `idExercise` INT(11) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idSolution`))
ENGINE = InnoDB
AUTO_INCREMENT = 42
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyeninstance.Users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyeninstance`.`Users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;
