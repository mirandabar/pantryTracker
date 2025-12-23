import React, { createContext, useContext, useState, useEffect } from 'react';
import { getUserInfoApi } from '../api/userInfoApi';

const UserContext = createContext();

export function UserProvider({ children }) {
  const [currentUser, setCurrentUser] = useState("Usuario");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    async function loadUser() {
      const token = sessionStorage.getItem("token");
      const data = await getUserInfoApi(token);
      if (data && data.userName) {
        setCurrentUser(data.userName);
      }
      setLoading(false);
    }

    loadUser();
  }, []);

  return (
    <UserContext.Provider value={{ currentUser, loading }}>
      {children}
    </UserContext.Provider>
  );
}

export function useUser() {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUser debe usarse dentro de un UserProvider');
  }
  return context;
}
