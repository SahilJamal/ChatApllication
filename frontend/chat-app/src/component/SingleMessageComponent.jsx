import React from "react";

const SingleMessageComponent = ({ message }) => {
  console.log(message);
  return (
    <div className="singleMessage">
      <h4>{message.content}</h4>
      <small>{message.timestamp}</small>
    </div>
  );
};

export default SingleMessageComponent;
