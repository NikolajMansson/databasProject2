-- MySQL Script generated by MySQL Workbench
-- 04/14/17 23:20:47
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema GameShop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema GameShop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `GameShop` DEFAULT CHARACTER SET utf8 ;
USE `GameShop` ;

-- -----------------------------------------------------
-- Table `GameShop`.`Employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Employees` (
  `SSN` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `EmploymentDate` DATETIME NOT NULL,
  `GamesSold` INT NOT NULL,
  `Income` DECIMAL(2) NOT NULL,
  `Email` VARCHAR(120) NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserName`),
  UNIQUE INDEX `UserName_UNIQUE` (`UserName` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Game` (
  `Title` VARCHAR(50) NOT NULL,
  `Genre` VARCHAR(45) NOT NULL,
  `Developer` VARCHAR(45) NOT NULL,
  `Price` DOUBLE NOT NULL,
  `DescriptionOfPlot` VARCHAR(300) NOT NULL,
  PRIMARY KEY (`Title`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Customer` (
  `SSN` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `RegistrationDate` DATE NOT NULL,
  `UserName` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`UserName`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Platform`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Platform` (
  `Abbreviation` VARCHAR(20) NOT NULL,
  `FullName` VARCHAR(45) NOT NULL,
  `Developer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Abbreviation`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Game_has_Platform`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Game_has_Platform` (
  `Game_Title` INT NOT NULL,
  `Platform_Abbreviation` VARCHAR(20) NOT NULL,
  `ArticleNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`ArticleNumber`),
  INDEX `fk_Game_has_Platform_Platform1_idx` (`Platform_Abbreviation` ASC),
  INDEX `fk_Game_has_Platform_Game1_idx` (`Game_Title` ASC),
  CONSTRAINT `fk_Game_has_Platform_Game1`
    FOREIGN KEY (`Game_Title`)
    REFERENCES `GameShop`.`Game` (`Title`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Game_has_Platform_Platform1`
    FOREIGN KEY (`Platform_Abbreviation`)
    REFERENCES `GameShop`.`Platform` (`Abbreviation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`RegularCustomerOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`RegularCustomerOrder` (
  `OrderNumber` INT NOT NULL AUTO_INCREMENT,
  `Quantity` INT NOT NULL,
  `Price` DOUBLE NOT NULL,
  `DateOfOrder` DATE NOT NULL,
  `Customer_UserName` VARCHAR(45) NOT NULL,
  `Employees_UserName` VARCHAR(45) NOT NULL,
  `Game_has_Platform_ArticleNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`OrderNumber`, `Game_has_Platform_ArticleNumber`),
  INDEX `fk_Order_Customer1_idx` (`Customer_UserName` ASC),
  INDEX `fk_Order_Employees1_idx` (`Employees_UserName` ASC),
  INDEX `fk_Order_Game_has_Platform1_idx` (`Game_has_Platform_ArticleNumber` ASC),
  CONSTRAINT `fk_Order_Customer1`
    FOREIGN KEY (`Customer_UserName`)
    REFERENCES `GameShop`.`Customer` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Employees1`
    FOREIGN KEY (`Employees_UserName`)
    REFERENCES `GameShop`.`Employees` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Game_has_Platform1`
    FOREIGN KEY (`Game_has_Platform_ArticleNumber`)
    REFERENCES `GameShop`.`Game_has_Platform` (`ArticleNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`GuestOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`GuestOrder` (
  `OrderNumber` INT NOT NULL AUTO_INCREMENT,
  `Employees_UserName` VARCHAR(45) NOT NULL,
  `Game_has_Platform_ArticleNumber` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  `Price` DOUBLE NOT NULL,
  `DateOfOrder` DATE NOT NULL,
  PRIMARY KEY (`OrderNumber`, `Game_has_Platform_ArticleNumber`),
  INDEX `fk_GuestOrder_Employees1_idx` (`Employees_UserName` ASC),
  INDEX `fk_GuestOrder_Game_has_Platform1_idx` (`Game_has_Platform_ArticleNumber` ASC),
  CONSTRAINT `fk_GuestOrder_Employees1`
    FOREIGN KEY (`Employees_UserName`)
    REFERENCES `GameShop`.`Employees` (`UserName`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_GuestOrder_Game_has_Platform1`
    FOREIGN KEY (`Game_has_Platform_ArticleNumber`)
    REFERENCES `GameShop`.`Game_has_Platform` (`ArticleNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
