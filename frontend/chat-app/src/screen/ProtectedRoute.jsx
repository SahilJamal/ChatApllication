import React from "react";
import { Navigate } from "react-router-dom";
import auth from "../service/auth";

const ProtectedRoute = ({ component: Component }) => {
  return auth.isLoggedIn() ? <Component /> : <Navigate to="/login" />;
};

export default ProtectedRoute;
