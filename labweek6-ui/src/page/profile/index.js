import "bootstrap/dist/css/bootstrap.min.css";
import React, { useEffect, useState } from "react";
import "./styles.css";
import { MDBContainer, MDBRow, MDBCol } from "mdb-react-ui-kit";
import { Link, useParams } from "react-router-dom";
import { Requests } from "../../utils/request";

const ProfilePage = () => {
    const params = useParams();
    const [data, setData] = useState({myAccount:false,email:"",middleName:"",lastName:"",firstName:"",posts:[],intro:'',profile:''});
    const { post } = Requests();
    const getData = async () => {
        const token= await localStorage.getItem('token');
        const body = {  id:params.userId, token:token };
        console.log(body);
        const res = await post('getDetails', body)
        console.log(res);setData(res.data);
      
    }
    useEffect(() => {
        getData();
    }, [])
    useEffect(() => {
        console.log("data", data);
     },[data])
  return (
    <div>
      <link
        rel="stylesheet"
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"
      />
      <div class="page shadow">
        <div class="main-container shadow">
          <MDBContainer>
            <br />
            <br />
            <MDBRow>
              <MDBCol sm={12} md={4}>
                <div class="container">
                  <img
                    src="https://d1l05b.csb.app/img.jpg"
                    alt="John"
                    style={{ width: "65%", borderRadius: "100%" }}
                  />
                  <br />
                  <a href="#">
                    <i className="fa fa-twitter" />
                  </a>
                  <a href="#">
                    <i className="fa fa-linkedin" />
                  </a>
                  <a href="#">
                    <i className="fa fa-facebook" />
                                  </a>
                                  
                              </div>
                              {data.myAccount && <Link to={"/update"} state={{ id:params.userId,...data }}>
                                  <i className="fa fa-pencil" /> edit profile
                              </Link>}
              </MDBCol>

              <MDBCol>
                <div class="container">
                        <h2>{data.firstName??""}</h2>
                                  <p>Email:{data.email??""}</p>
                                  <p>Mobile:{data.mobile??""}</p>
                                  <p>Intro:{data.intro??""}</p>
                </div>

                <hr />

                <MDBContainer>
                  <MDBRow>
                    <MDBCol sm={2} lg={2} md={2}>
                      <h6 className="m-4">Profile </h6>
                    </MDBCol>
                    <MDBCol>
                      <p class="bio">
                      {data.profile??""}
                      </p>
                    </MDBCol>
                  </MDBRow>
                </MDBContainer>
                <br />
                <br />
              </MDBCol>
            </MDBRow>
          </MDBContainer>
        </div>

        <div class="bottom-container" style={{ textAlign: "center" }}>
          <h4
            style={{
              paddingBottom: "1%",
              paddingTop: "3%",
              color: "rgb(70, 131, 192)"
            }}
          >
            {" "}
            Artical
          </h4>
          {data.posts.length>0?
          <table>
            <tr>
              <th>No.</th>
                          <th>Artical Name</th>
                          <th>Summary</th>
              <th>Action</th>
            </tr>
                      {data.posts.map((x) => {
                          return <tr>
                          <td>{x.id}</td>
                              <td>{x.title}</td>
                              <td>{x.summary}</td>
                          <td>
                            <Link to={"/viewpost/"+x.id} class="view-article-btn">
                              view artical
                            </Link>
                          </td>
                        </tr>
                      })
                          
            }
             
            
          </table>:"No Artical"}
          {/* <p>
            <button>Submit Pitch</button>
          </p> */}
        </div>
      </div>
    </div>
  );
};
export default ProfilePage;