-- ****************** SqlDBM: MySQL ******************;
-- ***************************************************;

DROP TABLE IF EXISTS `Doctor_SpecificSpecialty`;


DROP TABLE IF EXISTS `GeneralSpecialty_SpecificSpecialty`;


DROP TABLE IF EXISTS `Doctor`;


DROP TABLE IF EXISTS `GeneralSpecialty`;


DROP TABLE IF EXISTS `SpecificSpecialty`;


DROP TABLE IF EXISTS `Patient`;


DROP TABLE IF EXISTS `Hospital`;



-- ************************************** `GeneralSpecialty`

CREATE TABLE `GeneralSpecialty`
(
 `ID`   INT NOT NULL AUTO_INCREMENT ,
 `Name` TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `SpecificSpecialty`

CREATE TABLE `SpecificSpecialty`
(
 `ID`   INT NOT NULL AUTO_INCREMENT ,
 `Name` TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `Patient`

CREATE TABLE `Patient`
(
 `ID`         INT NOT NULL AUTO_INCREMENT ,
 `First_Name` TEXT NOT NULL ,
 `Last_Name`  TEXT NOT NULL ,
 `Gender`     ENUM('MALE', 'FEMALE') NOT NULL ,
 `Email`      TEXT NOT NULL ,
 `Password`   TEXT NOT NULL ,
 `Address`    TEXT NOT NULL ,
 `Languages`  TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `Hospital`

CREATE TABLE `Hospital`
(
 `ID`                           INT NOT NULL AUTO_INCREMENT ,
 `Name`                         TEXT NOT NULL ,
 `Address`                      TEXT NOT NULL ,
 `Website`                      TEXT NOT NULL ,
 `Hospital_Admin_Name`          TEXT NOT NULL ,
 `Hospital_Admin_Email_Address` TEXT NOT NULL ,

PRIMARY KEY (`ID`)
);





-- ************************************** `GeneralSpecialty_SpecificSpecialty`

CREATE TABLE `GeneralSpecialty_SpecificSpecialty`
(
 `Specific_Specialty_ID` INT NOT NULL ,
 `General_Specialty_ID`  INT NOT NULL ,

PRIMARY KEY (`Specific_Specialty_ID`, `General_Specialty_ID`),
KEY `fkIdx_159` (`Specific_Specialty_ID`),
CONSTRAINT `FK_159` FOREIGN KEY `fkIdx_159` (`Specific_Specialty_ID`) REFERENCES `SpecificSpecialty` (`ID`),
KEY `fkIdx_164` (`General_Specialty_ID`),
CONSTRAINT `FK_164` FOREIGN KEY `fkIdx_164` (`General_Specialty_ID`) REFERENCES `GeneralSpecialty` (`ID`)
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
KEY `fkIdx_143` (`Hospital_ID`),
CONSTRAINT `FK_143` FOREIGN KEY `fkIdx_143` (`Hospital_ID`) REFERENCES `Hospital` (`ID`)
);





-- ************************************** `Doctor_SpecificSpecialty`

CREATE TABLE `Doctor_SpecificSpecialty`
(
 `Doctor_ID`             INT NOT NULL ,
 `Specific_Specialty_ID` INT NOT NULL ,

PRIMARY KEY (`Doctor_ID`, `Specific_Specialty_ID`),
KEY `fkIdx_171` (`Doctor_ID`),
CONSTRAINT `FK_171` FOREIGN KEY `fkIdx_171` (`Doctor_ID`) REFERENCES `Doctor` (`ID`),
KEY `fkIdx_179` (`Specific_Specialty_ID`),
CONSTRAINT `FK_179` FOREIGN KEY `fkIdx_179` (`Specific_Specialty_ID`) REFERENCES `SpecificSpecialty` (`ID`)
);




