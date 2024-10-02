import logo from "./logo.svg";
import "./App.css";
import {
  BrowserRouter,
  Navigate,
  Route,
  Router,
  Routes,
} from "react-router-dom";
import Login from "./screen/Login";
import Signup from "./screen/Signup";
import ProtectedRoute from "./screen/ProtectedRoute";
import Home from "./screen/Home";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="/" element={<ProtectedRoute component={Home} />} />
        <Route path="*" element={<Navigate to="/login" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
