-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdshopall
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdshopall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdshopall` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bdshopall` ;

-- -----------------------------------------------------
-- Table `bdshopall`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`categoria` (
  `id_categoria` INT NOT NULL AUTO_INCREMENT,
  `ct_titulo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categoria`),
  UNIQUE INDEX `UK_kgfuox1ryegs30v55vblg1dg6` (`ct_titulo` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`usuarios`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`usuarios` (
  `id_usuario` INT NOT NULL AUTO_INCREMENT,
  `u_apellidos` VARCHAR(45) NOT NULL,
  `u_email` VARCHAR(45) NOT NULL,
  `u_fecha_registro` DATETIME(6) NOT NULL,
  `u_nombre` VARCHAR(45) NOT NULL,
  `u_password` VARCHAR(8) NOT NULL,
  `u_rol` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE INDEX `UK_3mxlm2tgbyytwckj66g4i507q` (`u_email` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`pedidos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`pedidos` (
  `id_pedido` INT NOT NULL AUTO_INCREMENT,
  `pd_estado` VARCHAR(10) NOT NULL,
  `pd_no_seguimiento` VARCHAR(25) NULL DEFAULT NULL,
  `pd_notas_cliente` VARCHAR(140) NULL DEFAULT NULL,
  `pd_subtotal` DOUBLE NOT NULL,
  `pd_total` DOUBLE NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_pedido`),
  INDEX `FK4a0lfwlpmytywxpwjfa1a3ar2` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `FK4a0lfwlpmytywxpwjfa1a3ar2`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdshopall`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`subcategoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`subcategoria` (
  `id_subcategoria` INT NOT NULL AUTO_INCREMENT,
  `sc_titulo` VARCHAR(45) NOT NULL,
  `id_categoria` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id_subcategoria`),
  INDEX `FK5n9f7pm966nyw6mue7994u3bl` (`id_categoria` ASC) VISIBLE,
  CONSTRAINT `FK5n9f7pm966nyw6mue7994u3bl`
    FOREIGN KEY (`id_categoria`)
    REFERENCES `bdshopall`.`categoria` (`id_categoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`productos` (
  `id_producto` INT NOT NULL AUTO_INCREMENT,
  `p_descripcion` VARCHAR(1000) NOT NULL,
  `p_nombre` VARCHAR(45) NOT NULL,
  `p_precio` DOUBLE NOT NULL,
  `p_stock` INT NOT NULL,
  `id_subcategoria` INT NOT NULL,
  PRIMARY KEY (`id_producto`),
  INDEX `FK9v702bgl5x2ydqv83xawa5hsf` (`id_subcategoria` ASC) VISIBLE,
  CONSTRAINT `FK9v702bgl5x2ydqv83xawa5hsf`
    FOREIGN KEY (`id_subcategoria`)
    REFERENCES `bdshopall`.`subcategoria` (`id_subcategoria`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`perfil_vendedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`perfil_vendedor` (
  `id_vendedor` INT NOT NULL AUTO_INCREMENT,
  `v_descripcion` VARCHAR(1000) NOT NULL,
  `v_giro` VARCHAR(140) NOT NULL,
  `v_nombre` VARCHAR(45) NOT NULL,
  `v_sitio_web` VARCHAR(150) NULL DEFAULT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_vendedor`),
  INDEX `FKfib4fbw24db2fn2utcagiilr3` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `FKfib4fbw24db2fn2utcagiilr3`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdshopall`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`detalle_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`detalle_pedido` (
  `iddpedido` INT NOT NULL AUTO_INCREMENT,
  `dtp_cantidad` INT NOT NULL,
  `dtp_total` DOUBLE NOT NULL,
  `id_pedido` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_vendedor` INT NOT NULL,
  PRIMARY KEY (`iddpedido`),
  INDEX `FKh10qteor08f4cbxhsf97qtgyk` (`id_pedido` ASC) VISIBLE,
  INDEX `FKnwadx4gon4by0uw748yo8chit` (`id_producto` ASC) VISIBLE,
  INDEX `FKnx26uef8eawiv57ii6duv3600` (`id_vendedor` ASC) VISIBLE,
  CONSTRAINT `FKh10qteor08f4cbxhsf97qtgyk`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `bdshopall`.`pedidos` (`id_pedido`),
  CONSTRAINT `FKnwadx4gon4by0uw748yo8chit`
    FOREIGN KEY (`id_producto`)
    REFERENCES `bdshopall`.`productos` (`id_producto`),
  CONSTRAINT `FKnx26uef8eawiv57ii6duv3600`
    FOREIGN KEY (`id_vendedor`)
    REFERENCES `bdshopall`.`perfil_vendedor` (`id_vendedor`))
ENGINE = InnoDB
AUTO_INCREMENT = 12
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`detalle_producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`detalle_producto` (
  `iddproducto` INT NOT NULL AUTO_INCREMENT,
  `dp_atributo` VARCHAR(45) NOT NULL,
  `dp_valor` VARCHAR(45) NOT NULL,
  `id_producto` INT NOT NULL,
  PRIMARY KEY (`iddproducto`),
  INDEX `FKmqea4ytgas8mnpef7le4wy0j1` (`id_producto` ASC) VISIBLE,
  CONSTRAINT `FKmqea4ytgas8mnpef7le4wy0j1`
    FOREIGN KEY (`id_producto`)
    REFERENCES `bdshopall`.`productos` (`id_producto`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`detalle_usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`detalle_usuario` (
  `iddusuario` INT NOT NULL AUTO_INCREMENT,
  `du_direccion` VARCHAR(140) NOT NULL,
  `du_telefono` VARCHAR(16) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`iddusuario`),
  INDEX `FK55lnv78j2qqb8h135l1hcdx8i` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `FK55lnv78j2qqb8h135l1hcdx8i`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdshopall`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`metodo_envio`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`metodo_envio` (
  `id_metodo_envio` INT NOT NULL AUTO_INCREMENT,
  `me_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_metodo_envio`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`metodo_pago`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`metodo_pago` (
  `id_metodo_pago` INT NOT NULL AUTO_INCREMENT,
  `mp_tipo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_metodo_pago`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`notificaciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`notificaciones` (
  `id_notificacion` INT NOT NULL AUTO_INCREMENT,
  `n_descripcion` VARCHAR(140) NOT NULL,
  `n_fecha` DATETIME(6) NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_notificacion`),
  INDEX `FKrr0ikjdv4qycj44q3lohskm4k` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `FKrr0ikjdv4qycj44q3lohskm4k`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdshopall`.`usuarios` (`id_usuario`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`perfil_vendedor_productos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`perfil_vendedor_productos` (
  `id_producto` INT NOT NULL,
  `id_vendedor` INT NOT NULL,
  PRIMARY KEY (`id_producto`, `id_vendedor`),
  INDEX `FKqrgutsq2l4pam7ni16hj7e33v` (`id_vendedor` ASC) VISIBLE,
  CONSTRAINT `FK8x4thl1bk88xo9rk1skuo0upr`
    FOREIGN KEY (`id_producto`)
    REFERENCES `bdshopall`.`productos` (`id_producto`),
  CONSTRAINT `FKqrgutsq2l4pam7ni16hj7e33v`
    FOREIGN KEY (`id_vendedor`)
    REFERENCES `bdshopall`.`perfil_vendedor` (`id_vendedor`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`reseñas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`reseñas` (
  `id_reseña` INT NOT NULL AUTO_INCREMENT,
  `r_comentario` VARCHAR(140) NOT NULL,
  `r_fecha` DATETIME(6) NOT NULL,
  `r_puntuacion` INT NOT NULL,
  `id_producto` INT NOT NULL,
  `id_usuario` INT NOT NULL,
  PRIMARY KEY (`id_reseña`),
  INDEX `FKokbfxrgg89h81ha3j1082swym` (`id_producto` ASC) VISIBLE,
  INDEX `FKj8ogkiyov278f81hxm3jswl54` (`id_usuario` ASC) VISIBLE,
  CONSTRAINT `FKj8ogkiyov278f81hxm3jswl54`
    FOREIGN KEY (`id_usuario`)
    REFERENCES `bdshopall`.`usuarios` (`id_usuario`),
  CONSTRAINT `FKokbfxrgg89h81ha3j1082swym`
    FOREIGN KEY (`id_producto`)
    REFERENCES `bdshopall`.`productos` (`id_producto`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bdshopall`.`transacciones`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdshopall`.`transacciones` (
  `id_transaccion` INT NOT NULL AUTO_INCREMENT,
  `t_direccion_envio` VARCHAR(140) NOT NULL,
  `t_fecha` DATETIME(6) NOT NULL,
  `t_total` DOUBLE NOT NULL,
  `id_metodo_envio` INT NOT NULL,
  `id_metodo_pago` INT NOT NULL,
  `id_pedido` INT NOT NULL,
  PRIMARY KEY (`id_transaccion`),
  INDEX `FKjdovpnc6ovl3ka1a6iplnt061` (`id_metodo_envio` ASC) VISIBLE,
  INDEX `FKktrjdf2xe9o037f5bqihg6xre` (`id_metodo_pago` ASC) VISIBLE,
  INDEX `FKera6kgnhabgxcyg5paabxg8ns` (`id_pedido` ASC) VISIBLE,
  CONSTRAINT `FKera6kgnhabgxcyg5paabxg8ns`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `bdshopall`.`pedidos` (`id_pedido`),
  CONSTRAINT `FKjdovpnc6ovl3ka1a6iplnt061`
    FOREIGN KEY (`id_metodo_envio`)
    REFERENCES `bdshopall`.`metodo_envio` (`id_metodo_envio`),
  CONSTRAINT `FKktrjdf2xe9o037f5bqihg6xre`
    FOREIGN KEY (`id_metodo_pago`)
    REFERENCES `bdshopall`.`metodo_pago` (`id_metodo_pago`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
