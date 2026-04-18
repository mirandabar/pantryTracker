---
name: planning
description: Plan implementations in detail by decomposing requirements, analyzing impact, designing solutions, and breaking down tasks with clear dependencies and realistic estimates. Use during the PLAN phase to create comprehensive implementation roadmaps.
---

# Skill: Planificación (Planning)

**Propósito:** Planificar de forma detallada cómo implementar soluciones  
**Cuándo usar:** Durante la fase PLAN del workflow  
**Aplicable a:** Todos los proyectos

---

## 🎯 Objetivo

Este skill te ayuda a:
- Descomponer problemas complejos
- Identificar todas las tareas necesarias
- Plantificar el orden de ejecución
- Anticipar problemas
- Crear un plan realista

## 🛠️ Técnicas de Planificación

### 1. Descomposición de Requisitos

**¿Qué hacer?**
- Leer requisito completo
- Identificar sub-requisitos
- Listar casos de uso
- Identificar Validaciones

**Matriz de Análisis:**

```markdown
| Requisito | Sub-req 1 | Sub-req 2 | Sub-req 3 |
|-----------|-----------|-----------|-----------|
| [Feature] | [1.1]     | [1.2]     | [1.3]     |
| Validación| [val 1]   | [val 2]   | [val 3]   |
| Error     | [err 1]   | [err 2]   | [err 3]   |
```

**Preguntas:**
1. ¿Qué es exactamente lo que hay que hacer?
2. ¿Hay sub-componentes?
3. ¿Hay validaciones necesarias?
4. ¿Qué errores pueden ocurrir?
5. ¿Hay casos especiales?

### 2. Análisis de Impacto

**¿Qué Evaluar?**

```
Componentes Que Se Crearán
- [Componente 1]: [ubicación], [responsabilidades]
- [Componente 2]: [ubicación], [responsabilidades]

Componentes Que Se Modificarán
- [Componente1]: [cambios específicos]
- [Componente 2]: [cambios específicos]

Riesgos Potenciales
- [Riesgo 1]: Probabilidad [Alta/Media/Baja], Impacto [Alto/Medio/Bajo]
- [Riesgo 2]: Probabilidad [], Impacto []
```

**Preguntas:**
1. ¿Qué componentes existentes afecta?
2. ¿Qué nuevos componentes se crean?
3. ¿Qué puede romperse?
4. ¿Cómo mitigamos riesgos?

### 3. Diseño de Solución

**Estructura a Definir:**

```markdown
## Componente [Nombre]
- **Propósito:** [Qué hace]
- **Ubicación:** [Ruta]
- **Responsabilidades:** [lista]
- **Interfaz Pública:**
  - Método/Función 1: [firma]
  - Método/Función 2: [firma]
- **Dependencias Internas:** [lista]
- **Dependencias Externas:** [lista]
```

**Diagrama (si aplica):**
```
[Componente A]
    ↓
[Componente B]  ← [Componente C]
    ↓
[Base de Datos]
```

**Preguntas:**
1. ¿Cómo se integra todo?
2. ¿Cuál es el flujo de datos?
3. ¿Hay dependencias circulares?
4. ¿Es escalable?

### 4. Desglose de Tareas

**Para cada tarea, definir:**

```markdown
### Tarea N: [Nombre Descriptivo]

**Descripción:**
[Qué se va a hacer exactamente]

**Archivos Involucrados:**
- archivo1.ext: [crear/modificar]
- archivo2.ext: [crear/modificar]

**Cambios Específicos:**
1. [Cambio 1]
2. [Cambio 2]
3. [Cambio 3]

**Criterios de Aceptación:**
- [ ] [Criterio 1]
- [ ] [Criterio 2]
- [ ] [Criterio 3]

**Dependencias:**
- Requiere Tarea X: [Por qué]
- Bloquea Tarea Y: [Por qué]

**Duración Estimada:** [X horas]

**Complejidad:** [Baja/Media/Alta]
```

### 5. Secuenciación de Tareas

**Crear Tabla de Dependencias:**

```markdown
| Tarea | Depende de | Bloqueado por | Crítica |
|-------|-----------|---------------|---------|
| 1     | -         | -             | ❌ No   |
| 2     | 1         | -             | ✅ Sí   |
| 3     | 1, 2      | -             | ✅ Sí   |
| 4     | 3         | -             | ❌ No   |
| 5     | 2         | 4             | ❌ No   |
```

**Critical Path:**
```
1 → 2 → 3 → [Crítica]
      ↓
      4 → 5
```

**Paralelización:**
- Tareas 4 y 5 pueden hacerse al tiempo
- Tareas 1 y X no pueden paralelizarse

### 6. Estimación de Esfuerzo

**Técnica de Estimación:**

```markdown
| Tarea | Complejidad | Estimación | Rango |
|-------|-------------|-----------|-------|
| 1     | Baja        | 1h        | 0.5-1.5h |
| 2     | Media       | 3h        | 2-4h |
| 3     | Alta        | 5h        | 4-7h |

Total Estimado: 9h
Rango: 6.5-12.5h
Contingencia: +20%
**Total con Contingencia: ~11h**
```

**Factores a Considerar:**
- Complejidad técnica
- Familiaridad con tecnología
- Testing necesario
- Documentación
- Unknowns

## 📊 Checklist de Plan

```markdown
## Validación del Plan

### Completitud
- [ ] ¿Todos los requisitos están cubiertos?
- [ ] ¿Se crearon todas las tareas?
- [ ] ¿Hay algún requisito sin abordar?
- [ ] ¿Hay tareas innecesarias?

### Claridad
- [ ] ¿Cada tarea es clara?
- [ ] ¿Criterios de aceptación son medibles?
- [ ] ¿Dependencias están documentadas?
- [ ] ¿Estimaciones son realistas?

### Riesgos
- [ ] ¿Se identificaron todos los riesgos?
- [ ] ¿Hay mitigation plan para cada riesgo?
- [ ] ¿Hay tareas contingencia?

### Viabilidad
- [ ] ¿Plan es realista?
- [ ] ¿Hay suficientes recursos?
- [ ] ¿Timeline es alcanzable?

### Testing
- [ ] ¿Testing está considerado?
- [ ] ¿Coverage es suficiente?
- [ ] ¿Tests están planeados?
```

## 🎯 Formatos de Salida

### Plan Simple (Proyecto Pequeño)

```markdown
# Plan: [Título]

## Requisitos
[Listar]

## Tareas
1. [Tarea 1] - 1h
2. [Tarea 2] - 2h
3. [Tarea 3] - 1h

## Total Estimado: 4h

## Riesgos
- [Riesgo 1]: [Mitigation]

## Validación: ✅ OK para implementar
```

### Plan Completo (Proyecto Grande)

```markdown
# Plan de Implementación: [Título]

## 1. Requisitos Descompuestos
[Matriz de requisitos]

## 2. Análisis de Impacto
[Componentes afectados]

## 3. Diseño de Solución
[Componentes a crear]

## 4. Tareas Detalladas
[Cada tarea con criterios]

## 5. Dependencias y Secuenciación
[Tabla de dependencias]
[Critical path]

## 6. Estimaciones
[Tabla de estimaciones]

## 7. Riesgo y Mitigation
[Lista de riesgos]

## 8. Validación
[Checklist]

## 9. Aprobación: ✅ Listo a implementar
```

## 💡 Tips y Mejores Prácticas

### Estimación Realista
- Siempre agregar 20% contingencia
- Overestimate es mejor que underestimate
- Considerar testing en estimaciones
- Considerar documentación

### Descomposición Efectiva
- Tareas entre 1-2 horas son ideales
- Tareas > 4 horas = refactorizar
- Tareas < 30 min probablemente se pueden agrupar

### Identificar Riesgos
- "Qué podría salir mal"
- Buscar unknowns
- Conectar con experiencia pasada

### Validación Continua
- Revisar plan con otros (si posible)
- Preguntar si algo no está claro
- Ajustar si es necesario antes de implementar

## 🚀 Casos de Uso

### Caso 1: Feature Nueva
**Aplicar:**
1. Descomposición de requisitos
2. Análisis de impacto
3. Diseño de solución
4. Desglose de tareas
5. Estimación

### Caso 2: Bug Fix
**Aplicar:**
1. Análisis del bug
2. Identificar causa root
3. Listar cambios necesarios
4. Identificar riesgos
5. Crear plan mínimo

### Caso 3: Refactoring
**Aplicar:**
1. Analizar código actual
2. Definir estado final
3. Identificar pasos intermedios
4. Estimar complejidad
5. Planificar rollback si es necesario
