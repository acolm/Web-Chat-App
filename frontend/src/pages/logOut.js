import React from 'react';
import Axios from 'axios';
import { Redirect, Switch, Route, Link } from 'react-router-dom';



const LogOut = ({ appUser, setAppUser }) => {
    const [isLoggedIn, setIsLoggedIn] = React.useState(false);

    const removeLiveUser = () => {
        const body = {
            username: appUser
        };

        Axios.post('/removeLiveUser', body)
            .then((res) => {
                console.log(res.data)
            })
    }

    React.useEffect(() => {
        setAppUser(null);
        setIsLoggedIn(false);
        removeLiveUser();
    }, []);


    if (isLoggedIn && appUser){
        return <Redirect to="/chatRoom" />
    }else {
        return <Redirect to="/Login" />
    }
};

export default LogOut;