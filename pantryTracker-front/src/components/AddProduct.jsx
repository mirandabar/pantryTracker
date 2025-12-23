import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { sendProductListApi } from '../api/sendProductList';
import './AddBase.css';

function AddProduct() {
  const navigate = useNavigate();
  
  // Configuración centralizada de las columnas del formulario
  const FORM_COLUMNS = [
    { field: 'productName', label: 'Producto', required: true, type: 'text', placeholder: 'Nombre del producto' },
    { field: 'category', label: 'Categoría', required: false, type: 'select', options: [
      'Frutas y Verduras',
      'Carnes y Pescados',
      'Lácteos',
      'Panadería',
      'Bebidas',
      'Conservas',
      'Congelados',
      'Limpieza',
      'Higiene Personal',
      'Otros'
    ]},
    { field: 'quantity', label: 'Cantidad', required: true, type: 'number', placeholder: 'Cantidad', min: 0 }
  ];
  //Numero de filas iniciales
  const INITIAL_ROW_COUNT = 5;

  // Genera un objeto vacío con todas las columnas
  const createEmptyRow = (id) => {
    const row = { id };
    FORM_COLUMNS.forEach(col => {
      row[col.field] = '';
    });
    return row;
  };

  const [rows, setRows] = useState(Array(INITIAL_ROW_COUNT).fill().map((_, i) => createEmptyRow(i)));
  const [image, setImage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [message, setMessage] = useState({ text: '', type: '' }); // 'success' | 'error' | ''

  const addRows = (count) => {
    const newRows = Array(count).fill().map((_, i) => createEmptyRow(rows.length + i));
    setRows([...rows, ...newRows]);
  };

  const handleRowChange = (id, field, value) => {
    setRows(rows.map(row => row.id === id ? { ...row, [field]: value } : row));
  };

  const elimnateEmptyRows = (rows) => {
    return rows.filter(row => FORM_COLUMNS.some(col => row[col.field]));
  };

  const validateRows = (rows) => {
    if (rows.length === 0) {
      return -1; // No rows to validate
    }
    for (let row of rows) {
      // Validar campos obligatorios
      for (let col of FORM_COLUMNS.filter(c => c.required)) {
        if (!row[col.field]) {
          return -2; // Missing required fields
        }
      }
      // Validación específica para cantidad
      if (isNaN(row.quantity) || row.quantity <= 0 || !Number.isInteger(Number(row.quantity))) {
        return -3; // Invalid quantity
      }
    }
    return 0; // All rows valid
  };

  const generateAlertMessage = (code) => {
    switch(code) {
      case -1:
        return 'Error: No hay filas para guardar.';
      case -2:
        return 'Error: Por favor, complete todos los campos obligatorios (*) en las filas no vacías.';
      case -3:
        return 'Error: La cantidad debe ser un número entero positivo en las filas no vacías.';
      default:
        return 'Error desconocido. Felicidades, has encontrado un bug! 😤';
    }
  };

  const cleanPurchaseData = () => {
    setRows(Array(5).fill().map((_, i) => createEmptyRow(i)));
    handleImageDelete();
  };

  const sendPurchaseData = async (data) => {
    setIsLoading(true);
    document.body.style.cursor = 'wait';
    setMessage({ text: '', type: '' });
    
    try {
      const token = sessionStorage.getItem('token');
      
      if (!token) {
        setMessage({ text: 'Error: No se encontró token de autenticación. Por favor, inicie sesión nuevamente.', type: 'error' });
        setTimeout(() => navigate('/login'), 5000);
        return;
      }

      const result = await sendProductListApi(data, token);
      
      if (result.ok) {
        setMessage({ text: '¡Lista de la compra guardada exitosamente!', type: 'success' });
      } else {
        setMessage({ text: `Error al guardar la lista de la compra: ${result.data.message || 'Error desconocido'}`, type: 'error' });
      }
    } catch (error) {
      console.error('Error al enviar datos de lista de la compra:', error);
      setMessage({ text: 'Error de conexión. Por favor, intente nuevamente.', type: 'error' });
    } finally {
      setIsLoading(false);
      document.body.style.cursor = 'default';
    }
  };

  const clearMessage = () => {
    setMessage({ text: '', type: '' });
  }

  const saveList = async (e) => {
    e.preventDefault();

    if (isLoading) return;

    clearMessage();

    const productList = rows.map(row => {
      const data = {};
      FORM_COLUMNS.forEach(col => {
        data[col.field] = row[col.field];
      });
      return data;
    });

    const productListFiltered = elimnateEmptyRows(productList);

    let flag = validateRows(productListFiltered);
    
    if (flag !== 0) {
      setMessage({ text: generateAlertMessage(flag), type: 'error' });
      return;
    }

    await sendPurchaseData(productListFiltered);
    
    if (message.type === 'success') {
      cleanPurchaseData();
    }
  };

  const renderTableCell = (row, column) => {
    if (column.type === 'select') {
      return (
        <select
          value={row[column.field]}
          onChange={(e) => handleRowChange(row.id, column.field, e.target.value)}
        >
          <option value="">Seleccionar {column.label.toLowerCase()}</option>
          {column.options.map((opt) => (
            <option key={opt} value={opt}>{opt}</option>
          ))}
        </select>
      );
    }
    
    return (
      <input
        type={column.type}
        value={row[column.field]}
        onChange={(e) => handleRowChange(row.id, column.field, e.target.value)}
        placeholder={column.placeholder}
        min={column.min}
        step={column.step}
      />
    );
  };

  return (
    <div className="home-content">
      <h2>Registrar compra</h2>
      
      <form onChange={clearMessage}>
        <div className="form-actions">
          <div className="add-rows-buttons">
            <button type="button" onClick={() => addRows(1)}>+ 1 fila</button>
            <button type="button" onClick={() => addRows(5)}>+ 5 filas</button>
            <button type="button" onClick={() => addRows(10)}>+ 10 filas</button>
          </div>
        </div>

        <div className="table-container">
          <table className="purchase-table">
            <thead>
              <tr>
                <th>#</th>
                {FORM_COLUMNS.map(col => (
                  <th key={col.field}>{col.label}{col.required ? '*' : ''}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {rows.map((row, index) => (
                <tr key={row.id}>
                  <td>{index + 1}</td>
                  {FORM_COLUMNS.map(col => (
                    <td key={col.field}>
                      {renderTableCell(row, col)}
                    </td>
                  ))}
                </tr>
              ))}
            </tbody>
          </table>
        </div>

        <button 
          type="submit" 
          className="submit-btn" 
          onClick={saveList}
          disabled={isLoading}
          style={{ cursor: isLoading ? 'wait' : 'pointer' }}
        >
          {isLoading ? 'Guardando...' : 'Guardar productos necesarios'}
        </button>
        
        {message.text && (
          <div className={`message ${message.type}`}>
            {message.text}
          </div>
        )}
      </form>
    </div>
  );
}

export default AddProduct;