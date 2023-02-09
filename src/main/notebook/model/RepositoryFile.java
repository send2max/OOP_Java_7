package model;

import views.ViewUser;
import java.util.ArrayList;
import java.util.List;

public class RepositoryFile implements Repository {
    private NoteMapper mapper = new NoteMapper();
    private FileOperation fileOperation;

    public RepositoryFile(FileOperation fileOperation) {
        this.fileOperation = fileOperation;
    }

    @Override
    public List<Note> getAllNotes() {
        List<String> lines = fileOperation.readAllLines();
        List<Note> notes = new ArrayList<>();
        for (String line : lines) {
            notes.add(mapper.map(line));
        }
        return notes;
    }

    @Override
    public String CreateNote(Note note) {

        List<Note> notes = getAllNotes();
        int max = 0;
        for (Note item : notes) {
            int id = Integer.parseInt(item.getId());
            if (max < id){
                max = id;
            }
        }
        int newId = max + 1;
        String id = String.format("%d", newId);
        note.setId(id);
        notes.add(note);
        List<String> lines = new ArrayList<>();
        for (Note item: notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
        return id;
    }

    @Override
    public void NewVersionNote(Note note) {
        note.setHeading(views.ViewUser.prompt("Введите новый текст заголовка: "));
        note.setTextNote(views.ViewUser.prompt("Введите новый текст заметки: "));
        note.setDate(views.ViewUser.prompt("Введите новую дату заметки: "));
        List<Note> notes = getAllNotes();
        for (Note item: notes) {
            if(item.getId().equals(note.getId())){
                item.setDate(note.getDate());
                item.setTextNote(note.getTextNote());
                item.setHeading(note.getHeading());
            }
        }
        List<String> lines = new ArrayList<>();
        for (Note item: notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }

    @Override
    public void delNoteRepo(String id) {
        int idDel = Integer.parseInt(id);
        int base = 0;
        List<Note> notes = getAllNotes();
        for (Note note: notes) {
            base = Integer.parseInt(note.getId());
            if(base == idDel){
                notes.remove(note);
            }
            else if(base > idDel){
                note.setId(String.valueOf(base-1));
            }
        }
        List<String> lines = new ArrayList<>();
        for (Note item: notes) {
            lines.add(mapper.map(item));
        }
        fileOperation.saveAllLines(lines);
    }


}
