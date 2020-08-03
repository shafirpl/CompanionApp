const express = require("express");



let http = require("http");



let compName, totalMem, cpuUsage, cpuFree, cpuModel,memUsage, memFree, uptime;

/*
* for socket io I watched brad's tutorial
* https://www.youtube.com/watch?v=jD7FnbI76Hg
*/

const socketIo = require('socket.io');

const app = express();

// var jsonParser = bodyParser.json();
// var urlencodedParser = bodyParser.urlencoded({ extended: false });


// we need this for socket.io to work
const server = http.createServer(app)

const io = socketIo(server);



app.use(express.json({extended: false}));

app.get("/", (req, res) => {
  res.send("API Running");
});

app.post("/socket",(req,res) => {
  compName = req.body.compName;
  totalMem = req.body.totalMem;
  cpuUsage = req.body.cpuUsage;
  cpuFree = req.body.cpuFree;
  cpuModel = req.body.cpuModel;
  memUsage = req.body.memUsage;
  memFree = req.body.memFree;
  uptime = req.body.uptime;

  res.status(200).send(true);
});



// run when a client connects

// similar to electron, whenever an event with the name 'connection' is emitted, we start listening to the server
/*
* in the monitor.js file of my electron app, I have a function called sendData() that
* emits an event with name "data" with the required info
* in order to catch that data, we need to have the socket variable inside the io.on function
*/
// io.on('connection', socket => {
//   console.log('New WS connection')
//     // socket.on("data", (msg) => {
//     //   console.log(msg);
//     // });
//     // start sending data to client
//     setInterval(() => {
//       socket.emit("data", {
//         compName,
//         totalMem,
//         cpuUsage,
//         cpuFree,
//         cpuModel,
//         memUsage,
//         memFree,
//         uptime,
//       });
//     },2000)
// });

app.get("/socket", (req,res) => {
  res.send({
    compName,
    totalMem,
    cpuUsage,
    cpuFree,
    cpuModel,
    memUsage,
    memFree,
    uptime,
  });
})

const PORT = process.env.PORT || 4200;

// do stuff with the data received from the socket

server.listen(PORT, () => {
  console.log(`Server started on port ${PORT}`);
});