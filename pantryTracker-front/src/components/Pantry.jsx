import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useUser } from '../context/UserContext';
import { getPantry } from '../api/userPantryApi';
import './Pantry.css';

function Pantry() {
  const { currentUser, loading: userLoading } = useUser();
  const navigate = useNavigate();
  const [products, setProducts] = useState([]);
  const [filteredProducts, setFilteredProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [sortConfig, setSortConfig] = useState({ key: null, direction: 'asc' });
  const [searchTerm, setSearchTerm] = useState('');

  const filterProductsByKey = (data, key, direction) => {

    const sorted = [...data].sort((a, b) => {
      if (key === 'productName') {
        return direction === 'asc'
          ? a[key].localeCompare(b[key])
          : b[key].localeCompare(a[key]);
      } 
      else if (key === 'purchaseDate' || key === 'expirationDate') {
        const dateA = a[key] ? new Date(a[key]) : null;
        const dateB = b[key] ? new Date(b[key]) : null;
        
        // Fechas vacías/inválidas van al final
        if (!dateA || isNaN(dateA.getTime())) return 1;
        if (!dateB || isNaN(dateB.getTime())) return -1;
        
        return direction === 'asc'
          ? dateA - dateB
          : dateB - dateA;
      }
      else {
        return direction === 'asc'
          ? a[key] - b[key]
          : b[key] - a[key];
      }
    });

    return sorted;
  }

  useEffect(() => {
    const fetchPantry = async () => {
      if (userLoading) return;
      
      try {
        const token = sessionStorage.getItem('token');
        const data = await getPantry(token);
        let productsData = filterProductsByKey(data, 'productName', 'asc');
        setProducts(productsData); // Store original products by name ascending
        setFilteredProducts(productsData); // Initialize filtered products
      } catch (err) {
        setError(err.message);
      } finally {
        setLoading(false);
      }
    };

    fetchPantry();
  }, [userLoading]);

  const handleSort = (key) => {
    let direction = 'asc';
    if (sortConfig.key === key && sortConfig.direction === 'asc') {
      direction = 'desc';
    }
    setSortConfig({ key, direction });
    const sorted = filterProductsByKey(filteredProducts, key, direction);
    setFilteredProducts(sorted);
  };

  const getSortIndicator = (key) => {
    if (sortConfig.key !== key) return ' ⇅';
    return sortConfig.direction === 'asc' ? ' ↑' : ' ↓';
  };

  const findProductByName = (name) => {
    setSearchTerm(name);
    const filtered = products.filter(product => 
      product.productName.toLowerCase().includes(name.toLowerCase())
    );
    setFilteredProducts(filtered);
  }

  const highlightText = (text, highlight) => {
    if (!highlight.trim()) {
      return text;
    }
    
    const regex = new RegExp(`(${highlight})`, 'gi');
    const parts = text.split(regex);
    
    return parts.map((part, index) => 
      regex.test(part) ? <mark key={index}>{part}</mark> : part
    );
  };

  if (loading) return <div>Cargando productos...</div>;
  if (error) return <div>Error: {error}</div>;

  return (
    <div className="pantry-page">
      <h1>Productos en la despensa</h1>
      
      <div className="pantry-controls">
        <input
          type="text"
          placeholder="Buscar producto..."
          value={searchTerm}
          onChange={(e) => findProductByName(e.target.value)}
          className="search-input"
        />
      </div>

      {filteredProducts.length === 0 ? (
        <p className="no-products">No hay productos en la despensa</p>
      ) : (
        <table className="products-table">
          <thead>
            <tr>
              <th onClick={() => handleSort('productName')}>
                Nombre{getSortIndicator('productName')}
              </th>
              <th onClick={() => handleSort('quantity')}>
                Cantidad{getSortIndicator('quantity')}
              </th>
              <th onClick={() => handleSort('price')}>
                Precio{getSortIndicator('price')}
              </th>
              <th onClick={() => handleSort('purchaseDate')}>
                Fecha compra{getSortIndicator('purchaseDate')}
              </th>
              <th onClick={() => handleSort('expirationDate')}>
                Fecha caducidad{getSortIndicator('expirationDate')}
              </th>
            </tr>
          </thead>
          <tbody>
            {filteredProducts.map((product) => (
              <tr key={product.id}>
                <td>
                  {highlightText(
                    product.productName.charAt(0).toUpperCase() + product.productName.slice(1),
                    searchTerm
                  )}
                </td>
                <td>{product.quantity}</td>
                <td>{product.price.toFixed(2)} €</td>
                <td>{new Date(product.purchaseDate).toLocaleDateString()}</td>
                <td>{isNaN(new Date(product.expirationDate).getTime()) ? '-' : new Date(product.expirationDate).toLocaleDateString()}</td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  )
}

export default Pantry;