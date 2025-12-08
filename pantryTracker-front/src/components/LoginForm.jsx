import React, { useState } from "react";
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useAuth } from '../context/AuthContext';
import { useNavigate } from "react-router-dom"; 
import './LoginForm.css';
import { setEmail, setPassword } from "../utils/setters";

import { loginApi } from "../api/authApi";

const LoginForm = () => {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [email, setEmailState] = useState("");
  const [password, setPasswordState] = useState("");
  const [emailError, setEmailError] = useState(null);
  const [passwordError, setPasswordError] = useState(null);
  const [errorMessage, setErrorMessage] = useState(""); 
  const [isLoading, setIsLoading] = useState(false);

  const onChangeEmail = (e) => {
    setEmailState(e.target.value);
  };
  
  const onChangePassword = (e) => {
    setPasswordState(e.target.value);
  };

  const handleEmailChange = (e) => {
    console.log("Email changed:", e.target.value);
    const result = setEmail(e.target.value);
    setEmailState(result.value);
    setEmailError(result.error);
  };

  const handlePasswordChange = (e) => {
    console.log("Password changed:", e.target.value);
    const result = setPassword(e.target.value);
    setPasswordState(result.value);
    setPasswordError(result.error);
  };

  const newUser = () => {
    navigate("/register");
  };

  const loginSubmit = async (event) => {

    if (isLoading) return;

    event.preventDefault();
    setIsLoading(true);
    document.body.style.cursor = 'wait';

    const emailResult = setEmail(email);
    const passwordResult = setPassword(password);
    
    setEmailError(emailResult.error);
    setPasswordError(passwordResult.error);
    
    if (emailResult.isValid && passwordResult.isValid) {
      console.log("Email:", email);
      console.log("Password:", password);
      
      try {
        const result = await loginApi(email, password);

        if (result.ok) {
          login({ token: result.data.data.token, username: result.data.data.username });
          navigate('/home');
        } else {
          setErrorMessage(result.data.error || result.data.message || "Error de autenticación");
        }
      } catch (error) {
        setErrorMessage("Error de conexión con el servidor");
      } finally {
        setIsLoading(false);
        document.body.style.cursor = 'default';
      }
    }
    else {
      setErrorMessage("Por favor corrige los errores en el formulario");
    }
  };

  return (
    <div className="form-container">
      <form autoComplete="off" onSubmit={loginSubmit} className="form-card" 
          disabled={isLoading}
          style={{ cursor: isLoading ? 'wait' : 'pointer' }}>
        <div className="p-fluid">
          <div className="form-field">
            <h2 className="form-title">Iniciar Sesión</h2>
          </div>
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="email" className="input-label">Email</label>
            <InputText
              id="email"
              type="email"
              value={email}
              onChange={onChangeEmail}
              onBlur={handleEmailChange}
              placeholder="introduzca su Email"
              className={emailError ? "p-invalid input-error" : ""}
            />
            {emailError && <small className="p-error">{emailError}</small>}
          </div>
          <div className="p-4"></div>
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="password" className="input-label">Contraseña</label>
            <InputText
              id="password"
              autoComplete="current-password"
              type="password"
              value={password}
              onChange={onChangePassword}
              onBlur={handlePasswordChange}
              placeholder="introduzca su contraseña"
              className={passwordError ? "p-invalid input-error" : ""}
            />
            {passwordError && <small className="p-error">{passwordError}</small>}
          </div>
          <div className="p-field p-col-12 p-md-12">
            <Button type="submit" label="Ingresar" onClick={loginSubmit} />
          </div>
          <div className="p-field p-col-12 p-md-12">
            <Button type="button" label="Nuevo Usuario" onClick={newUser} />
          </div>
          {errorMessage && (
            <div className="p-field">
              <small className="p-error">{errorMessage}</small>
            </div>
          )}
        </div>
      </form>
    </div>
  );
};

export default LoginForm;