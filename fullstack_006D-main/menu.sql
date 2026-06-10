-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 10-06-2026 a las 09:37:00
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
-- Estructura de tabla para la tabla `menu`
--

CREATE TABLE `menu` (
  `id_menu` int(11) NOT NULL,
  `bebidas_pedidas` varchar(255) DEFAULT NULL,
  `numero_personas` int(11) DEFAULT NULL,
  `plato_entrada` varchar(255) DEFAULT NULL,
  `plato_postre` varchar(255) DEFAULT NULL,
  `plato_principal` varchar(255) DEFAULT NULL,
  `restricciones_alergias` varchar(255) DEFAULT NULL,
  `tipo_barra` varchar(255) DEFAULT NULL,
  `tipo_menu` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `menu`
--

INSERT INTO `menu` (`id_menu`, `bebidas_pedidas`, `numero_personas`, `plato_entrada`, `plato_postre`, `plato_principal`, `restricciones_alergias`, `tipo_barra`, `tipo_menu`) VALUES
(1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'Bebidas, Jugos naturales, Vino', 150, 'Ceviche de salmón', 'Volcán de chocolate', 'Filete con papas rústicas', '2 vegetarianos, 1 celíaco', 'Abierta Internacional', 'Premium'),
(3, 'Vinos reserva, bebidas, jugos naturales y agua mineral con gas', 120, 'Timbal de centolla magallánica con palta e hilos de camote', 'Trilogía de chocolates (volcán, mousse y helado artesanal de avellana)', 'Garrón de cordero lechal al carmenere con puré rústico trufado', '4 opciones vegetarianas, 2 menús sin gluten (celíacos)', 'Barra Abierta Premium (Whisky, Pisco, Gin y Cocktails de autor)', 'Gala Corporativa'),
(4, 'Vinos y Bebidas', 150, 'Ceviche de salmón', 'Volcán de chocolate', 'Filete con papas rústicas', 'Ninguna', 'Abierta Internacional', 'Premium Gala'),
(5, 'Bebidas ilimitadas, jugos de frambuesa y menta-jengibre, agua mineral', 180, 'Crudo de res sobre tostadas al ajo y emulsión de alcaparras', 'Mousse de maracuyá y Cheesecake de frutos del bosque', 'Plateada de vacuno en cocción lenta con pastel de choclo tradicional', '5 menús veganos, 3 menús para niños', 'Barra Abierta Nacional (Pisco Sour, Cervezas, Mistral y Alto)', 'Matrimonio Premium'),
(6, 'Bebidas línea Coca-Cola, agua mineral con/sin gas y jugos naturales', 250, 'Estación de sushi variado, mini empanadas de lomo y brochetas caprese', 'Cascada de chocolate con frutas de la estación y mini pastelería fina', 'Medallón de filete a la pimienta o Pechuga de pavo rellena con papas duquesas', '10 menús vegetarianos, 2 menús aptos para celíacos', 'Barra Extendida (Pisco Sour, Mojitos, Cervezas y Bebidas)', 'Buffet de Gala'),
(7, 'Bebidas línea Coca-Cola, agua mineral con/sin gas y jugos naturales', 250, 'Estación de sushi variado, mini empanadas de lomo y brochetas caprese', 'Cascada de chocolate con frutas de la estación y mini pastelería fina', 'Medallón de filete a la pimienta o Pechuga de pavo rellena con papas duquesas', '10 menús vegetarianos, 2 menús aptos para celíacos', 'Barra Extendida (Pisco Sour, Mojitos, Cervezas y Bebidas)', 'Buffet de Gala'),
(8, 'Bebidas de Prueba', 10, 'Entrada Gourmet de Prueba', 'Postre Dulce de Prueba', 'Plato Fondo de Prueba', 'Ninguna', 'Barra de Prueba', 'Prueba Definitiva'),
(9, 'Café espresso ilimitado, té, infusiones, agua purificada y jugos verdes', 80, 'Ensalada gourmet de quinoa real, espárragos, queso de cabra y frutos secos', 'Panacota de vainilla de Madagascar con salsa de frutos del bosque', 'Salmón grillado en costra de hierbas con risotto de champiñones', '15 menús veganos, intolerancia estricta a los frutos secos en 1 comensal', 'Barra Sobria (Vinos de viñas boutique chilenas, espumantes y cervezas artesanal)', 'Almuerzo Ejecutivo Premium'),
(10, 'Vinos Malbec de Mendoza, agua mineral de manantial y gaseosas', 100, 'Empanadas mendocinas de carne cortada a cuchillo, provoleta a la chapa con orégano y un toque de oliva, y choripán gourmet con chimichurri casero', 'Panqueques con dulce de leche de campo (flambeados al ron) y porción de vigilante (queso y dulce de membrillo)', 'Cortes tradicionales: Bife de chorizo y Vacío hecho a la cruz, acompañados de papas fritas a caballo y ensalada criolla', 'Opcional de bife de quinoa para vegetarianos, opciones sin TACC (sin gluten) para celíacos en las masas', 'Barra de Vinos y Coctelería Porteña (Fernet con Coca-Cola, Vermut con soda y Clericot de estación)', 'Asado & Experiencia Argentina');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id_menu`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `menu`
--
ALTER TABLE `menu`
  MODIFY `id_menu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
