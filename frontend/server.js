const express = require('express');
const path = require('path');
const httpProxy = require('http-proxy');
const app = express();
const port = process.env.PORT_NUMBER || 80;
const apiProxy = httpProxy.createProxyServer();

apiProxy.on('error', (err, req, res) => {
  console.log(err)
  res.status(500).send('Proxy Error');
});


app.post("/addUser", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.post("/login", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.post("/addMessage", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.get("/getAllMessages", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.post("/addLiveUser", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.post("/removeLiveUser", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.get("/getLiveUsers", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});

app.post("/likeMessage", (req, res) => {
  // sends api requests to the backend
  console.log(req.path)
  apiProxy.web(req, res, {
    target: 'http://127.0.0.1:1234',
  });
});



app.use(express.static(path.join(__dirname, 'build')));

app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'build/index.html'));
});

app.listen(port, () => console.log(`Front end server on port ${port}!`));