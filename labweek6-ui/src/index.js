import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Routes,
  Link
} from "react-router-dom";
import LoginPage from './page/login';
import RegisterPage from './page/register';
import ProfilePage from './page/profile';
import PostForm from './page/createArtical';
import EditProfilePage from './page/editProfile';
import ListArtical from './page/listArtical';
import ViewArticalPage from './page/viewArtical';


const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
          <Route path="/login" element={<LoginPage></LoginPage>}>
           
        </Route>
        <Route path="/register" element={<RegisterPage></RegisterPage>}>
           
           </Route>
          <Route path="/profile/:userId" element={<ProfilePage></ProfilePage>}>
           
          </Route>
        <Route path="/new-post" element={<PostForm></PostForm>}></Route>
        <Route path="/update" element={<EditProfilePage></EditProfilePage>}> </Route>
        <Route path="/" element={<ListArtical></ListArtical>}>
            
        </Route>
        <Route path="/viewpost/:PostId" element={<ViewArticalPage></ViewArticalPage>}>
            
        </Route>
    </Routes>
    </Router>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
