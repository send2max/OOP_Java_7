package model;

import java.util.Date;

public class Note {
    private String id = ""; // номер заметки
    private String heading; // заголовок заметки
    private String textNote; // текст заметки
    private String date;      // дата составления заметки

    public Note(String heading, String textNote, String date) {
        this.heading = heading;
        this.textNote = textNote;
        this.date = date;
    }

    public Note(String id, String heading, String textNote, String date) {
        this(heading, textNote, date);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public String getDate(){
        return date;
    }


    @Override
    public String toString() {
        return String.format("Заметка № %s . Заголовок: %s. Формулировка: %s. Дата создания: %s.", id, heading, textNote, date);
    }
}
