---
description: "Guidelines for Python and Flask development"
applyTo: "**/*.py"
---

# Python Development

**Versión:** 1.0  
**Aplica a:** Todos los proyectos con Python  
**Lenguaje:** Python 3.8+  
**Framework:** Flask 1.1+

---

## 📋 Introducción

Este documento contiene las convenciones y mejores prácticas para desarrollo en Python que deben seguirse en **todos** los proyectos con Python en PantryTracker.

Estas instrucciones son **independientes** de la arquitectura específica de cada proyecto, pero deben aplicarse **junto con** las instrucciones específicas del proyecto.

## 🏗️ Arquitectura Flask

La arquitectura de Python en PantryTracker sigue el patrón **MVT (Model-View-Template)** adaptado a APIs:

```
┌─────────────────────────────────────┐
│  Blueprints/Controllers (Routes)    │ → Recibe requests
├─────────────────────────────────────┤
│  Services (Business Logic)          │ → Procesa logic
├─────────────────────────────────────┤
│  Models (Data)                      │ → Datos
├─────────────────────────────────────┤
│  Database (SQLite/PostgreSQL)       │ → Almacena datos
└─────────────────────────────────────┘
```

## 📁 Estructura de Carpetas

```
pantryTracker-back-python/
├── app.py                    # Inicialización de Flask
├── config.py                 # Configuración
├── requirements.txt          # Dependencias
├── .env                      # Variables de entorno
├── controllers/              # Blueprints/Rutas
│   ├── __init__.py
│   ├── product_controller.py
│   ├── auth_controller.py
│   └── ...
├── services/                 # Lógica de negocio
│   ├── __init__.py
│   ├── product_service.py
│   ├── pdf_service.py
│   └── ...
├── models/                   # Modelos/Entidades
│   ├── __init__.py
│   ├── product.py
│   └── ...
├── utils/                    # Utilidades
│   ├── __init__.py
│   ├── validators.py
│   ├── decorators.py
│   └── ...
└── tests/                    # Tests con pytest
    ├── __init__.py
    ├── test_product_controller.py
    └── ...
```

## 🎯 Convenciones de Nombres

### Archivos y Módulos

```python
# ✅ CORRECTO - snake_case
product_controller.py
productList_service.py
pdf_generator.py
validators.py

# ❌ INCORRECTO
ProductController.py
productListService.py
PDFGenerator.py
Validators.py
product-controller.py
```

### Clases

```python
# ✅ CORRECTO - PascalCase
class ProductService:
    pass

class PDFGenerator:
    pass

class UserValidator:
    pass

# ❌ INCORRECTO
class product_service:
    pass

class pdfgenerator:
    pass

class user_validator:
    pass
```

### Funciones y Métodos

```python
# ✅ CORRECTO - snake_case, verbos claros
def get_all_products():
    pass

def create_product(data):
    pass

def update_product(product_id, data):
    pass

def delete_product(product_id):
    pass

def generate_pdf(items):
    pass

# ❌ INCORRECTO
def getAllProducts():
    pass

def CreateProduct(data):
    pass

def prod_update(product_id, data):
    pass

def get(product_id):
    pass
```

### Constantes

```python
# ✅ CORRECTO - UPPER_SNAKE_CASE
API_BASE_URL = "http://localhost:8080"
MAX_RETRIES = 3
DATABASE_URL = "sqlite:///pantry.db"
UPLOAD_FOLDER = "uploads"

# ❌ INCORRECTO
api_base_url = ""
MaxRetries = 3
database_url = ""
upload_folder = ""
```

### Variables

```python
# ✅ CORRECTO - descriptivas, snake_case
user_email = "user@example.com"
all_products = []
product_quantity = 5
is_valid = True

# ❌ INCORRECTO
e = "user@example.com"
products = []  # ambiguo
q = 5
v = True
userData = {}  # mixto
```

## 🛠️ Blueprints/Controllers

### Estructura Base

```python
# controllers/product_controller.py
from flask import Blueprint, request, jsonify
from services.product_service import ProductService
import logging

# Configurar logger
logger = logging.getLogger(__name__)

# Crear blueprint
bp = Blueprint('products', __name__, url_prefix='/api/products')

@bp.route('/', methods=['GET'])
def get_all_products():
    """
    Obtener todos los productos.
    
    Returns:
        JSON: Lista de productos
    """
    try:
        products = ProductService.get_all()
        return jsonify(products), 200
    except Exception as e:
        logger.error(f"Error getting products: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500

@bp.route('/<int:product_id>', methods=['GET'])
def get_product(product_id):
    """
    Obtener producto por ID.
    
    Args:
        product_id (int): ID del producto
        
    Returns:
        JSON: Datos del producto
    """
    try:
        if product_id <= 0:
            return jsonify({'error': 'Invalid product ID'}), 400
        
        product = ProductService.get_by_id(product_id)
        if not product:
            return jsonify({'error': 'Product not found'}), 404
        
        return jsonify(product), 200
    except Exception as e:
        logger.error(f"Error getting product {product_id}: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500

@bp.route('/', methods=['POST'])
def create_product():
    """
    Crear nuevo producto.
    
    Request body:
        - name (str): Nombre del producto
        - quantity (int): Cantidad
        
    Returns:
        JSON: Producto creado
    """
    try:
        data = request.json
        
        # Validar datos
        if not data:
            return jsonify({'error': 'Request body required'}), 400
        
        if not data.get('name') or not isinstance(data.get('name'), str):
            return jsonify({'error': 'Product name is required'}), 400
        
        if data.get('quantity') is None or not isinstance(data.get('quantity'), int):
            return jsonify({'error': 'Product quantity is required'}), 400
        
        # Crear producto
        product = ProductService.create(data)
        return jsonify(product), 201
    except ValueError as e:
        logger.warning(f"Validation error: {str(e)}")
        return jsonify({'error': str(e)}), 400
    except Exception as e:
        logger.error(f"Error creating product: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500

@bp.route('/<int:product_id>', methods=['PUT'])
def update_product(product_id):
    """
    Actualizar producto.
    
    Args:
        product_id (int): ID del producto
        
    Returns:
        JSON: Producto actualizado
    """
    try:
        if product_id <= 0:
            return jsonify({'error': 'Invalid product ID'}), 400
        
        data = request.json
        if not data:
            return jsonify({'error': 'Request body required'}), 400
        
        product = ProductService.update(product_id, data)
        if not product:
            return jsonify({'error': 'Product not found'}), 404
        
        return jsonify(product), 200
    except ValueError as e:
        logger.warning(f"Validation error: {str(e)}")
        return jsonify({'error': str(e)}), 400
    except Exception as e:
        logger.error(f"Error updating product: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500

@bp.route('/<int:product_id>', methods=['DELETE'])
def delete_product(product_id):
    """
    Eliminar producto.
    
    Args:
        product_id (int): ID del producto
        
    Returns:
        JSON: Confirmación
    """
    try:
        if product_id <= 0:
            return jsonify({'error': 'Invalid product ID'}), 400
        
        success = ProductService.delete(product_id)
        if not success:
            return jsonify({'error': 'Product not found'}), 404
        
        return '', 204
    except Exception as e:
        logger.error(f"Error deleting product: {str(e)}")
        return jsonify({'error': 'Internal server error'}), 500
```

### Registrar Blueprints en app.py

```python
# app.py
from flask import Flask
from controllers.product_controller import bp as product_bp
from controllers.auth_controller import bp as auth_bp
import logging

def create_app():
    """Crear y configurar la aplicación Flask."""
    app = Flask(__name__)
    app.config['JSON_SORT_KEYS'] = False
    
    # Configurar logging
    logging.basicConfig(level=logging.INFO)
    
    # Registrar blueprints
    app.register_blueprint(product_bp)
    app.register_blueprint(auth_bp)
    
    return app

if __name__ == '__main__':
    app = create_app()
    app.run(debug=True, host='0.0.0.0', port=5000)
```

## 🔄 Services

### Estructura Base

```python
# services/product_service.py
from models.product import Product
import logging

logger = logging.getLogger(__name__)

class ProductService:
    """Servicio de lógica de negocio para productos."""
    
    @staticmethod
    def get_all():
        """
        Obtener todos los productos.
        
        Returns:
            list: Lista de productos
        """
        try:
            products = Product.query.all()
            return [p.to_dict() for p in products]
        except Exception as e:
            logger.error(f"Error fetching products: {str(e)}")
            raise
    
    @staticmethod
    def get_by_id(product_id):
        """
        Obtener producto por ID.
        
        Args:
            product_id (int): ID del producto
            
        Returns:
            dict: Datos del producto o None
        """
        if not isinstance(product_id, int) or product_id <= 0:
            raise ValueError("Invalid product ID")
        
        product = Product.query.get(product_id)
        return product.to_dict() if product else None
    
    @staticmethod
    def create(data):
        """
        Crear nuevo producto.
        
        Args:
            data (dict): Datos del producto
            
        Returns:
            dict: Producto creado
        """
        # Validar datos
        ProductService._validate_product_data(data)
        
        try:
            product = Product(
                name=data['name'].strip(),
                quantity=data['quantity']
            )
            product.save()
            logger.info(f"Product created: {product.id}")
            return product.to_dict()
        except Exception as e:
            logger.error(f"Error creating product: {str(e)}")
            raise
    
    @staticmethod
    def update(product_id, data):
        """
        Actualizar producto.
        
        Args:
            product_id (int): ID del producto
            data (dict): Nuevos datos
            
        Returns:
            dict: Producto actualizado o None
        """
        product = Product.query.get(product_id)
        if not product:
            return None
        
        ProductService._validate_product_data(data, partial=True)
        
        try:
            if 'name' in data:
                product.name = data['name'].strip()
            if 'quantity' in data:
                product.quantity = data['quantity']
            
            product.save()
            logger.info(f"Product updated: {product_id}")
            return product.to_dict()
        except Exception as e:
            logger.error(f"Error updating product: {str(e)}")
            raise
    
    @staticmethod
    def delete(product_id):
        """
        Eliminar producto.
        
        Args:
            product_id (int): ID del producto
            
        Returns:
            bool: True si se eliminó, False si no existe
        """
        product = Product.query.get(product_id)
        if not product:
            return False
        
        try:
            product.delete()
            logger.info(f"Product deleted: {product_id}")
            return True
        except Exception as e:
            logger.error(f"Error deleting product: {str(e)}")
            raise
    
    @staticmethod
    def _validate_product_data(data, partial=False):
        """
        Validar datos del producto.
        
        Args:
            data (dict): Datos a validar
            partial (bool): Si es actualización parcial
            
        Raises:
            ValueError: Si los datos no son válidos
        """
        if not isinstance(data, dict):
            raise ValueError("Data must be a dictionary")
        
        if not partial or 'name' in data:
            if not data.get('name') or not isinstance(data.get('name'), str):
                raise ValueError("Product name is required and must be a string")
            if len(data['name'].strip()) < 3:
                raise ValueError("Product name must be at least 3 characters")
        
        if not partial or 'quantity' in data:
            if data.get('quantity') is None or not isinstance(data.get('quantity'), int):
                raise ValueError("Product quantity is required and must be an integer")
            if data['quantity'] < 0:
                raise ValueError("Product quantity cannot be negative")
```

## 📦 Models

### Estructura Base

```python
# models/product.py
from datetime import datetime
import logging

logger = logging.getLogger(__name__)

class Product:
    """Modelo de producto."""
    
    def __init__(self, name, quantity, description=None, product_id=None):
        """
        Inicializar producto.
        
        Args:
            name (str): Nombre del producto
            quantity (int): Cantidad
            description (str): Descripción opcional
            product_id (int): ID del producto (para actualizaciones)
        """
        self.id = product_id
        self.name = name
        self.quantity = quantity
        self.description = description
        self.created_at = datetime.now()
        self.updated_at = datetime.now()
        self.active = True
    
    def save(self):
        """Guardar producto en base de datos."""
        try:
            self.updated_at = datetime.now()
            # Lógica de guardado (simulada)
            logger.info(f"Product saved: {self.name}")
        except Exception as e:
            logger.error(f"Error saving product: {str(e)}")
            raise
    
    def delete(self):
        """Eliminar producto de base de datos."""
        try:
            logger.info(f"Product deleted: {self.id}")
        except Exception as e:
            logger.error(f"Error deleting product: {str(e)}")
            raise
    
    def to_dict(self):
        """
        Convertir producto a diccionario.
        
        Returns:
            dict: Representación del producto
        """
        return {
            'id': self.id,
            'name': self.name,
            'quantity': self.quantity,
            'description': self.description,
            'created_at': self.created_at.isoformat(),
            'updated_at': self.updated_at.isoformat(),
            'active': self.active,
        }
    
    @staticmethod
    def query_all():
        """Obtener todos los productos (simulado)."""
        return []
    
    @staticmethod
    def query_by_id(product_id):
        """Obtener producto por ID (simulado)."""
        return None
    
    @classmethod
    def query(cls):
        """Query builder (simular ORM)."""
        class Query:
            @staticmethod
            def all():
                return []
            
            @staticmethod
            def get(product_id):
                return None
        
        return Query()
```

## 🧪 Testing con Pytest

### Estructura Base

```python
# tests/test_product_service.py
import pytest
from services.product_service import ProductService
from models.product import Product

class TestProductService:
    """Tests para ProductService."""
    
    def test_get_all_products(self):
        """Test obtener todos los productos."""
        # Arrange
        products = ProductService.get_all()
        
        # Act & Assert
        assert isinstance(products, list)
    
    def test_create_product_with_valid_data(self):
        """Test crear producto con datos válidos."""
        # Arrange
        data = {
            'name': 'Test Product',
            'quantity': 10
        }
        
        # Act
        product = ProductService.create(data)
        
        # Assert
        assert product is not None
        assert product['name'] == 'Test Product'
        assert product['quantity'] == 10
    
    def test_create_product_with_invalid_name(self):
        """Test crear producto con nombre inválido."""
        # Arrange
        data = {
            'name': '',
            'quantity': 10
        }
        
        # Act & Assert
        with pytest.raises(ValueError):
            ProductService.create(data)
    
    def test_delete_nonexistent_product(self):
        """Test eliminar producto inexistente."""
        # Act
        result = ProductService.delete(999)
        
        # Assert
        assert result is False
```

### Ejecutar Tests

```bash
# Ejecutar todos los tests
pytest

# Tests con verbose
pytest -v

# Tests con cobertura
pytest --cov=.

# Tests de un archivo específico
pytest tests/test_product_service.py

# Tests de una función específica
pytest tests/test_product_service.py::TestProductService::test_create_product_with_valid_data
```

## 📄 Generación de PDFs (Especialidad)

### Ejemplo con ReportLab

```python
# services/pdf_service.py
from reportlab.lib.pagesizes import letter
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.platypus import SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle
from reportlab.lib.units import inch
from reportlab.lib import colors
import logging
from datetime import datetime

logger = logging.getLogger(__name__)

class PDFService:
    """Servicio para generación de PDFs."""
    
    @staticmethod
    def generate_shopping_list(items, filename=None):
        """
        Generar PDF de lista de compra.
        
        Args:
            items (list): Lista de items
            filename (str): Nombre del archivo (opcional)
            
        Returns:
            str: Ruta del archivo PDF
        """
        try:
            if not filename:
                timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
                filename = f"shopping_list_{timestamp}.pdf"
            
            # Crear documento
            doc = SimpleDocTemplate(filename, pagesize=letter)
            story = []
            
            # Estilos
            styles = getSampleStyleSheet()
            title_style = ParagraphStyle(
                'CustomTitle',
                parent=styles['Heading1'],
                fontSize=24,
                textColor=colors.HexColor('#333333'),
                spaceAfter=20,
            )
            
            # Título
            story.append(Paragraph("Lista de Compra", title_style))
            story.append(Spacer(1, 0.3 * inch))
            
            # Tabla de items
            data = [['Producto', 'Cantidad']]
            for item in items:
                data.append([item['name'], str(item['quantity'])])
            
            table = Table(data)
            table.setStyle(TableStyle([
                ('BACKGROUND', (0, 0), (-1, 0), colors.grey),
                ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
                ('ALIGN', (0, 0), (-1, -1), 'LEFT'),
                ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
                ('FONTSIZE', (0, 0), (-1, 0), 14),
                ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
                ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
                ('GRID', (0, 0), (-1, -1), 1, colors.black),
            ]))
            
            story.append(table)
            
            # Construir PDF
            doc.build(story)
            logger.info(f"PDF generated: {filename}")
            return filename
        
        except Exception as e:
            logger.error(f"Error generating PDF: {str(e)}")
            raise
```

## ✅ Validaciones

```python
# utils/validators.py
import re
import logging

logger = logging.getLogger(__name__)

class Validators:
    """Utilidades de validación."""
    
    @staticmethod
    def validate_email(email):
        """
        Validar correo electrónico.
        
        Args:
            email (str): Email a validar
            
        Returns:
            bool: True si es válido
        """
        pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
        return re.match(pattern, email) is not None
    
    @staticmethod
    def validate_password(password):
        """
        Validar contraseña.
        
        Args:
            password (str): Contraseña a validar
            
        Returns:
            bool: True si cumple requisitos
        """
        if not password or len(password) < 8:
            return False
        if not re.search(r'[A-Z]', password):
            return False
        if not re.search(r'[a-z]', password):
            return False
        if not re.search(r'[0-9]', password):
            return False
        return True
    
    @staticmethod
    def validate_product_name(name):
        """
        Validar nombre de producto.
        
        Args:
            name (str): Nombre a validar
            
        Returns:
            bool: True si es válido
        """
        return name and isinstance(name, str) and len(name.strip()) >= 3
```

## ⚠️ Lo que NO Hacer

- ❌ Usar `print()` en producción (usar `logging`)
- ❌ Hardcodear credenciales o URLs
- ❌ Ignorar excepciones con try-except vacío
- ❌ Crear funciones muy largas (>30 líneas)
- ❌ Dejar imports sin usar
- ❌ Usar `*` en imports
- ❌ Mezclar español e inglés en nombres
- ❌ No validar inputs de usuario

## 📦 Gestión de Dependencias

```bash
# Crear requirements.txt
pip freeze > requirements.txt

# Instalar dependencias
pip install -r requirements.txt

# Ejecutar aplicación
python app.py

# Ejecutar con modo debug
FLASK_ENV=development FLASK_DEBUG=1 flask run

# Validar tipos (si usa type hints)
mypy .

# Linting
pylint **/*.py
black --check .
flake8 .
```

## 📚 Recursos

- [Flask Official Docs](https://flask.palletsprojects.com)
- [Pytest Documentation](https://docs.pytest.org)
- [PEP 8 Style Guide](https://pep8.org)
- [ReportLab Documentation](https://www.reportlab.com/docs/reportlab-userguide.pdf)
- [Black Code Formatter](https://github.com/psf/black)
