import { validateEmail, validatePassword } from './validators.js';

export function setEmail(email) {
  const isValid = validateEmail(email);
  return {
    value: email,
    isValid: isValid,
    error: isValid ? null : 'Email format is invalid'
  };
}

export function setPassword(password) {
  const isValid = validatePassword(password);
  return {
    value: password,
    isValid: isValid,
    error: isValid ? null : 'Password must be at least 6 characters'
  };
}

export function setUserName(userName) {
  const isValid = userName && userName.trim().length > 0;
  return {
    value: userName,
    isValid: isValid,
    error: isValid ? null : 'Username cannot be empty'
  };
}

export function setConfirmPassword(confirmPassword, password) {
  const isValid = confirmPassword === password;
  return {
    value: confirmPassword,
    isValid: isValid,
    error: isValid ? null : 'Passwords do not match'
  };
}