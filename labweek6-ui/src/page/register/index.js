import React, { useState } from "react";
import ReactDOM from "react-dom";

import "./styles.css";
import { Link, Navigate } from "react-router-dom";
import { Requests } from "../../utils/request";

function RegisterPage() {
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

    var { fname,lname,mname,email,mobile, pass } = document.forms[0];
    const body = { fname:fname.value, lname:lname.value, mname:mname.value, email:email.value, mobile:mobile.value, password:pass.value };
    console.log(body);
    const res = await post('register',body )
    if (res.data.isSuccess) {
      alert("back to login page");
      window.location.assign('/login');
    }
    else {
      alert("register failed");
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
          <label>FirstName </label>
          <input type="text" name="fname" required />
        </div>
        <div className="input-container">
          <label>MiddleName </label>
          <input type="text" name="mname" required />
        </div>
        <div className="input-container">
          <label>LastName </label>
          <input type="text" name="lname" required />
        </div>
        <div className="input-container">
          <label>Mobile </label>
          <input type="text" name="mobile" required />
        </div>
        <div className="input-container">
          <label>Email </label>
          <input type="text" name="email" required />
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
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
        <div className="title">Register</div>
        {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
        <div className="title" style={{fontSize:18,marginTop:10}}>Login <Link to={'/login'}>here</Link></div>
      </div>
    </div>
  );
}

export default RegisterPage;