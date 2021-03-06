import './App.css';
import React, {useState} from 'react';
import Header from './components/Header/Header';
import RegistrationForm from './components/RegistrationForm/RegistrationForm';
import Home from './components/Home/Home';
import LoginForm from './components/LoginForm/LoginForm';
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import AlertComponent from './components/AlertComponent/AlertComponent';
import Posts from './components/Posts/Posts';
import PostDetail from './components/Posts/PostDetail';
import PostForm from './components/Posts/PostForm';
import Footer from './components/footer/footer';
import resumeData from './resumeData';

function getIdPost(){
    //console.log(this.props.match.params);
    return '8a386a-1770-07a1-4bad-df0fcdf71a4f'
}


function App() {
  const [title, updateTitle] = useState(null);
  const [errorMessage, updateErrorMessage] = useState(null);
  //const { idPost } = useParams();
  return (
   <Router>
      <div className="App">
        <Header title={title}/>
          <div className="container d-flex align-items-center flex-column">
            <Switch>
              <Route path="/" exact={true}>
               <Home/>
              </Route>
              <Route path="/blog" exact={true}>
               <Posts/>
              </Route>
              <Route exact path="/blog/:id" render={(props) => (
                  <PostDetail id={props.match.params.id}/>
              )} />
              <Route path="/NewPost" exact={true}>
                <PostForm/>
              </Route>
              <Route path="/register">
                <RegistrationForm showError={updateErrorMessage} updateTitle={updateTitle}/>
              </Route>
              <Route path="/login">
                 <LoginForm showError={updateErrorMessage} updateTitle={updateTitle}/>
              </Route>
              <Route path="/home">
                <Home/>
              </Route>
            </Switch>
            <AlertComponent errorMessage={errorMessage} hideError={updateErrorMessage}/>
          </div>
      </div>
       <Footer resumeData={resumeData}/>
      </Router>
  );
}

export default App;
