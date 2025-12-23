import { useEffect, useState, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import { getProductList } from '../api/getProductList';

function ShopingListPDF({ token }) {
  const navigate = useNavigate();
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);
  const hasDownloaded = useRef(false);

  useEffect(() => {
    const downloadPDF = async () => {
      // Prevenir múltiples descargas
      if (hasDownloaded.current) return;
      hasDownloaded.current = true;

      // Intentar obtener token desde props o sessionStorage
      const authToken = token || sessionStorage.getItem('token');
      
      if (!authToken) {
        setError('No hay sesión activa. Por favor, inicia sesión.');
        setLoading(false);
        setTimeout(() => navigate('/login'), 2000);
        return;
      }

      try {
        setLoading(true);
        const blob = await getProductList(authToken);
        const url = window.URL.createObjectURL(blob);

        const a = document.createElement("a");
        a.href = url;
        a.download = "lista_compra.pdf";
        a.click();

        window.URL.revokeObjectURL(url);
        setLoading(false);
        
        // Navegar de vuelta después de descargar
        setTimeout(() => navigate('/home'), 500);
      } catch (error) {
        console.error('Error downloading PDF:', error);
        setError(error.message || 'Error al descargar el PDF');
        setLoading(false);
      }
    };

    downloadPDF();
  }, [token, navigate]);

  if (loading) {
    return (
      <div style={{ padding: '20px', textAlign: 'center' }}>
        <p>Generando PDF...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div style={{ padding: '20px', textAlign: 'center' }}>
        <p style={{ color: 'red' }}>Error: {error}</p>
        <button onClick={() => navigate('/home')}>Volver</button>
      </div>
    );
  }

  return null;
}

export default ShopingListPDF;