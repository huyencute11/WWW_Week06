import React, { useState } from "react";

import "./styles.css";
import { Link } from "react-router-dom";
import { Requests } from "../../utils/request";

function LoginPage() {
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
    uname: "invalid email",
    pass: "invalid password"
    };
 const { post } =   Requests();

  const handleSubmit = async(event) => {
    //Prevent page reload
    event.preventDefault();

    var { uname, pass } = document.forms[0];

    
    const body = {  username:uname.value, password:pass.value };
    console.log(body);
      const res = await post('login', body)

    if (res.data.isSuccess) {
        localStorage.setItem('token', res.data.token);
        window.location.assign('/');
    }
    else {
        alert("login failed");
        window.location.assign('/login');
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
          <label>Username </label>
          <input type="text" name="uname" required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("pass")}
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
        <div className="title">Login</div>
              {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
              <div className="title" style={{fontSize:18,marginTop:10}}>Register <Link to={'/register'}>here</Link></div>
      </div>
    </div>
  );
}

export default LoginPage;