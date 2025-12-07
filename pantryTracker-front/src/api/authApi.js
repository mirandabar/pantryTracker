export async function loginApi(email, password) {
  const response = await fetch("http://localhost:8080/api/login", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ email, password }),
  });

  const data = await response.json();
  return { ok: response.ok, data };
}

export async function registerApi(userName, email, password) {
  const response = await fetch("http://localhost:8080/api/register", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ userName, email, password }),
  });

  const data = await response.json();
  return { ok: response.ok, data };
}