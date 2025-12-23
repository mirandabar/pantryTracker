from flask import Flask
from flask_cors import CORS
from controllers.auth_controller import auth_bp
from controllers.productList_controller import productList_bp

app = Flask(__name__)
CORS(app)  # Habilitar CORS para toda la aplicación

# Registrar blueprints
app.register_blueprint(auth_bp)
app.register_blueprint(productList_bp)

@app.route('/')
def index():
    return {"status": "Server is running"}

if __name__ == '__main__':
    app.run(host='localhost', port=5000, debug=True)
