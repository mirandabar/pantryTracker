import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Home.css';

function Home() {
  const navigate = useNavigate();

  const menuOptions = [
    {title: 'Mi despensa', path: '/pantry', icon: '🧺' },
    {title: 'Registar compra', path: '/add-purchase', icon: '🛒' },
    {title: 'Agregar producto necesario', path: '/add-product', icon: '➕' },
    {title: 'Lista de la compra', path: '/shopping-list', icon: '📝' },
    {title: 'Caducidades', path: '/expirations', icon: '⏳' },
    {title: 'Historial de compras', path: '/history', icon: '📜' },
  ];

  return (
    <div className="home-content">
      <h2>Bienvenido a Pantry Tracker</h2>
      <div className="menu-grid">
        {menuOptions.map((option) => (
          <button
            key={option.path}
            className="menu-button"
            onClick={() => navigate(option.path)}
          >
            <span className="menu-icon">{option.icon}</span>
            <span className="menu-title">{option.title}</span>
          </button>
        ))}
      </div>
    </div>
  );
}

export default Home;