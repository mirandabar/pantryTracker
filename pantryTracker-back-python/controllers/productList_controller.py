from flask import Blueprint, request, send_file, jsonify
import requests
import tempfile
from reportlab.lib.pagesizes import A4
from reportlab.pdfgen import canvas

productList_bp = Blueprint('productList', __name__)

@productList_bp.route('/api/productList/get', methods=['GET', 'OPTIONS'])
def generate_pdf():
    # Manejar preflight CORS
    if request.method == 'OPTIONS':
        return '', 200
    
    authorization = request.headers.get('Authorization')
    print(f"Received authorization: {authorization}")
    
    if not authorization:
        return jsonify({"error": "No authorization header"}), 401
    
    if not authorization.startswith("Bearer "):
        return jsonify({"error": "Invalid authorization format"}), 401
    
    # 1. Obtener productos del backend Java pasando el token
    try:
        response = requests.get(
            "http://localhost:8080/api/productList/get",
            headers={"Authorization": authorization}
        )
        
        if response.status_code == 401:
            return jsonify({"error": "Invalid or expired token"}), 401
        elif response.status_code == 403:
            return jsonify({"error": "Access forbidden"}), 403
        elif response.status_code != 200:
            return jsonify({"error": "Failed to fetch products"}), response.status_code
        
        products = response.json()
        print(products)
        
    except requests.RequestException as e:
        return jsonify({"error": f"Backend service unavailable: {str(e)}"}), 503

    # 2. Crear PDF temporal
    tmp = tempfile.NamedTemporaryFile(delete=False, suffix=".pdf")
    c = canvas.Canvas(tmp.name, pagesize=A4)

    y = 800
    c.drawString(50, y, "Lista de la compra")
    y -= 30

    for p in products:
        line = f"{p['productName']} - {p['quantity']} - {p['category']}"
        c.drawString(50, y, line)
        y -= 20
        if y < 50:  # Nueva página si se acaba el espacio
            c.showPage()
            y = 800

    c.save()

    # 3. Devolver PDF
    return send_file(
        tmp.name,
        mimetype='application/pdf',
        as_attachment=True,
        download_name='lista_compra.pdf'
    )
