const express = require("express");
const router = express.Router();
const Note = require("../models/notes");

router.post('/', async(req,res) => {
    try {
        const newNote = new Note({
          noteId: req.body.noteId,
          title: req.body.title,
          description: req.body.description,
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

router.put('/:id', async (req,res) => {
    try {
        let note = await Note.findOneAndUpdate({noteId:req.params.id},{
            title: req.body.title,
            description: req.body.description
        });
        res.json({done: 1});
    } catch (error) {
        console.log(error);
        res.status(500).send("Server Error");
    }
})

router.delete('/:id', async(req,res) => {
    try {
        await Note.findOneAndDelete({noteId: req.params.id});
        res.json({done: 1});
        
    } catch (error) {
        console.log(error);
        res.status(500).send("Server Error");
    }
})

module.exports = router;