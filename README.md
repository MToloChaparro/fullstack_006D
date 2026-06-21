# Readme - Evaluación 3 Desarrollo Fullstack

- nombre: MATÍAS TOLOZA CHAPARRO
- Carrera: Ing. Informatica - DUOC UC: Sede Maipú
- Sección: 006D
- Asignatura: Desarrollo Fullstack
- Profesora: VIVIANA SOLEDAD POBLETE LOPEZ


# GestionEcogarzones - Ecosistema de Microservicios:

Ecosistema distribuido desarrollado con Spring Boot y Spring Cloud para la gestión logística, financiera y de personal de la empresa de garzoneria y bartender *Ecogarzones*. El sistema busca modernizar y solucionar problemas de gestión y
administracion de los servicios solicitados por los clientes, al mismo tiempo que organizar datos de negocios; desde el personal, los servicios agendados, los pagos concretados y la asignación de personal a los servicios correspondientes.

## Arquitectura del Sistema:

El proyecto implementa el patrón Base de datos por microservicio, centralizando el tráfico externo a través de un API Gateway.

- **API Gateway (Puerto `9090`)**: Punto único de entrada. Se encarga del enrutamiento dinámico hacia los servicios internos ocultando la topología de la red.


feat: documentar topología de puertos y responsabilidades de los 10 microservicios

Se detalla la distribución de puertos del ecosistema y el alcance de cada módulo:

- MS Inventario (Puerto 8087): Registro de stock físico y valorización económica de insumos.
- MS Evento (Puerto 8082): Gestión de reservas (salones/canchas) y logística culinaria (alberga la entidad Menu).
- MS Staff (Puerto 8086): Control de garzones, asignación de cargos, tarifas horarias y disponibilidad.
- MS Cliente (Puerto 8093): Módulo de registro, validación de identidad y fichas de clientes externos.
- MS Admin (Puerto 8084): Panel de control, auditoría y moderación para el personal administrativo.
- MS Pago Servicio (Puerto 8085): Procesamiento financiero, pasarelas de pago y estados de cuentas por evento.
- MS Transporte (Puerto 8090): Gestión de transportistas oficiales para el traslado de insumos y personal de servicio.
- MS Incidencias (Puerto 8083): Sistema de tickets, reporte, priorización y resolución de contingencias en tiempo real.
- MS Cronograma (Puerto 8092): Planificación del ciclo de vida del evento, secuencia de actividades y hojas de ruta para el staff.
- MS Electrónica (Puerto 8090): Generación y almacenamiento de boletas/facturas electrónicas en formato PDF bajo normativas del SII.
- 
---

## Tecnologías Utilizadas:

- **Lenguaje de Programación:** Java 25 (OpenJDK)
- **Framework Principal:** Spring Boot 3.3.4
- **Ecosistema Cloud / Enrutamiento:** Spring Cloud Gateway (Módulo WebMVC)
- **Persistencia de Datos:** Spring Data JPA / Hibernate ORM
- **Motor de Base de Datos:** MySQL (Gestionado a través de HikariCP)
- **Gestor de Proyectos y Dependencias:** Apache Maven
- **Control de Versiones y Respaldo:** Git & GitHub

### Endpoints a través del API Gateway (Puerto 9090)

Todas las peticiones en desarrollo y producción deben apuntar al Gateway para el enrutamiento centralizado:

- **Staff:** `http://localhost:9090/api/staff`
- **Eventos:** `http://localhost:9090/api/v1/eventos`
- **Clientes:** `http://localhost:9090/api/clientes`
- **Administrador:** `http://localhost:9090/api/administradores`
- **Pago Servicio:** `http://localhost:9090/api/v1/pagos`
- **Inventario:** `http://localhost:9090/api/inventario`
- **Transporte:** `http://localhost:9090/api/transporte`
- **Electrónica:** `http://localhost:9090/api/boletas`
- **Cronograma:** `http://localhost:9090/api/cronogramas`
- **Autenticación (Usuarios):** `http://localhost:9090/api/auth`
- **Incidencias:** `http://localhost:9090/api/v1/incidencias`

### Endpoints Directos por Microservicio (Puertos Individuales)

Direcciones de respaldo para pruebas unitarias o debug directo sin pasar por el Gateway:

- **Autenticación:** `http://localhost:8081/api/auth`
- **Eventos:** `http://localhost:8082/api/v1/eventos`
- **Incidencias:** `http://localhost:8083/api/v1/incidencias`
- **Administrador:** `http://localhost:8084/api/administradores`
- **Pago Servicio:** `http://localhost:8085/api/v1/pagos`
- **Staff:** `http://localhost:8086/api/staff`
- **Inventario:** `http://localhost:8087/api/inventario`
- **Transporte:** `http://localhost:9090/api/transporte`
- **Electrónica:** `http://localhost:9091/api/boletas`
- **Cronograma:** `http://localhost:9092/api/cronogramas`
- **Clientes:** `http://localhost:8093/api/clientes`
