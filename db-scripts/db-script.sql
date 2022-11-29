-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema iot_device_manager
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema iot_device_manager
-- -----------------------------------------------------
TRUNCATE SCHEMA IF EXISTS `iot_device_manager`;
CREATE SCHEMA IF NOT EXISTS `iot_device_manager` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `iot_device_manager` ;

-- -----------------------------------------------------
-- Table `iot_device_manager`.`asset`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iot_device_manager`.`asset` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `type` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iot_device_manager`.`iot_sessions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iot_device_manager`.`iot_sessions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `machine_id` BIGINT NOT NULL,
  `device_id` BIGINT NOT NULL,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_reservation_asset_idx` (`machine_id` ASC) VISIBLE,
  INDEX `fk_reservation_asset1_idx` (`device_id` ASC) VISIBLE,
  CONSTRAINT `fk_reservation_asset`
    FOREIGN KEY (`machine_id`)
    REFERENCES `iot_device_manager`.`asset` (`id`),
  CONSTRAINT `fk_reservation_asset1`
    FOREIGN KEY (`device_id`)
    REFERENCES `iot_device_manager`.`asset` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `iot_device_manager`.`device_metrics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iot_device_manager`.`device_metrics` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `iot_sessions_id` BIGINT NOT NULL,
  `value` INT NOT NULL,
  `timestamp` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_device_metrics_iot_sessions1_idx` (`iot_sessions_id` ASC) VISIBLE,
  CONSTRAINT `fk_device_metrics_iot_sessions1`
    FOREIGN KEY (`iot_sessions_id`)
    REFERENCES `iot_device_manager`.`iot_sessions` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 967
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
