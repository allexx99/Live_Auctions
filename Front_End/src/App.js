import 'bootstrap/dist/css/bootstrap.min.css';
import ClientRegistration from './components/ClientRegistration';
import Navbar from './components/Navbar';
import Feed from './components/Feed';
import React, { useState } from "react";
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'; 
import ClientLogin from './components/ClientLogin';
import PostDetails from './components/PostDetails';
import NewPost from './components/NewPost';
import MyAccount from './components/MyAccount';
import ModifyUserData from './components/ModifyUserData';

function App() {

  return (
    <Router>
      <div className="App">
          <Navbar />
          <div className="content">
            <Routes>
              <Route path="/feed" element={<Feed/>} />
              <Route path="/register" element={<ClientRegistration/>} />
              <Route path="/login" element={<ClientLogin/>} />
              <Route path="/posts/:id" element={<PostDetails/>} />
              <Route path="/feed/new" element={<NewPost/>} />
              <Route path="/myaccount/:id" element={<MyAccount/>} /> 
              <Route path="/modifydata/:id" element={<ModifyUserData/>} />
            </Routes>
          </div>
      </div>
    </Router>
  );
}

export default App;
