import React from 'react';
import Axios from 'axios';
import {Redirect} from 'react-router-dom';

const ws = new WebSocket('ws://localhost:1234/ws');

const ChatRoom = ({ appUser, setAppUser }) => {
    const [error, setError] = React.useState('');
    const [message, setMessage] = React.useState('');
    const [messages, setMessages] = React.useState([]);
    const [liveUsers, setLiveUsers] = React.useState([]);

    
    const addMessage = (stringMessage) => {
        console.log(stringMessage.data); // incoming from server
        setMessages((messages) => {
          const newNotes = messages.slice(); // copy from item 0
          newNotes.push(stringMessage.data);
          console.log(newNotes);
          return newNotes;
        });
      };

      const getMessages = () => {
        // utility to get all notes

        Axios.get('/getAllMessages')
            .then((res) => {
                if (res.data.success) {
                    console.log(res.data.messages)
                    setMessages(res.data.messages)
                }

            })
            .catch(console.log);
    }
    
      
    
      const submitMessage = () => {
        console.log(message);
        let options = { dateStyle:"short", timeStyle:"short"};
        const body = {
            // Will need to update this
            messageID: 1,
            message: message,
            user: appUser,
            likedBy: [],
            numLikes: 0,
            messageTime: new Date().toLocaleString('en-US', options ),
            index:0
        };
        //console.log("message time: " + body.messageTime)
        //console.log("Stringified message time:" + JSON.stringify(body.messageTime))
        console.log(JSON.stringify(body))
        ws.send(JSON.stringify(body))
        setMessage('')
        addMessage(body.message)
        getMessages()

    };
      const likeMessage = (appUser,i) => {
        //console.log(message);

        const body = {
            appUser: appUser,
            i:i
        };

        Axios.post('/likeMessage', body)
            .then(() => setMessage(''))
            .then(() => getMessages())
            .catch(console.log)
    };

    React.useEffect(() => {
        getMessages();
        // do something when component mounts
        ws.addEventListener('message', addMessage);
        return () => ws.removeEventListener('message', addMessage), ws.removeEventListener('message',getMessages);
      }, []);


    if(!appUser) return( 
         <Redirect to='/Login' />

    )


    return (
        <div>
            <div className="chat-grid-container">
                <div className="header">Hi {appUser}, Welcome to chatter!</div>
                <div className="adds">{/*Put adds or anything else here*/}</div>
                <div className="main">
                    {messages.map((item,i) => {
                        return (
                            <div key={i} className="chat-message">
                                {item} <button onClick={()=>likeMessage(appUser, i)} className="like-button">Like</button>
                            </div>
                        );
                    })}
                </div>
                <div className="users">
                    {/*<div>Online Users</div>
                    {liveUsers.map((user) => {
                        return (
                            <div className="user-list">
                                {user}
                            </div>
                        );
                    })}*/}
                </div>
                <p className="chat"><textarea value={message} onChange={e => setMessage(e.target.value)}> </textarea></p>
                <button type="submit" disabled={!message} className="send" onClick={submitMessage}>Send</button>
                {error && <strong> {error} </strong>}
            </div>
        </div>


    );
};

export default ChatRoom;
