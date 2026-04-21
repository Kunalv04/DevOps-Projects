// Sample code for a simple Express backend server

const express = require('express');
const app = express();

app.get('/', (req, res) => {
  res.send('Hello from Backend!');
});

app.listen(5000, () => {
  console.log('Server running on port 5000');
});
