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
        if(props.location.pathname !== '/login'){
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
     if(props.location.pathname === '/' || props.location.pathname === '/home'){
            return(
            <header id="home">
                     <nav id="nav-wrap">
                                     <a className="mobile-btn" href="#nav-wrap" title="Show navigation">Show navigation</a>
                                     <a className="mobile-btn" href="#" title="Hide navigation">Hide navigation</a>
                                     <ul id="nav" className="nav">
                                         <li className="current"><a className="" href="/">Home</a></li>
                                         <li><a className="" href="/blog">Blog</a></li>
                                         <li><a className="" href="/login">Login</a></li>
                                     </ul>
                                 </nav>

                     <div className="row banner">
                        <div className="banner-text">
                           <h1 className="responsive-headline">Ramón Llinares</h1>
                           <h3>
                                Soy un desarrollador de software
                           </h3>
                           <hr/>
                           <ul className="social">

                           </ul>
                        </div>
                     </div>

                     <p className="scrolldown">
                        <a className="smoothscroll" href="#about"><i className="icon-down-circle"></i></a>
                     </p>

                  </header>

            )
    }else{
        return(
            <nav id="nav-wrap" className="toolBarBlack">
                <a className="mobile-btn" href="#nav-wrap" title="Show navigation">Show navigation</a>
                <a className="mobile-btn" href="#" title="Hide navigation">Hide navigation</a>
                <ul id="nav" className="nav">
                    <li className="current"><a className="" href="/">Home</a></li>
                    <li><a className="" href="/blog">Blog</a></li>
                    <li><a className="" href="/login">Login</a></li>
                </ul>
            </nav>
        )
    }
}
export default withRouter(Header);