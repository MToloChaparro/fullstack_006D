-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:19
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
-- Base de datos: `cronograma_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `actividades`
--

CREATE TABLE `actividades` (
  `id_actividad` int(11) NOT NULL,
  `area_recinto` varchar(255) DEFAULT NULL,
  `descripcion_detallada` varchar(1000) DEFAULT NULL,
  `hora_fin` datetime(6) NOT NULL,
  `hora_inicio` datetime(6) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `responsable_bloque` varchar(255) DEFAULT NULL,
  `titulo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ms_cronograma_actividades`
--

CREATE TABLE `ms_cronograma_actividades` (
  `id_actividad` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `descripcion_detallada` varchar(1000) DEFAULT NULL,
  `hora_inicio` datetime NOT NULL,
  `hora_fin` datetime NOT NULL,
  `responsable_bloque` varchar(100) DEFAULT NULL,
  `area_recinto` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ms_cronograma_actividades`
--

INSERT INTO `ms_cronograma_actividades` (`id_actividad`, `id_evento`, `titulo`, `descripcion_detallada`, `hora_inicio`, `hora_fin`, `responsable_bloque`, `area_recinto`) VALUES
(1, 16, 'Recepción de Autoridades y Copa de Bienvenida', 'Llegada del cuerpo diplomático chileno, ministros de estado y embajadores. Servicio de espumante y appetizers finos.', '2026-07-09 22:00:00', '2026-07-09 22:30:00', 'Maitre de Protocolo', 'Hall de Acceso Principal'),
(2, 16, 'Discursos Protocolares e Himnos', 'Palabras de bienvenida de los embajadores oficiales e inicio formal de la velada conmemorativa.', '2026-07-09 22:30:00', '2026-07-09 23:15:00', 'Coordinador de Producción', 'Salón de Honor'),
(3, 16, 'Cena de Gala y Banquete Transandino', 'Servicio del menú de alta gama (id_menu: 10) a las mesas de los invitados principales y delegaciones.', '2026-07-09 23:15:00', '2026-07-10 01:00:00', 'Jefe de Cocina / Maitre', 'Salón de Honor'),
(4, 30, 'Almuerzo Buffet', 'Servicio de almuerzo principal con estaciones de carnes y ensaladas.', '2026-12-05 16:30:00', '2026-12-05 18:30:00', 'Jefe de Cocina', 'Comedor Central'),
(5, 16, 'Show Cultural: Muestra de Tango en Vivo', 'Presentación de la compañía de baile invitada. Despeje parcial de la zona del frente del escenario para visibilidad de las mesas diplomáticas.', '2026-07-10 01:00:00', '2026-07-10 01:45:00', 'Coordinador de Eventos / Encargado de Audio', 'Escenario Central'),
(6, 16, 'Brindis de la Confraternidad y Servicio de Postres', 'Discurso de cierre de los ministros de estado. Servicio simultáneo de postre tradicional (id_menu: 10) y copas de vino de honor para el brindis.', '2026-07-10 01:45:00', '2026-07-10 02:30:00', 'Maitre Principal / Supervisor de Cocina', 'Salón de Honor'),
(7, 16, 'Término de la Gala y Despido de Autoridades', 'Retiro ordenado del cuerpo diplomático y autoridades. Cierre de puertas del recinto y comienzo del inventario de vajilla por parte de Ecogarzones.', '2026-07-10 02:30:00', '2026-07-10 03:30:00', 'Supervisor de Turno / Seguridad', 'Todo el Recinto (Salón y Accesos)');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `actividades`
--
ALTER TABLE `actividades`
  ADD PRIMARY KEY (`id_actividad`);

--
-- Indices de la tabla `ms_cronograma_actividades`
--
ALTER TABLE `ms_cronograma_actividades`
  ADD PRIMARY KEY (`id_actividad`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `actividades`
--
ALTER TABLE `actividades`
  MODIFY `id_actividad` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ms_cronograma_actividades`
--
ALTER TABLE `ms_cronograma_actividades`
  MODIFY `id_actividad` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
