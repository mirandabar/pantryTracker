export async function getProductList(token) {
  const response = await fetch("http://localhost:5000/api/productList/get", {
    method: "GET",
    headers: { 
      "Authorization": `Bearer ${token}`
    },
  });
  
  if (response.status === 401) {
    throw new Error("Sesión expirada. Por favor, inicia sesión nuevamente.");
  }
  
  if (response.status === 503) {
    throw new Error("El servicio no está disponible. Verifica que el backend esté ejecutándose.");
  }
  
  if (!response.ok) {
    throw new Error(`Error ${response.status}: ${response.statusText}`);
  }
  
  return response.blob();
}