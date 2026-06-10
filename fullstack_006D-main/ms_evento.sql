-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2026 a las 09:37:18
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
-- Base de datos: `evento_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ms_evento`
--

CREATE TABLE `ms_evento` (
  `id_evento` int(11) NOT NULL,
  `descripcion_evento` varchar(500) DEFAULT NULL,
  `direccion_evento` varchar(200) NOT NULL,
  `fecha_evento` date NOT NULL,
  `hora_evento` time NOT NULL,
  `id_administrador` int(11) DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_comuna` varchar(255) DEFAULT NULL,
  `id_estado_evento` int(11) NOT NULL,
  `id_pago_servicio` int(11) DEFAULT NULL,
  `nombre_evento` varchar(100) NOT NULL,
  `id_menu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ms_evento`
--

INSERT INTO `ms_evento` (`id_evento`, `descripcion_evento`, `direccion_evento`, `fecha_evento`, `hora_evento`, `id_administrador`, `id_cliente`, `id_comuna`, `id_estado_evento`, `id_pago_servicio`, `nombre_evento`, `id_menu`) VALUES
(12, 'Celebración de boda con ceremonia al aire libre y fiesta bailable.', 'Av. Pajaritos 2500', '2026-11-28', '17:30:00', NULL, 201, 'rm0119', 2, NULL, 'Matrimonio Civil e Iglesia - Ignacio y Camila', 2),
(15, 'Congreso tecnológico con expositores internacionales de IA y Break de almuerzo.', 'Alameda Bernardo O\'Higgins 1300', '2026-10-15', '09:00:00', NULL, 302, 'rm0101', 5, NULL, 'Tech & Future Summit 2026', 9),
(16, 'Cena de gala oficial conmemorativa de la Independencia Argentina. Contará con la presencia del cuerpo diplomático chileno, ministros de estado y embajadores. Incluye una muestra de tango en vivo, discursos protocolares y un banquete tradicional transandino de alta gama.', 'Av. Vicuña Mackenna 450', '2026-07-09', '20:00:00', NULL, 401, 'rm0101', 2, NULL, 'Gala de la Confraternidad Argentino-Chilena', 10);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `ms_evento`
--
ALTER TABLE `ms_evento`
  ADD PRIMARY KEY (`id_evento`),
  ADD KEY `FKhydui1cv7mje976l0togwyl4r` (`id_menu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `ms_evento`
--
ALTER TABLE `ms_evento`
  MODIFY `id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ms_evento`
--
ALTER TABLE `ms_evento`
  ADD CONSTRAINT `FKhydui1cv7mje976l0togwyl4r` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
