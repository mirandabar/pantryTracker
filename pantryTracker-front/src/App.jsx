import { useState } from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import HomePage from "./pages/HomePage";
import ItemsPage from "./pages/ItemsPage";
import HistoryPage from "./pages/HistoryPage";
import GenerateListPage from "./pages/GenerateListPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/home" element={<HomePage />} />
        <Route path="/items" element={<ItemsPage />} />
        <Route path="/history" element={<HistoryPage />} />
        <Route path="/generate" element={<GenerateListPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;