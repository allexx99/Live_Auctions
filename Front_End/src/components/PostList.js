import React from "react"
import { Link } from "react-router-dom";

const PostList = ({posts}) => {
  

  return ( 
    <div className="blog-list">
       {posts.map((postObj) => (
        <div className="blog-preview" key={postObj.id}>
          <Link to={`/posts/${postObj.id}`}>
            <h2>{ postObj.title }</h2>
            <p>Starting bid: { postObj.bid }</p>
          </Link>
        </div>
      ))}
    </div>
   );
}
 
export default PostList;