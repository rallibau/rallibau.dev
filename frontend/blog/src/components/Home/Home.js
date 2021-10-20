import React,{ useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { ACCESS_TOKEN_NAME, API_BASE_URL } from '../../constants/apiConstants';
import About from '../../components/about/about';
import Portfolio from '../../components/portfolio/portfolio';
import Resume from '../../components/resume/resume';
import resumeData from '../../resumeData';
import axios from 'axios'
function Home(props) {
    useEffect(() => {
        console.log("consultando token...")
        axios.get(API_BASE_URL+'/user', { headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }})
        .then(function (response) {
             console.log(response.status)
            if(response.status !== 200){
             // redirectToLogin()
            }
        })
        .catch(function (error) {
          console.log("error...")
          //redirectToLogin()
        });
      })
      return(
        <div className="mt-2">
             <Portfolio resumeData={resumeData}/>
             <About resumeData={resumeData}/>
             <Resume resumeData={resumeData}/>
        </div>
    )
}

export default withRouter(Home);