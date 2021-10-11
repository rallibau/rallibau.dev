import React,{ useEffect } from 'react';
import { withRouter } from 'react-router-dom';
import axios from 'axios';
import {API_BASE_URL, ACCESS_TOKEN_NAME} from '../../constants/apiConstants';
function News(props) {
    axios.get(API_BASE_URL+'/page', { headers: { 'Authorization': localStorage.getItem(ACCESS_TOKEN_NAME) }})
           .then(function (response) {
                console.log(response.status)

           })
           .catch(function (error) {
            console.log("error..."+error)

           });
    return(
        <div className="mt-2">
            News
        </div>
    )
}

export default withRouter(News);