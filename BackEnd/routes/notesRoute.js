const express = require("express");
const router = express.Router();
const Note = require("../models/notes");

router.post('/', async(req,res) => {
    try {
        const newNote = new Note({
            noteId: req.body.noteId,
            title: req.body.title,
            description: req.body.description
        });
        const note = await newNote.save();
        res.status(200).json(note);
    } catch (error) {
        console.log(error);
        res.status(500).send("Server error");
    }
});

router.get('/', async(req,res) => {
    const notes = await Note.find();
    res.json(notes);
})

module.exports = router;