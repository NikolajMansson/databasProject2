-- MySQL Script generated by MySQL Workbench
-- 05/20/17 18:44:38
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
-- Table `GameShop`.`Employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Employee` (
  `SSN` INT NOT NULL,
  `FirstName` VARCHAR(45) NOT NULL,
  `Surname` VARCHAR(45) NOT NULL,
  `EmploymentDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `GamesSold` INT NOT NULL,
  `Income` DOUBLE NOT NULL,
  `Email` VARCHAR(120) NOT NULL,
  `Username` VARCHAR(45) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `PrivilegeLevel` INT NOT NULL,
  `IsEmployed` TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`Username`),
  UNIQUE INDEX `UserName_UNIQUE` (`Username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Game` (
  `Title` VARCHAR(50) NOT NULL,
  `Genre` VARCHAR(45) NOT NULL,
  `Developer` VARCHAR(45) NOT NULL,
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
  `RegistrationDate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Username` VARCHAR(45) NOT NULL,
  `UserPassword` VARCHAR(45) NOT NULL,
  `Email` VARCHAR(120) NOT NULL,
  PRIMARY KEY (`Username`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`RegularCustomerOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`RegularCustomerOrder` (
  `OrderNumber` INT NULL AUTO_INCREMENT,
  `DateOfOrder` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Customer_Username` VARCHAR(45) NOT NULL,
  `Employees_Username` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  `TotalPricePerItem` DOUBLE NOT NULL,
  PRIMARY KEY (`OrderNumber`),
  INDEX `fk_Order_Customer1_idx` (`Customer_Username` ASC),
  INDEX `fk_Order_Employees1_idx` (`Employees_Username` ASC),
  CONSTRAINT `fk_Order_Customer1`
    FOREIGN KEY (`Customer_Username`)
    REFERENCES `GameShop`.`Customer` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Employees1`
    FOREIGN KEY (`Employees_Username`)
    REFERENCES `GameShop`.`Employee` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
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
-- Table `GameShop`.`ItemCollection`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`ItemCollection` (
  `ArticleNo` INT NOT NULL AUTO_INCREMENT,
  `Platform_Abbreviation` VARCHAR(20) NOT NULL,
  `Price` DOUBLE NOT NULL,
  `Game_Title` VARCHAR(50) NOT NULL,
  `ReleaseDate` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  INDEX `fk_Game_has_Platform_Platform1_idx` (`Platform_Abbreviation` ASC),
  INDEX `fk_Item_Game1_idx` (`Game_Title` ASC),
  PRIMARY KEY (`ArticleNo`),
  CONSTRAINT `fk_Game_has_Platform_Platform1`
    FOREIGN KEY (`Platform_Abbreviation`)
    REFERENCES `GameShop`.`Platform` (`Abbreviation`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_Game1`
    FOREIGN KEY (`Game_Title`)
    REFERENCES `GameShop`.`Game` (`Title`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`GuestOrder`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`GuestOrder` (
  `OrderNumber` INT NULL AUTO_INCREMENT,
  `DateOfOrder` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Employees_Username` VARCHAR(45) NOT NULL,
  `Quantity` INT NOT NULL,
  `TotalPricePerItem` DOUBLE NOT NULL,
  PRIMARY KEY (`OrderNumber`),
  INDEX `fk_GuestOrder_Employees1_idx` (`Employees_Username` ASC),
  CONSTRAINT `fk_GuestOrder_Employees1`
    FOREIGN KEY (`Employees_Username`)
    REFERENCES `GameShop`.`Employee` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Log` (
  `IDLog` INT NOT NULL AUTO_INCREMENT,
  `AddUsername` VARCHAR(45) NOT NULL,
  `Employee_Username` VARCHAR(45) NOT NULL,
  `Time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IDLog`),
  INDEX `fk_Log_Employee1_idx` (`Employee_Username` ASC),
  CONSTRAINT `fk_Log_Employee1`
    FOREIGN KEY (`Employee_Username`)
    REFERENCES `GameShop`.`Employee` (`Username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `GameShop`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `GameShop`.`Item` (
  `IDItem` INT NOT NULL AUTO_INCREMENT,
  `ItemCollection_ArticleNo` INT NOT NULL,
  `GuestOrder_OrderNumber` INT NULL DEFAULT NULL,
  `IsSold` TINYINT(1) NOT NULL DEFAULT 0,
  `RegularCustomerOrder_OrderNumber` INT NULL DEFAULT NULL,
  PRIMARY KEY (`IDItem`),
  INDEX `fk_Item_ItemCollection1_idx` (`ItemCollection_ArticleNo` ASC),
  INDEX `fk_Item_GuestOrder1_idx` (`GuestOrder_OrderNumber` ASC),
  INDEX `fk_Item_RegularCustomerOrder1_idx` (`RegularCustomerOrder_OrderNumber` ASC),
  CONSTRAINT `fk_Item_ItemCollection1`
    FOREIGN KEY (`ItemCollection_ArticleNo`)
    REFERENCES `GameShop`.`ItemCollection` (`ArticleNo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_GuestOrder1`
    FOREIGN KEY (`GuestOrder_OrderNumber`)
    REFERENCES `GameShop`.`GuestOrder` (`OrderNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Item_RegularCustomerOrder1`
    FOREIGN KEY (`RegularCustomerOrder_OrderNumber`)
    REFERENCES `GameShop`.`RegularCustomerOrder` (`OrderNumber`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
