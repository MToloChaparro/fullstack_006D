-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-06-2026 a las 21:34:55
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ecogarzones_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id_insumo` bigint(20) NOT NULL,
  `cantidad_insumo` int(11) NOT NULL,
  `estado_insumos` enum('DEFECTUOSO','DISPONIBLE','INCOMPLETO','NO_DISPONIBLE','VENCIDO') NOT NULL,
  `fecha_actualizacion` datetime(6) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `nombre_insumo` varchar(255) NOT NULL,
  `precio_costo_unitario` decimal(10,2) DEFAULT NULL,
  `tipo_insumo` enum('ALIMENTOS_NO_PERECIBLES','ALIMENTOS_PERECIBLES','BEBIDAS_ALCOHOLICAS','BEBIDAS_GASEOSAS','BEBIDAS_NO_ALCOHOLICAS','CARNES','FRUTAS','HIGIENE_GENERAL','HIGIENE_PERSONAL','LACTEOS','MUEBLERIA','OTROS','PESCADOS','UTENSILIOS','VERDURAS') NOT NULL,
  `unidad_medida` enum('CC','GRAMOS','KILOGRAMOS','LITROS','PACK_CAJA','UNIDADES') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id_insumo`, `cantidad_insumo`, `estado_insumos`, `fecha_actualizacion`, `fecha_creacion`, `nombre_insumo`, `precio_costo_unitario`, `tipo_insumo`, `unidad_medida`) VALUES
(1, 30, 'DISPONIBLE', '2026-06-09 19:31:22.000000', '2026-06-09 19:31:22.000000', 'Manteles Blancos Premium', 4500.00, 'MUEBLERIA', 'UNIDADES'),
(2, 15, 'DISPONIBLE', '2026-06-09 19:31:29.000000', '2026-06-09 19:31:29.000000', 'Lomo Liso Vacuno', 10500.00, 'CARNES', 'KILOGRAMOS'),
(3, 60, 'DISPONIBLE', '2026-06-09 19:31:36.000000', '2026-06-09 19:31:36.000000', 'Bebida Cola 3L', 2200.00, 'BEBIDAS_GASEOSAS', 'LITROS'),
(4, 120, 'DISPONIBLE', '2026-06-09 19:31:42.000000', '2026-06-09 19:31:42.000000', 'Platos de Fondo Porcelana', 2800.00, 'UTENSILIOS', 'UNIDADES');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id_insumo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id_insumo` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
