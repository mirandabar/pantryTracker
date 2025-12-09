export async function sendPurchaseApi(purchaseData, token) {
  const response = await fetch("http://localhost:8080/api/purchase/add", {
    method: "POST",
    headers: { 
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
    body: JSON.stringify( purchaseData ),
  });

  const data = await response.json();
  return { ok: response.ok, data };
}