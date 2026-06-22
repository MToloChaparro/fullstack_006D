-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:39
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
-- Base de datos: `staff_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ms_staff`
--

CREATE TABLE `ms_staff` (
  `id_staff` int(11) NOT NULL,
  `apellidom_staff` varchar(255) DEFAULT NULL,
  `apellidop_staff` varchar(255) NOT NULL,
  `correo_staff` varchar(255) DEFAULT NULL,
  `dv_rut` varchar(1) NOT NULL,
  `id_estado_staff` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `nombre_staff` varchar(255) NOT NULL,
  `rut_staff` int(11) NOT NULL,
  `telefono_staff` varchar(255) DEFAULT NULL,
  `valor_hora` double NOT NULL,
  `motivo_desvinculacion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ms_staff`
--

INSERT INTO `ms_staff` (`id_staff`, `apellidom_staff`, `apellidop_staff`, `correo_staff`, `dv_rut`, `id_estado_staff`, `id_rol`, `nombre_staff`, `rut_staff`, `telefono_staff`, `valor_hora`, `motivo_desvinculacion`) VALUES
(14, 'Pérez', 'Muñoz', 'roberto.vacaciones@ecogarzones.com', '3', 2, 5, 'Roberto', 16448392, '+56977771111', 14000, NULL),
(15, 'Palma', 'Herrera', 'carlos.chef@ecogarzones.com', 'K', 1, 5, 'Carlos', 13884920, '+56955551111', 18000, NULL),
(16, 'Torres', 'San Martín', 'miguel.copero@ecogarzones.com', '4', 2, 7, 'Miguel', 16774829, '+56955552222', 6500, NULL),
(17, 'Cruzat', 'Subercaseaux', 'elena.sommelier@ecogarzones.com', '8', 3, 2, 'Elena', 15223849, '+56955553333', 15000, NULL),
(18, 'Molina', 'Barrientos', 'alvaro.coordinador@ecogarzones.com', '2', 1, 6, 'Álvaro', 17441920, '+56955554444', 13500, NULL),
(19, 'Cotapos', 'Pérez', 'juan.perez@ecogarzones.com', 'K', 1, 1, 'Juan', 19223847, '+56911112222', 8500, NULL),
(21, 'SisNine', 'Sixseven', 'sixseven.67@gmail.com', 'k', 4, 5, 'Skibidi', 16444587, '67676767', 67000, 'Incumplimiento de contrato.');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ms_staff`
--
ALTER TABLE `ms_staff`
  ADD PRIMARY KEY (`id_staff`),
  ADD UNIQUE KEY `UKjvj8drfkfj4q75khpq1tyxpdc` (`correo_staff`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ms_staff`
--
ALTER TABLE `ms_staff`
  MODIFY `id_staff` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
