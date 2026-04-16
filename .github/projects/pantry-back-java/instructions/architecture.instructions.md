---
description: Este archivo describe la arquitectura y convenciones de código para el backend Java del proyecto.
applyTo: **/pantryTracker-back-java/**
---

# Instrucciones: Arquitectura Backend Java

**Project:** Pantry Tracker - Backend Java  
**Version:** 1.0

## 📐 Estructura de Capas

```
Controllers (REST API)
        ↓
Services (Lógica de Negocio)
        ↓
Repositories (Acceso a Datos)
        ↓
Database (SQLite)
```

## 🏗️ Convenciones de Código

### Nombres de Clases
- **Controllers:** `*Controller` (PascalCase)  
  Ejemplo: `ProductController`, `AuthController`
- **Services:** `*Service` (PascalCase)  
  Ejemplo: `ProductService`, `AuthService`
- **Repositories:** `*Repository` (PascalCase)  
  Ejemplo: `ProductRepository`, `UserRepository`
- **Models:** `*` (PascalCase)  
  Ejemplo: `Product`, `User`

### Packages
```
com.pantrytracker
  ├── controllers
  ├── services
  ├── repositories
  ├── models
  └── security
```

## 🔒 Seguridad
- Usar `@Valid` para validar inputs
- Implementar autenticación en controladores
- Usar `@PreAuthorize` para autorización
- Validar datos de entrada antes de guardar

## 🧪 Testing
- Tests para servicios (unitarios)
- Tests para controladores (integración)
- Usar `@MockBean` para dependencias
- Mínimo 80% de cobertura en servicios críticos

## 📦 Build
```bash
# Compilar
mvn clean install

# Ejecutar tests
mvn test

# Ejecutar aplicación
mvn spring-boot:run
```
