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


-- Tabla de Preguntas
CREATE TABLE tbpregunta (
  idpregunta   int(11) NOT NULL,
  codpregunta   char(50) NOT NULL,
  nompregunta  varchar(250) DEFAULT NULL,
  tipocampo    char(50) NOT NULL,
  formulario   char(50) NOT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbpregunta
  ADD PRIMARY KEY (idpregunta);
  
ALTER TABLE tbpregunta
  MODIFY idpregunta int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

-- Tabla de Respuestas

CREATE TABLE tbrespuesta (
  idrespuesta      int(11) NOT NULL,
  codpregunta  char(50) NOT NULL,
  codrespuesta     char(50) NOT NULL,
  nomrespuesta     varchar(250) DEFAULT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbrespuesta
  ADD PRIMARY KEY (idrespuesta);
  
ALTER TABLE tbrespuesta
  MODIFY idrespuesta int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


-- Tabla de Encuestas

CREATE TABLE tbencuesta (
  idregistro   int(11) NOT NULL,
  idencuesta   int(11) NOT NULL,
  codpregunta  char(50) NOT NULL,
  valrespuesta     char(50) NOT NULL,
  fecregistro  datetime DEFAULT current_timestamp(),
  fecmodifica  date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbencuesta
  ADD PRIMARY KEY (idregistro);

ALTER TABLE tbencuesta
  MODIFY idregistro int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;



-- Tabla de Variables Finales
CREATE TABLE tbvarfinal(
	idregistro		int(11) NOT NULL,
	codvarfin	char(15) NOT NULL,
	nomvarfin	Varchar(50) NOT NULL,
	operacion   CHAR(15) NOT NULL,
	origen      CHAR(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE tbvarfinal
  ADD PRIMARY KEY (idregistro);

ALTER TABLE tbvarfinal
  MODIFY idregistro int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


INSERT INTO tbvarfinal(codvarfin,nomvarfin,operacion,origen) VALUES
('ESTRES','Estrés','SUMA','BASE'),
('ANSIEDAD','Ansiedad','SUMA','BASE'),
('DEPRESION','Depresión','SUMA','BASE'),
('GPD','GDP Total','SUMA','BASE'),
('RIESGO_SUICIDA','Riesgo Suicida','SUMA','BASE'),
('TEPT','TEPT','SUMA','BASE'),
('RISILIENCIA','Risiliencia de Block','SUMA','BASE'),
('POBREZA','Nivel de Pobreza','SUMA','BASE');


-- Tabla de Parametrizacion para Calculo
CREATE TABLE tbparamcal(
	idparam		int(11) NOT NULL,
	codvarfin	char(15) NOT NULL,
	codvarori	char(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbparamcal
  ADD PRIMARY KEY (idparam);

ALTER TABLE tbparamcal
  MODIFY idparam int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;

-- Parametrizar Estres
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('ESTRES','iii1a'),
('ESTRES','iii1f'),
('ESTRES','iii1h'),
('ESTRES','iii1k'),
('ESTRES','iii1l'),
('ESTRES','iii1n'),
('ESTRES','iii1r');

-- Parametrizar Ansiedad
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('ANSIEDAD','iii1b'),
('ANSIEDAD','iii1d'),
('ANSIEDAD','iii1g'),
('ANSIEDAD','iii1i'),
('ANSIEDAD','iii1o'),
('ANSIEDAD','iii1s'),
('ANSIEDAD','iii1t');

-- Parametrizar Ansiedad
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('DEPRESION','iii1c'),
('DEPRESION','iii1e'),
('DEPRESION','iii1j'),
('DEPRESION','iii1m'),
('DEPRESION','iii1p'),
('DEPRESION','iii1q'),
('DEPRESION','iii1u');

-- Parametrizar GPD
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('GPD','iii1a'),
('GPD','iii1f'),
('GPD','iii1h'),
('GPD','iii1k'),
('GPD','iii1l'),
('GPD','iii1n'),
('GPD','iii1r'),
('GPD','iii1b'),
('GPD','iii1d'),
('GPD','iii1g'),
('GPD','iii1i'),
('GPD','iii1o'),
('GPD','iii1s'),
('GPD','iii1t'),
('GPD','iii1c'),
('GPD','iii1e'),
('GPD','iii1j'),
('GPD','iii1m'),
('GPD','iii1p'),
('GPD','iii1q'),
('GPD','iii1u');


-- Parametrizar Riesgo Suicida
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('RIESGO_SUICIDA','iii3a'),
('RIESGO_SUICIDA','iii3b'),
('RIESGO_SUICIDA','iii3c'),
('RIESGO_SUICIDA','iii3d'),
('RIESGO_SUICIDA','iii3e'),
('RIESGO_SUICIDA','iii3f');

-- Parametrizar Riesgo Suicida
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('TEPT','iii2b'),
('TEPT','iii2c'),
('TEPT','iii2d'),
('TEPT','iii2e'),
('TEPT','iii2f');

-- Parametrizar Risiliencia Block
INSERT INTO tbparamcal(codvarfin,codvarori) VALUES
('RISILIENCIA','iii10a'),
('RISILIENCIA','iii10b'),
('RISILIENCIA','iii10c'),
('RISILIENCIA','iii10d'),
('RISILIENCIA','iii10e'),
('RISILIENCIA','iii10f'),
('RISILIENCIA','iii10g'),
('RISILIENCIA','iii10h'),
('RISILIENCIA','iii10i'),
('RISILIENCIA','iii10j'),
('RISILIENCIA','iii10k'),
('RISILIENCIA','iii10l'),
('RISILIENCIA','iii10m'),
('RISILIENCIA','iii10n');

-- Tabla de Rango de Variable Final
CREATE TABLE tbrangovar(
	idregistro		int(11) NOT NULL,
	codvarfin	char(15) NOT NULL,
	rangoini	int(11) NOT NULL,
	rangofin	int(11) NOT NULL,
	desrango	VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE tbrangovar
  ADD PRIMARY KEY (idregistro);

ALTER TABLE tbrangovar
  MODIFY idregistro int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('ESTRES',0,14,'Normal'),
('ESTRES',15,18,'Medio'),
('ESTRES',19,25,'Moderado'),
('ESTRES',26,33,'Severo'),
('ESTRES',34,999,'Extremadamente Severo');

INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('ANSIEDAD',0,7,'Normal'),
('ANSIEDAD',8,9,'Medio'),
('ANSIEDAD',10,14,'Moderado'),
('ANSIEDAD',15,19,'Severo'),
('ANSIEDAD',20,999,'Extremadamente Severo');

INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('DEPRESION',0,9,'Normal'),
('DEPRESION',10,13,'Medio'),
('DEPRESION',14,20,'Moderado'),
('DEPRESION',21,27,'Severo'),
('DEPRESION',28,999,'Extremadamente Severo');

INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('GPD',0,25,'Normal'),
('GPD',26,34,'Medio'),
('GPD',35,59,'Moderado'),
('GPD',60,78,'Severo'),
('GPD',79,999,'Extremadamente Severo');

INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('RIESGO_SUICIDA',1,5,'Bajo'),
('RIESGO_SUICIDA',6,9,'Moderado'),
('RIESGO_SUICIDA',10,999,'Alto');


INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('TEPT',0,2,'Bajo'),
('TEPT',3,3,'Alta'),
('TEPT',4,5,'Muy Alta');

INSERT INTO tbrangovar(codvarfin,rangoini,rangofin,desrango) VALUES
('POBREZA',1,1,'Pobre Extremo'),
('POBREZA',2,2,'Pobre'),
('POBREZA',3,4,'No Pobre');


-- Tabla Consolidada
CREATE TABLE tbReporte(
	idregistro		int(11) NOT NULL,
	idencuesta		char(15) NOT NULL,
	departamento    char(25) NOT NULL,
	sexo            CHAR(15) NOT NULL,
	codvarfin		char(15) NOT NULL,
	valresult		numeric(11,2) NOT NULL,
	codresultado	char(15) NOT NULL,
	porcentaje      DECIMAL(11,2) NOT NULL,
	desviacion      DECIMAL(11,2) NOT NULL,
	media           DECIMAL(11,2) NOT NULL,
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
ALTER TABLE tbReporte
  ADD PRIMARY KEY (idregistro);

ALTER TABLE tbReporte
  MODIFY idregistro int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1;


