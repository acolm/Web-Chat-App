import React from 'react';
import Axios from 'axios';
import { Redirect } from 'react-router-dom';

const SignUp = ({ appUser, setAppUser }) => {
    const [username, setUsername] = React.useState('');
    const [password, setPassword] = React.useState('');
    const [password2, setPassword2] = React.useState('');
    const [error, setError] = React.useState('');
    const [isLoggedIn, setIsLoggedIn] = React.useState(false);

    const handleSignUp = () => {
        const body = {
            username: username,
            password: password
        };
        // eslint-disable-next-line no-restricted-globals
        event.preventDefault();
        if (password === password2) {
            Axios.post('/addUser', body)
                .then((res) => {
                    console.log(res.data)
                    if (res.data.success) {
                        // it worked
                        setIsLoggedIn(true);
                        setAppUser(username);
                        console.log(username + " has signed up.");
                    } else {
                        // Auth Error
                        alert(res.data.response);
                        setError(res.data.response);
                    }
                })

        } else {
            alert("Password Mismatch");
            console.log("Failed To Sign Up. Passwords do not math")
        }
    };



    if (isLoggedIn && appUser)
        return <Redirect to="/chatRoom" />
    return (
        <div>
            <form onSubmit={handleSignUp}>
                <tr>
                    <h1 className="center"> Sign Up </h1>
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
                        onChange={e => setPassword(e.target.value)}
                    />
                </tr>
                <tr>
                    <input
                        className="login-text"
                        placeholder="Repeat Password"
                        type="password"
                        value={password2}
                        onChange={e => setPassword2(e.target.value)}
                    />
                </tr>
                <tr>
                    <button disabled={!username||!password || !password2} onClick={handleSignUp} className="login-button"> Sign Up </button>
                </tr>
                <tr>
                    {error && <strong> {error} </strong>}
                </tr>
            </form>
        </div>
    )
};

export default SignUp;