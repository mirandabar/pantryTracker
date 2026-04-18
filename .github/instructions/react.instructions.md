---
description: "Guidelines for React development"
applyTo: "**/*.jsx, **/*.js"
---

# React Development

**Versión:** 1.0  
**Aplica a:** Todos los proyectos con React  
**Lenguaje:** JavaScript/JSX  
**Framework:** React 18+

---

## 📋 Introducción

Este documento contiene las convenciones y mejores prácticas para desarrollo en React que deben seguirse en **todos** los proyectos con React en PantryTracker.

Estas instrucciones son **independientes** de la arquitectura específica de cada proyecto, pero deben aplicarse **junto con** las instrucciones específicas del proyecto.

## 🏗️ Estructura Base de Componentes

### Componente Funcional (Patrón Recomendado)

```jsx
import { useState, useEffect } from 'react';
import PropTypes from 'prop-types';
import './ProductCard.css';

/**
 * ProductCard - Muestra información de un producto
 * 
 * @param {Object} props
 * @param {Product} props.product - Objeto producto con id, name, cantidad
 * @param {Function} props.onEdit - Callback para editar
 * @param {Function} props.onDelete - Callback para eliminar
 */
export default function ProductCard({ product, onEdit, onDelete }) {
  const [isLoading, setIsLoading] = useState(false);

  const handleDelete = async () => {
    setIsLoading(true);
    try {
      await onDelete(product.id);
    } catch (error) {
      console.error('Error deleting product:', error);
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="product-card">
      <h3>{product.name}</h3>
      <p>Cantidad: {product.quantity}</p>
      <button onClick={() => onEdit(product)} disabled={isLoading}>
        Editar
      </button>
      <button onClick={handleDelete} disabled={isLoading}>
        {isLoading ? 'Eliminando...' : 'Eliminar'}
      </button>
    </div>
  );
}

ProductCard.propTypes = {
  product: PropTypes.shape({
    id: PropTypes.number.isRequired,
    name: PropTypes.string.isRequired,
    quantity: PropTypes.number.isRequired,
  }).isRequired,
  onEdit: PropTypes.func.isRequired,
  onDelete: PropTypes.func.isRequired,
};
```

### Custom Hook (Patrón Recomendado)

```javascript
import { useState, useEffect } from 'react';

/**
 * useProducts - Hook personalizado para gestionar productos
 * 
 * @returns {Object} { products, isLoading, error, fetchProducts }
 */
export function useProducts() {
  const [products, setProducts] = useState([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchProducts = async () => {
    setIsLoading(true);
    setError(null);
    try {
      const response = await fetch('/api/products');
      if (!response.ok) throw new Error('Failed to fetch');
      const data = await response.json();
      setProducts(data);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return { products, isLoading, error, fetchProducts };
}
```

## 📁 Estructura de Archivos

```
src/
├── components/                 # Componentes reutilizables
│   ├── ProductCard/
│   │   ├── ProductCard.jsx     # Componente
│   │   └── ProductCard.css     # Estilos
│   ├── LoginForm/
│   │   ├── LoginForm.jsx
│   │   └── LoginForm.css
│   └── ...
├── pages/                   # Páginas completas (nivel de ruta)
│   ├── HomePage.jsx
│   ├── ProductsPage.jsx
│   └── ...
├── api/                     # Funciones de llamadas API
│   ├── productsApi.js       # Métodos para productos
│   ├── authApi.js           # Métodos de autenticación
│   └── ...
├── context/                 # Context API
│   ├── AuthContext.jsx      # Auth global state
│   └── UserContext.jsx      # User global state
├── hooks/                   # Custom hooks reutilizables
│   ├── useProducts.js
│   ├── useAuth.js
│   └── ...
├── utils/                   # Utilidades
│   ├── validators.js        # Validaciones
│   ├── formatters.js        # Formateo de datos
│   └── ...
├── styles/                  # Estilos globales
│   ├── global.css           # Estilos generales
│   ├── variables.css        # Variables CSS
│   └── utilities.css        # Clases utilitarias
├── App.jsx                  # Componente principal
└── main.jsx                 # Entrada de aplicación
```

## 🎯 Convenciones de Nombres

### Componentes

```javascript
// ✅ CORRECTO - PascalCase
export default function ProductCard() { }
export function UserProfile() { }
export const LoginForm = () => { }

// ❌ INCORRECTO
export default function productCard() { }
export function userProfile() { }
export const login_form = () => { }
export const ProductCardComponent = () { }
```

### Archivos

```
// ✅ CORRECTO
ProductCard.jsx
useProducts.js
productsApi.js
validators.js

// ❌ INCORRECTO
product-card.jsx
use_products.js
products_api.js
Validators.js
productCard.jsx (sin capital)
```

### Funciones y Variables

```javascript
// ✅ CORRECTO
const handleClick = () => { }
const fetchProducts = async () => { }
const isLoading = false;
const userData = { };

// ❌ INCORRECTO
const click = () => { }
const getproducts = async () => { }
const loading = false;
const data = { };
```

### Props

```javascript
// ✅ CORRECTO
function ProductCard({ product, onDelete, onEdit }) { }

// ❌ INCORRECTO
function ProductCard({ p, del, ed }) { }
function ProductCard({ product_data, delete_product }) { }
```

## 🎨 Gestión de Estilos

### Estructura CSS

```css
/* ProductCard.css */

/* Variables del componente (si es necesario) */
:root {
  --card-padding: 1rem;
  --card-border-radius: 8px;
}

/* Estilos del componente */
.product-card {
  padding: var(--card-padding);
  border: 1px solid #ddd;
  border-radius: var(--card-border-radius);
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.product-card h3 {
  margin: 0;
  font-size: 1.25rem;
}

.product-card button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-weight: 600;
}

.product-card button:hover {
  opacity: 0.9;
}

.product-card button:disabled {
  opacity: 0.5;
  cursor: not-allowed;
}
```

### CSS Global

```css
/* styles/variables.css */
:root {
  /* Colores */
  --primary: #3498db;
  --secondary: #2ecc71;
  --danger: #e74c3c;
  --warning: #f39c12;
  
  /* Espacios */
  --spacing-xs: 0.25rem;
  --spacing-sm: 0.5rem;
  --spacing-md: 1rem;
  --spacing-lg: 2rem;
  
  /* Tipografía */
  --font-main: 'Arial', sans-serif;
  --font-size-base: 1rem;
}
```

```css
/* styles/global.css */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: var(--font-main);
  font-size: var(--font-size-base);
  color: #333;
  background-color: #f5f5f5;
}

h1, h2, h3 { font-weight: 600; }
button { transition: all 0.3s ease; }
```

## 📊 State Management con Context

### Estructura de Context

```javascript
// context/AuthContext.jsx
import { createContext, useState, useCallback } from 'react';

export const AuthContext = createContext();

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const login = useCallback(async (email, password) => {
    setIsLoading(true);
    try {
      const response = await fetch('/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email, password }),
      });
      const data = await response.json();
      setUser(data);
      setIsAuthenticated(true);
      return true;
    } catch (error) {
      console.error('Login error:', error);
      return false;
    } finally {
      setIsLoading(false);
    }
  }, []);

  const logout = useCallback(() => {
    setUser(null);
    setIsAuthenticated(false);
  }, []);

  const value = {
    user,
    isAuthenticated,
    isLoading,
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}
```

### Usando Context

```javascript
// Consumir Context
import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext';

export default function Dashboard() {
  const { user, isAuthenticated, logout } = useContext(AuthContext);

  if (!isAuthenticated) {
    return <p>No autenticado</p>;
  }

  return (
    <div>
      <h1>Bienvenido, {user.name}</h1>
      <button onClick={logout}>Cerrar sesión</button>
    </div>
  );
}
```

## 🔌 APIs Integration

### Estructura de Modulo API

```javascript
// api/productsApi.js
const BASE_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api';

export const productsApi = {
  getAll: async () => {
    const response = await fetch(`${BASE_URL}/products`);
    if (!response.ok) throw new Error('Failed to fetch products');
    return response.json();
  },

  getById: async (id) => {
    const response = await fetch(`${BASE_URL}/products/${id}`);
    if (!response.ok) throw new Error(`Product ${id} not found`);
    return response.json();
  },

  create: async (data) => {
    const response = await fetch(`${BASE_URL}/products`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to create product');
    return response.json();
  },

  update: async (id, data) => {
    const response = await fetch(`${BASE_URL}/products/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(data),
    });
    if (!response.ok) throw new Error('Failed to update product');
    return response.json();
  },

  delete: async (id) => {
    const response = await fetch(`${BASE_URL}/products/${id}`, {
      method: 'DELETE',
    });
    if (!response.ok) throw new Error('Failed to delete product');
    return response.ok;
  },
};
```

## 🧠 Optimización y Performance

### Memoización de Componentes

```javascript
import { memo, useCallback } from 'react';

// ✅ Envolver componentes que reciben props frecuentemente
const ProductCard = memo(function ProductCard({ product, onEdit }) {
  return (
    <div>
      <h3>{product.name}</h3>
      <button onClick={() => onEdit(product)}>Editar</button>
    </div>
  );
});
```

### useCallback para Callbacks

```javascript
// ✅ Memoizar callbacks para evitar re-renders innecesarios
import { useCallback } from 'react';

export default function ProductList({ products }) {
  const handleDelete = useCallback((id) => {
    // API call
  }, []);

  return (
    <div>
      {products.map(p => (
        <ProductCard
          key={p.id}
          product={p}
          onDelete={handleDelete}
        />
      ))}
    </div>
  );
}
```

### Lazy Loading

```javascript
import { lazy, Suspense } from 'react';

// Cargar componentes bajo demanda
const ProductsPage = lazy(() => 
  import('../pages/ProductsPage')
);

export default function App() {
  return (
    <Suspense fallback={<div>Cargando...</div>}>
      <ProductsPage />
    </Suspense>
  );
}
```

## ✅ Validaciones

```javascript
// utils/validators.js

export const validators = {
  email: (email) => {
    const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return re.test(email);
  },

  password: (password) => {
    return password.length >= 8;
  },

  productName: (name) => {
    return name && name.trim().length > 0;
  },
};

// Usar en formularios
import { validators } from '../utils/validators';

export default function LoginForm({ onSubmit }) {
  const [email, setEmail] = useState('');
  const [errors, setErrors] = useState({});

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};
    
    if (!validators.email(email)) {
      newErrors.email = 'Email inválido';
    }
    
    if (Object.keys(newErrors).length === 0) {
      onSubmit({ email });
    } else {
      setErrors(newErrors);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
      />
      {errors.email && <span className="error">{errors.email}</span>}
      <button type="submit">Enviar</button>
    </form>
  );
}
```

## 🧪 Testing

```javascript
// __tests__/ProductCard.test.jsx
import { render, screen, fireEvent } from '@testing-library/react';
import ProductCard from '../components/ProductCard';

describe('ProductCard', () => {
  const mockProduct = {
    id: 1,
    name: 'Test Product',
    quantity: 5,
  };

  test('renders product information', () => {
    render(
      <ProductCard 
        product={mockProduct} 
        onEdit={() => {}} 
        onDelete={() => {}} 
      />
    );
    
    expect(screen.getByText('Test Product')).toBeInTheDocument();
  });

  test('calls onDelete when delete button is clicked', () => {
    const mockDelete = jest.fn();
    render(
      <ProductCard 
        product={mockProduct} 
        onEdit={() => {}} 
        onDelete={mockDelete} 
      />
    );
    
    fireEvent.click(screen.getByText('Eliminar'));
    expect(mockDelete).toHaveBeenCalledWith(1);
  });
});
```

## ⚠️ Lo que NO Hacer

- ❌ Usar `index.js` como nombre de componente
- ❌ Crear componentes con lógica muy compleja (>100 líneas)
- ❌ Pasar más de 5 props a un componente
- ❌ Guardar estado en `localStorage` sin sincronización
- ❌ Hacer llamadas API directamente en componentes (usar hooks)
- ❌ Ignorar warnings de ESLint
- ❌ Hardcodear URLs de APIs
- ❌ Usar `any` type en TypeScript (si aplica)
- ❌ Crear componentes sin PropTypes o TypeScript

## 📚 Recursos

- [React Official Docs](https://react.dev)
- [React Hooks API Reference](https://react.dev/reference/react)
- [React Best Practices](https://react.dev/learn)
- [Vite Configuration](https://vitejs.dev/config/)
