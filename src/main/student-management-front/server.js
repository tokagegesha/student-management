const path = require('path');
const express = require('express');
const proxy = require('http-proxy-middleware');

const app = express();

// static files
app.use(express.static(path.join(__dirname, 'dist')));

const options = require('./proxy.config.json');
Object.keys(options).forEach(key => {
    app.use(key, proxy(options[key]));
});

// Catch all other routes and return the index file
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/index.html'));
});

app.listen(80, () => {
  console.log('Application listening on port 80!');
});
