import axios from "axios";

const API_URL = "http://localhost:9090"; // Replace with your actual API URL

const encodeCredentials = (token) => {
  return token;
};

// Login method for Basic Authentication
const login = async (credentials) => {
  const response = await axios.post(`${API_URL}/auth/signin`, credentials);

  console.log(response);

  const encodedCredentials = encodeCredentials(response.data);

  if (response.status === 200) {
    localStorage.setItem("authData", encodedCredentials);
    localStorage.setItem("name", credentials.userName);
  }

  return response.data;
};

const signup = async (user) => {
  return await axios.post(`${API_URL}/auth/signup`, user);
};

const logout = () => {
  localStorage.removeItem("authData");
};

const isLoggedIn = () => {
  return !!localStorage.getItem("authData");
};

const getAllUsers = async () => {
  const authData = localStorage.getItem("authData");
  return await axios.get(`${API_URL}/chat/getallusers`, {
    headers: {
      Authorization: authData,
    },
  });
};

// const getAllUsers = async () => {
//   try {
//     const authData = localStorage.getItem("authData"); // Retrieve token from localStorage

//     const response = await axios.get(`${API_URL}/chat/getallusers`, {
//       headers: {
//         Authorization: authData, // Pass the token in the headers
//       },
//     });

//     return response.data; // Return the data from the response
//   } catch (err) {
//     console.error("Error fetching users:", err); // Better error logging
//     return null; // Return null or handle the error as needed
//   }
// };
const getAllMessages = async (requestBody) => {
  const authData = localStorage.getItem("authData");
  return await axios.post(`${API_URL}/chat/allmessages`, requestBody, {
    headers: {
      Authorization: authData,
    },
  });
};

const sendMessage = async (requestBody) => {
  const authData = localStorage.getItem("authData");
  return await axios.post(`${API_URL}/chat/send`, requestBody, {
    headers: {
      Authorization: authData, // Ensure this is in the correct format
    },
  });
};

export default {
  login,
  signup,
  logout,
  isLoggedIn,
  getAllUsers,
  getAllMessages,
  sendMessage,
};
