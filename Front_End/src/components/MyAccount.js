import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import useFetch from "./UseFetch";
import PostList from "./PostList";
import axios from "axios";
import { Link } from "react-router-dom";
import {Button} from '@material-ui/core'; //importing material ui component
import { saveAs } from 'file-saver';
import { Navigate, useNavigate } from "react-router-dom";
import NotificationsComponent from './NotificationsComponent';


const MyAccount = () => {
  const username = localStorage.getItem("username");
  const { id } = useParams();
  const navigate = useNavigate('');
  // console.log(id);
  const { data: client, isPending, error } = useFetch("http://localhost:8080/getclientbyid/" + id);
  // const { data: posts, isPending: isPendingPosts, error: errorPosts } = useFetch("http://localhost:8080/getpostsbyclient/" + id);

  // const myWatchlist = useFetch("http://localhost:8080/yourwatchlist/" + id);
  const [myWatchlist, setWatchlist] = useState([]);
  //var myWatchlist = new Array();
  async function getMyWatchlist() {
    await axios.get("http://localhost:8080/yourwatchlist/" + id)
    .then((response) => {
      // myWatchlist = response.data;
      setWatchlist(response.data);
      // myWatchlist.forEach(printMyWatchlist);
    });
  }

  // function printMyWatchlist(value) {
  //   console.log(value);
  // }

  useEffect(() => {
    getMyWatchlist();
  }, [])

  async function exportData(fileType, id) {
    // await axios.get("/export/" + id , { params: { fileType: fileType }, responseType: 'text' })
    await axios.get("http://localhost:8080/export/" + id + "/" + fileType)
      .then(res => {
                  let typeForBlob = fileType === 'txt' ? 'text/plain;charset=utf-8' : 'text/xml;charset=utf-8';
                  let blob = new Blob([res.data], { type: typeForBlob });
                  //saveAs(blob, "client-data." + fileType);
                  saveAs(blob, localStorage.getItem("username") + "." + fileType);
                  console.log(blob);
      
              }) .catch(error => {
                console.log(error);
            })
  }

  async function navigateToUserEditPage() {
    navigate("/modifydata/" + id);
  }

  return ( 
    <div className="myaccount">
      <h2>My Account</h2>
      {/* <NotificationsComponent /> */}
      <br></br>
      {username !== "" ? <Button onClick={() => exportData("xml",localStorage.getItem("clientId"))}>Generate XML</Button> : <br></br>}
      { client && (
        <article>
          <h4>First Name: { client.firstName }</h4>
          <h4>Last Name: { client.lastName }</h4>
          <h4>Email: { client.email }</h4>
          <h4>Username: { client.username }</h4>
          <Button onClick={navigateToUserEditPage}>Edit your personal information</Button>
          <br></br>
          <h3>Your posts</h3>
          { client.postList && <PostList posts={client.postList} />}
          <br></br>
          <h3>Your Watchlist</h3>
          { myWatchlist && <PostList posts={myWatchlist} />}
        </article>
        ) }
    </div>
   );
}
 
export default MyAccount;