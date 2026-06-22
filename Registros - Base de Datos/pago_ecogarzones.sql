-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:34
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
-- Base de datos: `pago_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ms_pago_servicio`
--

CREATE TABLE `ms_pago_servicio` (
  `id_pago_servicio` int(11) NOT NULL,
  `fecha_pago` date NOT NULL,
  `hora_pago` time NOT NULL,
  `id_estado_pago` int(11) NOT NULL,
  `id_metodo_pago` int(11) NOT NULL,
  `monto_pago` int(11) NOT NULL,
  `id_boleta` int(11) DEFAULT NULL,
  `id_evento` int(11) NOT NULL,
  `numero_transaccion` varchar(100) DEFAULT NULL,
  `registrado_por` varchar(50) DEFAULT NULL,
  `rut_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ms_pago_servicio`
--

INSERT INTO `ms_pago_servicio` (`id_pago_servicio`, `fecha_pago`, `hora_pago`, `id_estado_pago`, `id_metodo_pago`, `monto_pago`, `id_boleta`, `id_evento`, `numero_transaccion`, `registrado_por`, `rut_cliente`) VALUES
(3, '2026-06-11', '14:30:00', 1, 2, 150000, NULL, 10, NULL, NULL, 17438921),
(4, '2026-06-11', '21:00:00', 1, 1, 145000, NULL, 12, NULL, NULL, 19883472),
(5, '2026-06-11', '15:30:00', 1, 2, 250000, NULL, 1, NULL, NULL, 16332994);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ms_pago_servicio`
--
ALTER TABLE `ms_pago_servicio`
  ADD PRIMARY KEY (`id_pago_servicio`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ms_pago_servicio`
--
ALTER TABLE `ms_pago_servicio`
  MODIFY `id_pago_servicio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
