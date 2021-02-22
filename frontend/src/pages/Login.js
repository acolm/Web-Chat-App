import React from 'react';
import Axios from 'axios';
import { Redirect, Switch, Route, Link } from 'react-router-dom';



const Login = ({ appUser, setAppUser}) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [error, setError] = React.useState('');
    const [isLoggedIn, setIsLoggedIn] = React.useState(false);
    


    const handleLogin = () => {
        console.log(username);
        const body = {
            username: username,
            password: password
        };
        // eslint-disable-next-line no-restricted-globals
        event.preventDefault();
        Axios.post('/login', body)
            .then((res) => {
                console.log(res.data)
                if (res.data.success) {
                    // it worked
                    setIsLoggedIn(true);
                    setAppUser(username);
                    addLiveUser(username);
                } else {
                    // Auth Error
                    setError(res.data.response);
                    alert(res.data.response);
                }
            })
            .catch(() => {
                setError('Failed to Auth');
            });
            
    };
    
    const addLiveUser = () => {
        const body = {
            username: username
        };

        Axios.post('/addLiveUser', body)
            .then((res) => {
                console.log(res.data)
            })
    }

    if (isLoggedIn && appUser)
        return <Redirect to="/chatRoom" />
    return (
        <div>
            <form onSubmit={handleLogin}>
                <tbody>
                    <tr>
                        <h1 className="center"> Login </h1>
                    </tr>
                    <tr>
                        <input 
                            className="login-text"
                            placeholder="UserName"
                            value={username}
                            onChange={e => setUsername(e.target.value)}
                        />
                    </tr>
                    <tr>
                        <input 
                            className="login-text"
                            placeholder="Password"
                            type="password"
                            value={password}
                            onChange={e => setPassword(e.target.value)}></input>
                            
                    </tr>
                    <tr>
                        <button type = "submit" disabled={!username || !password} onSubmit={handleLogin} className="login-button"> log in </button>
                        
                    </tr>
                    <tr>
                        <p>Not Signed Up?</p>
                        <Link to="/SignUp">Become A User!</Link>
                    </tr>
                    <tr>
                        {error && <strong> {error} </strong>}
                    </tr>
                </tbody>
            </form>
        </div>
    );
};

export default Login;