import React from "react";
import useFetch from "./UseFetch";
import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import { Button } from "@material-ui/core";
import { Navigate, useNavigate } from "react-router-dom";
import { Prev } from "react-bootstrap/esm/PageItem";


const ModifyUserData = () => {
  const { id } = useParams();
  
  const [client, setClient] = useState({});
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const navigate = useNavigate('');

  async function getClientById() {
    await axios.get("http://localhost:8080/getclientbyid/" + id)
    .then((response) => {
      setClient(response.data);
      setFirstName(response.data.firstName);
      setLastName(response.data.lastName);
      setEmail(response.data.email);
      setUsername(response.data.username);
      setPassword(response.data.password);
      // console.log(response.data.email);
    })
  }

  useEffect(() => {
    getClientById();
  }, [])

  async function handleUpdateData() {

    console.log("new first name: " + firstName);
    await axios.post("http://localhost:8080/updateClient/" + id, {
        firstName: firstName,
        lastName: lastName,
        username: username,
        password: password,
        email: email,
        // posts: client.posts,
        // postList: client.postList
    }).then(response => alert(response.data))

    navigate(-1);
  }

  return ( 
    <div className="modify-data">
      <h2>Edit Your Data</h2>
      { client && (
        <form onSubmit={handleUpdateData}>
          <label>First Name:</label>
          <input 
            type="text"
            defaultValue={firstName}
            onChange={(event) => setFirstName(event.target.value)}
          />
          <br></br>
          <label>Last Name:</label>
          <input
          type="text"
          defaultValue={ client.lastName }
          onChange={(event) => setLastName(event.target.value)}
          />
          <br></br>
          <label>Username:</label>
          <input
            type="text"
            defaultValue={ client.username }
            onChange={(event) => {
              setUsername(event.target.value)
              localStorage.setItem("username", client.username)
            }}
          />
          <br></br>   
          <label>Email:</label>
          <input
            type="text"
            defaultValue={ client.email }
            onChange={(event) => setEmail(event.target.value)}
          />
          <br></br>
          <label>Password:</label>
          <input
            type="text"
            readOnly
            defaultValue={ client.password }
            onChange={(event) => setPassword(event.target.value)}
          />
          <br></br>
          <button>Update</button>
        </form>
        
      ) }
    </div>
   );
}
 
export default ModifyUserData;