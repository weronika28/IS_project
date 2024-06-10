import React, {useRef, useState, useEffect} from "react";
import "./signup.css";
import { Link } from "react-router-dom";
import { STORAGE_KEY, axios } from "../../axios/axios";
import { useAuth } from "../../auth/AuthContext";
import { useNavigate } from "react-router-dom";

const Signup = () => {
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

    const response = await axios.post("/api/register", {username, password})

    if(response.status == 409) {
      setError("Nazwa uzytkownika jest zajęta")
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
      <h3>Zarejestruj się</h3>
      <form className="addUserForm" onSubmit={onSubmit}>
        <div className="inputGroup">
          <label htmlFor="username">Nazwa użytkownika:</label>
          <input
            ref={usernameRef}
            type="text"
            id="username"
            name="username"
            autoComplete="off"
            placeholder="Wprowadź nazwę"
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
          <button type="submit" className="btn btn-success">
            Zarejestruj się
          </button>
        </div>
      </form>
      <div className="login">
        <p>Masz już konto? </p>
        <Link to="/login" type="submit" className="btn btn-primary">
          Zaloguj się
        </Link>
      </div>
    </div>
  );
};

export default Signup;