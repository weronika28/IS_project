import React from "react";
import "./signup.css";
import { Link } from "react-router-dom";

const Signup = () => {
  return (
    <div className="addUser">
      <h3>Zarejestruj się</h3>
      <form className="addUserForm">
        <div className="inputGroup">
          <label htmlFor="name">Imie:</label>
          <input
            type="text"
            id="name"
            name="name"
            autoComplete="off"
            placeholder="Enter your name"
          />
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
            placeholder="Enter Password"
          />
          <button type="submit" class="btn btn-success">
            Zarejestruj się
          </button>
        </div>
      </form>
      <div className="login">
        <p>Masz już konto? </p>
        <Link to="/login" type="submit" class="btn btn-primary">
          Zaloguj się
        </Link>
      </div>
    </div>
  );
};

export default Signup;