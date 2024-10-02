import React from "react";

const UserList = ({ names, self, setreceivername }) => {
  // If names are empty, show a message
  if (!names || names.length === 0) {
    return <div>No Users</div>;
  }

  // Proper function declaration
  function handleChange(name) {
    setreceivername(name); // Set receiver name when clicked
  }

  return (
    <>
      {names.map((user) => (
        <div className="userNames" key={user}>
          {user !== self && ( // Conditional rendering
            <button
              onClick={() => handleChange(user)}
              style={{ width: "200px", margin: "10px" }}
            >
              {user}
            </button>
          )}
        </div>
      ))}
    </>
  );
};

export default UserList;
