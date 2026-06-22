-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:01
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
-- Base de datos: `administrador_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ms_administrador`
--

CREATE TABLE `ms_administrador` (
  `id_administrador` int(11) NOT NULL,
  `apellidom_administrador` varchar(255) DEFAULT NULL,
  `apellidop_administrador` varchar(255) NOT NULL,
  `correo_administrador` varchar(255) NOT NULL,
  `dv_rut` varchar(1) NOT NULL,
  `nombre_administrador` varchar(255) NOT NULL,
  `rol_administrador` varchar(255) NOT NULL,
  `rut_administrador` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ms_administrador`
--

INSERT INTO `ms_administrador` (`id_administrador`, `apellidom_administrador`, `apellidop_administrador`, `correo_administrador`, `dv_rut`, `nombre_administrador`, `rol_administrador`, `rut_administrador`) VALUES
(1, 'Silva', 'Garzones', 'alejandro.admin@ecogarzones.com', 'K', 'Alejandro', 'Administrador General', 15993847),
(2, 'Castro', 'Villalobos', 'beatriz.villalobos@ecogarzones.com', '8', 'Beatriz', 'Supervisora de Finanzas', 18273645),
(3, 'Fuenzalida', 'Tapia', 'gonzalo.tapia@ecogarzones.com', 'K', 'Gonzalo', 'Coordinador de Logística', 14883920),
(4, 'Pinto', 'Vergara', 'camila.vergara@ecogarzones.com', '4', 'Camila', 'Jefa de Planificación', 17394850),
(5, 'Cárdenas', 'Sanhueza', 'rodrigo.sanhueza@ecogarzones.com', '3', 'Rodrigo', 'Encargado de Selección Staff', 15662738),
(6, 'Araya', 'Donoso', 'valeria.donoso@ecogarzones.com', '5', 'Valeria', 'Directora de Finanzas', 16992834),
(7, 'Maturana', 'Oyanedel', 'patricio.oyanedel@ecogarzones.com', 'K', 'Patricio', 'Supervisor de Abastecimiento', 13442819),
(8, 'Jara', 'Riquelme', 'natalia.riquelme@ecogarzones.com', '2', 'Natalia', 'Gestora de Experiencia Cliente', 18554920);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ms_administrador`
--
ALTER TABLE `ms_administrador`
  ADD PRIMARY KEY (`id_administrador`),
  ADD UNIQUE KEY `UK6gb14jpwdidlkugambdxxg5sb` (`correo_administrador`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ms_administrador`
--
ALTER TABLE `ms_administrador`
  MODIFY `id_administrador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
