import React, { Component } from 'react';
import './App.css';
import { Switch, Route, Link } from "react-router-dom";
import Login from './pages/Login';
import SignUp from './pages/SignUp';
import LogOut from './pages/logOut';
import ChatRoom from './pages/chatRoom';

const App = () => {
  const [totalUsers, setTotalUsers] = React.useState(0);
  const [appUser, setAppUser] = React.useState(null);
  const [show, setShow] = React.useState(true);

  const handleClick = () => {
    const wrapper = this.wrapper.current;
    wrapper.classlist.toggle('is-nav-open');
  }

  React.useEffect(() => {

  }, []);

  return (
    <div>
      <nav>
        <table className="menuLeft">
          <tbody className="menuBox">
            <tr>
              <td>
                <button onClick={() => setShow(!show)} className="menu-button">Menu</button>
              </td>
            </tr>
            <tr>
              {show && <div className="dropMenu">
                {/*<tr>
                  Users Online: {totalUsers}
                </tr>*/}
                <tr>
                  {!appUser && <Link to="/Login" style={{ textDecoration: 'none' }}>Login</Link>}
                </tr>
                <tr>
                  {appUser && <Link to="/logOut" style={{ textDecoration: 'none' }}>LogOut</Link>}
                </tr>
                <tr>
                  {!appUser && <Link to='/SignUp' style={{ textDecoration: 'none' }}> Signup </Link>}
                </tr>
              </div>
              }
            </tr>
          </tbody>
        </table>
        <h1 className="center">Chatter</h1>
      </nav>

      <Switch>
        <Route path="/Login">
          <Login appUser={appUser} setAppUser={setAppUser} />
        </Route>
      </Switch>

      <Switch>
        <Route path="/Signup">
          <SignUp appUser={appUser} setAppUser={setAppUser} />
        </Route>
      </Switch>

      <Switch>
        <Route path="/logOut">
          <LogOut appUser={appUser} setAppUser={setAppUser} />
        </Route>
      </Switch>

      <Switch>
        <Route path="/chatRoom">
          <ChatRoom appUser={appUser} setAppUser={setAppUser} />
        </Route>
      </Switch>

    </div>
  );
};

export default App;

