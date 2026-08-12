# 🏥 Kinesio Vitality - Backend

Sistema de Gestión para Centros de Fisioterapia desarrollado con **Spring Boot** y **PostgreSQL**, enfocado en la administración de pacientes, fisioterapeutas, citas, tratamientos, sesiones y ventas, implementando autenticación segura mediante **JWT** y documentación interactiva con **Swagger OpenAPI**.

---

## 📌 Características

- ✅ Autenticación mediante JWT
- ✅ Control de acceso por Roles (ADMIN y FISIOTERAPEUTA)
- ✅ BlackList de Tokens para Logout Seguro
- ✅ Gestión de Usuarios
- ✅ Gestión de Pacientes
- ✅ Gestión de Fisioterapeutas
- ✅ Gestión de Citas
- ✅ Gestión de Evaluaciones
- ✅ Gestión de Tratamientos
- ✅ Gestión de Sesiones
- ✅ Gestión de Servicios
- ✅ Gestión de Ventas
- ✅ Dashboard con indicadores generales
- ✅ Validaciones utilizando Bean Validation
- ✅ Manejo centralizado de excepciones
- ✅ Respuestas estandarizadas mediante DTO
- ✅ Documentación completa con Swagger/OpenAPI

---

# 🏗 Arquitectura

El proyecto sigue una arquitectura por capas:

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
PostgreSQL
```

Además incorpora una capa de seguridad basada en Spring Security y JWT.

---

# 🔐 Seguridad

El sistema utiliza:

- Spring Security
- JSON Web Token (JWT)
- BCrypt Password Encoder
- BlackList de Tokens
- Roles de Usuario

Roles implementados:

- ADMIN
- FISIOTERAPEUTA

Las rutas protegidas requieren un Token Bearer válido.

---

# 📂 Estructura del Proyecto

```
src
 ├── config
 ├── common
 │     ├── enums
 │     ├── exceptions
 │     └── response
 │
 ├── security
 │     ├── config
 │     ├── controller
 │     ├── dto
 │     ├── filter
 │     ├── jwt
 │     └── blacklist
 │
 ├── usuario
 ├── paciente
 ├── fisioterapeuta
 ├── cita
 ├── evaluacion
 ├── tratamiento
 ├── sesion
 ├── servicio
 ├── venta
 └── dashboard
```

---

# ⚙ Tecnologías Utilizadas

- Java 21
- Spring Boot 3
- Spring Security
- Spring Data JPA
- Hibernate
- PostgreSQL
- JWT (JJWT)
- Maven
- Swagger OpenAPI
- Bean Validation
- BCrypt
- Eclipse IDE

---

# 📖 Documentación de la API

La API está completamente documentada mediante Swagger.

Una vez iniciado el proyecto acceder a:

```
http://localhost:8080/swagger-ui/index.html
```

Desde Swagger se pueden realizar todas las pruebas utilizando autenticación JWT.

---

# 🔑 Flujo de Autenticación

1. Iniciar sesión

```
POST /api/auth/login
```

2. Copiar el Token JWT

3. Presionar el botón **Authorize** en Swagger

```
Bearer TU_TOKEN
```

4. Consumir los endpoints protegidos.

---

# 📋 Módulos Implementados

## 🔐 Seguridad

- Login
- Logout
- JWT
- BlackList
- Roles
- Cambio de contraseña

---

## 👤 Usuarios

- Crear usuario
- Actualizar usuario
- Listar usuarios
- Buscar usuario
- Activar usuario
- Desactivar usuario
- Cambiar contraseña

---

## 🧑 Pacientes

- Registrar
- Actualizar
- Consultar
- Inactivar

---

## 👨‍⚕️ Fisioterapeutas

- CRUD completo
- Activar/Inactivar

---

## 📅 Citas

- Registrar
- Actualizar
- Consultar
- Cancelar

---

## 📋 Evaluaciones

- CRUD completo

---

## 💪 Tratamientos

- CRUD completo

---

## 🩺 Sesiones

- CRUD completo

---

## 🧾 Servicios

- CRUD completo

---

## 💰 Ventas

- Registro de ventas
- Consulta
- Actualización

---

## 📊 Dashboard

- Estadísticas generales
- Indicadores del sistema

---

# 🗄 Base de Datos

Motor utilizado:

```
PostgreSQL
```

ORM:

```
Hibernate (Spring Data JPA)
```

Creación automática de tablas mediante:

```properties
spring.jpa.hibernate.ddl-auto=update
```

---

# 🚀 Instalación

## Clonar el proyecto

```bash
git clone https://github.com/PaulArias27/Sistema-de-gesti-n---centro-fisioterapia-.git
```

---

## Configurar Variables de Entorno

```properties
DB_URL=
DB_USERNAME=
DB_PASSWORD=

JWT_SECRET=

ADMIN_USERNAME=
ADMIN_PASSWORD=
```

---

## Ejecutar

```bash
mvn spring-boot:run
```

o desde Eclipse:

```
Run As
Spring Boot App
```

---

# 📌 Principales Características Técnicas

- Arquitectura por capas
- DTO Pattern
- Mapper Pattern
- Repository Pattern
- Validaciones
- Manejo Global de Excepciones
- API Response Unificada
- Seguridad JWT
- Swagger OpenAPI
- Password Encryption
- Roles y Autorización
- Código modular y escalable

---

# 📈 Próximas Mejoras

- Frontend en React
- Dashboard con gráficos
- Reportes PDF
- Exportación a Excel
- Notificaciones por correo
- Agenda inteligente
- Historial clínico completo
- Recordatorios automáticos

---

# 👨‍💻 Autor

**Byron Paul Arias Vicuña**

Desarrollador de Software

GitHub:
> https://github.com/PaulArias27

Correo:
> paularias2727@gmail.com

---

# ⭐ Estado del Proyecto

**Backend Finalizado ✔**

- Seguridad implementada
- API documentada
- Módulos funcionales
- Base de datos integrada
- Listo para el desarrollo del Frontend en React.

---

> Proyecto desarrollado como parte del Sistema de Gestión para Centros de Fisioterapia **Kinesio Vitality**, aplicando buenas prácticas de desarrollo backend con Spring Boot y arquitectura escalable.
