import React, { useEffect, useState } from "react";
import ReactDOM from "react-dom";

import "./styles.css";
import { Link, Navigate } from "react-router-dom";
import { Requests } from "../../utils/request";
import { Checkbox } from "antd";
import TextArea from "antd/es/input/TextArea";

function ListArtical() {
  const [data, setData] = useState({content:[],totalElements:0});
  const [page, setPage] = useState(0);
  const [num, setNum] = useState(2);
  const { post } = Requests();
    const getData = async () => {
        const token= await localStorage.getItem('token');
        const body = {  page:page,num:num, token:token };
        console.log(body);
        const res = await post(`/post/getAll?page=${page}&num=${num}`, body)
        console.log(res);setData(res.data);
      
    }
    useEffect(() => {
        getData();
    }, [])
    useEffect(() => {
        console.log("datanày", data);
     },[data])
  return <>
    <div class="container">
  <div id="feed"><section className="feed">
        <h1>List Artical</h1>
        <h2>You can Create new artical <Link to={"/new-post"}>here</Link>!</h2>

        <div className="tiles" aria-live="polite">
          {data.content.map((item, index) => {
            return (
              <div className="tile fade-in" key={item.id}>
                <span className="count">{item.id}</span>
                <h2>{item.title}</h2>
                <p>Metadata:{item.metaTitle}</p>
                <p>Summary:{item.summary}</p>
                <Link to={"/viewpost/"+item.id}>View Detail</Link>
              </div>
            );
          })}
        </div> 
         {(num*(page+1)) < data.totalElements && (
          <button onClick={()=>{}} type="button" className="load-more">
            Load more
          </button>
        )} 
      </section></div>
</div>
  </>
}

export default ListArtical;