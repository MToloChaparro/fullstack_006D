# Readme - Evaluación 2 Desarrollo Fullstack

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

- **MS Inventario (Puerto `8081`)**: Registro de inventario y valores economico.
- **MS Evento (Puerto `8081`)**: Gestión centralizada de reservas de salones, canchas y logística de banquetes. Alberga de forma interna el contexto de la tabla `Menu`.
- **MS Staff (Puerto `8082`)**: Administración de garzones, cargos, tarifas por hora y disponibilidad horaria.
- **MS Cliente (Puerto `8083`)**: Registro, validación y control de datos de clientes externos.
- **MS Admin (Puerto `8084`)**: Control de acceso y moderación para el personal administrativo de la empresa.
- **MS Pago Servicio (Puerto `8085`)**: Módulo financiero para transacciones, métodos de pago y estados de cuentas por evento.

---

## Tecnologías Utilizadas:

- **Backend:** Java 21.0.10, Spring Boot 4.0.6
- **Ecosistema Cloud:** Spring Cloud Gateway WebMVC
- **Persistencia:** Spring Data JPA
- **Base de Datos:** MySQL
- **Gestor de Dependencias:** Maven
- **Control de Versiones:** Git & GitHub

## Direcciones Postman:

- Cliente: https://Localhost.8083/api/v1/clientes
- Administrador: https://Localhost.8084/api/v1/administrador
- Evento: https://Localhost.8081/api/v1/eventos
- Pago Servicio: https://Localhost.8085/api/v1/pagos
- Staff: https://Localhost.8082/api/v1/staff

