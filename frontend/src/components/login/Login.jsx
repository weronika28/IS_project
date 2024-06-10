import React, { useEffect, useState, useRef } from "react";
import "./login.css";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../../auth/AuthContext";
import { STORAGE_KEY, axios } from "../../axios/axios";

const Login = () => {
  const [error, setError] = useState("")
  const [auth, setAuth] = useAuth();
  const usernameRef = useRef();
  const passwordRef = useRef();
  const navigate = useNavigate()

  async function onSubmit(e) {
    e.preventDefault();
    // todo add error messages and validation
    const username = usernameRef.current.value
    const password = passwordRef.current.value

    if(!username || !password) {
      setError("Błąd")
      return
    }

    const response = await axios.post("/api/login", {username, password})

    if(response.status == 400) {
      setError("Nieprawidłowa nazwa")
      return;
    }

    setAuth(response.data.username)
    localStorage.setItem(STORAGE_KEY, response.data.token)
    navigate("/")
  }

  useEffect(() => {
    if(auth)
      navigate("/")
  }, [auth])

  return (
    <div className="addUser">
      <h3>Zaloguj się</h3>
      <form className="addUserForm" onSubmit={onSubmit}>
        <div className="inputGroup">
          <label htmlFor="username">Nazwa użytkownika:</label>
          <input
            ref={usernameRef}
            type="username"
            id="username"
            name="username"
            autoComplete="off"
            placeholder="Wprowadź nazwę użytkownika"
          />
          <label htmlFor="Password">Hasło:</label>
          <input
            ref={passwordRef}
            type="password"
            id="password"
            name="password"
            autoComplete="off"
            placeholder="Wprowadź hasło"
          />
          {error}
          <button type="submit" className="btn btn-primary">
            Zaloguj się
          </button>
        </div>
      </form>
      <div className="login">
        <p>Nie masz konta? </p>
        <Link to="/register" type="submit" className="btn btn-success">
          Zarejestruj się
        </Link>
      </div>
    </div>
  );
};

export default Login;