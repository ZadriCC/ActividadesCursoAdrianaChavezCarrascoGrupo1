-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 19-08-2023 a las 05:25:56
-- Versión del servidor: 8.0.31
-- Versión de PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `taskmanager`
--
CREATE DATABASE IF NOT EXISTS `taskmanager` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `taskmanager`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

DROP TABLE IF EXISTS `roles`;
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre_rol` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `nombre_rol`) VALUES
(1, 'ROLE_USER'),
(2, 'ROLE_ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

DROP TABLE IF EXISTS `tareas`;
CREATE TABLE IF NOT EXISTS `tareas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKq4s0yoobcrqe2yay8v6l1nau5` (`descripcion`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`id`, `descripcion`, `status`) VALUES
(3, 'Tarea 1', 'Completada'),
(4, 'Tarea 3', 'pendiente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKm2dvbwfge291euvmk6vkkocao` (`username`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `email`, `nombre`, `password`, `username`) VALUES
(1, 'a1@hotmail.com', 'adri', '$2a$10$97SjfjiMjYS04zGgSwwq7uofSBKr.6UX/JrCBeDo/FlmCTjQ1t1B6', 'Zadri'),
(2, 'a2@hotmail.com', 'admin', '$2a$10$97SjfjiMjYS04zGgSwwq7uofSBKr.6UX/JrCBeDo/FlmCTjQ1t1B6', 'admin');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
CREATE TABLE IF NOT EXISTS `usuarios_roles` (
  `usuario_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FK5338ehgluufgc8bpj08nrq970` (`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT INTO `usuarios_roles` (`usuario_id`, `rol_id`) VALUES
(1, 1),
(2, 2);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `FK5338ehgluufgc8bpj08nrq970` FOREIGN KEY (`rol_id`) REFERENCES `roles` (`id`),
  ADD CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
