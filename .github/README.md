# Estructura de Agentes y Proyectos - Pantry Tracker

Este directorio contiene la configuración modular de agentes, instrucciones y skills para cada proyecto dentro del Pantry Tracker.

## 📁 Estructura

```
.github/
├── projects/
│   ├── pantry-back-java/          # Agente Java Development Team
│   │   ├── agents/                # Definiciones de agentes
│   │   ├── instructions/          # Guías específicas del proyecto
│   │   └── skills/                # Competencias requeridas
│   │
│   ├── pantry-back-python/        # Agente Python Development Team
│   │   ├── agents/
│   │   ├── instructions/
│   │   └── skills/
│   │
│   └── pantry-front/              # Agente Frontend Development Team
│       ├── agents/
│       ├── instructions/
│       └── skills/
│
├── shared/                        # Recursos compartidos entre proyectos
│   ├── instructions/
│   │   └── coding-standards.md   # Estándares generales de código
│   └── skills/
│
├── config/
│   └── projects.json             # Configuración central de proyectos
│
└── README.md                       # Este archivo

```

## 🎯 Proyectos

### 1️⃣ Backend Java (`pantry-back-java/`)
**Agente:** [Java Developer](projects/pantry-back-java/agents/java-developer.agent.md)  
**Tech Stack:** Java, Spring Boot, Maven, JUnit  
**Responsabilidades:**
- APIs REST con Spring Boot
- Lógica de negocio en servicios
- Acceso a datos con repositorios
- Tests unitarios

[📚 Ver Instrucciones](projects/pantry-back-java/instructions/architecture.instructions.md)

---

### 2️⃣ Backend Python (`pantry-back-python/`)
**Agente:** [Python Developer](projects/pantry-back-python/agents/python-developer.agent.md)  
**Tech Stack:** Python, Flask, reportlab  
**Responsabilidades:**
- Microservicios en Flask
- Generación de PDFs y reportes
- Integración con APIs externas
- Tests con pytest

[📚 Ver Instrucciones](projects/pantry-back-python/instructions/architecture.instructions.md)

---

### 3️⃣ Frontend React (`pantry-front/`)
**Agente:** [React Developer](projects/pantry-front/agents/react-developer.agent.md)  
**Tech Stack:** React 18, Vite, JavaScript, CSS  
**Responsabilidades:**
- Componentes React reutilizables
- Páginas y rutas
- Integración con APIs
- Estado global con Context API
- Optimización de performance

[📚 Ver Instrucciones](projects/pantry-front/instructions/architecture.instructions.md)

---

## 📋 Estándares Compartidos

Todos los proyectos heredan los estándares definidos en:  
[🔗 Coding Standards](shared/instructions/coding-standards.md)

Incluye:
- ✅ Principios de legibilidad y consistencia
- 🐛 Control de calidad y testing
- 📝 Convenciones de commits y PRs
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
