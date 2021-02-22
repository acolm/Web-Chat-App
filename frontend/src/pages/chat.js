import React from 'react';
import Axios from 'axios';
import {Redirect} from 'react-router-dom';


const Login = ({appUser, setAppUser}) => {
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

        Axios.post('/api/authenticate', body)
        .then((res) => {
            console.log(res.data)
            if(res.data.success) {
                // it worked
                setIsLoggedIn(true);
                setAppUser(username);
            } else {
                // Auth Error
                setError(res.data.response);
            }
        })
        .catch(() => {
            setError('Failed to Auth');
        });
    };

    

    if(isLoggedIn && appUser)
        return <Redirect to="/chatRoom" />
    return (
        <div>
            <div>TEST!</div>
            <h1>Welcome to the JavaJuice chatroom {appUser}!</h1>
            <h1> Login </h1>
            <div>
                <input
                    placeholder="UserName"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                />
            </div>
            <div>
                <input
                    placeholder="Password"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
            </div>
            <div>
                <button onClick={handleLogin}> log in </button>
            </div>
            {error && <strong> {error} </strong>}
        </div>
    )
};

export default Login;