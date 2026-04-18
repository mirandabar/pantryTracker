---
description: "Guidelines for PantryTracker Backend Java application development"
applyTo: "pantryTracker-back-java/**/*.java, pantryTracker-back-java/**/*.kt"
---

# PantryTracker Backend - Java

**Proyecto:** PantryTracker - Backend Java  
**Versión:** 1.0  
**Ruta:** `pantryTracker-back-java/`  
**Stack:** Spring Boot 2.7+, SQLite, Maven

---

## 📋 Descripción General

Backend de **PantryTracker** desarrollado en **Java con Spring Boot**. Proporciona REST APIs para gestionar usuarios, productos, autenticación y listas de compra.

### Objetivos Principales
- Proporcionar APIs REST seguras
- Gestionar usuarios y autenticación
- Administrar despensa de usuarios
- Gestionar listas de compra
- Validar y procesar datos

## 🏗️ Arquitectura en 3 Capas

```
Controllers (REST API)
    ↓
Services (Lógica de Negocio)
    ↓
Repositories (Acceso a Datos)
    ↓
Database (SQLite)
```

## 🗂️ Estructura Actual

```
src/main/java/com/pantrytracker/pantrytracker_back_java/
├── PantryTrackerBackJavaApplication.java
├── controllers/
│   ├── ProductController.java
│   ├── AuthController.java
│   ├── ShoppingListController.java
│   ├── UserController.java
│   └── ...
├── services/
│   ├── ProductService.java
│   ├── AuthService.java
│   ├── ShoppingListService.java
│   └── ...
├── repositories/
│   ├── ProductRepository.java
│   ├── UserRepository.java
│   ├── ShoppingListRepository.java
│   └── ...
├── models/
│   ├── User.java
│   ├── Product.java
│   ├── ShoppingList.java
│   ├── ShoppingItem.java
│   └── (DTOs)
└── security/
    ├── SecurityConfig.java
    ├── JwtManager.java
    └── ...
```

## 👥 Entidades Principales

### User
```
- id: Long (PK)
- email: String (UNIQUE)
- password: String (hashed)
- name: String
- created_at: LocalDateTime
- updated_at: LocalDateTime
```

### Product
```
- id: Long (PK)
- user_id: Long (FK)
- name: String
- quantity: Integer
- expirationDate: LocalDate
- description: String
- created_at: LocalDateTime
- updated_at: LocalDateTime
```

### ShoppingList
```
- id: Long (PK)
- user_id: Long (FK)
- title: String
- created_at: LocalDateTime
- updated_at: LocalDateTime
```

### ShoppingItem
```
- id: Long (PK)
- shopping_list_id: Long (FK)
- name: String
- quantity: Integer
- completed: Boolean
```

## 🔌 Endpoints REST

### Autenticación
```
POST   /api/auth/register           # Registrar usuario
POST   /api/auth/login              # Login (retorna JWT)
POST   /api/auth/refresh-token      # Refrescar token
POST   /api/auth/logout             # Logout
```

### Usuarios
```
GET    /api/users/me                # Obtener usuario actual
PUT    /api/users/me                # Actualizar usuario
PUT    /api/users/me/password       # Cambiar contraseña
DELETE /api/users/me                # Eliminar cuenta
```

### Productos
```
GET    /api/products                # Listar productos del usuario
POST   /api/products                # Crear producto
GET    /api/products/:id            # Obtener producto
PUT    /api/products/:id            # Actualizar producto
DELETE /api/products/:id            # Eliminar producto
GET    /api/products/expiring       # Productos próximos a expirar
```

### Listas de Compra
```
GET    /api/shopping-lists          # Listar listas del usuario
POST   /api/shopping-lists          # Crear lista
GET    /api/shopping-lists/:id      # Obtener lista con items
PUT    /api/shopping-lists/:id      # Actualizar lista
DELETE /api/shopping-lists/:id      # Eliminar lista

# Items de lista
POST   /api/shopping-lists/:id/items     # Agregar item
PUT    /api/shopping-lists/:listId/items/:itemId   # Actualizar item
DELETE /api/shopping-lists/:listId/items/:itemId   # Eliminar item
```

## 🔒 Seguridad

### Autenticación
- JWT (JSON Web Tokens)
- Almacenamiento de contraseñas con bcrypt
- Token expiration: 24 horas
- Refresh token: 7 días

### Autorización
- Cada usuario solo accede a sus propios datos
- Validación en Services
- Decoradores @PreAuthorize donde sea necesario

### CORS
```
Originies permitidos:
- http://localhost:5173  (frontend dev)
- http://localhost:3000  (frontend prod)
```

## 🗄️ Base de Datos

### SQLite
- Archivo: `pantry.db` (en workdir)
- Inicialización: Scripts SQL en `db/schema.sql`
- Migraciones: Gestionadas con Flyway (opcional)

### Tablas Principales
```sql
users (id, email, password, name, created_at, updated_at, active)
products (id, user_id, name, quantity, expiration_date, description, created_at, updated_at)
shopping_lists (id, user_id, title, created_at, updated_at)
shopping_items (id, shopping_list_id, name, quantity, completed)
```

## 🧪 Testing

### Test Unitarios (Services)
```
test/java/com/pantrytracker/pantrytracker_back_java/services/
├── ProductServiceTest.java
├── AuthServiceTest.java
└── ...
```

### Tests de Integración (Controllers)
```
test/java/com/pantrytracker/pantrytracker_back_java/controllers/
├── ProductControllerTest.java
├── AuthControllerTest.java
└── ...
```

### Cobertura Esperada
- Services: 80%+
- Controllers: 70%+
- Total: 75%+

## 📦 Build y Ejecución

### Maven
```bash
# Compilar
mvn clean install

# Ejecutar tests
mvn test

# Ejecutar aplicación
mvn spring-boot:run

# Build .jar
mvn clean package
```

### Propiedades (application.properties)

```properties
server.port=8080
spring.application.name=pantrytracker-back-java

# Database
spring.datasource.url=jdbc:sqlite:pantry.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.database-platform=org.hibernate.dialect.SQLiteDialect
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=your-secret-key-here
jwt.expiration=86400000

# Logging
logging.level.root=INFO
logging.level.com.pantrytracker=DEBUG
```

## 🌍 Integración con Frontend

### Login Flow
1. Frontend envía credenciales a `POST /api/auth/login`
2. Backend retorna JWT
3. Frontend almacena JWT en localStorage
4. Frontend incluye JWT en header: `Authorization: Bearer <token>`

### Error Handling
```json
{
  "status": 400,
  "message": "Invalid credentials",
  "timestamp": "2024-01-01T12:00:00Z"
}
```

## 🌐 Integración con Python Backend

### PDF Generation
```
Frontend → Java Backend → Python Backend
  (GET /api/shopping-lists/1/pdf-url)
         → (POST /api/shopping-lists/pdf)
```

### Communication
- HTTP REST calls entre servicios
- Python Backend llamado cuando se requiere PDF

## 🚀 Próximas Funcionalidades

- [ ] Integración con supermercados
- [ ] Notificaciones de expiración
- [ ] Analytics y reportes
- [ ] Sincronización multi-dispositivo
- [ ] Export a Excel

## 💡 Notas Importantes

1. **Validación:** Siempre usar @Valid en controllers
2. **Transaccionalidad:** Usar @Transactional en Services
3. **Logging:** Loguear operaciones importantes
4. **Errores:** Customizar excepciones y manejo
5. **DTOs:** Usar DTOs para responses públicas

## 🤝 Comunicación con Frontend

- **URL Base:** `http://localhost:8080/api`
- **Documentación:** Mantener Swagger/OpenAPI actualizado
- **Cambios de API:** Notificar al React Developer

## 🤝 Comunicación con Python Backend

- **URL Base:** `http://localhost:5000/api`
- **Servicios:** PDFs, reportes, procesamiento pesado
- **Protocolo:** REST JSON

## 📚 Stack Tecnológico

- Java 11+
- Spring Boot 2.7+
- Spring Data JPA
- Spring Security
- JWT (jjwt)
- SQLite + Hibernate
- Maven
- JUnit 5 + Mockito
- Lombok (opcional)
