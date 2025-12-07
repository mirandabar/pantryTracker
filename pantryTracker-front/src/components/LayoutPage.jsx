import React from 'react';
import './_Page.css';
import './Home.css';

function Layout({ children }) {
  // TODO: Obtener el usuario actual desde el contexto o estado global
  const currentUser = "Usuario Demo";

  const handleSettings = () => {
    // TODO: Implementar navegación a configuración de usuario
    console.log("Abrir configuración de usuario");
  };

  return (
    <div className="page-container">
      <header className="page-header">
        <div className="header-content">
          <h1>Pantry Tracker</h1>
          <div className="header-user">
            <span className="user-name">{currentUser}</span>
            <button className="settings-button" onClick={handleSettings}>
              ⚙️
            </button>
          </div>
        </div>
      </header>
      
      <main className="page-content">
        {children}
      </main>
      
      <footer className="page-footer">
        <p>Desarrollado por David</p>
      </footer>
    </div>
  );
}

export default Layout;
