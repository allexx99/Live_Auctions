import axios from "axios";
import { useState, useEffect } from "react";
import React from "react";
import PostList from "./PostList";
import useFetch from "./UseFetch";
import { Link, Navigate, useNavigate } from "react-router-dom";
import { saveAs } from 'file-saver';
import {Button} from '@material-ui/core'; //importing material ui component

function Feed() {
  const username = localStorage.getItem("username");
  const navigate = useNavigate('');
  console.log(username);

  // const { data: posts, isPending, error } = useFetch(
  //   "http://localhost:8080/posts"
  // )

  const [posts, setPosts] = useState([]);
  const [filteredPosts, setFilteredPosts] = useState([]);
  const [filterValues, setFilterValues] = useState({
    title: '',
    minPrize: '',
    maxPrize: '',
    category: ''
  });

  const [sortOrder, setSortOrder] = useState('asc');

  useEffect(() => {
    // Fetch the posts from an API or any data source
    axios.get('http://localhost:8080/posts')
      .then(response => {
        setPosts(response.data);
        setFilteredPosts(response.data);
      })
      .catch(error => {
        console.log(error);
      });
  }, []);

  // When an input field's value changes, this function is called with the event object. It extracts the name and value from the event's target (the input field that triggered the event) and uses them to update the filterValues state.

  // The function uses the setFilterValues function, provided by the useState hook, to update the state. It takes the previous values of filterValues using the functional form of setState and spreads them into a new object. Then, it updates the specific property indicated by the name with the new value.

  // This allows the filter values to be updated dynamically as the user types or selects options in the input fields, triggering a re-render of the component with the updated filter values.
  const handleFilterChange = event => {
    const { name, value } = event.target;
    setFilterValues(prevValues => ({
      ...prevValues,
      [name]: value
    }));
  };

  // When you see ...filteredPosts, it means that the elements of the filteredPosts array are being spread out into a new array. This new array will contain the same elements as filteredPosts, maintaining the same order.

  // The purpose of using the spread operator in this code is to create a new array that is a copy of filteredPosts.
  const handleSortAscending = () => {
    const sortedPosts = [...filteredPosts];
    sortedPosts.sort((a, b) => a.bid - b.bid);
    setFilteredPosts(sortedPosts);
    setSortOrder('asc');
  };

  const handleSortDescending = () => {
    const sortedPosts = [...filteredPosts];
    sortedPosts.sort((a, b) => b.bid - a.bid);
    setFilteredPosts(sortedPosts);
    setSortOrder('desc');
  };

  // The filter method is used to iterate over each post in the posts array and apply the filtering conditions.
  //    The title of each post is checked to see if it includes the filterValues.title, regardless of the case.
  //    If filterValues.minPrize is provided, the post's prize must be greater than or equal to filterValues.minPrize to pass the filter. If filterValues.minPrize is not provided, this condition is ignored.
  //    If filterValues.maxPrize is provided, the post's prize must be less than or equal to filterValues.maxPrize to pass the filter. If filterValues.maxPrize is not provided, this condition is ignored.
  //    The category of each post is checked to see if it includes the filterValues.category, regardless of the case.
  useEffect(() => {
    // Filter the posts based on the filterValues
    const filtered = posts.filter(post =>
      post.title.toLowerCase().includes(filterValues.title.toLowerCase()) &&
      (!filterValues.minPrize || post.bid >= filterValues.minPrize) &&
      (!filterValues.maxPrize || post.bid <= filterValues.maxPrize) &&
      post.category.toLowerCase().includes(filterValues.category.toLowerCase())
    );
    setFilteredPosts(filtered);
  }, [filterValues, posts]);

  // const [posts, setPosts] = useState([]);

  // async function fetchData() {
  //   try {
  //     const response = await axios.get("http://localhost:8080/posts")
  //     setPosts(response.data)
  //   } catch (error) {
  //     console.error(error);
  //   }
  // }

  // useEffect(() => {
  //   fetchData();
  // },[])

  

    async function handleLogout(event) {
    
      await axios.post("http://localhost:8080/logout/" + localStorage.getItem("clientId"))
        .then(response => alert(response.data));

      localStorage.setItem("username", "");
      localStorage.setItem("clientId", "");
      navigate("/feed");

      window.location.reload(false);
    }

    // ONLINE USERS
    async function handleOnlineUsers() {
      await axios.get("http://localhost:8080/onlineusers")
        .then((response) => {
          // setNbOfClientsOnline(response.data);
          localStorage.setItem("nbOfUsersOnline", response.data);
        })
    }

    useEffect(() => {
      handleOnlineUsers();
    },[]) 

  return (
    <div className="feed">
      {/* {error && <div>{error}</div>}
      {isPending && <div>Loading...</div>} */}
      <p> Users online: {localStorage.getItem("nbOfUsersOnline")} </p>
      {username !== "" ? <h3>Welcome, {username}</h3> : <br></br>}

      <br></br>
      {username !== "" ? <Link to={`/myaccount/${localStorage.getItem("clientId")}`}>My Account</Link> : <br></br>}
      {username !== "" ? (
        <Button
          // onClick={() => {
          //   localStorage.setItem("username", "");
          //   localStorage.setItem("clientId", "");
          //   navigate("/feed");
          // }}
          onClick={handleLogout}
        >
          Log out
        </Button>
      ) : (
        <br></br>
      )}
      
      {username !== "" ? <Link to="/feed/new">New Auction</Link> : <br></br>}

      <br></br>
      <br></br>
      <h3>Filter Posts</h3>
      <div>
        <input
          type="text"
          placeholder="Search by title"
          name="title"
          value={filterValues.title}
          onChange={handleFilterChange}
        />
        <input
          type="text"
          placeholder="Search by category"
          name="category"
          value={filterValues.category}
          onChange={handleFilterChange}
        />
        <br></br>
        <input
          type="number"
          placeholder="Min Prize"
          name="minPrize"
          value={filterValues.minPrize}
          onChange={handleFilterChange}
        />
        <input
          type="number"
          placeholder="Max Prize"
          name="maxPrize"
          value={filterValues.maxPrize}
          onChange={handleFilterChange}
        />
      </div>
      <br></br>
      <div>
        <Button onClick={handleSortAscending}>
          Sort Ascending
        </Button>
        <Button onClick={handleSortDescending}>
          Sort Descending
        </Button>
      </div>
      
      {filteredPosts && <PostList posts={filteredPosts} />}
      {/* {posts && <PostList posts={posts} />} */}
      
    </div>
  );
}

export default Feed;
