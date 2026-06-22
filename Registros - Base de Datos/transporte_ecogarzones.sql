-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:45
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `transporte_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transporte`
--

CREATE TABLE `transporte` (
  `idtransporte` int(11) NOT NULL,
  `apellidomconductor` varchar(255) DEFAULT NULL,
  `apellidopconductor` varchar(255) DEFAULT NULL,
  `comunadestino` varchar(255) DEFAULT NULL,
  `costotraslado` double DEFAULT NULL,
  `estadovehiculo` varchar(255) DEFAULT NULL,
  `horadespachoestimada` datetime(6) DEFAULT NULL,
  `horasalidaestimada` datetime(6) DEFAULT NULL,
  `idevento` int(11) DEFAULT NULL,
  `marcavehiculo` varchar(255) DEFAULT NULL,
  `modelovehiculo` varchar(255) DEFAULT NULL,
  `nombreconductor` varchar(255) DEFAULT NULL,
  `patentevehiculo` varchar(255) DEFAULT NULL,
  `rutconductor` varchar(255) DEFAULT NULL,
  `tipotransporte` enum('GENERAL','INSUMOS','PARTICULAR','STAFF') DEFAULT NULL,
  `tipovehiculo` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `transporte`
--
ALTER TABLE `transporte`
  ADD PRIMARY KEY (`idtransporte`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `transporte`
--
ALTER TABLE `transporte`
  MODIFY `idtransporte` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
