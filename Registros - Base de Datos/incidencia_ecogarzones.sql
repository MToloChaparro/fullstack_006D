-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:25
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
-- Base de datos: `incidencia_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reportes_incidencias`
--

CREATE TABLE `reportes_incidencias` (
  `id` bigint(20) NOT NULL,
  `categoria_incidencia` enum('CLIENTE','INVENTARIO','LOGISTICA','PERSONAL','SEGURIDAD') NOT NULL,
  `codigo_ticket` varchar(50) NOT NULL,
  `comentarios_resolucion` text DEFAULT NULL,
  `costo_estimado` double DEFAULT NULL,
  `descripcion_incidencia` text NOT NULL,
  `estado_incidencia_string` enum('ANULADO','EN_PROCESO','PENDIENTE','RESUELTO') NOT NULL,
  `fecha_incidencia` datetime(6) DEFAULT NULL,
  `fecha_resolucion` datetime(6) DEFAULT NULL,
  `id_administrador_asignado` bigint(20) DEFAULT NULL,
  `id_usuario_reporta` bigint(20) NOT NULL,
  `prioridad_incidencia` enum('ALTA','BAJA','CRITICA','MEDIA') NOT NULL,
  `titulo_incidencia` varchar(100) NOT NULL,
  `id_evento` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `reportes_incidencias`
--

INSERT INTO `reportes_incidencias` (`id`, `categoria_incidencia`, `codigo_ticket`, `comentarios_resolucion`, `costo_estimado`, `descripcion_incidencia`, `estado_incidencia_string`, `fecha_incidencia`, `fecha_resolucion`, `id_administrador_asignado`, `id_usuario_reporta`, `prioridad_incidencia`, `titulo_incidencia`, `id_evento`) VALUES
(1, 'LOGISTICA', 'INC-B9029736', NULL, 350000, 'El proveedor del servicio de alimentación presenta un retraso de 45 minutos afectando el cronograma oficial de la cena de gala.', 'PENDIENTE', '2026-06-20 17:34:41.000000', NULL, NULL, 12, 'ALTA', 'Retraso en la entrega del catering para la gala', 16),
(2, 'LOGISTICA', 'INC-2CD83677', NULL, 150000, 'El camión de provisiones quedó atrapado en el tráfico de Av. Vicuña Mackenna. Se estima un retraso de 20 minutos para el inicio de la Entrada.', 'PENDIENTE', '2026-06-20 17:57:41.000000', NULL, NULL, 1, 'ALTA', 'Retraso crítico en el catering de la Gala', 16),
(3, 'LOGISTICA', 'INC-3F7C3D83', 'Se cambio cable XLR danado y se soluciono el zumbido', 45000, 'El microfono principal presenta un zumbido constante durante las pruebas de la muestra de tango.', 'RESUELTO', '2026-06-20 18:01:51.000000', '2026-06-20 18:03:15.000000', 1, 1, 'ALTA', 'Falla de sonido en escenario', 16);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `reportes_incidencias`
--
ALTER TABLE `reportes_incidencias`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKbu2xvjuvsmlrukarww0hpgja0` (`codigo_ticket`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `reportes_incidencias`
--
ALTER TABLE `reportes_incidencias`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
