const mongoose = require("mongoose");
const Schema = mongoose.Schema;

const NoteSchema = new Schema ({
    noteId:{
        type:String,
        required: true,
        unique: true 
    },
    title:{
        type: String
    },
    description:{
        type: String
    }
});

module.exports = Notes = mongoose.model("notes", NoteSchema);