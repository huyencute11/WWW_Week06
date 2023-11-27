import React, { useState } from "react";

import "./styles.css";
import { Link, useLocation } from "react-router-dom";
import { Requests } from "../../utils/request";

function EditProfilePage(props) {
  // React States
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  const {state} = useLocation();
  console.log(props,state);
  // //const { state } = props.location;
  const [intro, setIntro] = useState(state.intro?? "");
  const [profile, setProfile] = useState(state.profile??"");
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

const token=localStorage.getItem('token');
    
    const body = {  intro:intro, profile:profile,token:token };
    console.log(body);
      const res = await post('/update', body)
console.log(res);
      if (res.data) {
        alert("updated successfully");
        window.location.assign('/profile/'+state.id);
      }
      else {
        alert("updated failed, please try again");
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
          <label>Intro </label>
          <textarea type="text" name="intro" value={intro}  onChange={(e)=>{setIntro(e.target.value)}} required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Profile </label>
          <textarea type="text" name="profile" value={profile} onChange={(e)=>{setProfile(e.target.value)}} required />
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
        <div className="title">Edit Info</div>
              {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
               {/*<div className="title" style={{fontSize:18,marginTop:10}}>Register <Link to={'/register'}>here</Link></div> */}
      </div>
    </div>
  );
}

export default EditProfilePage;