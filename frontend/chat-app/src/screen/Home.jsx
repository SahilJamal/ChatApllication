import React, { useEffect, useState } from "react";
import auth from "../service/auth";
import { useNavigate } from "react-router-dom";
import UserList from "../component/UserList";
import Messages from "../component/Messages";
import "./homestyle.css";

const Home = () => {
  const navigate = useNavigate();
  const [message, setMessage] = useState("");
  const [messages, setMessages] = useState([]);
  const [usernames, setUsernames] = useState([]);
  const [receivername, setreceivername] = useState("");
  const [error, setError] = useState(false);

  const handleLogout = () => {
    auth.logout();
    navigate("/login");
  };

  // Fetching users
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        setError(false); // Reset error state
        const data = await auth.getAllUsers();
        setUsernames(data.data.name); // Assuming data.data.name contains the array of names
      } catch (err) {
        console.error(err);
        setError(true);
      }
    };

    fetchUsers();
  }, []);

  // Fetching messages for the selected user
  useEffect(() => {
    const fetchMessages = async () => {
      try {
        const body = {
          receiverName: receivername,
        };

        const data = await auth.getAllMessages(body);
        const response = data.data;
        setMessages(response);
      } catch (err) {
        console.error(err);
      }
    };

    if (receivername) {
      fetchMessages();
    }
  }, [receivername]);

  const handleMessageSaveSubmit = async () => {
    try {
      const body = {
        message: message,
        receiverName: receivername,
      };

      if (receivername) {
        const data = await auth.sendMessage(body);
        console.log(data);
      }

      const fetchMessages = async () => {
        try {
          const body = {
            receiverName: receivername,
          };

          const data = await auth.getAllMessages(body);
          const response = data.data;
          setMessages(response);
        } catch (err) {
          console.error(err);
        }
      };

      if (receivername) {
        fetchMessages();
      }

      setMessage(""); // Clear message after sending
    } catch (err) {
      console.error(err);
    }
  };

  // Loading state
  if (error) {
    return <div>Loading...</div>;
  }

  const self = localStorage.getItem("name");

  return (
    <div>
      <nav>
        <h2>Home</h2>
        <button onClick={handleLogout}>Logout</button>
      </nav>
      <div className="body">
        <div className="sidebar">
          <UserList
            names={usernames}
            self={self}
            setreceivername={setreceivername}
          />
        </div>
        <div className="messagebody">
          <Messages messages={messages} />
          <div className="sendingBox">
            <input
              type="text"
              placeholder="Type a message..."
              value={message}
              onChange={(e) => setMessage(e.target.value)}
            />
            <button onClick={handleMessageSaveSubmit}>Send</button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
