---
description: "Guidelines for PantryTracker Frontend React application development"
applyTo: "pantryTracker-front/**/*.jsx, pantryTracker-front/**/*.js"
---

# PantryTracker Frontend - React

**Proyecto:** PantryTracker - Frontend React  
**Versión:** 1.0  
**Ruta:** `pantryTracker-front/`  

---

## 📋 Descripción General

Frontend de **PantryTracker**, una aplicación de gestión de despensa y lista de compras. Desarrollado en **React 18** con **Vite**, utiliza **Context API** para estado global y **CSS** para estilos.

### Objetivos Principales
- Permitir usuarios gestionar su despensa (agregar/editar/elimnar productos)
- Visualizar fechas de expiración
- Generar listas de compra
- Descargar PDFs de listas

## 🏗️ Estructura Actual

```
src/
├── components/          # Componentes reutilizables
├── pages/               # Páginas (nivel de ruta)
├── api/                 # Funciones para llamadas HTTP
├── context/             # Context API (Auth, User)
├── hooks/               # Custom hooks (vacío por completar)
├── utils/               # Validadores, formateros
├── styles/              # CSS global y variables
└── App.jsx              # Enrutador principal
```

## 🔗 Dependencias con Backend

### Backend Java
- **Auth:** Login/Register/JWT
- **Productos:** CRUD de productos en despensa
- **Listas:** Crear/actualizar/eliminar listas de compra
- **Usuarios:** Datos de usuarios

### Backend Python
- **PDFs:** Generar PDFs de listas de compra
- **Reportes:** Generación de reportes (futuro)

## 🎯 Funcionalidades Principales

### 1. Autenticación
- **Login:** Email + Contraseña
- **Register:** Crear nuevo usuario
- **JWT:** Token almacenado en localStorage/cookie
- **Context:** AuthContext gestiona estado de autenticación

### 2. Gestión de Despensa
- Listar productos
- Agregar producto (nombre, cantidad, fecha expiración)
- Editar producto
- Eliminar producto
- Vista de expiración próxima

### 3. Listas de Compra
- Crear lista de compra
- Agregar/remover items
- Marcar items como comprados
- Descargar como PDF

### 4. Perfil de Usuario
- Ver datos del usuario
- Modificar datos
- Cambiar contraseña

## 📱 Estructura de Pages

```
pages/
├── LoginPage.jsx              # Login
├── RegisterPage.jsx           # Registro
├── HomePage.jsx               # Dashboard principal
├── PantryPage.jsx             # Gestión de despensa
├── ItemsPage.jsx              # Vista de items (futuro)
├── ExpirationsPage.jsx        # Items próximos a expirar
├── ShoppingListPage.jsx       # Listas de compra
├── AddProductPage.jsx         # Agregar producto
├── AddPurchasePage.jsx        # Agregar lista de compra
├── HistoryPage.jsx            # Historial (futuro)
└── ProtectedRoute.jsx         # Wrapper para rutas protegidas
```

## 🗂️ Estructura de Components

```
components/
├── ProductCard.jsx            # Tarjeta de producto
├── LoginForm.jsx              # Formulario login
├── RegisterForm.jsx           # Formulario registro
├── AddProduct.jsx             # Formulario agregar producto
├── ShopingListPDF.jsx         # Generador de PDF
├── Home.jsx                   # Página inicio
├── Pantry.jsx                 # Vista de despensa
├── LayoutPage.jsx             # Layout principal
└── (otros componentes)
```

## 🌍 Variables de Entorno

Archivo `.env` requerido:

```env
VITE_API_URL=http://localhost:8080/api
VITE_API_PYTHON=http://localhost:5000/api
VITE_ENVIRONMENT=development
```

## 🔌 Endpoints Esperados del Backend

### Java Backend
```
POST   /api/auth/login              # Login
POST   /api/auth/register           # Registro
GET    /api/auth/me                 # Datos usuario actual
POST   /api/products                # Crear producto
GET    /api/products                # Listar productos
GET    /api/products/:id            # Obtener producto
PUT    /api/products/:id            # Actualizar producto
DELETE /api/products/:id            # Eliminar producto
POST   /api/shopping-lists          # Crear lista
GET    /api/shopping-lists          # Listar listas
PUT    /api/shopping-lists/:id      # Actualizar lista
DELETE /api/shopping-lists/:id      # Eliminar lista
```

### Python Backend
```
POST   /api/shopping-lists/pdf      # Generar PDF
GET    /api/shopping-lists/:id/pdf  # Descargar PDF
```

## 🎨 Guía de Estilos

### Colores (variables.css)
```css
--primary: #3498db       /* Azul principal */
--secondary: #2ecc71     /* Verde secundario */
--danger: #e74c3c        /* Rojo para eliminar */
--warning: #f39c12       /* Naranja para avisos */
--text-primary: #333     /* Texto principal */
--text-secondary: #666   /* Texto secundario */
--success: #27ae60       /* Verde éxito */
```

### Tipografía
- Fuente: Arial, sans-serif
- Tamaño base: 1rem (16px)
- Títulos: 1.25rem - 2rem
- Textos: 0.875rem - 1rem

## 🧪 Testing

### Estructura de Tests
```
__tests__/
├── components/
│   └── ProductCard.test.jsx
├── pages/
│   └── HomePage.test.jsx
└── utils/
    └── validators.test.js
```

### Cobertura Esperada
- Componentes principales: 80%+
- Utils: 90%+

## 📦 Build y Deployment

### Desarrollo
```bash
npm run dev          # Inicia servidor dev (http://localhost:5173)
npm run lint         # ESLint
npm run build        # Build para producción
npm run preview      # Preview del build
```

### Assets Estáticos
```
public/              # Archivos estáticos
└── (favicons, etc)
```

## 🚀 Próximas Funcionalidades

- [ ] Modo oscuro
- [ ] Histórico de compras
- [ ] Análisis y estadísticas
- [ ] Exportación a Excel
- [ ] Integración con supermercados

## 🔒 Seguridad

- JWT almacenado en localStorage
- CORS habilitado para Backend
- Validación de inputs en formularios
- Sanitización de HTML (si es necesario)

## 💡 Notas Importantes

1. **Context API:** No usar Redux, mantener solo autenticación y usuario global
2. **API Calls:** Centralizar en carpeta `api/`, no en componentes
3. **Componentes:** Máximo 100 líneas, si es más grande refactorizar
4. **CSS:** Una hoja por componente, variables en `styles/variables.css`
5. **Props:** Máximo 5 props, si es más usar context o custom hook

## 🤝 Comunicación con Backend

- **Java Dev:** Para cambios en endpoints de autenticación, productos, listas
- **Python Dev:** Para cambios en generación de PDFs
- **Documentación:** Mantener API actualizada en este archivo

## 📚 Stack Tecnológico

- React 18.2+
- Vite 4+
- CSS3
- Context API
- Fetch API (sin axios required)
- ESLint + Prettier (opcional)
