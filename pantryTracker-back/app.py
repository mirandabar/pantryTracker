from flask import Flask
from flask_cors import CORS
from controllers.auth_controller import auth_bp

app = Flask(__name__)
CORS(app)  # Habilitar CORS para toda la aplicación

# Registrar blueprints
app.register_blueprint(auth_bp)

if __name__ == '__main__':
    app.run(host='localhost', port=8080, debug=True)
