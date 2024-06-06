import React from "react";
import "./login.css";
import { Link } from "react-router-dom";

const Login = () => {
  return (
    <div className="addUser">
      <h3>Zaloguj się</h3>
      <form className="addUserForm">
        <div className="inputGroup">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            name="email"
            autoComplete="off"
            placeholder="Enter your Email"
          />
          <label htmlFor="Password">Hasło:</label>
          <input
            type="password"
            id="password"
            name="password"
            autoComplete="off"
            placeholder="Enter your Password"
          />
          <button type="submit" class="btn btn-primary">
            Zaloguj się
          </button>
        </div>
      </form>
      <div className="login">
        <p>Nie masz konta? </p>
        <Link to="/" type="submit" class="btn btn-success">
          Zarejestruj się
        </Link>
      </div>
    </div>
  );
};

export default Login;