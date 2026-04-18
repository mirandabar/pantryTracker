---
description: "Guidelines for PantryTracker Backend Python application development"
applyTo: "pantryTracker-back-python/**/*.py"
---

# PantryTracker Backend - Python

**Proyecto:** PantryTracker - Backend Python  
**Versión:** 1.0  
**Ruta:** `pantryTracker-back-python/`  
**Stack:** Flask 1.1+, SQLite, Pytest

---

## 📋 Descripción General

Backend de **PantryTracker** desarrollado en **Python con Flask**. Proporciona servicios especializados como generación de PDFs y procesamiento de datos para complementar el backend Java.

### Objetivos Principales
- Generar PDFs de listas de compra
- Procesamiento de reportes
- Servicios complementarios
- Integración con servicios externos
- APIs REST específicas

## 🏗️ Arquitectura

```
Blueprints/Controllers (Routes)
    ↓
Services (Business Logic)
    ↓
Models (Data)
    ↓
Database (SQLite)
```

## 🗂️ Estructura Actual

```
pantryTracker-back-python/
├── app.py                          # Inicialización Flask
├── config.py                       # Configuración
├── requirements.txt                # Dependencias
├── .env                            # Variables de entorno
├── .env.example                    # Ejemplo de .env
├── controllers/
│   ├── __init__.py
│   ├── product_controller.py       # (si es necesario)
│   ├── auth_controller.py          # (si es necesario)
│   └── ...
├── services/
│   ├── __init__.py
│   ├── productList_service.py      # Servicios de listas
│   ├── generatePDF_service.py      # Generación de PDFs
│   └── ...
├── models/
│   ├── __init__.py
│   ├── product.py
│   └── ...
├── utils/
│   ├── __init__.py
│   ├── validators.py               # Validaciones
│   ├── decorators.py               # Decoradores útiles
│   └── ...
└── tests/
    ├── __init__.py
    ├── test_product_service.py
    └── ...
```

## 🔌 Endpoints REST

### PDFs
```
POST   /api/shopping-lists/pdf                    # Generar PDF
GET    /api/shopping-lists/:id/pdf                # Descargar PDF existente
POST   /api/reports/shopping-analysis             # Reporte de compras
```

### Productos (si aplica)
```
GET    /api/products                              # Listar productos
GET    /api/products/by-expiration                # Productos por expiración
```

### Reportes (futuro)
```
GET    /api/reports/monthly-spending              # Gasto mensual
GET    /api/reports/category-distribution         # Distribución por categoría
```

## 📄 Specialidad: Generación de PDFs

### PDF Service Features
- Generación de PDFs con ReportLab
- Estilos profesionales
- Tablas y gráficos
- Exportación a archivo

### Ejemplo de Flujo
```
Frontend emite: POST /api/shopping-lists/pdf
├─ Datos: {items: [...], title: "..."}
│
Python Backend:
├─ Valida datos
├─ Genera PDF usando ReportLab
├─ Guarda archivo
└─ Retorna URL/bytes

Frontend:
└─ Descarga PDF o abre en nueva ventana
```

## 🌐 Integración con Java Backend

### Autenticación
- No requiere autenticación propia
- Java Backend valida y envía datos limpios
- Python Backend confía en Java Backend

### Comunicación
```
Java Backend → Python Backend
    POST /api/shopping-lists/pdf
    Body: {
      items: [...lista de items...],
      title: "Nombre de lista",
      userId: 123
    }
    
Python Backend → responde
    {
      filename: "shopping_list_20240101.pdf",
      url: "/uploads/shopping_list_20240101.pdf"
    }
```

## 📊 Datos Esperados

### Shopping List Item
```python
{
  "id": 123,
  "name": "Leche",
  "quantity": 2,
  "completed": False
}
```

### Shopping List
```python
{
  "id": 1,
  "title": "Lista de compra - semana 1",
  "items": [
    {"id": 1, "name": "Leche", "quantity": 2, "completed": False},
    {"id": 2, "name": "Pan", "quantity": 1, "completed": False}
  ],
  "created_at": "2024-01-01T10:00:00"
}
```

## 🗄️ Base de Datos (si es necesario)

### SQLite
- Archivo: `pantry-python.db`
- Manejo: SQLAlchemy (opcional)
- Tablas: Solo si requiere persistencia local

## 🧪 Testing

### Test Structure
```
tests/
├── test_product_service.py
├── test_pdf_service.py
└── ...
```

### Cobertura Esperada
- Services: 75%+
- Utils: 80%+
- Total: 75%+

### Ejecutar Tests
```bash
pytest
pytest -v
pytest --cov=.
```

## 📦 Dependencias Principales

```txt
Flask==1.1.x
reportlab==4.0.x
Pillow==9.x.x
requests==2.28.x
python-dotenv==0.x.x
pydantic==1.x.x (validación)
```

### requirements.txt
```bash
# Crear
pip freeze > requirements.txt

# Instalar
pip install -r requirements.txt
```

## 🌍 Variables de Entorno

Archivo `.env`:
```env
FLASK_ENV=development
FLASK_DEBUG=1
FLASK_APP=app.py

# Server
SERVER_HOST=0.0.0.0
SERVER_PORT=5000

# Java Backend
JAVA_BACKEND_URL=http://localhost:8080/api

# Uploads
UPLOAD_FOLDER=uploads/
MAX_FILE_SIZE=10485760

# Logging
LOG_LEVEL=INFO
```

## 🚀 Ejecución

```bash
# Desarrollo
python app.py

# Con modo debug
FLASK_ENV=development FLASK_DEBUG=1 flask run

# Producción (usar gunicorn)
gunicorn -w 4 -b 0.0.0.0:5000 app:app
```

## 📦 Build y Deployment

### Local Development
```bash
python -m venv venv
.\venv\Scripts\activate  # Windows
source venv/bin/activate # Linux/Mac

pip install -r requirements.txt
python app.py
```

### Docker (opcional)
```dockerfile
FROM python:3.11-slim

WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .

CMD ["python", "app.py"]
```

## 🔒 Seguridad

### No requiere autenticación JWT propia
- Java Backend valida JWT
- Python Backend recibe requests ya validadas
- Confiar en Java Backend

### Validación de Datos
- Validar estructura de datos
- Validar tipos
- Validar rangos

## 💡 Notas Importantes

1. **PDF Service:** Especialidad del backend Python
2. **Independencia:** Funciona independientemente del Java Backend
3. **Escalabilidad:** Puede procesar múltiples PDFs en paralelo
4. **Manejo de Archivos:** Guardar PDFs temporal o permanentemente según requisitos
5. **Logging:** Loguear todas las operaciones importantes

## 🤝 Comunicación con Java Backend

- **URL Base:** `http://localhost:5000/api`
- **Protocolo:** REST JSON
- **Responsabilidades:** PDFs, reportes, procesamiento pesado

## 🤝 Comunicación con Frontend

- **Indirecta:** A través de Java Backend
- **Directa:** Descargar PDF generado
- **URL:** Java Backend proporciona URL de PDF

## 🚀 Próximas Funcionalidades

- [ ] Gráficos en PDFs
- [ ] Reportes mensuales
- [ ] Exportación a Excel
- [ ] Email automático de listas
- [ ] QR en PDFs

## 📚 Stack Tecnológico

- Python 3.8+
- Flask 1.1+
- ReportLab 4.0+
- Pytest
- SQLAlchemy (optional)
- Pydantic (validación)
- Python-dotenv

## 🎯 Especialidades

### 1. PDF Generation
- ReportLab para generación
- Estilos personalizados
- Manejo de imágenes
- Tablas y datos

### 2. Data Processing
- Validación de datos
- Transformaciones
- Formateo

### 3. External Integrations
- Servicios externos
- APIs REST
- Webhooks (futuro)
