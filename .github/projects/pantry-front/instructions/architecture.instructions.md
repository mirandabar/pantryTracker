---
description: Este archivo describe las instrucciones de arquitectura para el desarrollo del frontend en React del proyecto Pantry Tracker.
applyTo: **/pantryTracker-front/**
---

# Instrucciones: Arquitectura Frontend React

**Project:** Pantry Tracker - Frontend  
**Version:** 1.0

## 📐 Estructura de Carpetas

```
src/
  ├── components/ (Componentes reutilizables)
  ├── pages/ (Páginas completas)
  ├── api/ (Llamadas a APIs)
  ├── context/ (Estado global)
  ├── hooks/ (Custom hooks)
  ├── utils/ (Funciones utilitarias)
  ├── styles/ (CSS global)
  └── App.jsx (Enrutador principal)
```

## 🏗️ Convenciones de Código

### Nombres de Componentes
- **PascalCase:** `ProductCard.jsx`, `LoginForm.jsx`
- **Hooks:** `useAuth.js`, `useFetch.js`
- **Archivos CSS:** `ComponentName.css`

### Estructura de Componente
```jsx
export default function ProductCard({ product, onDelete }) {
  const [isLoading, setIsLoading] = useState(false);
  
  const handleDelete = async (id) => {
    setIsLoading(true);
    // Lógica aquí
  };
  
  return (
    <div className="product-card">
      {/* JSX aquí */}
    </div>
  );
}
```

## 🎨 Estilos
- Usar CSS Modules o CSS global en `styles/`
- Mantener clase por componente: `styles/ComponentName.css`
- Variables en `styles/variables.css`
- Clases utilitarias en `styles/utilities.css`

## 🔄 Estado Global
- Context API para autenticación
- Context API para datos del usuario
- No usar Redux por ahora

## 🔗 API Integration
```javascript
// Centralizar calls en api/
export const getProducts = async () => {
  const response = await fetch('/api/products');
  return response.json();
};
```

## 📦 Build & Run
```bash
# Instalar dependencias
npm install

# Ejecutar en dev
npm run dev

# Build para producción
npm run build

# Lint
npm run lint
```

## ⚡ Performance
- Lazy loading de componentes
- Code splitting por rutas
- Optimizar re-renders con React.memo
- Memoizar callbacks con useCallback
