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
