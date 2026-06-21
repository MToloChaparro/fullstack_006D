Estudiante: Matias Toloza Chaparro
Docente: Viviana Soledad Poblete Lopez
Asignatura: DESARROLLO FULLSTACK I_006D

---

# GestionEcogarzones - Ecosistema de Microservicios

Ecosistema distribuido desarrollado con **Spring Boot** y **Spring Cloud** para la gestión logística, financiera y de personal de la empresa de garzonería y bartender **Ecogarzones**. 

## 1. Descripción del Contexto y Dominio del Proyecto
El sistema busca modernizar y solucionar problemas de gestión y administración de los servicios de banquetería, barras y atención solicitados por los clientes de forma externa. El dominio abarca la organización integral y centralizada de los datos críticos de negocio: desde el reclutamiento y control horario del personal (*staff*), la planificación de los servicios agendados (*eventos y cronogramas*), el control logístico (*inventario y transporte*), hasta la culminación del flujo con los cobros y la emisión de documentos legales (*pagos y boletas electrónicas*).

---

## 2. Arquitectura del Sistema y Listado de Microservicios
El proyecto implementa el patrón arquitectónico de **Base de datos por microservicio**, aislando por completo los contextos lógicos para garantizar la independencia y autonomía de despliegue.

* **API Gateway (Puerto 9090):** Actúa como el punto único perimetral de entrada. Se encarga del enrutamiento dinámico hacia los servicios internos, ocultando la topología de la red física, centralizando la seguridad y mitigando las restricciones de CORS.

### Listado de Microservicios Implementados:
1. **MS Autenticación (Puerto 8081):** Gestión de usuarios, roles, seguridad corporativa y emisión de tokens de acceso.
2. **MS Evento (Puerto 8082):** Gestión centralizada de reservas de salones, canchas y logística culinaria (alberga de forma interna el contexto de la tabla `Menu`).
3. **MS Incidencias (Puerto 8083):** Sistema de tickets, reporte, priorización y resolución de contingencias o faltas del staff en tiempo real durante los eventos.
4. **MS Admin (Puerto 8084):** Panel de control, auditoría, reportería analítica y moderación para el personal administrativo de alta jerarquía.
5. **MS Pago Servicio (Puerto 8085):** Módulo financiero enfocado en el procesamiento de transacciones, métodos de pago y estados de cuentas por evento.
6. **MS Staff (Puerto 8086):** Control administrativo de garzones y bartenders, asignación de cargos, tarifas horarias y disponibilidad de turnos.
7. **MS Inventario (Puerto 8087):** Registro de stock físico, control de mermas y valorización económica de los insumos de la empresa.
8. **MS Transporte (Puerto 8090):** Registro, validación y control de transportistas para el traslado logístico de insumos y movilización del personal de servicio.
9. **MS Electrónica (Puerto 8091):** Módulo encargado de la generación, firma y almacenamiento de boletas electrónicas en formato PDF bajo las normativas vigentes del SII.
10. **MS Cronograma (Puerto 8092):** Planificación detallada del ciclo de vida del evento, secuencia cronológica de actividades y hojas de ruta de tareas para el staff.
11. **MS Cliente (Puerto 8093):** Módulo de registro, validación de identidad, historial de solicitudes y fichas de clientes externos.

---

## 3. Rutas Principales del Gateway (Entorno de Desarrollo)
Para interactuar con el ecosistema de manera centralizada desde herramientas de testing (Postman / Thunder Client) o aplicaciones Frontend, todas las llamadas HTTP deben apuntar estrictamente a la frontera del **Gateway (`9090`)**:

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

---

## 4. Enlaces a la Documentación Swagger (API Docs)
Cada microservicio expone de forma interna e independiente su documentación viva interactiva bajo la especificación **OpenAPI 3 / Swagger UI**.

### Acceso a interfaces Swagger locales:
Para probar y revisar la estructura de los JSON (*Request Body*) y esquemas de respuesta, ejecute el microservicio correspondiente y acceda en su navegador web a las siguientes rutas:
* **Swagger Incidencias:** `http://localhost:8083/swagger-ui/index.html`
* **Swagger Eventos:** `http://localhost:8082/swagger-ui/index.html`
* *(Aplica la misma estructura de ruta sustituyendo el puerto respectivo del microservicio que desee auditar de forma aislada: `http://localhost:[PUERTO_MS]/swagger-ui/index.html`)*

---

## 5. Tecnologías y PersistenciaUtilizadas
* **Lenguaje de Programación:** Java 25 (OpenJDK)
- **Framework Principal:** Spring Boot 3.3.4
- **Ecosistema Cloud / Enrutamiento:** Spring Cloud Gateway (Módulo WebMVC)
- **Persistencia de Datos:** Spring Data JPA / Hibernate ORM
- **Motor de Base de Datos:** MySQL (Gestionado de manera eficiente a través de HikariCP)
- **Gestor de Proyectos y Dependencias:** Apache Maven
- **Control de Versiones y Respaldo:** Git & GitHub

### Mapa de Persistencia Relacional

| Microservicio | Esquema en MySQL | Tablas Principales / Contexto |
| :--- | :--- | :--- |
| **MS Autenticación** | `usuario_ecogarzones` | Gestión de credenciales, usuarios y roles de acceso. |
| **MS Evento** | `evento_ecogarzones` | `ms_evento` (Datos del evento), `menu` (Logística de banquetes). |
| **MS Incidencias** | `incidencia_ecogarzones` | `reportes_incidencias` (Tickets, prioridades y estados). |
| **MS Admin** | `administrador_ecogarzones` | `ms_administrador` (Perfiles y logs del personal administrativo). |
| **MS Pago Servicio** | `pago_ecogarzones` | `ms_pago_service` (Transacciones y registros financieros). |
| **MS Staff** | `staff_ecogarzones` | Control de contratos, tarifas horarias y disponibilidad del staff. |
| **MS Inventario** | `ecogarzones` | Catálogo de stock físico, insumos y auditoría de materiales. |
| **MS Transporte** | `transporte_ecogarzones` | `transporte` (Control logístico de traslados e insumos). |
| **MS Electrónica** | `boleta_ecogarzones` | `boleta` (Ficheros PDF y metadatos de facturación electrónica). |
| **MS Cronograma** | `cronograma_ecogarzones` | `actividades`, `ms_cronograma_actividades` (Secuencia del evento). |
| **MS Cliente** | `cliente_ecogarzones` | `cliente` (Fichas personales, validaciones e historiales). |

---

## 6. Instrucciones de Ejecución e Instalación

### A. Ejecución en Entorno Local (Localhost)

#### 1. Requisitos Previos
- Contar con el JDK 25 configurado.
- Apache Maven 3.9+ instalado.
- Motor MySQL activo (mediante herramientas como XAMPP) con los **11 esquemas independientes** creados previamente mediante phpMyAdmin. Las tablas internas se estructurarán automáticamente en el primer inicio gracias a la propiedad `ddl-auto=update` de Hibernate.

#### 2. Orden de Arranque Crítico
Para evitar la pérdida de trazas de red y fallos de enrutamiento temprano, ejecute los proyectos Java en este estricto orden secuencial:
1. **Servicios Core (Negocio Base):** Ejecutar individualmente `MS Autenticación`, `MS Cliente` y `MS Staff`.
2. **Servicios de Operación (Dependientes):** Ejecutar `MS Evento`, `MS Incidencias`, `MS Cronograma` y los servicios financieros/logísticos restantes.
3. **Frontera de Red (API Gateway):** Levantar el proyecto `Gateway` (Puerto `9090`) una vez que todos los puertos anteriores se encuentren activos y respondiendo con estabilidad.

#### 3. Comandos de Compilación
Abra una terminal en la carpeta raíz de cada microservicio y ejecute:
```bash
mvn clean spring-boot:run
