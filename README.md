# Proyecto - Desarrollo de API REST con Spring Boot

## Sistema de Gestión de Inventario para Tienda de Alimentos
## 🛠 Tecnologías Utilizadas 
`Lenguaje:` Java 21.

`Framework para desarrollo de API RESTs:` Spring Boot 4.0.2

`Persistencia y acceso a datos:` Spring Data JPA

`Base de Datos:` MySQL

`Mapeo objeto-relacional:` Hibernate 7.2

`Pruebas de API:` Apidog 2.8.2

`Entorno de desarrollo:` IntelliJ IDEA 2025.3

## 💻 Software Requerido 
- Java Development Kit (JDK) 21
- IntelliJ IDEA Community Edition 2022.3
- MySQL Community Server 8.0
- MySQL Workbench

## PASO A PASO PARA EJECUTAR EL PROYECTO
### 1) CONFIGURAR BASE DE DATOS MYSQL
- Iniciar MySQL Workbench
- Crear una nueva pestaña SQL para ejecutar el siguiente código:
```sql
create database inventario_db;
use inventario_db;
```

### 2) Preparar el proyecto en Intellij Idea.
- Abrir IntelliJ IDEA Community Edition
- Dirígete a la clase `TiendaApplication` para ejecutar el proyecto.

### 3) Verificar que la aplicación esté corriendo correctamente
Se debe de mostrar el siguiente mensaje en la terminal:
```text
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.5)

Started TiendaApplication in 3.456 seconds
Tomcat started on port 8080
```

### 4) Ejecutar el Frontend
- Dirígete al navegador de tu preferencia
- En la barra de direcciones coloca el siguiente enlace: `http://localhost:8080/` o `http://localhost:8080/index.html` 
- Ya estas listo para el uso del sistema.

## Colección de endpoints en ApiDog:
1lpfn40261.apidog.io
