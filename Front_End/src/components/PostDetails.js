import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import useFetch from "./UseFetch";
import axios from "axios";
import {Button} from '@material-ui/core'; //importing material ui component
import { Navigate, useNavigate } from "react-router-dom";


const PostDetails = () => {
  // take the id parameter form path
  const { id } = useParams();
  const clientId = localStorage.getItem("clientId");
  const [myPost, setMyPost] = useState('');

  const navigate = useNavigate('');

  const { data: post, isPending, error } = useFetch("http://localhost:8080/posts/" + id);
  const [newBid, setNewBid] = useState(0);
  const [newTitle, setNewTitle] = useState('');
  const [newDescription ,setNewDescription] = useState('');
  const [newCategory, setNewCategory] = useState('');

  async function handleWatchlist() {
    console.log(localStorage.getItem("username") + " added post with id " + id + " to his wathclist!");

    await axios.post("http://localhost:8080/saveToMyWatchlist/" + localStorage.getItem("username") + "/" + id).then(response => alert(response.data));
  }

  async function handleInDeleteFromWathclist() {
    
    // this only checks if the item is in the watchlist
    // await axios.post("http://localhost:8080/checkInWatchlist/" + localStorage.getItem("username") + "/" + id).then((response) => {
    // if(response.data === true) {
    //   console.log(localStorage.getItem("username") + " removed post with id " + id + " from his wathclist!");
    // }  else {
    //   console.log(localStorage.getItem("username") + " already removed post with id " + id + " from his wathclist!");
    // }
    // alert(response.data)});

    // delete item from watchlist
    await axios.delete("http://localhost:8080/deleteFromWatchlist/" + localStorage.getItem("username") + "/" + id).then(response => alert(response.data));
  }

  async function handleBidding() {
    //when you submit this form take the value of the new bid and pass it to backend. There change the price attribute to that specific item
    await axios.post("http://localhost:8080/newPrice/" + id + "/" + newBid).then(response => alert(response.data));

    // this doesn't help
    // console.log(localStorage.getItem("username") + "'s new bid is " + newBid + " for item with id " + id + " and name " + post.title);
  }

  async function handleIsMyPost() {
    
    await axios.get("http://localhost:8080/ismypost/" + clientId + "/" + id)
    .then((response) => {
      setMyPost(response.data);
      console.log(myPost);
    })
  }

  useEffect(() => {
    if(localStorage.getItem("username") !== "") {
      console.log("Checking my post...");
      handleIsMyPost();
    }
  }, [myPost])

  async function handleDeleteMyPost() {
    console.log(localStorage.getItem("username") + " is deleting item with id " + id + " and title " + post.title);
    await axios.delete("http://localhost:8080/deleteMyPost/" + clientId + "/" + id).then(response => alert(response.data));
    navigate("/feed");
  }

  async function handleTitle() {
    await axios.post("http://localhost:8080/newTitle/" + id + "/" + newTitle);
  }

  async function handleDescription() {
    await axios.post("http://localhost:8080/newDescription/" + id + "/" + newDescription);
  }

  async function handleCategory() {
    await axios.post("http://localhost:8080/newCategory/" + id + "/" + newCategory);
  }

  return ( 
    <div className="blog-details">
      { error && <div>{ error }</div> }
      { isPending && <div>Loading...</div> }
      { localStorage.getItem("username") && (myPost) ? <Button onClick={handleDeleteMyPost}>Delete your post</Button> : <br></br> }
      { post && (
        <article>
          {localStorage.getItem("username") !== "" ? <Button onClick={handleWatchlist}>Add to your watchlist</Button> : <br></br>}
          <br></br>
          {localStorage.getItem("username") !== "" ? <Button onClick={handleInDeleteFromWathclist}>Delete from watchlist</Button> : <br></br>}
          <h2>{ post.title }</h2>
          <p>{ post.description }</p>
          <p>Category: { post.category }</p>
          <p>Price: { post.bid }</p>
        </article>

      )}
      
      {localStorage.getItem("username") !== "" ? <form onSubmit={handleBidding}>
        <label>Your bid: </label>
        <input 
          type="number"
          min="1"
          value={newBid}
          onChange={(e) => setNewBid(e.target.value)}
        />
        <button>Bid</button>
      </form> : <br></br>}
      <div>
        
        <h2>Edit Section</h2>
        { localStorage.getItem("username") && (myPost) ? <form onSubmit={handleTitle}>
          <label>New title: </label>
          <input
            type="text"
            value={newTitle}
            onChange={(e) => setNewTitle(e.target.value)}
            />
          <button>Update</button>
        </form> : <br></br> }

        { localStorage.getItem("username") && (myPost) ? <form onSubmit={handleDescription}>
          <label>New description: </label>
          <input
            type="text"
            value={newDescription}
            onChange={(e) => setNewDescription(e.target.value)}
            />
          <button>Update</button>
        </form> : <br></br> }

        { localStorage.getItem("username") && (myPost) ? <form onSubmit={handleCategory}>
        <label>Category:</label>
        <select
          value={newCategory}
          onChange={(e) => setNewCategory(e.target.value)}
        >
          <option value="toys">toys</option>
          <option value="cars">cars</option>
          <option value="furniture">furniture</option>
          <option value="antiquity">antiquity</option>
          <option value="watches">watches</option>
        </select>
          <button>Update</button>
        </form> : <br></br> }

      </div>
    </div>
   );
}
 
export default PostDetails;