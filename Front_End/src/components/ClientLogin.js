import axios from "axios";
import { useState } from "react";
import React from "react";
import { useNavigate } from "react-router-dom";

const ClientLogin = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate("");

  async function login(event) {
    event.preventDefault();

    await axios
      .post("http://localhost:8080/login", {
        username: username,
        password: password,
      })
      .then((response) => {
        localStorage.setItem("username", response.data.username);
        localStorage.setItem("clientId", response.data.id);
        console.log("is in login ", response.data);
        // console.log("logged in with id " + localStorage.getItem("clientId"));
        alert(response.data.username);
      });

      // await axios
      // .post("http://localhost:8080/loginId", {
      //   username: username,
      //   password: password,
      // })
      // .then((response) => {
      //   localStorage.setItem("clientId", response.data)
      //   console.log("logged in with id " + localStorage.getItem("clientId"));
      //   alert(response.data.username);
      // });

    // alert("Client Registration Successful");
    navigate("/feed");
  }

  return (
    <div className="login">
      <h1>Login</h1>

      <form onSubmit={login}>
        <label>Username</label>
        <input
          type="text"
          placeholder="Enter username..."
          value={username} // <-- this is set below
          onChange={(event) => setUsername(event.target.value)} // onChange uses setFirstName function to set the "firstName" value from above
        />

        <label>Password</label>
        <input
          type="text"
          placeholder="Enter password..."
          value={password}
          onChange={(event) => setPassword(event.target.value)}
        />

        <button>Login</button>
      </form>
    </div>
  );
};

export default ClientLogin;
