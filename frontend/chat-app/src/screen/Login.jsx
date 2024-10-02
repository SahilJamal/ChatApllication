import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import auth from "../service/auth";
import "./Login.css"; // Import the CSS file

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const body = {
      userName: username,
      password: password,
    };
    try {
      await auth.login(body);
      navigate("/"); // Redirect to home page on success
    } catch (err) {
      setError("Invalid credentials");
    }
  };

  const redirectSignIn = () => {
    navigate("/signup");
  };

  return (
    <div className="login-container">
      <div className="login-form">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Login</button>
        </form>
        {error && <p className="error-message">{error}</p>}
        <button
          type="button"
          className="redirect-button"
          onClick={redirectSignIn}
        >
          Register
        </button>
      </div>
    </div>
  );
};

export default Login;
