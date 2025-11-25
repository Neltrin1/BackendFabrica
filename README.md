# Backend del Proyecto: TelcoNova SupportSuite

Este repositorio contiene el código fuente del servicio backend para el proyecto **TelcoNova SupportSuite**, una plataforma integral desarrollada bajo la iniciativa Fábrica Escuela para la gestión de soporte técnico en telecomunicaciones.

El objetivo de este backend es proveer una API RESTful robusta, segura y escalable para gestionar el registro de usuarios, administración de técnicos, asignación de recursos y el ciclo de vida completo de las órdenes de trabajo.

---

##  Contexto Académico (Fábrica Escuela 2025-2)

Este proyecto es el entregable central para las siguientes materias de Ingeniería de Sistemas en la Universidad de Antioquia:

| Materia | Profesor |
| :--- | :--- |
| **Bases de Datos y Laboratorio** | John Jairo Prado Piedrahita |
| **Arquitectura de Software** | Didier Correa Londoño |

### Integrantes del Equipo
* Cristian David Diez Lopez
* Roller Andrés Hernández López

---

##  Stack Tecnológico

Este backend está construido sobre una arquitectura de **Monolito Modular** moderna en Java, enfocada en la mantenibilidad, la seguridad y el despliegue automatizado.

* **Framework:** Spring Boot 3.5.x
* **Lenguaje:** Java 21
* **Seguridad:** Spring Security + **JWT (JSON Web Tokens)** para autenticación stateless.
* **Base de Datos:** Spring Data JPA / Hibernate.
* **Persistencia (Dev):** H2 Database (En memoria) con **Triggers nativos en Java** para auditoría.
* **Validación:** `jakarta.validation` para integridad de datos de entrada.
* **Documentación API:** SpringDoc (OpenAPI 3 / Swagger UI).
* **Build Tool:** Apache Maven.
* **Calidad & Pruebas:** JUnit 5 + MockMvc (Pruebas de Integración).
* **Monitoreo:** Spring Boot Actuator + Micrometer (métricas para Prometheus).
* **Containerización:** Docker (Dockerfile optimizado multi-stage).

---

##  Cómo Ejecutar Localmente

### Prerrequisitos
* Java JDK 21 instalado.
* Git instalado.
* Docker (Opcional, si se desea desplegar en contenedor).

### Pasos de Instalación

1.  **Clonar el repositorio:**
    ```sh
    git clone [https://github.com/codeFactory20252Feature7/BackendFabrica.git](https://github.com/codeFactory20252Feature7/BackendFabrica.git)
    cd BackendFabrica
    ```

2.  **Ejecutar la aplicación (Usando Maven Wrapper):**
    *En Windows:*
    ```cmd
    ./mvnw spring-boot:run
    ```
    *En macOS/Linux:*
    ```sh
    ./mvnw spring-boot:run
    ```

La aplicación iniciará y estará disponible en `http://localhost:8080`.

> **Nota:** Al iniciar, el sistema cargará automáticamente un conjunto de datos de prueba (Seed Data) incluyendo usuarios, técnicos y órdenes de trabajo para facilitar la revisión.

---

##  Seguridad y Autenticación (JWT)

El sistema implementa seguridad avanzada. La mayoría de los endpoints están protegidos y requieren un Token válido.

1.  **Obtener Token:** Realice una petición `POST` a `/api/auth/login` con las credenciales de administrador (`admin@telconova.com` / `secret`).
2.  **Usar Token:** En cada petición subsiguiente, incluya el encabezado:
    `Authorization: Bearer <SU_TOKEN>`

---

##  Documentación y Endpoints de la API

La documentación interactiva completa (Swagger) está disponible en:
* **Interfaz UI:** `http://localhost:8080/swagger-ui.html`
* **Definición JSON:** `http://localhost:8080/v3/api-docs`

### Catálogo de Servicios Principales

#### 1. Autenticación (`/api/auth`)
* `POST /register`: Registrar nuevos usuarios operadores.
* `POST /login`: Autenticación y generación de Token JWT.

#### 2. Gestión de Técnicos (`/api/technicians`)
* `POST /create`: Registrar un nuevo técnico en el sistema.
* `GET /all`: Listar la fuerza de trabajo disponible.

#### 3. Órdenes de Trabajo (`/api/orders`) - *Core del Negocio*
* `POST /`: Crear una nueva orden de servicio (Estado inicial: PENDIENTE).
* `GET /`: Listar todas las órdenes (Dashboard administrativo).
* `GET /{id}`: Consultar detalle de una orden específica.
* `PUT /{orderId}/assign/{techId}`: Asignar un técnico a una orden (Cambia estado a ASIGNADA).
* `PATCH /{orderId}/status`: Actualizar el ciclo de vida (EN_PROGRESO, FINALIZADA).

---

##  Base de Datos y Auditoría

El sistema utiliza una base de datos H2 embebida para desarrollo ágil.

* **Consola de Administración:** `http://localhost:8080/h2-console`
  * **JDBC URL:** `jdbc:h2:mem:demo`
  * **User:** `sa`
  * **Password:** (Dejar vacío)

### Características Avanzadas de BD
* **Relaciones:** Integridad referencial entre Órdenes y Técnicos.
* **Auditoría (Triggers):** Se implementó un Trigger nativo (`AuditTrigger`) que intercepta actualizaciones en la tabla `work_orders` y registra automáticamente cualquier cambio de estado en la tabla histórica `auditoria_ordenes`.

---

##  Despliegue con Docker

El proyecto incluye configuración lista para despliegue en contenedores, ideal para entornos de CI/CD.

1.  **Construir la imagen:**
    ```sh
    docker build -t telconova-backend .
    ```

2.  **Ejecutar el contenedor:**
    ```sh
    docker run -p 8080:8080 telconova-backend
    ```