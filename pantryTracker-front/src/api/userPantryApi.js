export async function getPantry(token) {
  const response = await fetch("http://localhost:8080/api/pantry/get", {
    method: "GET",
    headers: { 
      "Content-Type": "application/json",
      "Authorization": `Bearer ${token}`
    },
  });
  if (!response.ok) {
    throw new Error("Failed to fetch user pantry");
  }
  const data = await response.json();
  return data;
}