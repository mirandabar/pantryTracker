import { BrowserRouter, Routes, Route } from 'react-router-dom'
import { AuthProvider } from './context/AuthContext'
import { UserProvider } from './context/UserContext';
import ProtectedRoute from './components/ProtectedRoute'
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import HomePage from "./pages/HomePage";
import HistoryPage from "./pages/HistoryPage";
import PantryPage from "./pages/PantryPage";
import AddPurchasePage from "./pages/AddPurchasePage";
import AddProductPage from "./pages/AddProductPage";
import ShoppingListPage from "./pages/ShoppingListPage";
import ExpirationsPage from "./pages/ExpirationsPage";

function App() {
  return (
    <AuthProvider>
      <UserProvider>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            
            {/* Rutas protegidas */}
            <Route path="/home" element={
              <ProtectedRoute>
                <HomePage />
              </ProtectedRoute>
            } />
            <Route path="/pantry" element={
              <ProtectedRoute>
                <PantryPage />
              </ProtectedRoute>
            } />
            <Route path="/add-purchase" element={
              <ProtectedRoute>
                <AddPurchasePage />
              </ProtectedRoute>
            } />
            <Route path="/add-product" element={
              <ProtectedRoute>
                <AddProductPage />
              </ProtectedRoute>
            } />
            <Route path="/shopping-list" element={
              <ProtectedRoute>
                <ShoppingListPage />
              </ProtectedRoute>
            } />
            <Route path="/expirations" element={
              <ProtectedRoute>
                <ExpirationsPage />
              </ProtectedRoute>
            } />
            <Route path="/history" element={
              <ProtectedRoute>
                <HistoryPage />
              </ProtectedRoute>
            } />
            <Route path="*" element={<div>Página no encontrada</div>} />

          </Routes>
        </BrowserRouter>
      </UserProvider>
    </AuthProvider>
  );
}

export default App;