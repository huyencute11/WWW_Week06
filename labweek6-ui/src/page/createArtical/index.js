import React, { useState } from "react";
import ReactDOM from "react-dom";

import "./styles.css";
import { Link, Navigate } from "react-router-dom";
import { Requests } from "../../utils/request";
import { Checkbox } from "antd";
import TextArea from "antd/es/input/TextArea";

function PostForm() {
  // React States
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);

  // User Login info
  const database = [
    {
      username: "user1",
      password: "pass1"
    },
    {
      username: "user2",
      password: "pass2"
    }
  ];
  const errors = {
    uname: "invalid username",
    pass: "invalid password"
  };
  const { post } = Requests();

  const handleSubmit = async(event) => {
    //Prevent page reload
    event.preventDefault();

    var { title, mtitle, summary, content, published } = document.forms[0];
    const token= await localStorage.getItem('token');
    const body = { token:token,title:title.value, metaTitle:mtitle.value, summary:summary.value, content:content.value, published:published.value==='on'?1:0 };
    console.log(body);
    const res = await post('post/create', body)
    console.log(res);
    if (res.data) {
      alert("created successfully");
      window.location.assign('/');
    }
    else {
      alert("created failed, please try again");
    }
    
   
  };

  // Generate JSX code for error message
  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
    );

  // JSX code for login form
  const renderForm = (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Title </label>
          <input type="text" name="title" required />
        </div>
        <div className="input-container">
          <label>Meta Title </label>
          <TextArea type="text" name="mtitle" required />
        </div>
        <div className="input-container">
          <label>Summary </label>
          <TextArea type="text" name="summary" required />
        </div>
        <div className="input-container">
          <label>Content </label>
          <TextArea type="text" name="content" required />
        </div>
        <div className="input-container">
          <label>Pulished </label>
          <input style={{marginLeft:'-600px'}} type="checkbox" name="published" required />
        </div>
        
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Create New Post</div>
        {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
        {/* <div className="title" style={{fontSize:18,marginTop:10}}>Login <Link to={'/login'}>here</Link></div> */}
      </div>
    </div>
  );
}

export default PostForm;