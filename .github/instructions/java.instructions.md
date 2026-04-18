---
description: "Guidelines for Java and Spring Boot development"
applyTo: "**/*.java, **/*.kt"
---

# Java Development

**Versión:** 1.0  
**Aplica a:** Todos los proyectos con Java/Spring Boot  
**Lenguaje:** Java 11+  
**Framework:** Spring Boot 2.7+

---

## 📋 Introducción

Este documento contiene las convenciones y mejores prácticas para desarrollo en Java que deben seguirse en **todos** los proyectos con Java en PantryTracker.

Estas instrucciones son **independientes** de la arquitectura específica de cada proyecto, pero deben aplicarse **junto con** las instrucciones específicas del proyecto.

## 🏗️ Arquitectura en Capas

La arquitectura de Java en PantryTracker sigue el patrón de **3 capas**:

```
┌─────────────────────────────────────┐
│  Controller (REST API)              │ → Recibe requests
├─────────────────────────────────────┤
│  Service (Business Logic)           │ → Procesa logic
├─────────────────────────────────────┤
│  Repository (Data Access)           │ → Accede a datos
├─────────────────────────────────────┤
│  Database (SQLite)                  │ → Almacena datos
└─────────────────────────────────────┘
```

### Responsabilidades por Capa

#### 🎛️ Controllers
- Reciben HTTP requests
- Validan inputs
- Llaman a Services
- Retornan HTTP responses
- **No contienen lógica de negocio**

#### 🔄 Services
- Contienen lógica de negocio
- Llaman a Repositories
- Ejecutan transformaciones
- Validan reglas de negocio
- **No conocen detalles HTTP**

#### 💾 Repositories
- Acceden a la base de datos
- Escriben queries
- Mapped a Entities
- **No contienen lógica**

#### 📦 Models/Entities
- Representan objetos del dominio
- Tienen anotaciones JPA
- Mapean a tablas

## 📁 Estructura de Paquetes

```java
com.pantrytracker.pantrytracker_back_java
├── PantryTrackerBackJavaApplication.java    // Main class
├── controllers/
│   ├── ProductController.java
│   ├── AuthController.java
│   └── ...
├── services/
│   ├── ProductService.java
│   ├── AuthService.java
│   └── ...
├── repositories/
│   ├── ProductRepository.java
│   ├── UserRepository.java
│   └── ...
├── models/
│   ├── Product.java
│   ├── User.java
│   ├── dto/
│   │   ├── ProductDTO.java
│   │   └── UserDTO.java
│   └── ...
└── security/
    ├── SecurityConfig.java
    ├── JwtManager.java
    └── ...
```

## 🎯 Convenciones de Nombres

### Clases

```java
// ✅ CORRECTO - PascalCase
public class ProductController { }
public class ProductService { }
public class ProductRepository { }
public class Product { }
public class ProductDTO { }

// ❌ INCORRECTO
public class product_controller { }
public class productservice { }
public class PRODUCTREPOSITORY { }
public class Prod { }
```

### Métodos

```java
// ✅ CORRECTO - camelCase, verbo claro
public List<Product> getAllProducts() { }
public Product getProductById(Long id) { }
public Product createProduct(Product p) { }
public void updateProduct(Long id, Product p) { }
public void deleteProduct(Long id) { }

// ❌ INCORRECTO
public List<Product> prodList() { }
public Product get(Long id) { }
public Product make(Product p) { }
public void change(Long id, Product p) { }
```

### Variables

```java
// ✅ CORRECTO - descriptivas, camelCase
String userEmail;
List<Product> allProducts;
int productQuantity;
boolean isValid;

// ❌ INCORRECTO
String e;
List<Product> list;
int q;
boolean v;
String userData; // ambiguo
```

### Constantes

```java
// ✅ CORRECTO - UPPER_SNAKE_CASE
public static final String API_BASE_URL = "http://localhost:8080";
public static final int MAX_RETRIES = 3;
public static final String ERROR_MESSAGE = "Error processing request";

// ❌ INCORRECTO
public static final String apiBaseUrl = "";
public static final int maxRetries = 3;
public static final String errorMessage = "";
```

## 🛠️ Controllers

### Estructura Base

```java
package com.pantrytracker.pantrytracker_back_java.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

import com.pantrytracker.pantrytracker_back_java.models.Product;
import com.pantrytracker.pantrytracker_back_java.services.ProductService;

/**
 * ProductController - Maneja operaciones CRUD de productos
 */
@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;
    
    /**
     * Obtener todos los productos
     */
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Obtener producto por ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Crear nuevo producto
     */
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        try {
            Product created = productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Actualizar producto existente
     */
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
        @PathVariable Long id, 
        @Valid @RequestBody Product product
    ) {
        try {
            Product updated = productService.updateProduct(id, product);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * Eliminar producto
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
```

### Validación en Controllers

```java
// ✅ CORRECTO - Usar @Valid
@PostMapping
public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    // Spring automáticamente valida según anotaciones en Product
    Product created = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}

// ❌ INCORRECTO - Sin validación
@PostMapping
public ResponseEntity<Product> createProduct(@RequestBody Product product) {
    Product created = productService.createProduct(product);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
}
```

## 🔄 Services

### Estructura Base

```java
package com.pantrytracker.pantrytracker_back_java.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

import com.pantrytracker.pantrytracker_back_java.models.Product;
import com.pantrytracker.pantrytracker_back_java.repositories.ProductRepository;

/**
 * ProductService - Lógica de negocio para productos
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    /**
     * Obtener todos los productos
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    /**
     * Obtener producto por ID
     */
    public Product getProductById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        Optional<Product> product = productRepository.findById(id);
        return product.orElse(null);
    }
    
    /**
     * Crear nuevo producto
     */
    @Transactional
    public Product createProduct(Product product) {
        validateProduct(product);
        return productRepository.save(product);
    }
    
    /**
     * Actualizar producto
     */
    @Transactional
    public Product updateProduct(Long id, Product product) {
        validateProduct(product);
        Product existing = getProductById(id);
        if (existing == null) {
            throw new IllegalArgumentException("Product not found");
        }
        product.setId(id);
        return productRepository.save(product);
    }
    
    /**
     * Eliminar producto
     */
    @Transactional
    public void deleteProduct(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        productRepository.deleteById(id);
    }
    
    /**
     * Validar datos del producto
     */
    private void validateProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Product name is required");
        }
        if (product.getQuantity() == null || product.getQuantity() < 0) {
            throw new IllegalArgumentException("Product quantity must be positive");
        }
    }
}
```

## 💾 Repositories

### Estructura Base

```java
package com.pantrytracker.pantrytracker_back_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.pantrytracker.pantrytracker_back_java.models.Product;

/**
 * ProductRepository - Acceso a datos de productos
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    /**
     * Buscar producto por nombre
     */
    Optional<Product> findByName(String name);
    
    /**
     * Buscar productos activos
     */
    List<Product> findByActive(Boolean active);
    
    /**
     * Buscar productos por usuario
     */
    List<Product> findByUserId(Long userId);
}
```

## 📦 Models/Entities

### Estructura Base

```java
package com.pantrytracker.pantrytracker_back_java.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Product - Entidad que representa un producto
 */
@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Product name is required")
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    @Column(nullable = false)
    private String name;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity cannot be negative")
    @Column(nullable = false)
    private Integer quantity;
    
    @Column(length = 500)
    private String description;
    
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    // Constructores
    public Product() { }
    
    public Product(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    // ... resto de getters/setters
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
```

### DTOs (Data Transfer Objects)

```java
package com.pantrytracker.pantrytracker_back_java.models.dto;

import javax.validation.constraints.*;

/**
 * ProductDTO - DTO para transferencia de datos de productos
 */
public class ProductDTO {
    
    private Long id;
    
    @NotBlank(message = "Product name is required")
    private String name;
    
    @NotNull(message = "Quantity is required")
    @Min(value = 0)
    private Integer quantity;
    
    private String description;
    
    // Constructores
    public ProductDTO() { }
    
    public ProductDTO(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    // ... resto
}
```

## 🔒 Seguridad

### Validación Básica

```java
// ✅ Validar todos los inputs
@PostMapping
public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
    // Las validaciones se ejecutan automáticamente
    return ResponseEntity.ok(productService.createProduct(product));
}

// ✅ Usar @PreAuthorize para autorización
@DeleteMapping("/{id}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.noContent().build();
}
```

## 🧪 Testing

### Tests Unitarios (Service)

```java
package com.pantrytracker.pantrytracker_back_java.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pantrytracker.pantrytracker_back_java.models.Product;
import com.pantrytracker.pantrytracker_back_java.repositories.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    
    @Mock
    private ProductRepository productRepository;
    
    private ProductService productService;
    
    @BeforeEach
    void setUp() {
        productService = new ProductService();
        productService.productRepository = productRepository;
    }
    
    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> products = new ArrayList<>();
        products.add(new Product("Product1", 10));
        when(productRepository.findAll()).thenReturn(products);
        
        // Act
        List<Product> result = productService.getAllProducts();
        
        // Assert
        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }
    
    @Test
    void testCreateProductWithValidData() {
        // Arrange
        Product product = new Product("Test", 5);
        when(productRepository.save(any(Product.class))).thenReturn(product);
        
        // Act
        Product result = productService.createProduct(product);
        
        // Assert
        assertNotNull(result);
        assertEquals("Test", result.getName());
    }
}
```

### Tests de Integración (Controller)

```java
package com.pantrytracker.pantrytracker_back_java.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.pantrytracker.pantrytracker_back_java.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private ProductService productService;
    
    @Test
    void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
            .andExpect(status().isOk());
    }
}
```

## ⚠️ Lo que NO Hacer

- ❌ Poner lógica en Controllers
- ❌ Usar `public` para todo
- ❌ Ignorar excepciones
- ❌ Hardcodear valores
- ❌ Crear métodos muy largos (>30 líneas)
- ❌ No usar `@Transactional` en operaciones críticas
- ❌ No validar inputs
- ❌ Dejar imports sin usar

## 📚 Recursos

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)
- [Baeldung Spring Guides](https://www.baeldung.com)
