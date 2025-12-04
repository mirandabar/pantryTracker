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