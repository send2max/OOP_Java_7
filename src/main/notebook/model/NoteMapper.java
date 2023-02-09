package model;

public class NoteMapper {
    public static String map(Note note) {
        return String.format("%s,%s,%s,%s", note.getId(), note.getHeading(), note.getTextNote(), note.getDate());
    }

    public Note map(String line) {
        String[] lines = line.split(",");
        return new Note(lines[0], lines[1], lines[2], lines[3]);
    }
}
