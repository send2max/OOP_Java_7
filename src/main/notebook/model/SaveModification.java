package model;

import static views.ViewUser.ANSI_RESET;

public class SaveModification {
    public static final String ANSI_RED = "\u001B[31m";
    public static void saveAllNote() {
        System.out.println(ANSI_RED+"\t\t\t\t\tКаждая заметка сохранена на диске в отдельный файл"+ANSI_RESET);
    }

    public static void saveZipFile(){
        System.out.println(ANSI_RED+"\t\t\t\t\tзаписная книжка сохранена в формате ZIP"+ANSI_RESET);
    }


    public static void sendToWeb(String web) {
        System.out.println(ANSI_RED+"\t\t\t\t\tЗаписная книжка отправлена по адресу:\n" +
                "\t\t\t\t\t"+web+ANSI_RESET);;
    }
}
