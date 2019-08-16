-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `atm_db` DEFAULT CHARACTER SET utf8 ;
USE `atm_db` ;

-- -----------------------------------------------------
-- Table `mydb`.`accounts`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`accounts` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `authority` VARCHAR(45) NOT NULL DEFAULT 'ROLE_USER',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) ,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`credit_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `atm_db`.`credit_cards` (
  `id` INT NOT NULL,
  `cc_number` VARCHAR(16) NOT NULL,
  `cc_expiration` VARCHAR(5) NOT NULL,
  `cc_cvv` VARCHAR(3) NOT NULL,
  `credits` INT NOT NULL DEFAULT 0,
  `accounts_id` INT NOT NULL,
  PRIMARY KEY (`id`, `accounts_id`),
  UNIQUE INDEX `cc_number_UNIQUE` (`cc_number` ASC) ,
  INDEX `fk_credit_cards_accounts_idx` (`accounts_id` ASC) ,
  CONSTRAINT `fk_credit_cards_accounts`
    FOREIGN KEY (`accounts_id`)
    REFERENCES `atm_db`.`accounts` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
