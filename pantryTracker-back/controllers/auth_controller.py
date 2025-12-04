from flask import Flask, Blueprint, request, jsonify
from flask_cors import CORS

auth_bp = Blueprint('auth', __name__)
CORS(auth_bp)  # Habilitar CORS para este blueprint

@auth_bp.route('/api/login', methods=['POST'])
def login():
    try:
        # Obtener datos de la petición
        data = request.get_json()
        email = data.get('email')
        password = data.get('password')
        
        # Validar que se recibieron email y password
        if not email or not password:
            return jsonify({"message": "Email y password son requeridos"}), 400
        
        # TODO: Consultar base de datos para verificar credenciales
        # user = db.session.query(User).filter_by(email=email).first()
        # if not user or not check_password_hash(user.password, password):
        #     return jsonify({"message": "Credenciales inválidas"}), 401
        
        # TODO: Generar token JWT o sesión
        # token = generate_jwt_token(user.id)
        
        # Respuesta por defecto (OK)
        return jsonify({
            "message": "Login exitoso",
            "user": {
                "email": email,
                # "id": user.id,
                # "name": user.name
            },
            "token": "dummy_token_for_now"  # Token temporal
        }), 200
        
    except Exception as e:
        return jsonify({"message": "Error interno del servidor"}), 500