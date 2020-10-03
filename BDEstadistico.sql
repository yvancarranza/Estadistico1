-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 14-12-2019 a las 16:59:38
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sanamente`
--
CREATE DATABASE IF NOT EXISTS `sanamente` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `sanamente`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tbpersonal`
--

CREATE TABLE `tbpersonal` (
  `idpersonal` int(11) NOT NULL,
  `dni` char(12) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` char(50) NOT NULL,
  `telefono` char(50) NOT NULL,
  `direccion` varchar(150) DEFAULT NULL,
  `ubigeo` char(50) DEFAULT NULL,
  `genero` char(15) DEFAULT NULL,
  `fecregistro` timestamp NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecmodifica` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbpersonal`
--

INSERT INTO `tbpersonal` (`idpersonal`, `dni`, `nombres`, `apellidos`, `correo`, `telefono`, `direccion`, `ubigeo`, `genero`, `fecregistro`, `fecmodifica`) VALUES
(12, '42937032', 'Yvan', 'Carranza', 'yvan@correo.com', '2851338', 'Av. Lima 258', 'Comas', 'Hombre', '2019-12-12 01:36:24', '0000-00-00 00:00:00'),
(13, '12345678', 'Guillermo', 'Gonzales', 'guillermo@peru.com', '12346678', 'Jr. Murillo 898', 'Surco', 'Hombre', '2019-12-12 01:36:50', '0000-00-00 00:00:00'),
(14, '34567895', 'Jhosep', 'Garcia', 'jhosep@peru.com', '12345678', 'Av. Costanera 879', 'Magdalena del Mar', 'Hombre', '2019-12-12 01:37:03', '0000-00-00 00:00:00'),
(15, '42557289', 'Luis', 'Ramirez', 'lramirez@peru.com', '536-8989', 'jr. Jose Olaya 875', 'Magdalena del Mar', 'Hombre', '2019-12-12 01:37:22', '0000-00-00 00:00:00'),
(17, '42369855', 'Maria', 'De la Vega', 'mdelavega@peru.com', '536-9898', 'Jr. José Gálvez 276', 'Independencia', 'Mujer', '2019-12-12 01:38:22', '0000-00-00 00:00:00');


--
-- Estructura de tabla para la tabla `tbusuario`
--

CREATE TABLE `tbusuario` (
  `idusuario` char(50) NOT NULL,
  `nomusuario` varchar(100) DEFAULT NULL,
  `password` char(15) NOT NULL,
  `tipodeusuario` char(10) NOT NULL,
  `estado` char(10) NOT NULL,
  `tienecaducidad` char(2) NOT NULL,
  `fechacaduca` date DEFAULT NULL,
  `fecregistro` datetime DEFAULT current_timestamp(),
  `fecmodifica` date DEFAULT NULL,
  `fecacceso` date DEFAULT NULL,
  `idpersona` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tbusuario`
--

INSERT INTO `tbusuario` (`idusuario`, `nomusuario`, `password`, `tipodeusuario`, `estado`, `tienecaducidad`, `fechacaduca`, `fecregistro`, `fecmodifica`, `fecacceso`, `idpersona`) VALUES
('dfarro@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-11-16 10:21:31', NULL, NULL, 3),
('fmorante@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-11-16 12:28:24', NULL, NULL, 4),
('guillermo@peru.com', NULL, '123456', 'Admin', 'ACTIVO', 'NO', NULL, '2019-11-15 07:57:04', NULL, NULL, 13),
('jhosep@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'NO', NULL, '2019-11-15 07:57:49', NULL, NULL, 14),
('jperez@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'NO', NULL, '2019-11-15 22:30:44', NULL, NULL, 15),
('jsaavedra@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-11-16 09:08:06', NULL, NULL, 2),
('julio@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-11-16 08:47:15', NULL, NULL, 1),
('liliana@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-12-12 22:23:39', NULL, NULL, 6),
('lramirez@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'NO', NULL, '2019-11-16 12:21:52', NULL, NULL, 15),
('lrodriguez@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'SI', NULL, '2019-11-16 12:48:50', NULL, NULL, 5),
('mdelavega@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'NO', NULL, '2019-11-16 12:47:38', NULL, NULL, 17),
('rcalla@peru.com', NULL, '123456', 'Personal', 'ACTIVO', 'NO', NULL, '2019-11-16 12:27:30', NULL, NULL, 16),
('yvan@correo.com', NULL, '123456', 'Admin', 'ACTIVO', 'NO', NULL, '2019-11-15 00:43:22', NULL, NULL, 12);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `tbpersonal`
--
ALTER TABLE `tbpersonal`
  ADD PRIMARY KEY (`idpersonal`);


--
-- Indices de la tabla `tbusuario`
--
ALTER TABLE `tbusuario`
  ADD PRIMARY KEY (`idusuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `tbpersonal`
--
ALTER TABLE `tbpersonal`
  MODIFY `idpersonal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;


--
-- Restricciones para tablas volcadas
--


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
