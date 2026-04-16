---
description: Este archivo describe la arquitectura recomendada para el desarrollo del backend en Python del proyecto Pantry Tracker.
applyTo: **/pantryTracker-back-python/**
---

# Instrucciones: Arquitectura Backend Python

**Project:** Pantry Tracker - Backend Python  
**Version:** 1.0

## 📐 Estructura de Carpetas

```
pantryTracker-back-python
  ├── app.py (Inicialización Flask)
  ├── controllers/ (Rutas y handlers)
  ├── services/ (Lógica de negocio)
  ├── models/ (Entidades de datos)
  └── utils/ (Utilidades)
```

## 🏗️ Convenciones de Código

### Nombres de Archivos y Funciones
- **Archivos:** `snake_case.py`  
  Ejemplo: `auth_controller.py`, `productList_service.py`
- **Funciones:** `snake_case()`
- **Classes:** `PascalCase`

### Estructura de Controladores
```python
from flask import Blueprint, request, jsonify

bp = Blueprint('products', __name__, url_prefix='/api/products')

@bp.route('/', methods=['GET'])
def get_products():
    # Lógica aquí
    pass
```

## 🔒 Seguridad
- Validar inputs en controladores
- Usar try-except para manejo de errores
- Sanitizar datos antes de procesarlos
- Loguear errores sin exponer datos sensibles

## 🧪 Testing
- Tests con pytest
- Fixtures para setup de datos
- Mocks para servicios externos
- Estructura: `test_*.py`

## 📦 Ejecución
```bash
# Instalar dependencias
pip install -r requirements.txt

# Ejecutar servidor
python app.py

# Ejecutar tests
pytest
```
