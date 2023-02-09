package model;

import java.util.List;

public interface Repository {
    List<Note> getAllNotes();
    String CreateNote(Note note);
    void NewVersionNote(Note note);

    void delNoteRepo(String id);
}
