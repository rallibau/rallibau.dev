import React,{ useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import { ACCESS_TOKEN_NAME, API_BASE_URL } from '../../constants/apiConstants';
import axios from 'axios'
function Home(props) {
    useEffect(() => {
        console.log("consultando token...")
        axios.get(API_BASE_URL+'/me', { headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }})
        .then(function (response) {
             console.log(response.status)
            if(response.status !== 200){
              redirectToLogin()
            }
        })
        .catch(function (error) {
          console.log("error...")
          redirectToLogin()
        });
      })
    function redirectToLogin() {
        console.log("go to login...")
        props.history.push('/login');
    }
    return(
        <div className="mt-2">
            Home page content
        </div>
    )
}

export default withRouter(Home);