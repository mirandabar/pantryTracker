# Agentes Especializados - PantryTracker

Este directorio contiene agentes especializados que siguen un workflow estructurado con handoffs para asegurar análisis profundo, planificación detallada e implementación de calidad.

---

## 🔄 Workflow General con Handoffs

```
User Request
    ↓
[Specialized Agent]
    ├─ READ: Entender lo que se necesita
    ├─ DECIDE: ¿Es simple o complejo?
    └─ Si es complejo:
        ↓ HANDOFF
    [Code Analysis Developer] ◄──────┐
    │ Analiza arquitectura actual      │
    │ Identifica patrones              │ FLUJO DE SKILLS
    │ Documenta hallazgos              │
    └─ HANDOFF ──────────────────────┐ │
        ↓                              │ │
    [Planning Developer]              │ │
    │ Lee análisis                     │ │
    │ Analiza requisitos               │ │
    │ Genera plan-*.md                 │ │
    └─ HANDOFF ──────────────────────┐ │ │
        ↓                              │ │ │
    [Implementation Developer]        │ │ │
    │ Lee plan                         │ │ │
    │ Implementa código                │◄─┘ │
    │ Escribe tests                    │◄───┘
    │ Valida criterios de éxito        │
    └─ COMPLETED ─────────────────────┘
        ↓
    Implementation Ready for Review
```

---

## 🤖 Agentes por Categoría

### 📊 Agentes Genéricos (Workflow de Skills)

#### 1. **Code Analysis Developer** (`code-analysis-developer.agent.md`)
Analiza la arquitectura y estructura del código actual.

- **Entrada:** Requisito del usuario
- **Análisis:**
  - Estructura de paquetes/módulos
  - Patrones arquitectónicos
  - Calidad de código
  - Puntos de integración
  - Dependencias
- **Salida:** Reporte de análisis
- **Handoff:** → Planning Developer

#### 2. **Planning Developer** (`planning-developer.agent.md`)
Crea planes detallados de implementación basados en análisis.

- **Entrada:** Análisis + Requisitos del usuario
- **Diseña:**
  - Arquitectura de solución
  - Componentes a crear/modificar
  - Fases de implementación
  - Estrategia de testing
  - Estimaciones y timeline
- **Salida:** `plan-*.md` en `.github/plans/`
- **Handoff:** → Implementation Developer

#### 3. **Implementation Developer** (`implementation-developer.agent.md`)
Implementa cambios siguiendo el plan generado.

- **Entrada:** `plan-*.md`
- **Implementa:**
  - Código de backend (Java/Python)
  - Código de frontend (React)
  - Tests unitarios e integración
  - Validations y error handling
- **Salida:** Código listo para review
- **Handoff:** Ninguno (Ready for PR)

---

### 👨‍💻 Agentes Especializados por Tecnología

#### 1. **Java Developer** (`java-developer.agent.md`)
Especializado en backend con Spring Boot.

- **Aplica a:** `pantryTracker-back-java/**`
- **Expertise:**
  - Arquitectura en capas
  - REST APIs
  - Seguridad y autenticación
  - Testing
  - Base de datos
- **Handoff Inicial:** → Code Analysis Developer

#### 2. **Python Developer** (`python-developer.agent.md`)
Especializado en backend con Flask.

- **Aplica a:** `pantryTracker-back-python/**`
- **Expertise:**
  - Flask y microservicios
  - APIs REST
  - PDF generation
  - Testing con pytest
  - Integración de dependencias
- **Handoff Inicial:** → Code Analysis Developer

#### 3. **React Developer** (`react-developer.agent.md`)
Especializado en frontend con React.

- **Aplica a:** `pantryTracker-front/**`
- **Expertise:**
  - Componentes React
  - Context API
  - Integración con APIs
  - Performance optimization
  - Responsive design
- **Handoff Inicial:** → Code Analysis Developer

---

## 🔀 Cuándo Usar Cada Agente

### Para Tareas Simples
```
User → [Specialized Agent] → Implementación Directa
   (bugs, pequeños cambios, refactoring interno)
```

### Para Tareas Complejas
```
User → [Specialized Agent] → Code Analysis → Planning → Implementation
   (new features, architectural changes, multi-component)
```

### Decisión: ¿Simple o Complejo?

**Simple si:**
- ❌ Solo 1-2 archivos afectados
- ❌ Cambio interno no visible
- ❌ Bug fix conocido
- ❌ Refactoring limitado

**Complejo si:**
- ✅ Múltiples componentes afectados
- ✅ Cambios en APIs
- ✅ Nuevas funcionalidades
- ✅ Cambios arquitectónicos
- ✅ Integración entre sistemas

---

## 🎯 Frontmatter de Agentes

Todos los agentes siguen esta estructura:

```yaml
---
description: "Breve descripción del agente y propósito"
model: "gpt-4"
tools: ["codebase", "file_create", "file_edit", "terminal", "git"]
name: "Agent Name"
handoffs:
  - label: "Label del botón"
    agent: "target-agent-name"
    prompt: "Prompt a enviar"
    send: true
---
```

---

## 📋 Ejemplo de Flujo Completo

### Escenario: "Quiero agregar autenticación con Google"

```
1. USER → React Developer
   "Necesito agregar login con Google"
   
2. React Developer
   "¿Es cambio complejo? Sí"
   [HANDOFF: Analyze Code Architecture]
   
3. Code Analysis Developer
   ├─ Revisa autenticación existente
   ├─ Identifica patrón de auth (JWT)
   ├─ Analiza contextos React
   ├─ Mapea integraciones
   └─ [HANDOFF: Create Implementation Plan]
   
4. Planning Developer
   ├─ Lee análisis
   ├─ Diseña solución
   ├─ Crea plan-google-auth.md con:
   │  - Fase 1: Backend - Google OAuth provider (Java)
   │  - Fase 2: Backend - JWT integration (Java)
   │  - Fase 3: Frontend - Google login button (React)
   │  - Fase 4: Frontend - Redirect logic (React)
   │  - Fase 5: Integration tests
   └─ [HANDOFF: Start Implementation]
   
5. Implementation Developer
   ├─ Lee plan-google-auth.md
   ├─ Implementa Fase 1 → Tests
   ├─ Implementa Fase 2 → Tests  
   ├─ Implementa Fase 3 → Tests
   ├─ Implementa Fase 4 → Tests
   ├─ Implementa Fase 5
   └─ ✅ "Code ready for PR"
   
6. USER
   Revisar cambios y hacer PR
```

---

## 📁 Estructura de Directorios

```
.github/agents/
├── README.md (este archivo)
├── code-analysis-developer.agent.md      [Genérico]
├── planning-developer.agent.md           [Genérico]
├── implementation-developer.agent.md     [Genérico]
├── java-developer.agent.md              [Especializado]
├── python-developer.agent.md            [Especializado]
└── react-developer.agent.md             [Especializado]

.github/plans/
├── README.md
└── plan-*.md                             [Generados por Planning Developer]
```

---

## 📚 Recursos Relacionados

- **Skills:** `.github/skills/` - Herramientas especializadas
- **Plans:** `.github/plans/` - Planes de implementación generados
- **Instructions:** `.github/instructions/` - Convenciones por proyecto
- **Prompts:** `.github/prompts/` - Plantillas de workflow

---

## ✅ Convenciones de Agentes

### Frontmatter Requerido
- ✅ `description`: Descripción clara
- ✅ `model`: Versión del modelo (gpt-4)
- ✅ `tools`: Lista de tools disponibles
- ✅ `name`: Nombre legible
- ✅ `handoffs`: Array de handoffs (si aplica)

### Presentación Inicial
Todos comienzan con:
> "Hola Sr. David 👋. Soy el [Nombre] Developer..."

### Responsabilidades Claras
- Lista de lo que hace
- Límites de lo que NO hace
- Referencias a skills y recursos

---

## 🎓 Cómo Usar Este Sistema

1. **Elige el agente especializado** según tu categoría (Java/Python/React)
2. **Describe tu tarea**
3. **El agente decide**:
   - Si es simple → Implementa directamente
   - Si es complejo → Activa handoff a Code Analysis
4. **Sigue el flujo** hasta que el código esté listo
5. **Revisa y aprueba** los cambios

---

## 🔗 Integración Entre Agentes

Los agentes NO se comunican directamente. La comunicación es a través de **handoffs con contexto**:

- El agente A entrega todos los hallazgos
- El handoff envía ese contexto al agente B
- El agente B retoma desde donde A dejó
- Continúa hasta completar la tarea

---

## 📞 Soporte

Para agregar nuevos agentes o modificar el workflow:

1. Seguir la convención de frontmatter YAML
2. Incluir sección de handoffs si corresponde
3. Documentar en este README
4. Actualizar `.github/plans/README.md` si es necesario
