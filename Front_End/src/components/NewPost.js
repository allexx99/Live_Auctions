import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import * as SockJS from 'sockjs-client';
import * as Stomp from 'stompjs';

const NewPost = () => {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [bid, setBid] = useState('');
  const [category, setCategory] = useState('toys');
  const navigate = useNavigate(''); 

  async function handleSubmit(event) {
    event.preventDefault(); // this privents for page being refreshed 
    
    const post = {title, description, category, bid};

    // await axios.post("http://localhost:8080/savePost",
    //   {
    //     title: title,
    //     description: description,
    //     category: category,
    //     bid: bid,
    //     commentList: []
    //   }).then((response) => {
    //     localStorage.setItem("postId", response.data);
    //     console.log("post with id " + localStorage.getItem("postId") + " is saved in local storage");
    //     alert(response.data)
    //   });

      setTitle('')
      setDescription('')
      setBid('')
      setCategory('')

      await axios.post("http://localhost:8080/saveToMyPosts/" + localStorage.getItem("username"), {
        title: title,
        description: description,
        category: category,
        bid: bid,
        commentList: []
      })
      //.then(response => alert(respones.data));      
      navigate('/feed');

      connect();
    }

  function connect() {
    console.log("In Connect");
    const URL = "http://localhost:8080/socket";
    const websocket = new SockJS(URL);
    const stompClient = Stomp.over(websocket);
    stompClient.connect({}, frame => {
        stompClient.subscribe("/topic/socket/newPost", notification => {
            let message = notification.body;
            console.log(message);
            alert(message);

        })
    })
  }
    

  return ( 
    <div className="new-post">
      <h1>New Post</h1>
      <form onSubmit={handleSubmit}>
        <label>Post title:</label>
        <input 
          type="text" 
          required 
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        
        <label>Description:</label>
        <textarea 
          required 
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />

        <label>Category:</label>
        <select
          value={category}
          onChange={(e) => setCategory(e.target.value)}
        >
          <option value="toys">toys</option>
          <option value="cars">cars</option>
          <option value="furniture">furniture</option>
          <option value="antiquity">antiquity</option>
          <option value="watches">watches</option>
        </select>

        <label>Starting bid:</label>
        <input 
          type="number" 
          required
          min="1"
          value={bid}
          onChange={(e) => setBid(e.target.value)}
        />

        <button>Add Post</button>
      </form>
    </div>
   );
}
 
export default NewPost;