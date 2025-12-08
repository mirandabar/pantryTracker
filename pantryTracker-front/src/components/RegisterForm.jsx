import React, { useState } from 'react';
import { InputText } from "primereact/inputtext";
import { Button } from "primereact/button";
import { useAuth } from '../context/AuthContext';
import { useNavigate } from "react-router-dom";
import './LoginForm.css';
import { setEmail, setPassword, setUserName, setConfirmPassword } from "../utils/setters";

import { registerApi } from "../api/authApi";

const RegisterForm = () => {
  const { login } = useAuth();
  const navigate = useNavigate();
  const [userName, setUserNameState] = useState("");
  const [email, setEmailState] = useState("");
  const [password, setPasswordState] = useState("");
  const [confirmPassword, setConfirmPasswordState] = useState("");
  const [userNameError, setUserNameError] = useState(null);
  const [emailError, setEmailError] = useState(null);
  const [passwordError, setPasswordError] = useState(null);
  const [confirmPasswordError, setConfirmPasswordError] = useState(null);
  const [errorMessage, setErrorMessage] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const onChangeEmail = (e) => {
    setEmailState(e.target.value);
  };
  
  const onChangePassword = (e) => {
    setPasswordState(e.target.value);
  };

  const onChangeConfirmPassword = (e) => {
    setConfirmPasswordState(e.target.value);
  };

  const onChangeUserName = (e) => {
    setUserNameState(e.target.value);
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

  const handleUserNameChange = (e) => {
    console.log("Username changed:", e.target.value);
    const result = setUserName(e.target.value);
    setUserName(result.value);
    setUserNameError(result.error);
  };

  const handleConfirmPasswordChange = (e) => {
    console.log("Confirm Password changed:", e.target.value);
    const result = setConfirmPassword(e.target.value, password);
    setConfirmPassword(result.value);
    setConfirmPasswordError(result.error);
  }

  const registerSubmit = async (event) => {

    if (isLoading) return;
    
    event.preventDefault();
    setIsLoading(true);
    document.body.style.cursor = 'wait';

    const emailResult    = setEmail(email);
    const passwordResult = setPassword(password);
    const confirmPasswordResult = setConfirmPassword(confirmPassword, password);
    const userNameResult = setUserName(userName);
    
    setEmailError(emailResult.error);
    setPasswordError(passwordResult.error);
    setConfirmPasswordError(confirmPasswordResult.error);
    setUserNameError(userNameResult.error);
    
    if (emailResult.isValid && 
        passwordResult.isValid && 
        confirmPasswordResult.isValid &&
        userNameResult.isValid) {

      console.log("Email:", email);
      console.log("Password:", password);
      console.log("Username:", userName);

      try {
        const result = await registerApi(userName, email, password);
        
        if (result.ok) {
          login({ token: result.data.data.token, username: result.data.data.username });
          navigate("/home");
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
      <form autoComplete="off" onSubmit={registerSubmit} className="form-card"
          disabled={isLoading}
          style={{ cursor: isLoading ? 'wait' : 'pointer' }}>
        <div className="p-fluid">
          <div className="form-field">
            <h2 className="form-title">Registro de Usuario</h2>
          </div>
          
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="username" className="input-label">Usuario</label>
            <InputText
              id="username"
              name="username"
              value={userName}
              onChange={onChangeUserName}
              onBlur={handleUserNameChange}
              placeholder="Ingrese su usuario"
              className={userNameError ? 'p-invalid input-error' : ''}
            />
            {userNameError && <small className="p-error">{userNameError}</small>}
          </div>

          <div className="p-4"></div>
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="email" className="input-label">Email</label>
            <InputText
              id="email"
              name="email"
              type="email"
              value={email}
              onChange={onChangeEmail}
              onBlur={handleEmailChange}
              placeholder="Ingrese su email"
              required
              className={emailError ? 'p-invalid input-error' : ''}
            />
            {emailError && <small className="p-error">{emailError}</small>}
          </div>

          <div className="p-4"></div>
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="password" className="input-label">Contraseña</label>
            <InputText
              id="password"
              name="password"
              type="password"
              value={password}
              onChange={onChangePassword}
              onBlur={handlePasswordChange}
              placeholder="Ingrese su contraseña"
              className={passwordError ? 'p-invalid input-error' : ''}
            />
            {passwordError && <small className="p-error">{passwordError}</small>}
          </div>

          <div className="p-4"></div>
          <div className="p-field p-col-12 p-md-12">
            <label htmlFor="confirmPassword" className="input-label">Confirmar Contraseña</label>
            <InputText
              id="confirmPassword"
              name="confirmPassword"
              type="password"
              value={confirmPassword}
              onChange={onChangeConfirmPassword}
              onBlur={handleConfirmPasswordChange}
              placeholder="Confirme su contraseña"
              className={confirmPasswordError ? 'p-invalid input-error' : ''}
            />
            {confirmPasswordError && <small className="p-error">{confirmPasswordError}</small>}
          </div>

          <div className="p-field p-col-12 p-md-12">
            <Button type="submit" label="Registrarse" onClick={registerSubmit} />
          </div>

          <div className="p-field p-col-12 p-md-12">
            <Button 
              type="button" 
              label="Volver al Login" 
              className="p-button-secondary"
              onClick={() => navigate("/")} 
            />
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

export default RegisterForm;
