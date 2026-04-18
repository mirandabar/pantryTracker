---
name: code-analysis
description: Analyze code structure, patterns, and quality to understand architecture, identify issues, and evaluate code consistency. Use when reviewing existing code, understanding project structure, or planning implementations.
---

# Skill: Análisis de Código (Code Analysis)

**Propósito:** Analizar código existente para entender estructura, patrones y problemas  
**Cuándo usar:** Durante la fase READ del workflow  
**Aplicable a:** Todos los proyectos

---

## 🎯 Objetivo

Este skill te ayuda a:
- Entender la arquitectura actual
- Identificar patrones y convenciones
- Detectar problemas o inconsistencias
- Evaluar calidad de código

## 🛠️ Técnicas de Análisis

### 1. Análisis Estructural

**¿Qué hacer?**
- Mapear la estructura de carpetas
- Identificar componentes principales
- Entender jerarquías y dependencias

**Cómo verificar:**
```
✅ ¿Hay estructura clara?
✅ ¿Componentes están bien organizados?
✅ ✅ ¿Separación de responsabilidades clara?
✅ ¿Fácil de navegar?
```

**Preguntas a Responder:**
1. ¿Cuáles son las carpetas principales?
2. ¿Qué propósito tiene cada carpeta?
3. ¿Hay patrones de organización?
4. ¿Hay código en carpeta incorrecta?

### 2. Análisis de Patrones

**¿Qué buscar?**
- Naming conventions
- Estrutura de archivos
- Patterns de arquitectura
- Librerías/frameworks usados

**Cómo identificar:**
```javascript
// Analizar múltiples archivos para ver patrón
// Archivo 1: ProductCard.jsx
// Archivo 2: UserProfile.jsx
// Archivo 3: CheckoutForm.jsx

// Patrón identificado: [NombreComponente].jsx
// Estructura dentro: [misma structure]
// Convención: PascalCase para componentes
```

**Preguntas a Responder:**
1. ¿Cuál es el naming convention?
2. ¿Hay excepciones?
3. ¿Por qué hay excepciones?
4. ¿Debería haber excepciones?

### 3. Análisis de Calidad

**Puntos a Verificar:**
```
Completitud
✅ ¿Hay imports sin usar?
✅ ¿Hay variables no utilizadas?
✅ ¿Hay código commented?
✅ ¿Hay TODOs sin resolver?

Legibilidad
✅ ¿Variables nombres significativos?
✅ ¿Funciones toman muchos parámetros?
✅ ¿Funciones demasiado largas?
✅ ¿Código duplicado?

Mantenibilidad
✅ ¿Hay documentación suficiente?
✅ ¿Código tiene sentido?
✅ ¿Fácil de modificar?
```

### 4. Análisis de Dependencias

**¿Qué Analizar?**

```
Dependencias Externas
- Listar de package.json/pom.xml/requirements.txt
- Versiones
- Propósito

Dependencias Internas
- Qué archivos importan qué
- Crear mapa de dependencias
- Detectar circular dependencies
```

**Preguntas:**
1. ¿Cuáles son las dependencias principales?
2. ¿Todas se usan?
3. ¿Hay versiones compatibles?
4. ¿Hay ciclos de dependencias?

###5. Análisis de Testing

**Verificar:**
```
✅ ¿Existen tests?
✅ ¿Cobertura aproximada?
✅ ¿Tests están organizados?
✅ ¿Tests usan patrones consistentes?
✅ ¿Hay tests de diferentes tipos?
```

**Preguntas:**
1. ¿Cuál es la estructura de tests?
2. ¿Qué está testeado?
3. ¿Qué no está testeado?
4. ¿Tests pasan?

## 📊 Checklist de Análisis

Para cada proyecto/componente:

```markdown
## Análisis de [Componente]

### Estructura
- [ ] Archivo ubicado correctamente
- [ ] Nombre sigue convención
- [ ] Responsabilidad clara

### Código
- [ ] Imports correctos
- [ ] Variables significativas
- [ ] Lógica clara
- [ ] Sin duplicación
- [ ] Sin código commented

### Dependencias
- [ ] Imports necesarios
- [ ] Sin imports innecesarios
- [ ] Dependencias internas claras

### Testing
- [ ] Tests existen
- [ ] Tests relevantes
- [ ] Coverage satisfactorio

### Documentación
- [ ] Docstring/JSDoc
- [ ] Comentarios en código complejo
- [ ] README actualizado
```

## 🔍 Ejemplos Prácticos

### React Component Analysis

```javascript
// ANALIZAR: ProductCard.jsx

1. ESTRUCTURA
   Ubicación: ✅ src/components/ProductCard/ProductCard.jsx
   Nombre: ✅ PascalCase correcto
   Estilo: ✅ ProductCard.css en misma carpeta

2. CÓDIGO
   Imports: ✅ React, useState, PropTypes
   Props: ✅ product, onDelete, onEdit (3 props - OK)
   Hooks: ✅ useState correcto
   Rendering: ✅ JSX limpio
   Largo: ✅ ~50 líneas (OK)

3. CALIDAD
   Variables: ✅ isLoading, handleDelete clara
   Error Handling: ✅ try-catch presente
   Loading State: ✅ Implementado
   
4. ISSUES ENCONTRADOS
   ❌ Sin PropTypes validations
   ❌ handleDelete no tiene validación
   
5. RECOMENDACIONES
   - Agregar PropTypes
   - Validar en handleDelete
```

### Java Service Analysis

```java
// ANALIZAR: ProductService.java

1. ESTRUCTURA
   Ubicación: ✅ src/.../services/ProductService.java
   Nombre: ✅ [NombreClase]Service - correcto
   Anotaciones: ✅ @Service presente

2. CÓDIGO
   Métodos: ✅ Responsabilidades claras
   Inyección: ✅ @Autowired correcto
   Transacciones: ✅ @Transactional en operaciones críticas
   Validación: ✅ Presente en métodos

3. TESTING
   Tests: ✅ ProductServiceTest existe
   Mocking: ✅ @MockBean correcto
   Coverage: ✅ 80%+

4. ISSUES ENCONTRADOS
   ⚠️ Algunos métodos > 30 líneas
   
5. RECOMENDACIONES
   - Refactorizar métodos largos
   - Agregar más tests para edge cases
```

## 📝 Crear Reporte de Análisis

```markdown
# 📋 ANÁLISIS DE CÓDIGO: [PROYECTO]

## Resumen Ejecutivo
[Descripión general del estado]

## Estructura
- Bien organizada: ✅/❌
- Patrones consistentes: ✅/❌
- Fácil de navegar: ✅/❌

## Convenciones
- Nombres: [Convención encontrada]
- Archivos: [Patrón]
- Componentes: [Patrón]

## Calidad
- Duplicación: [Nivel]
- Dead code: [Sí/No]
- Documentación: [Nivel]
- Testing: [Cobertura]%

## Problemas Identificados
1. [Problema 1]: [Severidad]
2. [Problema 2]: [Severidad]

## Recomendaciones
1. [Acción 1]
2. [Acción 2]

## Puntos Positivos
- [Fortaleza 1]
- [Fortaleza 2]
```

## 🚀 Casos de Uso

### Caso 1: Entender Proyecto Existente
**Cuando:** Empezar a trabajar en proyecto
**Qué hacer:**
1. Mapear estructura
2. Identificar patrones
3. Entender dependencias
4. Evaluar calidad

### Caso 2: Identificar Dónde Agregar Código
**Cuando:** Antes de implementar feature
**Qué hacer:**
1. Analizar componentes relevantes
2. Entender patrones
3. Identificar lugar correcto
4. Identificar cambios necesarios

### Caso 3: Revisar Código Completo
**Cuando:** Phase de REVIEW
**Qué hacer:**
1. Verificar convenciones
2. Verificar duplicación
3. Verificar documentación
4. Verificar testing

## 💡 Tips

- 🔍 Analizar múltiples archivos para desentrañar patrones
- 📊 Crear visualizaciones si es complejo
- ✅ Documentar findings detalladamente
- ❓ Preguntar si algo no está claro
- 🎯 Enfocarse en lo importante, no detalles menores
