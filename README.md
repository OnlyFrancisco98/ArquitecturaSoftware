# Sistema de Gestión de Inventario (CRUD)

Aplicación web para la gestión de inventario de prendas (crear, consultar, editar y eliminar productos) desarrollada con **Spring Boot**, **MySQL** en contenedores **Docker** y una interfaz web integrada.

---

## Tecnologías utilizadas

* **Backend:** Java 17, Spring Boot, Spring Data JPA (Hibernate), Maven
* **Base de datos:** MySQL 8 (gestionada con Docker Compose)
* **Frontend:** HTML5, CSS3, JavaScript (Fetch API)

---

## Requisitos previos

Antes de comenzar, asegúrate de tener instalado en tu equipo:

* **Java JDK 17** o superior
* **Docker** (Docker Desktop o OrbStack en macOS) con soporte para `docker compose`
* **Git** (opcional, para clonar el repositorio)

> **Nota:** No es necesario instalar MySQL ni XAMPP de forma local, ya que la base de datos se levanta automáticamente con Docker.

---

## Cómo ejecutar el proyecto

### 1. Clonar el repositorio
```bash
git clone https://github.com/TU_USUARIO/TU_REPOSITORIO.git
cd TU_REPOSITORIO
```

### 2. Levantar la base de datos MySQL
Desde la raíz del proyecto, inicia el contenedor de MySQL en segundo plano:
```bash
docker compose up -d
```
Esto creará automáticamente la base de datos `inventario_db` en el puerto `3306` con las credenciales configuradas en `docker-compose.yml`.

### 3. Iniciar la aplicación Spring Boot
Ejecuta el proyecto usando Maven Wrapper (o tu IDE ejecutando la clase `Main`):

* **macOS / Linux:**
  ```bash
  ./mvnw spring-boot:run
  ```
* **Windows:**
  ```bash
  mvnw.cmd spring-boot:run
  ```
*(Si tienes Maven instalado globalmente, también puedes usar `mvn spring-boot:run`)*.

Hibernate creará automáticamente la tabla `registro_de_prendas` al iniciar.

### 4. Abrir la aplicación
Una vez que el servidor haya iniciado en el puerto `8080`, abre tu navegador y entra a:

* **Interfaz web:** `http://localhost:8080`
* **API REST:** `http://localhost:8080/api/productos`

---

## Endpoints de la API

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `GET` | `/api/productos` | Lista todos los productos del inventario |
| `POST` | `/api/productos` | Registra un nuevo producto |
| `PUT` | `/api/productos/{id}` | Actualiza los datos de un producto existente |
| `DELETE` | `/api/productos/{id}` | Elimina un producto por su ID |

### Ejemplo de cuerpo JSON (`POST` / `PUT`)
```json
{
  "nombre": "Camisa de Lino Blanca",
  "precio": 499.99,
  "talla": 40,
  "stock": 15
}
```

---

## Detener el proyecto

1. Detén Spring Boot en la terminal presionando `Ctrl + C`.
2. Detén el contenedor de MySQL (conservando los datos guardados):
   ```bash
   docker compose down
   ```
   *(Si deseas detener el contenedor y **eliminar todos los datos** de la base de datos para empezar desde cero, ejecuta `docker compose down -v`)*.
