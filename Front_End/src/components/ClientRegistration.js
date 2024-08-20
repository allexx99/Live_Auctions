import axios from "axios";
import { useState, useEffect } from "react";
import React from "react";
import { useNavigate } from "react-router-dom";

import { useFormik } from 'formik';
import * as Yup from 'yup';

function ClientRegistration() {

  // create states
  // const[firstName, setFirstName] = useState('');
  // const[lastName, setLastName] = useState('');
  // const[username, setUsername] = useState('');
  // const[password, setPassword] = useState('');
  // const[email, setEmail] = useState('');

  const navigate = useNavigate('');

  // after filling the registration form and press "Register" button, these states will manage the variables firstName, lastName etc

  // NOW let's save these variables above by implementing the save function
//   async function save(event) {
//     // console.log(firstName, lastName, username, password);
//     event.preventDefault();

//     //try {
      
//       await axios.post("http://localhost:8080/saveClient",
//       {
//         firstName: firstName,
//         lastName: lastName,
//         username: username,
//         password: password,
//         postList: [],
//         // commentList: [],
//         posts: [],
//         email: email
//       }).then(response => alert(response.data)); // metoda din backend

      
// //         alert("Client Registration Successful");
//         // setFirstName("");
//         // setLastName("");
//         // setUsername("");
//         // setPassword("");

//         navigate("/feed");

//       // } catch (error) {
//       //   alert("Client Registration Failed");
//   //  }
//   }

  // useEffect (() => {
  //   console.log("pl calului");
  // }, [firstName])

  const formik = useFormik({
    initialValues: {
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      password: '',
      confirmPassword: '',
    },
    validationSchema: Yup.object({
      firstName: Yup.string().required('First name is required'),
      lastName: Yup.string().required('Last name is required'),
      email: Yup.string().email('Invalid email address').required('Email is required'),
      username: Yup.string().required('Username is required'),
      password: Yup.string()
        .required('Password is required')
        .matches(
          /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/,
          'Password must contain at least 8 characters, including uppercase, lowercase, and numbers'
        ),
      confirmPassword: Yup.string()
        .oneOf([Yup.ref('password'), null], 'Passwords must match')
        .required('Confirm password is required'),
    }),
    onSubmit: (values) => {
      // Handle form submission
      // console.log(values);

      axios.post("http://localhost:8080/saveClient",
      {
        firstName: values.firstName,
        lastName: values.lastName,
        username: values.username,
        password: values.password,
        postList: [],
        // commentList: [],
        posts: [],
        email: values.email
      }).then(response => alert(response.data)); // metoda din backend

      navigate("/feed");
    },
  });

  return (
    <div className="register">
      <h1>Register</h1>
      <br></br>
      {/* <form onSubmit={save}>

          <label>First Name</label>
          <input type="text" placeholder="Enter first name..."
          value={firstName} // <-- this is set below 
          onChange={(event) => setFirstName(event.target.value)} // onChange uses setFirstName function to set the "firstName" value from above
          />

          <label>Last Name</label>
          <input type="text" placeholder="Enter last name..."
          value={lastName}
          onChange={(event) => setLastName(event.target.value)}
          />

          <label>Email</label>
          <input type="text" placeholder="Enter email..."
          value={email}
          onChange={(event) => setEmail(event.target.value)}
          />
        
          <label>Username</label>
          <input type="text" placeholder="Enter username..."
          value={username}
          onChange={(event) => setUsername(event.target.value)}
          />

          <label>Password</label>
          <input type="text" placeholder="Enter password..."
          value={password}
          onChange={(event) => setPassword(event.target.value)}
          />

        <button>Register</button>

      </form> */}

    <form onSubmit={formik.handleSubmit} className="form-container">
      <div>
        <label htmlFor="firstName">First Name:</label>
        <input
          type="text"
          id="firstName"
          name="firstName"
          value={formik.values.firstName}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.firstName && formik.errors.firstName ? (
          <div className="error-message">{formik.errors.firstName}</div>
        ) : null}
      </div>
      <div>
        <label htmlFor="lastName">Last Name:</label>
        <input
          type="text"
          id="lastName"
          name="lastName"
          value={formik.values.lastName}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.lastName && formik.errors.lastName ? (
          <div className="error-message">{formik.errors.lastName}</div>
        ) : null}
      </div>
      <div>
        <label htmlFor="email">Email:</label>
        <input
          type="email"
          id="email"
          name="email"
          value={formik.values.email}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.email && formik.errors.email ? (
          <div className="error-message">{formik.errors.email}</div>
        ) : null}
      </div>
      <div>
        <label htmlFor="username">Username:</label>
        <input
          type="text"
          id="username"
          name="username"
          value={formik.values.username}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.username && formik.errors.username ? (
          <div className="error-message">{formik.errors.username}</div>
        ) : null}
      </div>
      <div>
        <label htmlFor="password">Password:</label>
        <input
          type="password"
          id="password"
          name="password"
          value={formik.values.password}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.password && formik.errors.password ? (
          <div className="error-message">{formik.errors.password}</div>
        ) : null}
      </div>
      <div>
        <label htmlFor="confirmPassword">Confirm Password:</label>
        <input
          type="password"
          id="confirmPassword"
          name="confirmPassword"
          value={formik.values.confirmPassword}
          onChange={formik.handleChange}
          onBlur={formik.handleBlur}
        />
        {formik.touched.confirmPassword && formik.errors.confirmPassword ? (
          <div className="error-message">{formik.errors.confirmPassword}</div>
        ) : null}
      </div>
      <button type="submit" disabled={!formik.isValid}>Register</button>
    </form>
      
    </div>
  );
}

export default ClientRegistration;
