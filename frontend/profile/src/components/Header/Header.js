import React from 'react';
import { withRouter } from "react-router-dom";
import { ACCESS_TOKEN_NAME } from '../../constants/apiConstants';
function Header(props) {
    const capitalize = (s) => {
        if (typeof s !== 'string') return ''
        return s.charAt(0).toUpperCase() + s.slice(1)
    }
    let title = capitalize(props.location.pathname.substring(1,props.location.pathname.length))
    if(props.location.pathname === '/') {
        title = 'Welcome';
    }
    function renderLogout() {
        if(props.location.pathname != '/login'){
            if(localStorage.getItem(ACCESS_TOKEN_NAME)){
                return(
                    <div className="ml-auto">
                        <button className="btn btn-danger" onClick={() => handleLogout()}>Logout</button>
                    </div>
                )
            }else{
                return(
                    <div className="ml-auto">
                        <button className="btn btn-info" onClick={() => props.history.push('/login')}>Login</button>
                    </div>
                 )
            }
        }
    }
    function handleLogout() {
        localStorage.removeItem(ACCESS_TOKEN_NAME)
        props.history.push('/')
    }
    return(
        <nav className="navbar navbar-dark bg-primary">
            <div className="row col-12 d-flex justify-content-center text-white">
            <a href="/">
                <span className="h3">{props.title || title}</span>
                </a>
                {renderLogout()}
            </div>
        </nav>
    )
}
export default withRouter(Header);