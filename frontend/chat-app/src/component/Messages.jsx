import React from "react";
import SingleMessageComponent from "./SingleMessageComponent";
import "./Messages.css";

const Messages = ({ messages }) => {
  return (
    <>
      {messages.map((message) => {
        const isSent = message.messageType === "SENT";
        return (
          <div
            className={isSent ? "sent" : "received"}
            key={message.id} // Use a unique identifier for the key
          >
            <SingleMessageComponent message={message} />
          </div>
        );
      })}
    </>
  );
};

export default Messages;
