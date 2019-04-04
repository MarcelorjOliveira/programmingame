-- ----------------------------------------------------------------------------
-- MySQL Workbench Migration
-- Migrated Schemata: halyen
-- Source Schemata: halyeninstance
-- Created: Wed Mar 27 14:30:19 2019
-- Workbench Version: 6.3.10
-- ----------------------------------------------------------------------------

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------------------------------------------------------
-- Table halyen.Course
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Course` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyen.Exercise
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Exercise` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `statement` TEXT NOT NULL,
  `modelResponse` TEXT NOT NULL,
  `tests` TEXT NOT NULL,
  `code` TEXT NULL DEFAULT NULL,
  `course` INT(11) NULL DEFAULT NULL,
  `module` INT(11) NULL DEFAULT NULL,
  `link` TEXT NULL DEFAULT NULL,
  `useDirectoryTree` INT(1) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 16
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyen.Module
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Module` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `idCourse` INT(11) NULL DEFAULT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyen.Movement
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Movement` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `exerciseId` BIGINT(20) NOT NULL,
  `userId` BIGINT(20) NOT NULL,
  `codeUsed` TEXT NULL DEFAULT NULL,
  `mark` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 132
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyen.Solution
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Solution` (
  `idSolution` INT(11) NOT NULL AUTO_INCREMENT,
  `test` VARCHAR(255) NULL DEFAULT NULL,
  `idExercise` INT(11) NULL DEFAULT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`idSolution`))
ENGINE = InnoDB
AUTO_INCREMENT = 57
DEFAULT CHARACTER SET = latin1;

-- ----------------------------------------------------------------------------
-- Table halyen.Users
-- ----------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS `halyen`.`Users` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = latin1;
SET FOREIGN_KEY_CHECKS = 1;
