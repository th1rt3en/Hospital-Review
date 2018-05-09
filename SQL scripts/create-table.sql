<<<<<<< HEAD
-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

CREATE DATABASE HospitalReview;
USE HospitalReview;

DROP TABLE IF EXISTS `Doctor_Bookmark`;


DROP TABLE IF EXISTS `Doctor_Rate`;


DROP TABLE IF EXISTS `Doctor_Comment`;


DROP TABLE IF EXISTS `Doctor_Specialty`;


DROP TABLE IF EXISTS `Hospital_Bookmark`;


DROP TABLE IF EXISTS `Hospital_Rate`;


DROP TABLE IF EXISTS `Hospital_Comment`;


DROP TABLE IF EXISTS `Doctor`;


DROP TABLE IF EXISTS `Specialty_Relation`;


DROP TABLE IF EXISTS `Patient`;


DROP TABLE IF EXISTS `Hospital`;


DROP TABLE IF EXISTS `User`;


DROP TABLE IF EXISTS `General_Specialty`;


DROP TABLE IF EXISTS `Specific_Specialty`;



-- ************************************** `User`

CREATE TABLE `User`
(
 `ID`           INT NOT NULL AUTO_INCREMENT ,
 `Email`        TEXT NOT NULL ,
 `Password`     TEXT NOT NULL ,
 `Is_Activated` TINYINT NOT NULL DEFAULT 0 ,
 `Type`         INT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `General_Specialty`

CREATE TABLE `General_Specialty`
(
 `ID`   INT NOT NULL AUTO_INCREMENT ,
 `Name` TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `Specific_Specialty`

CREATE TABLE `Specific_Specialty`
(
 `ID`   INT NOT NULL AUTO_INCREMENT ,
 `Name` TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `Specialty_Relation`

CREATE TABLE `Specialty_Relation`
(
 `ID`                    INT NOT NULL AUTO_INCREMENT ,
 `Specific_Specialty_ID` INT NOT NULL ,
 `General_Specialty_ID`  INT NOT NULL ,

PRIMARY KEY (`ID`),
KEY `fkIdx_383` (`Specific_Specialty_ID`),
CONSTRAINT `FK_383` FOREIGN KEY `fkIdx_383` (`Specific_Specialty_ID`) REFERENCES `Specific_Specialty` (`ID`),
KEY `fkIdx_387` (`General_Specialty_ID`),
CONSTRAINT `FK_387` FOREIGN KEY `fkIdx_387` (`General_Specialty_ID`) REFERENCES `General_Specialty` (`ID`)
);





-- ************************************** `Patient`

CREATE TABLE `Patient`
(
 `ID`         INT NOT NULL ,
 `First_Name` TEXT NOT NULL ,
 `Last_Name`  TEXT NOT NULL ,
 `Address`    TEXT NOT NULL ,
 `Gender`     ENUM('MALE', 'FEMALE') NOT NULL ,
 `Languages`  TEXT NOT NULL ,

PRIMARY KEY (`ID`),
KEY `fkIdx_337` (`ID`),
CONSTRAINT `FK_337` FOREIGN KEY `fkIdx_337` (`ID`) REFERENCES `User` (`ID`)
);





-- ************************************** `Hospital`

CREATE TABLE `Hospital`
(
 `ID`               INT NOT NULL ,
 `Name`             TEXT NOT NULL ,
 `Hospital_Address` TEXT NOT NULL ,
 `Website`          TEXT NOT NULL ,

PRIMARY KEY (`ID`),
KEY `fkIdx_333` (`ID`),
CONSTRAINT `FK_333` FOREIGN KEY `fkIdx_333` (`ID`) REFERENCES `User` (`ID`)
);





-- ************************************** `Hospital_Bookmark`

CREATE TABLE `Hospital_Bookmark`
(
 `Hospital_ID` INT NOT NULL ,
 `Patient_ID`  INT NOT NULL ,

PRIMARY KEY (`Hospital_ID`, `Patient_ID`),
KEY `fkIdx_354` (`Hospital_ID`),
CONSTRAINT `FK_354` FOREIGN KEY `fkIdx_354` (`Hospital_ID`) REFERENCES `Hospital` (`ID`),
KEY `fkIdx_358` (`Patient_ID`),
CONSTRAINT `FK_358` FOREIGN KEY `fkIdx_358` (`Patient_ID`) REFERENCES `Patient` (`ID`)
);





-- ************************************** `Hospital_Rate`

CREATE TABLE `Hospital_Rate`
(
 `Patient_ID`  INT NOT NULL ,
 `Rate`        INT NOT NULL,
 `Time`        DATETIME NOT NULL ,
 `Hospital_ID` INT NOT NULL ,

PRIMARY KEY (`Patient_ID`),
KEY `fkIdx_425` (`Hospital_ID`),
CONSTRAINT `FK_425` FOREIGN KEY `fkIdx_425` (`Hospital_ID`) REFERENCES `Hospital` (`ID`),
KEY `fkIdx_429` (`Patient_ID`),
CONSTRAINT `FK_429` FOREIGN KEY `fkIdx_429` (`Patient_ID`) REFERENCES `Patient` (`ID`)
);





-- ************************************** `Hospital_Comment`

CREATE TABLE `Hospital_Comment`
(
 `ID`          INT NOT NULL AUTO_INCREMENT ,
 `Comment`     TEXT NOT NULL ,
 `Time`        DATETIME NOT NULL ,
 `Hospital_ID` INT NOT NULL ,
 `Patient_ID`  INT NOT NULL ,

PRIMARY KEY (`ID`),
KEY `fkIdx_414` (`Hospital_ID`),
CONSTRAINT `FK_414` FOREIGN KEY `fkIdx_414` (`Hospital_ID`) REFERENCES `Hospital` (`ID`),
KEY `fkIdx_420` (`Patient_ID`),
CONSTRAINT `FK_420` FOREIGN KEY `fkIdx_420` (`Patient_ID`) REFERENCES `Patient` (`ID`)
);





-- ************************************** `Doctor`

CREATE TABLE `Doctor`
(
 `ID`                 INT NOT NULL AUTO_INCREMENT ,
 `First_Name`         TEXT NOT NULL ,
 `Last_Name`          TEXT NOT NULL ,
 `Gender`             ENUM('MALE', 'FEMALE') NOT NULL ,
 `Degree`             TEXT NOT NULL ,
 `Accepted_Insurance` TINYINT(1) NOT NULL ,
 `Office_Hours`       TEXT NOT NULL ,
 `Languages`          TEXT NOT NULL ,
 `Hospital_ID`        INT NOT NULL ,

PRIMARY KEY (`ID`),
KEY `fkIdx_401` (`Hospital_ID`),
CONSTRAINT `FK_401` FOREIGN KEY `fkIdx_401` (`Hospital_ID`) REFERENCES `Hospital` (`ID`)
);





-- ************************************** `Doctor_Bookmark`

CREATE TABLE `Doctor_Bookmark`
(
 `Patient_ID` INT NOT NULL ,
 `Doctor_ID`  INT NOT NULL ,

PRIMARY KEY (`Patient_ID`, `Doctor_ID`),
KEY `fkIdx_278` (`Doctor_ID`),
CONSTRAINT `FK_278` FOREIGN KEY `fkIdx_278` (`Doctor_ID`) REFERENCES `Doctor` (`ID`),
KEY `fkIdx_282` (`Patient_ID`),
CONSTRAINT `FK_282` FOREIGN KEY `fkIdx_282` (`Patient_ID`) REFERENCES `Patient` (`ID`)
);





-- ************************************** `Doctor_Rate`

CREATE TABLE `Doctor_Rate`
(
 `Patient_ID` INT NOT NULL ,
 `Doctor_ID`  INT NOT NULL ,
 `Rate`       INT NOT NULL ,
 `Enable`     TINYINT NOT NULL ,
 `Time`       DATETIME NOT NULL ,

PRIMARY KEY (`Patient_ID`, `Doctor_ID`),
KEY `fkIdx_221` (`Patient_ID`),
CONSTRAINT `FK_221` FOREIGN KEY `fkIdx_221` (`Patient_ID`) REFERENCES `Patient` (`ID`),
KEY `fkIdx_229` (`Doctor_ID`),
CONSTRAINT `FK_229` FOREIGN KEY `fkIdx_229` (`Doctor_ID`) REFERENCES `Doctor` (`ID`)
);





-- ************************************** `Doctor_Comment`

CREATE TABLE `Doctor_Comment`
(
 `Comment`    TEXT NOT NULL ,
 `Doctor_ID`  INT NOT NULL ,
 `Patient_ID` INT NOT NULL ,
 `Enable`     TINYINT NOT NULL DEFAULT 1 ,
 `Time`       DATETIME NOT NULL ,
 `ID`         INT NOT NULL AUTO_INCREMENT ,

PRIMARY KEY (`ID`),
KEY `fkIdx_199` (`Doctor_ID`),
CONSTRAINT `FK_199` FOREIGN KEY `fkIdx_199` (`Doctor_ID`) REFERENCES `Doctor` (`ID`),
KEY `fkIdx_211` (`Patient_ID`),
CONSTRAINT `FK_211` FOREIGN KEY `fkIdx_211` (`Patient_ID`) REFERENCES `Patient` (`ID`)
);





-- ************************************** `Doctor_Specialty`

CREATE TABLE `Doctor_Specialty`
(
 `Doctor_ID`    INT NOT NULL ,
 `Specialty_ID` INT NOT NULL ,

PRIMARY KEY (`Doctor_ID`),
KEY `fkIdx_171` (`Doctor_ID`),
CONSTRAINT `FK_171` FOREIGN KEY `fkIdx_171` (`Doctor_ID`) REFERENCES `Doctor` (`ID`),
KEY `fkIdx_391` (`Specialty_ID`),
CONSTRAINT `FK_391` FOREIGN KEY `fkIdx_391` (`Specialty_ID`) REFERENCES `Specialty_Relation` (`ID`)
);
=======
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hospital-review
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hospital-review
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital-review` DEFAULT CHARACTER SET utf8 ;
USE `hospital-review` ;

-- -----------------------------------------------------
-- Table `hospital-review`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`user` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(45) NOT NULL,
  `Password` TEXT NOT NULL,
  `Is_Activated` TINYINT(1) NULL DEFAULT 0,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE INDEX `Email_UNIQUE` (`Email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`hospital`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`hospital` (
  `ID` INT NOT NULL,
  `Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Website` VARCHAR(45) NULL DEFAULT 'N/A',
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_hospital_user`
    FOREIGN KEY (`ID`)
    REFERENCES `hospital-review`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`patient`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`patient` (
  `ID` INT NOT NULL,
  `First_Name` VARCHAR(45) NOT NULL,
  `Last_Name` VARCHAR(45) NOT NULL,
  `Address` VARCHAR(45) NOT NULL,
  `Gender` ENUM('male', 'female') NOT NULL,
  `Languages` TEXT NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_patient_user`
    FOREIGN KEY (`ID`)
    REFERENCES `hospital-review`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`hospital-review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`hospital-review` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Review` TEXT NOT NULL,
  `Time` DATETIME NOT NULL,
  `Hospital_ID` INT NOT NULL,
  `Patient_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `Hospital_ID_idx` (`Hospital_ID` ASC),
  INDEX `Patient_ID_idx` (`Patient_ID` ASC),
  CONSTRAINT `fk_hospital-review_hospital`
    FOREIGN KEY (`Hospital_ID`)
    REFERENCES `hospital-review`.`hospital` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_hospital-review_patient`
    FOREIGN KEY (`Patient_ID`)
    REFERENCES `hospital-review`.`patient` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`hospital-bookmark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`hospital-bookmark` (
  `Hospital_ID` INT NOT NULL,
  `Patient_ID` INT NOT NULL,
  PRIMARY KEY (`Hospital_ID`, `Patient_ID`),
  INDEX `Patient_ID_idx` (`Patient_ID` ASC),
  CONSTRAINT `fk_hospital-bookmark_hospital`
    FOREIGN KEY (`Hospital_ID`)
    REFERENCES `hospital-review`.`hospital` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_hospital-bookmark_patient`
    FOREIGN KEY (`Patient_ID`)
    REFERENCES `hospital-review`.`patient` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`doctor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`doctor` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `First_Name` VARCHAR(45) NOT NULL,
  `Last_Name` VARCHAR(45) NOT NULL,
  `Gender` ENUM('male', 'female') NOT NULL,
  `Degree` VARCHAR(45) NOT NULL,
  `Accepted_Insurance` TINYINT(1) NULL DEFAULT 0,
  `Office_Hours` VARCHAR(45) NOT NULL,
  `Languages` TEXT NOT NULL,
  `Hospital_ID` INT NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `Hospital_ID_idx` (`Hospital_ID` ASC),
  CONSTRAINT `fk_hospital_doctor`
    FOREIGN KEY (`Hospital_ID`)
    REFERENCES `hospital-review`.`hospital` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`general-specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`general-specialty` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`specific-specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`specific-specialty` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`specialty` (
  `Specific_Specialty_ID` INT NOT NULL,
  `General_Specialty_ID` INT NOT NULL,
  PRIMARY KEY (`Specific_Specialty_ID`, `General_Specialty_ID`),
  INDEX `General_Specialty_ID_idx` (`General_Specialty_ID` ASC),
  CONSTRAINT `fk_specialty_specific-specialty`
    FOREIGN KEY (`Specific_Specialty_ID`)
    REFERENCES `hospital-review`.`specific-specialty` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_specialty_general-specialty`
    FOREIGN KEY (`General_Specialty_ID`)
    REFERENCES `hospital-review`.`general-specialty` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`doctor-specialty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`doctor-specialty` (
  `Doctor_ID` INT NOT NULL,
  `Specific_Specialty_ID` INT NOT NULL,
  PRIMARY KEY (`Doctor_ID`, `Specific_Specialty_ID`),
  INDEX `Specific_Specialty_ID_idx` (`Specific_Specialty_ID` ASC),
  CONSTRAINT `fk_doctor-specialty_specific-specialty`
    FOREIGN KEY (`Specific_Specialty_ID`)
    REFERENCES `hospital-review`.`specific-specialty` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_doctor-specialty_doctor`
    FOREIGN KEY (`Doctor_ID`)
    REFERENCES `hospital-review`.`doctor` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`doctor-bookmark`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`doctor-bookmark` (
  `Doctor_ID` INT NOT NULL,
  `Patient_ID` INT NOT NULL,
  PRIMARY KEY (`Doctor_ID`, `Patient_ID`),
  INDEX `Patient_ID_idx` (`Patient_ID` ASC),
  CONSTRAINT `fk_doctor-bookmark_doctor`
    FOREIGN KEY (`Doctor_ID`)
    REFERENCES `hospital-review`.`doctor` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_doctor-bookmark_patient`
    FOREIGN KEY (`Patient_ID`)
    REFERENCES `hospital-review`.`patient` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`doctor-comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`doctor-comment` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Comment` TEXT NOT NULL,
  `Rating` INT NOT NULL,
  `Enabled` TINYINT(1) NULL DEFAULT 1,
  `Time` DATETIME NOT NULL,
  `Doctor_ID` INT NOT NULL,
  `Patient_ID` INT NOT NULL,
  PRIMARY KEY (`ID`, `Patient_ID`, `Doctor_ID`),
  INDEX `Doctor_ID_idx` (`Doctor_ID` ASC),
  INDEX `Patient_ID_idx` (`Patient_ID` ASC),
  CONSTRAINT `fk_doctor-comment_doctor`
    FOREIGN KEY (`Doctor_ID`)
    REFERENCES `hospital-review`.`doctor` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_doctor-comment_patient`
    FOREIGN KEY (`Patient_ID`)
    REFERENCES `hospital-review`.`patient` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hospital-review`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital-review`.`admin` (
  `ID` INT NOT NULL,
  `First_Name` VARCHAR(45) NOT NULL,
  `Last_Name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ID`),
  CONSTRAINT `fk_admin_user`
    FOREIGN KEY (`ID`)
    REFERENCES `hospital-review`.`user` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
>>>>>>> 46337c911e803dd0a8cacfb118ee41e9e0b32a9c
