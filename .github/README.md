# 📚 Documentación de .github

**Última actualización:** 2024

---

## 🎯 Propósito

Esta carpeta `.github/` contiene toda la documentación, instrucciones y workflows necesarios para que los agentes especializados desarrollen de forma consistente, coordinada y de alta calidad.

## 📁 Estructura

```
.github/
├── agents/                    # Definición de agentes especializados
├── instructions/              # Guías y estándares
│   ├── languages/            # Por lenguaje (React, Java, Python)
│   ├── projects/             # Por proyecto (front, back-java, back-python)
│   └── shared/               # Estándares compartidos
├── prompts/                  # Prompts para el workflow READ→PLAN→IMPLEMENT→REVIEW
├── skills/                   # Habilidades específicas (análisis, planning, etc)
├── config/                   # Configuración (workflow.yaml)
└── README.md                 # Este archivo
```

## 🤖 Agentes Especializados

### 1. React Developer
- **Archivo:** [agents/react-developer.agent.md](agents/react-developer.agent.md)
- **Especialidad:** Desarrollo de frontend en React
- **Scope:** `pantryTracker-front/**`

### 2. Java Developer
- **Archivo:** [agents/java-developer.agent.md](agents/java-developer.agent.md)
- **Especialidad:** Backend con Spring Boot
- **Scope:** `pantryTracker-back-java/**`

### 3. Python Developer
- **Archivo:** [agents/python-developer.agent.md](agents/python-developer.agent.md)
- **Especialidad:** Backend con Flask
- **Scope:** `pantryTracker-back-python/**`

## 📚 Instrucciones por Estratos

### Nivel 3: Estándares Compartidos
```
.github/instructions/shared/
└── coding-standards.md       # Convenciones para TODOS los proyectos
```

### Nivel 2: Instrucciones por Lenguaje
```
.github/instructions/languages/
├── react.instructions.md      # Convenciones específicas de React
├── java.instructions.md       # Convenciones específicas de Java
└── python.instructions.md     # Convenciones específicas de Python
```

### Nivel 1: Instrucciones por Proyecto
```
.github/instructions/projects/
├── front/
│   ├── context.md           # Contexto del proyecto frontend
│   ├── architecture.instructions.md
│   └── specifications.instructions.md
├── back-java/
│   ├── context.md           # Contexto del proyecto Java
│   ├── architecture.instructions.md
│   └── specifications.instructions.md
└── back-python/
    ├── context.md           # Contexto del proyecto Python
    ├── architecture.instructions.md
    └── specifications.instructions.md
```

**Jerarquía:** Proyecto > Lenguaje > Compartido


## 🔄 Workflow Principal

### READ → PLAN → IMPLEMENT → REVIEW

Todos los agentes deben seguir este workflow para cualquier tarea:

```
READ PROJECT       (15-30 min)
├─ Entender estructura
├─ Analizar código existente
└─ Output: Reporte de contexto

PLAN               (20-40 min)
├─ Descomponer requisitos
├─ Diseñar solución
├─ Crear plan detallado
└─ Output: Plan de implementación

IMPLEMENT          (Variable)
├─ Crear componentes
├─ Escribir código
├─ Crear tests
└─ Output: Código funcional

REVIEW             (10-20 min)
├─ Validar código
├─ Revisar tests
├─ Documentar cambios
└─ Output: Código listo para producción
```

## 💡 Prompts para Cada Fase

```
prompts/
├── read-project.prompt.md           # Para fase READ
├── plan-implementation.prompt.md    # Para fase PLAN
├── implement.prompt.md              # Para fase IMPLEMENT
└── review.prompt.md                 # Para fase REVIEW
```

Cada prompt contiene instrucciones detalladas, checklists y formatos esperados.

## 🛠️ Skills (Habilidades Específicas)

```
skills/
├── code-analysis.skill.md           # Analizar código existente
├── planning.skill.md                # Planificar implementaciones
├── implementation.skill.md          # Escribir código de calidad
└── handoff.skill.md                 # Coordinación con otros agentes
```

Cada skill proporciona técnicas, procedimientos y ejemplos prácticos.

## 🚀 Cómo Usar Esta Documentación

### Para Empezar un Feature/Bug Nuevo

```
1. Lee tu agent file
   └─ agents/[tu-especialidad]-developer.agent.md

2. Ejecuta el workflow
   ├─ Fase READ: Usa prompts/read-project.prompt.md + skills/code-analysis
   ├─ Fase PLAN: Usa prompts/plan-implementation.prompt.md + skills/planning
   ├─ Fase IMPLEMENT: Usa prompts/implement.prompt.md + skills/implementation
   └─ Fase REVIEW: Usa prompts/review.prompt.md

3. Si hay coordinación con otros agentes
   └─ Usa skills/handoff.skill.md
```

### Para Entender Convenciones

```
1. Lee en este orden:
   ├─ .github/instructions/projects/[tu-proyecto]/context.md
   ├─ .github/instructions/languages/[tu-lenguaje].instructions.md
   └─ .github/instructions/shared/coding-standards.md
```

## 🔗 Links Rápidos

**Por Rol:**
- [React Developer](agents/react-developer.agent.md)
- [Java Developer](agents/java-developer.agent.md)
- [Python Developer](agents/python-developer.agent.md)

**Por Proyecto:**
- [Frontend](instructions/projects/front/context.md)
- [Backend Java](instructions/projects/back-java/context.md)
- [Backend Python](instructions/projects/back-python/context.md)

**Por Lenguaje:**
- [React](instructions/languages/react.instructions.md)
- [Java](instructions/languages/java.instructions.md)
- [Python](instructions/languages/python.instructions.md)

**Workflow:**
- [Config Completo](config/workflows.yaml)

- 🚫 Prácticas a evitar

---

## 🚀 Cómo Usar

### Para Desarrolladores
1. Lee el **Agent** de tu proyecto para entender tu rol
2. Consulta **Instructions** para guías específicas
3. Revisa **Shared Standards** para estándares comunes

### Para Onboarding
1. Nuevo dev en Java? → Lee `projects/pantry-back-java/`
2. Nuevo dev en Python? → Lee `projects/pantry-back-python/`
3. Nuevo dev en React? → Lee `projects/pantry-front/`

### Para Mantenimiento
- Actualizar `config/projects.json` al agregar/modificar proyectos
- Mantener instrucciones sincronizadas en cada proyecto
- Revisar estándares compartidos regularmente

---

## 📅 Última Actualización
- **Fecha:** 2026-04-16
- **Versión:** 1.0
- **Estado:** En construcción (básico, mejorables)
