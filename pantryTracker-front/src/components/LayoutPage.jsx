import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { useUser } from '../context/UserContext';
import './_Page.css';
import './Home.css';

function Layout({ children }) {
  const { currentUser } = useUser();
  const location = useLocation();
  const navigate = useNavigate();

  const handleSettings = () => {
    // TODO: Implementar navegación a configuración de usuario
    console.log("Abrir configuración de usuario");
  };

  const handleGoHome = () => {
    navigate('/home');
  };

  const isHomePage = location.pathname === '/home';

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

      {!isHomePage && (
        <button className="home-button-floating" onClick={handleGoHome}>
          🏠 Home
        </button>
      )}
      
      <footer className="page-footer">
        <p>Desarrollado por David</p>
      </footer>
    </div>
  );
}

export default Layout;
