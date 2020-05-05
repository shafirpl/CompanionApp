const express = require("express");
const connectDB = require("./config/db");
const path = require("path");
const notesRoute = require("./routes/notesRoute");

const app = express();

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

const PORT = process.env.PORT || 5500;

app.listen(PORT, () => {
  console.log(`Server started on port ${PORT}`);
});