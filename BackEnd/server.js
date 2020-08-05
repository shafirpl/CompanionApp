const express = require("express");
const connectDB = require("./config/db");
const path = require("path");
const notesRoute = require("./routes/notesRoute");
const maintenanceRoute = require("./routes/carMaintenance");
const ipRoute = require("./routes/ipAddress");
// var http = require("http");

/*
* for socket io I watched brad's tutorial
* https://www.youtube.com/watch?v=jD7FnbI76Hg
*/

// const socketIo = require('socket.io');

const app = express();

// we need this for socket.io to work
// const server = http.createServer(app)

// const io = socketIo(server);

// Connect Database
connectDB();

// Middleware initialization
/*
* Usually we used to install body parser and do
* app.use(bodyparser.json()). But now bodyparser comes
* packaged with express. So we just have to do express.json()
* to use bodyparser
*/
app.use(express.json({extended: false}));

app.get("/", (req, res) => {
  res.send("API Running");
});

app.use("/notes",notesRoute);
app.use("/maintenance", maintenanceRoute);
app.use("/ip",ipRoute);
// run when a client connects

// similar to electron, whenever an event with the name 'connection' is emitted, we start listening to the server
/*
* in the monitor.js file of my electron app, I have a function called sendData() that
* emits an event with name "data" with the required info
* in order to catch that data, we need to have the socket variable inside the io.on function
*/
// io.on('connection', socket => {
//   console.log('New WS connection')
//     socket.on("data", (msg) => {
//       console.log(msg);
//     });
// });



const PORT = process.env.PORT || 5500;

// do stuff with the data received from the socket


app.listen(PORT, () => {
  console.log(`Server started on port ${PORT}`);
});