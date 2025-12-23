export async function sendProductListApi(productListData, token) {
  const response = await fetch("http://localhost:8080/api/productList/add", {
    method: "POST",
    headers: { 
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
    body: JSON.stringify( productListData ),
  });

  const data = await response.json();
  return { ok: response.ok, data };
}