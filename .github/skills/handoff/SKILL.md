---
name: handoff
description: Communicate effectively with other agents and stakeholders, coordinate work across different systems, and document integration points. Use when creating endpoints needed by other teams, sharing implementation details, or requesting help from other systems.
---

# Skill: Comunicación y Hand-offs

**Propósito:** Comunicación efectiva con otros agentes y stakeholders  
**Cuándo usar:** Cuando necesitas interacción con otros agentes o cambios externos  
**Aplicable a:** Todos los proyectos

---

## 🎯 Objetivo

Este skill te ayuda a:
- Comunicar cambios a otros agentes
- Solicitar ayuda cuando sea necesario
- Documentar puntos de integración
- Coordinar esfuerzos entre equipos
- Mantener contexto compartido

## 🛠️ Técnicas de Comunicación

### 1. Identificar Cuándo Hacer Hand-off

**Necesitas hacer hand-off si:**

```markdown
✅ Creaste un nuevo endpoint que el Frontend necesita
✅ Necesitas que Python genere un PDF
✅ Hay cambios en API que afectan a otros
✅ Necesitas datos de otro sistema
✅ Necesitas validar si tu cambio afecta a otros
✅ Necesitas coordinar timing de cambios
```

**NO necesitas hand-off si:**

```markdown
❌ Solo cambios internos no visibles
❌ Refactoring interno
❌ Mejora de performance interna
❌ Bug fixes no visibles externamente
```

### 2. Estructura de Hand-off

**Formato Estándar:**

```markdown
# 🤝 Hand-off: [Título Descriptivo]

## Para
**Agente:** [React Developer / Java Developer / Python Developer]  
**Prioridad:** [Crítica / Alta / Media / Baja]  
**Target Completion:** [Fecha/Cuando esté listo]

## Descripción
[Qué está pasando, qué necesitas, por qué]

### Contexto
[Información importante para que entienda el contexto]

### Cambios Realizados
- **Componente 1:** [Qué cambió]
- **Componente 2:** [Qué cambió]
- **Endpoint 1:** [Qué cambió]

## Lo Que Necesito de Ti

### Acción Requerida
- [ ] [Acción 1]: [Descripción]
- [ ] [Acción 2]: [Descripción]

### Especificaciones
[Detalles técnicos específicos]

### Ejemplos
```json
// Request esperado
{
  "name": "ejemplo",
  "quantity": 10
}

// Response esperado
{
  "id": 1,
  "name": "ejemplo",
  "quantity": 10
}
```
```

### Información Para Integración
- **URL Base:** [URL]
- **Endpoint:** [ruta exacta]
- **Método:** [GET/POST/PUT/DELETE]
- **Auth:** [Requerida/No]
- **Rate Limit:** [Si aplica]

### Dependencias
- [Dependencia 1]: [Por qué]
- [Dependencia 2]: [Por qué]

### Contacto
**Disponibilidad:** [Tu disponibilidad para preguntas]

**Preguntas sobre este hand-off:**
- [Tu nombre]

---

## Próximos Pasos
[ ] Tu confirmación de que recibiste esto
[ ] Tu implementación
[ ] Tu confirmación cuando esté lista
```

### 3. Tipos de Hand-offs

#### Hand-off Tipo A: "Necesito que crees un endpoint"

```markdown
# Hand-off: Nuevo Endpoint para Listárs de Compra

Para: Java Developer

## Descripción
El Frontend necesita un nuevo endpoint para obtener los items de una lista de compra específica.

## Especificaciones

### Endpoint Requerido
- **URL:** GET /api/shopping-lists/{id}/items
- **Auth:** JWT requerida
- **Response:** Array de items

### Response Esperado
```json
[
  {
    "id": 1,
    "name": "Leche",
    "quantity": 2,
    "completed": false
  }
]
```

### Validaciones
- La lista debe pertenecer al usuario autenticado
- Retornar 404 si no existe

## Cronograma
- [ ] Implementación dentro de 2 días
- [ ] Testing
- [ ] Documentación
- [ ] Confirmación cuando está lista
```

#### Hand-off Tipo B: "Necesito que modifiques un endpoint"

```markdown
# Hand-off: Actualizar Endpoint GET /api/products

Para: Java Developer

## Cambio Requerido
El endpoint debe retornar un nuevo campo `category` en cada producto.

### Response Anterior
```json
{
  "id": 1,
  "name": "Leche",
  "quantity": 2
}
```

### Response Nueva
```json
{
  "id": 1,
  "name": "Leche",
  "quantity": 2,
  "category": "Lácteos"
}
```

### Impacto
- Frontend no se rompe (field opcional)
- Otros clientes pueden ignorar el nuevo field
- No hay cambios en request

## Cronograma
- [ ] Cambio en 1 día
- [ ] Testing
- [ ] Confirmación lista
```

#### Hand-off Tipo C: "Necesito generar un PDF"

```markdown
# Hand-off: Generar PDF de Lista de Compra

Para: Python Developer

## Descripción
Cuando el usuario descarga una lista de compra, necesito que se genere un PDF profesional.

## Datos Disponibles
```json
{
  "list_id": 123,
  "title": "Lista de compra - Semana 1",
  "items": [
    {"name": "Leche", "quantity": 2},
    {"name": "Pan", "quantity": 1}
  ],
  "generated_at": "2024-01-15"
}
```

## Especificaciones del PDF
- Encabezado con título
- Tabla con items
- Cantidad en cada item
- Footer con fecha de generación
- Tamaño: Letter (8.5" x 11")

## Endpoint Requerido
- **URL:** POST /api/shopping-lists/pdf
- **Request:** JSON con datos arriba
- **Response:** URL del PDF o bytes del PDF

## Cronograma
- [ ] Endpoint funcional en 2 días
- [ ] Formato visual aprobado
- [ ] Confirmación lista
```

#### Hand-off Tipo D: "Necesito información"

```markdown
# Hand-off: Consulta - Flujo de Autenticación

Para: Java Developer

## Pregunta
¿Cuál es el formato del JWT token que retornas en login?

## Contexto
El Frontend intenta almacenar y usar el token, pero necesito saber:
- Dónde se almacena en el token el user ID
- Cuál es la expiración
- Cómo se refresca

## Detalles Técnicos Requeridos
- Payload del JWT (qué contiene)
- Tiempo de expiración
- Cómo obtener nuevo token

## Urgencia
Media - necesito esto para continuar con autenticación en Frontend
```

### 4. Comunicación Asincrónica

**Si no está disponible el otro agente:**

```markdown
1. Documentar el hand-off CLARAMENTE
2. Dejar en espacio compartido (issues, doc, etc)
3. Permitir que lo levante cuando esté disponible
4. Seguir con lo que sí puedas hacer

Ejemplo de issue/documentation:
- Título claro
- Descripción completa
- Aceptación criteria
- Ejemplos si aplica
```

## 📋 Checklist de Hand-off

```markdown
## Validación del Hand-off

Información
- [ ] Destinatario claro
- [ ] Acción requerida clara
- [ ] Especificaciones completas
- [ ] Ejemplos donde sea necesario

Contexto
- [ ] Agente entiende por qué
- [ ] Dependencias documentadas
- [ ] Impacto documentado

Sincronización
- [ ] Agente entiende timeline
- [ ] Hay fecha/target
- [ ] Prioridad clara

Seguimiento
- [ ] Cómo confirmar cuando esté ready
- [ ] Quién va a validar
- [ ] Próximos pasos claros
```

## 🎯 Ejemplos de Hand-offs Reales

### Ejemplo 1: Crear Endpoint (Más Común)

```markdown
# Hand-off: Endpoint para Crear Producto

**Para:** Java Developer  
**Prioridad:** Alta  

## Descripción
Frontend necesita endpoint para crear nuevo producto en despensa.

## Endpoint Requerido
```
POST /api/products
```

## Request Body
```json
{
  "name": "string (requerido, min 3 chars)",
  "quantity": "integer (requerido, >= 0)",
  "expirationDate": "date (opcional, formato: YYYY-MM-DD)",
  "description": "string (opcional)"
}
```

## Response Success (201)
```json
{
  "id": 123,
  "name": "Leche",
  "quantity": 2,
  "expirationDate": "2024-02-15",
  "description": null,
  "createdAt": "2024-01-15T10:00:00Z"
}
```

## Response Errors
```
400: Validation error (name too short, negative quantity)
401: Unauthorized
500: Server error
```

## Definition of Ready
- [ ] Endpoint responde con estructura correcta
- [ ] Validaciones implementadas
- [ ] Error handling correcto
- [ ] Tests creados (min 3)
- [ ] Documentación en Swagger/OpenAPI

## Timeline
- Inicio: Hoy
- Target: +2 días
- Notificar cuando esté lista
```

### Ejemplo 2: Integración Multi-Stack

```markdown
# Hand-off: Generar PDF desde Frontend

**Para:** Java Developer + Python Developer  
**Prioridad:** Media

## Flujo Completo Requerido

1. **Frontend (React)** - Por mí
   - Usuario hace click en "Descargar PDF"
   - UI muestra loading
   - Descarga archivo PDF

2. **Backend Java (Tu trabajo)**
   - Recibe request GET /api/shopping-lists/{id}/pdf
   - Valida que lista pertenece al user
   - Llama a Python backend con datos
   - Retorna URL del PDF al Frontend

3. **Backend Python (Su trabajo)**
   - Recibe POST /api/shopping-lists/pdf con datos
   - Genera PDF con reportlab
   - Guarda archivo en storage
   - Retorna URL del archivo

## Coordinación Requerida
- [ ] Java Developer: Crear endpoint GET /api/shopping-lists/{id}/pdf
- [ ] Java Developer: Llamar a Python backend en `http://localhost:5000/api/shopping-lists/pdf`
- [ ] Python Developer: Implementar POST endpoint para generar PDF
- [ ] Python Developer: Retornar URL del PDF generado

## Sincronización
Necesito que coordinen para que ambos endpoints estén listos al mismo tiempo.

## Testing
- Frontend → Java Backend OK
- Java Backend → Python Backend OK
- PDF se descarga correctamente
```

## 💡 Tips de Comunicación Efectiva

### Sé Específico
- ❌ "Necesito un endpoint"
- ✅ "Necesito GET /api/products/{id} que retorne producto con campos x, y, z"

### Proporciona Ejemplos
- ❌ "El formato debe ser JSON"
- ✅ "POST body debe ser: {...}"

### Documenta Cambios
- ❌ "Agregar validación"
- ✅ "Agregar validación: email debe contener @"

### Sé Claro en Timeline
- ❌ "Cuando puedas"
- ✅ "Necesito para el viernes antes de las 3pm"

### Facilita Validación
- ✅ "Cuando esté listo, confirma diciendo 'Listo: nombre-endpoint'"

## 🚨 Resolución de Conflictos

Si hay un conflicto con otro agente:

```markdown
# 🔴 Conflicto: [Descripción]

## Problema
[Qué está en conflicto]

## Perspectivas
- Perspectiva 1: [Explicar]
- Perspectiva 2: [Explicar]

## Opciones
1. [Solución A]: Pros/Cons
2. [Solución B]: Pros/Cons
3. [Solución C]: Pros/Cons

## Recomendación
[Tu recomendación técnica]

## Esperando Feedback De
- [Agente 1]
- [Agente 2]

Decidiremos en: [Tiempp]
```

## 📞 Canales de Comunicación

Usa el método más apropiado:
- **Urgent:** Direct message / Urgent notification
- **High Priority:** Comment in shared doc
- **Medium:** Hand-off document
- **Low:** Centralizado en tracking system
