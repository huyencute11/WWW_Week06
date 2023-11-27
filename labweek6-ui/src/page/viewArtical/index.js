import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import "./styles.css";
import { MDBContainer, MDBRow, MDBCol } from "mdb-react-ui-kit";
import { Link, useParams } from "react-router-dom";
import { Requests } from "../../utils/request";

const ViewArticalPage = () => {
    const params = useParams();
    const [data, setData] = useState({title:"",metaTitle:"",summary:"",userName:"",userId:"",content:"",comments:[],createdAt:''});
    const { post } = Requests();
    const getData = async () => {
        const token= await localStorage.getItem('token');
        const body = {  id:params.PostId, token:token };
        console.log(body);
        const res = await post('/post/getDetails', body)
        console.log(res);setData(res.data);
      
  }
  const [reload, setReload] = useState(0);
    useEffect(() => {
        getData();
    }, [reload])
    useEffect(() => {
        console.log("data", data);
    }, [data])
    const handleSubmit = async(event) => {
      //Prevent page reload
      event.preventDefault();
  
      var { uname} = document.forms[0];
  const token =localStorage.getItem('token');
      
      const body = {  title:uname.value,postId:params.PostId,token:token };
      console.log(body);
        const res = await post('comment/create', body)
      setReload((pev) => pev + 1);
      
    };
  return (
    <div>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
      />
      <div class="page shadow">
        

        <div class="bottom-container" style={{ textAlign: "center" }}>
          <h1>{data.title}</h1>
          <div>Summary:{data.summary},author:<Link to={"/profile/"+data.userId}>{data.userName}</Link></div>
          <h2>Meta title:{data.metaTitle}</h2>
          <p>{data.content}</p>
        </div>
        <div>
        
        </div>
        
      </div>
      {/* <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Comments </label>
          <input type="text" name="uname" required />
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
      {data.comments.map((x) => {
        return<div style={{display:'block'}}> <div style={{ marginLeft: 10, backgroundColor: 'yellow', color: "blue", float: 'left' }}>
          <Link to={"/profile/"+x.userId}>{x.userName}:</Link> {x.title}
        </div>
          <br />
        </div>
      }) }*/}

      
      <div class="comment-box">
      <form onSubmit={handleSubmit}>
        <input class="comment-input" name="uname"  placeholder="Please enter your comment...."></input>
        <button type="submit" class="comment-button">Send to Comment</button>
        </form>
        <ul class="comments-list">
        {data.comments.map((x) => { return(
            <li class="comment"><Link to={"/profile/"+x.userId}>{x.userName}:</Link> {x.title}</li>)
          }) }
            </ul>
    </div>
    </div>
  );
};
export default ViewArticalPage;