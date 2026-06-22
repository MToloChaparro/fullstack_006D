-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-06-2026 a las 03:29:13
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
-- Base de datos: `cliente_ecogarzones`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `apellidom_cliente` varchar(255) DEFAULT NULL,
  `apellidop_cliente` varchar(255) NOT NULL,
  `correo_cliente` varchar(255) DEFAULT NULL,
  `dv_rut` varchar(1) NOT NULL,
  `id_comuna` int(11) DEFAULT NULL,
  `nombre_cliente` varchar(255) NOT NULL,
  `rut_cliente` int(11) NOT NULL,
  `telefono_cliente` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id_cliente`, `apellidom_cliente`, `apellidop_cliente`, `correo_cliente`, `dv_rut`, `id_comuna`, `nombre_cliente`, `rut_cliente`, `telefono_cliente`) VALUES
(1, 'Pérez', 'García', 'ignacio.garcia@gmail.com', '4', 119, 'Ignacio', 18456123, '+56912345678'),
(2, 'Silva', 'Cárdenas', 'camila.cardenas@gmail.com', 'K', 115, 'Camila', 19234567, '+56987654321'),
(3, 'Araya', 'Muñoz', 'gonzalo.munoz@outlook.com', '3', 121, 'Gonzalo', 15678912, '+56955554433'),
(4, 'Castro', 'Rojas', 'v.rojas@yahoo.com', '2', 118, 'Valentina', 17987654, '+56944332211'),
(5, 'Vargas', 'Andrade', 'mauricio.andrade@gmail.com', '8', 13101, 'Mauricio', 16332994, '+56955554444');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `UKc8tvamu6wat8ol5707bfcd2og` (`correo_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
