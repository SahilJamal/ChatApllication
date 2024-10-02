import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import auth from "../service/auth";
import "./Signup.css"; // Import the CSS file

const Signup = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSignup = async (e) => {
    e.preventDefault();
    const user = {
      userName: username,
      email: email,
      password: password,
    };
    try {
      await auth.signup(user);
      navigate("/login"); // Redirect to login after successful signup
    } catch (error) {
      console.error("Sign up failed", error);
    }
  };

  const redirectLogIn = () => {
    navigate("/login");
  };

  return (
    <div className="signup-container">
      <div className="signup-form">
        <h2>Sign Up</h2>
        <form onSubmit={handleSignup}>
          <input
            type="text"
            placeholder="Username"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button type="submit">Sign Up</button>
        </form>
        <button
          type="button"
          className="redirect-button"
          onClick={redirectLogIn}
        >
          Log In
        </button>
      </div>
    </div>
  );
};

export default Signup;
