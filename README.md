# GestionEcogarzones - Ecosistema de Microservicios

Ecosistema distribuido desarrollado con **Spring Boot** y **Spring Cloud** para la gestión logística, financiera y de personal de la empresa de garzonería y bartender **Ecogarzones**. 

El sistema busca modernizar y solucionar problemas de gestión y administración de los servicios solicitados por los clientes, al mismo tiempo que organiza datos críticos de negocio: desde el control del personal, los servicios agendados, los pagos concretados y la asignación eficiente del staff a sus respectivos eventos.

---

## Arquitectura del Sistema

El proyecto implementa el patrón de **Base de datos por microservicio**, aislando los contextos de datos y centralizando todo el tráfico externo a través de un componente perimetral:

* **API Gateway (Puerto 9090):** Punto único de entrada. Se encarga del enrutamiento dinámico hacia los servicios internos, ocultando la topología de la red, centralizando la seguridad y evitando problemas de CORS en el cliente.

### 📊 Topología de Puertos y Responsabilidades

El ecosistema está fragmentado en **10 microservicios** especializados con responsabilidades únicas:

* **MS Autenticación (Puerto 8081):** Gestión de usuarios, roles, seguridad y emisión de tokens de acceso (`/api/auth`).
* **MS Evento (Puerto 8082):** Gestión centralizada de reservas de salones, canchas y logística culinaria (alberga de forma interna el contexto de la tabla `Menu`).
* **MS Incidencias (Puerto 8083):** Sistema de tickets, reporte, priorización y resolución de contingencias o faltas en tiempo real durante los eventos.
* **MS Admin (Puerto 8084):** Panel de control, auditoría, reportería y moderación para el personal administrativo de alta jerarquía.
* **MS Pago Servicio (Puerto 8085):** Módulo financiero enfocado en el procesamiento de transacciones, métodos de pago y estados de cuentas por evento.
* **MS Staff (Puerto 8086):** Control administrativo de garzones y bartenders, asignación de cargos, tarifas horarias y disponibilidad de turnos.
* **MS Inventario (Puerto 8087):** Registro de stock físico, control de mermas y valorización económica de los insumos de la empresa.
* **MS Transporte (Puerto 8090):** Registro, validación y control de transportistas para el traslado logístico de insumos y traslado del personal de servicio.
* **MS Electrónica (Puerto 8091):** Módulo encargado de la generación, firma y almacenamiento de boletas electrónicas en formato PDF bajo las normativas vigentes del SII.
* **MS Cronograma (Puerto 8092):** Planificación detallada del ciclo de vida del evento, secuencia cronológica de actividades y hojas de ruta de tareas para el staff.
* **MS Cliente (Puerto 8093):** Módulo de registro, validación de identidad, historial de solicitudes y fichas de clientes externos.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje de Programación:** Java 25 (OpenJDK)
- **Framework Principal:** Spring Boot 3.3.4
- **Ecosistema Cloud / Enrutamiento:** Spring Cloud Gateway (Módulo WebMVC)
- **Persistencia de Datos:** Spring Data JPA / Hibernate ORM
- **Motor de Base de Datos:** MySQL (Gestionado de manera eficiente a través de HikariCP)
- **Gestor de Proyectos y Dependencias:** Apache Maven
- **Control de Versiones y Respaldo:** Git & GitHub

---

## Endpoints de Acceso (Colecciones de Postman / Thunder Client)

### 1. Acceso Unificado a través del API Gateway (Recomendado)
Para interactuar con el ecosistema desde aplicaciones Frontend o entornos de producción, se debe apuntar exclusivamente al puerto del **Gateway (`9090`)**:

* **Autenticación (Usuarios):** `http://localhost:9090/api/auth`
* **Eventos:** `http://localhost:9090/api/v1/eventos`
* **Incidencias:** `http://localhost:9090/api/v1/incidencias`
* **Administrador:** `http://localhost:9090/api/administradores`
* **Pago Servicio:** `http://localhost:9090/api/v1/pagos`
* **Staff:** `http://localhost:9090/api/staff`
* **Inventario:** `http://localhost:9090/api/inventario`
* **Transporte:** `http://localhost:9090/api/transporte`
* **Electrónica:** `http://localhost:9090/api/boletas`
* **Cronograma:** `http://localhost:9090/api/cronogramas`
* **Clientes:** `http://localhost:9090/api/clientes`

### 2. Acceso Directo por Microservicio (Modo Debug / Respaldo)
Direcciones útiles para realizar pruebas unitarias aisladas o depuración técnica sin la intermediación del Gateway:

* **Autenticación:** `http://localhost:8081/api/auth`
* **Eventos:** `http://localhost:8082/api/v1/eventos`
* **Incidencias:** `http://localhost:8083/api/v1/incidencias`
* **Administrador:** `http://localhost:8084/api/administradores`
* **Pago Servicio:** `http://localhost:8085/api/v1/pagos`
* **Staff:** `http://localhost:8086/api/staff`
* **Inventario:** `http://localhost:8087/api/inventario`
* **Transporte:** `http://localhost:8090/api/transporte`
* **Electrónica:** `http://localhost:8091/api/boletas`
* **Cronograma:** `http://localhost:8092/api/cronogramas`
* **Clientes:** `http://localhost:8093/api/clientes`

  ## 🗄️ Persistencia y Modelo de Datos (Database per Service)

El ecosistema implementa un aislamiento completo de datos para garantizar la independencia y autonomía de cada módulo, utilizando **MySQL** como motor relacional. Cada microservicio administra estrictamente su propio esquema de base de datos:

| Microservicio | Esquema en MySQL | Tablas Principales / Contexto |
| :--- | :--- | :--- |
| **MS Autenticación** | `usuario_ecogarzones` | Gestión de credenciales, usuarios y roles de acceso. |
| **MS Evento** | `evento_ecogarzones` | `ms_evento` (Datos del evento), `menu` (Logística de banquetes). |
| **MS Incidencias** | `incidencia_ecogarzones` | `reportes_incidencias` (Tickets, prioridades y estados). |
| **MS Admin** | `administrador_ecogarzones` | `ms_administrador` (Perfiles y logs del personal administrativo). |
| **MS Pago Servicio** | `pago_ecogarzones` | `ms_pago_service` (Transacciones y registros financieros). |
| **MS Staff** | `staff_ecogarzones` | Control de contratos, tarifas horarias y disponibilidad del staff. |
| **MS Inventario** | `ecogarzones` *(Módulo base)* | Catálogo de stock físico, insumos y auditoría de materiales. |
| **MS Transporte** | `transporte_ecogarzones` | `transporte` (Control logístico de traslados e insumos). |
| **MS Electrónica** | `boleta_ecogarzones` | `boleta` (Ficheros PDF y metadatos de facturación electrónica). |
| **MS Cronograma** | `cronograma_ecogarzones` | `actividades`, `ms_cronograma_actividades` (Secuencia del evento). |
| **MS Cliente** | `cliente_ecogarzones` | `cliente` (Fichas personales, validaciones e historiales). |

-

## Instrucciones de Instalación y Despliegue Local

### 1. Requisitos Previos
Antes de iniciar, asegúrate de contar con el siguiente entorno configurado:
- **Java Development Kit:** JDK 25 instalado y configurado en las variables de entorno.
- **Gestor de Dependencias:** Apache Maven 3.9+.
- **Servidor de Base de Datos:** MySQL Server activo (por ejemplo, mediante XAMPP, Laragon o instalación nativa).
- **Herramientas de Testing:** Postman o la extensión Thunder Client en VS Code.

### 2. Preparación de la Base de Datos
1. Accede a tu gestor de base de datos (`http://localhost/phpmyadmin` o cliente CLI).
2. Asegúrate de que los **11 esquemas independientes** listados en la tabla superior se encuentren creados. 
3. Las tablas e índices se autogenerarán en el primer arranque de cada microservicio gracias a la propiedad `spring.jpa.hibernate.ddl-auto=update` configurada en los entornos de desarrollo.

### 3. Orden de Arranque Crítico del Ecosistema
Para evitar fallas de enrutamiento y pérdidas de trazas de red, los proyectos de Spring Boot deben ejecutarse estrictamente en la siguiente secuencia:

1. **Servicios Core (Negocio Base):** Ejecutar individualmente `MS Autenticación`, `MS Cliente` y `MS Staff`.
2. **Servicios de Operación (Dependientes):** Ejecutar `MS Evento`, `MS Incidencias`, `MS Cronograma` y los servicios financieros/logísticos.
3. **Frontera de Red (API Gateway):** Levantar el componente `Gateway` (Puerto `9090`) una vez que los 10 puertos anteriores estén respondiendo de manera estable.
