package com.example.companionandroid.models;

public class NoteItem {
    private String noteId;
    private String title;
    private String description;

    public NoteItem(String noteId, String title, String description) {
        this.noteId = noteId;
        this.title = title;
        this.description = description;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
