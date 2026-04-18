---
name: implementation
description: Execute implementation plans efficiently and with quality, writing code following conventions, handling errors properly, and completing all tasks systematically. Use during the IMPLEMENT phase to structure code, follow project patterns, and ensure comprehensive testing.
---

# Skill: Implementación (Implementation)

**Propósito:** Ejecutar el plan de forma eficiente y de calidad  
**Cuándo usar:** Durante la fase IMPLEMENT del workflow  
**Aplicable a:** Todos los proyectos

---

## 🎯 Objetivo

Este skill te ayuda a:
- Escribir código de calidad
- Siguiendo convenciones
- De forma ordenada
- Completando todas las tareas

## 🛠️ Técnicas de Implementación

### 1. Setup Inicial

**Antes de empezar a escribir código:**

```
1. Releer el plan
   ✅ Entender todas las tareas
   ✅ Conocer dependencias
   ✅ Saber orden de ejecución

2. Preparar el entorno
   ✅ Rama correcta
   ✅ Dependencias instaladas
   ✅ Configuración correcta

3. Crear estructura
   ✅ Carpetas necesarias
   ✅ Archivos base
   ✅ Importes básicos
```

### 2. Escribir Código por Componente

**Para cada componente:**

```markdown
### Step 1: Crear Estructura Base
```javascript
// ProductService.js
/**
 * ProductService - Servicios para productos
 */
class ProductService {
  static async getAll() {
    // Por implementar
  }
}

export default ProductService;
```
```

**Step 2: Implementar Lógica**
- Escribir la funcionalidad
- Validaciones
- Error handling

**Step 3: Documentar**
- Docstrings/JSDoc/JavaDoc
- Comentarios en código complejo
- Tipos (si TypeScript/Java)

```

### 3. Seguir Convenciones

**Mientras escribes:**

```
Nombres
✅ Variables: camelCase, descriptivas
✅ Funciones: camelCase, verbo+sustantivo
✅ Clases: PascalCase
✅ Constantes: UPPER_SNAKE_CASE

Estructura
✅ Imports al inicio
✅ Declaraciones de clase/función
✅ Métodos en orden lógico
✅ Exports al final

Código
✅ Máximo 30 líneas por función
✅ Máximo 5 parámetros
✅ Sin código comentado
✅ Sin imports sin usar
```

### 4. Validación y Manejo de Errores

**Agregar validaciones:**

```javascript
// ✅ CORRECTO
function createProduct(data) {
  // Validar
  if (!data || !data.name) {
    throw new Error('Name required');
  }
  if (data.quantity < 0) {
    throw new Error('Quantity must be positive');
  }
  
  // Crear
  return new Product(data.name, data.quantity);
}

// ❌ INCORRECTO
function createProduct(data) {
  const p = new Product(data.name, data.quantity);
  return p;
}
```

**Manejo de errores:**

```javascript
try {
  const product = await productService.create(data);
  return productService.create(data);
} catch (error) {
  logger.error('Error creating product:', error);
  throw new Error('Failed to create product');
}
```

### 5. Testing Durante Implementación

**Test-Driven Development (optional pero recomendado):**

```
1. Escribir test
2. Ver que falle
3. Escribir código
4. Ver que pase
5. Refactorizar
```

**O tradicional:**

```
1. Escribir código
2. Escribir tests
3. Verificar que pasen
```

**Mínimo a testear:**
- Happy path
- Error cases
- Edge cases
- Validaciones

### 6. Integración Paso a Paso

**Mientras integras:**

```
1. Crear componente
2. Si necesita ser importado:
   - Agregar import en archivo principal
   - Verificar path correcto
   - No crear imports circulares

3. Si necesita ser registrado:
   - Registrar en app.py/App.jsx/application.properites
   - Verificar order correcto
   - Testear integración

4. Verificar que no rompa nada:
   - Ejecutar tests existentes
   - Verificar que aplica funcione
   - Revisar logs
```

## 📋 Checklist por Componente

```markdown
## [Nombre Componente]

### Creación
- [ ] Archivo creado en ubicación correcta
- [ ] Nombre sigue convención
- [ ] Carpetas necesarias creadas

### Código
- [ ] Lógica implementada
- [ ] Validaciones presentes
- [ ] Error handling presente
- [ ] Documentado
- [ ] Sin imports sin usar
- [ ] Sin código commented
- [ ] Formato consistente

### Tests
- [ ] Tests creados
- [ ] Tests pasan
- [ ] Coverage > mínimo requerido
- [ ] Edge cases cubiertos

### Integración
- [ ] Importado correctamente
- [ ] Registrado si es necesario
- [ ] Funcionalidad existente intacta
- [ ] No hay warnings
```

## 🔧 Herramientas Útiles

### Linting y Formateo

```bash
# JavaScript/TypeScript
npm run lint
npx prettier --write .

# Python
pylint **/*.py
black .
flake8 .

# Java
mvn checkstyle:check
```

### Testing

```bash
# JavaScript
npm test

# Python
pytest
pytest --cov=.

# Java
mvn test
```

### Build/Compile

```bash
# JavaScript
npm run build

# Python
python -m pytest  # validates syntax

# Java
mvn clean install
```

## 📝 Versión de Código

**Mientras implementas:**

```markdown
# Progreso de Implementación

## ✅ Completado
- [Componente 1]: Creado y testeado
- [Componente 2]: Creado y testeado

## 🔄 En Progreso
- [Componente 3]: 50% - escribiendo tests

## ⏳ Próximos
- [Componente 4]
- [Tarea de integración]

## 🚨 Problemas
- [Problema 1]: Solución [...]
```

## 💡 Tips para Implementación Efectiva

### Velocidad
- Reutilizar código existente cuando sea posible
- Copiar/pegar patrones existentes
- No reinventar la rueda

### Calidad
- Tests mientras escribes
- Revisar código mientras lo escribes
- Preguntar si algo no está claro

### Gestión de Cambios
- Commit frecuente
- Mensajes de commit descriptivos
- Una feature por rama si es posible

### Si Encuentras Problemas
- Detener y investigar
- Ajustar plan si es necesario
- NO ignorar problemas
- Documentar changes
- Comunicar si es bloqueante

## 🎯 Patrones Comunes por Lenguaje

### React

```jsx
// Patrón: Componente funcional con hook
export default function ProductCard({ product, onDelete }) {
  const [isLoading, setIsLoading] = useState(false);
  
  const handleDelete = async () => {
    setIsLoading(true);
    try {
      await onDelete(product.id);
    } finally {
      setIsLoading(false);
    }
  };
  
  return (
    <div className="product-card">
      {/* JSX */}
    </div>
  );
}

ProductCard.propTypes = { /* ... */ };
```

### Java

```java
// Patrón: Controller → Service → Repository
@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;
    
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }
}

@Service
public class ProductService {
    @Autowired
    private ProductRepository repo;
    
    public List<Product> getAll() {
        return repo.findAll();
    }
}
```

### Python

```python
# Patrón: Blueprint → Service
bp = Blueprint('products', __name__, url_prefix='/api/products')

@bp.route('/', methods=['GET'])
def get_products():
    try:
        products = ProductService.get_all()
        return jsonify(products), 200
    except Exception as e:
        logger.error(f"Error: {str(e)}")
        return jsonify({'error': str(e)}), 500

class ProductService:
    @staticmethod
    def get_all():
        # Lógica
        pass
```

## 🚀 Flujo de Implementación Recomendado

```
1. Setup Inicial (5 min)
   └─ Releer plan, preparar ambiente

2. Por cada Componente (20-60 min por componente)
   ├─ Crear estructura base
   ├─ Implementar lógica
   ├─ Agregar validaciones
   ├─ Documentar
   └─ Crear tests

3. Integración (10-20 min)
   ├─ Registrar en app
   ├─ Verificar imports
   └─ Testing de integración

4. Validación Final (5 min)
   ├─ Ejecutar tests completos
   ├─ Revisar lint
   └─ Verificar que no rompa nada

5. Documentación (5-10 min)
   ├─ Actualizar README
   ├─ Documentar APIs
   └─ Agregar cambios a log
```

## 📊 Métricas de Progreso

```markdown
## Progreso General
- Tareas completadas: X/Y
- Porcentaje: X%
- Tiempo usado: X/Y horas

## Por Tipo
- Componentes creados: X
- Componentes modificados: X
- Tests: X pasando
- Coverage: X%

## Calidad
- Warnings: X
- Errores: X
- Tests fallidos: X
```
