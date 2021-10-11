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
import News from './components/News/News';
import PrivateRoute from './utils/PrivateRoute';


function App() {
  const [title, updateTitle] = useState(null);
  const [errorMessage, updateErrorMessage] = useState(null);
  return (
   <Router>
      <div className="App">
        <Header title={title}/>
          <div className="container d-flex align-items-center flex-column">
            <Switch>
              <Route path="/" exact={true}>
               <News/>
              </Route>
              <Route path="/register">
                <RegistrationForm showError={updateErrorMessage} updateTitle={updateTitle}/>
              </Route>
              <Route path="/login">
                 <LoginForm showError={updateErrorMessage} updateTitle={updateTitle}/>
              </Route>
              <PrivateRoute path="/home">
                <Home/>
              </PrivateRoute>
            </Switch>
            <AlertComponent errorMessage={errorMessage} hideError={updateErrorMessage}/>
          </div>
      </div>
      </Router>
  );
}

export default App;
