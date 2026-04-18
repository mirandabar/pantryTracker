# Estándares de Código Compartidos

**Version:** 1.0  
**Aplica a:** Todos los proyectos

## Forma de hablar

Empieza cada sesión diciendo: "Hola Sr. David"

## ✅ Principios Generales

### Legibilidad
- Código claro y autodocumentado
- Variables con nombres descriptivos
- Funciones pequeñas y con responsabilidad única
- Máximo 3 niveles de anidamiento

### Consistencia
- Mantener estilos dentro de cada proyecto
- Seguir convenciones del lenguaje
- Usar herramientas de formateo (prettier, black, etc)

### Documentación
- Comentar la lógica compleja
- Documentar APIs públicas
- Mantener README actualizado
- Documentar configuraciones no obvias

## 🐛 Control de Calidad

### Commits
- Mensajes claros y descriptivos
- Un propósito por commit
- Referencia issues cuando aplique
- Ejemplo: `fix: resolver bug en autenticación (#123)`

### Pull Requests
- Descripción clara del cambio
- Tests antes de mergear
- Revisar al menos 1 compañero
- Sin comentar código muerto

### Testing
- Tests para funcionalidad crítica
- Tests antes de pushear
- Nombres de tests descriptivos
- Cobertura mínima: 70%

## ♿ Accesibilidad & Performance
- Código limpio y eficiente
- No dejar imports sin usar
- Evitar código duplicado
- Logs informativo sin spam

## 🚫 Lo que NO hacer
- ❌ Commitear código comentado
- ❌ Hardcodear credenciales
- ❌ Funciones muy largas (>30 líneas)
- ❌ Nombres de variables genéricos (x, temp, data)
- ❌ Ignorar warnings del linter
