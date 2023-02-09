package views;

import controllers.UserController;
import model.Note;


import java.util.Scanner;

public class ViewUser {
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;
        CommandsSave commandsSave = CommandsSave.NONE;
        while (true) {
            String command = prompt(ANSI_YELLOW+"************** Добро пожаловать в записную книжку *************\n"+ANSI_RESET +ANSI_BLUE+
                    "Введите команду из нижеперечисленных (регистр не важен):"+ANSI_RESET+"\nДобавить заметку:\n\t\t\t\t - "+ANSI_RED+"CREATE"+ANSI_RESET+"\nПрочитать заметку:\n\t\t\t\t" +
                    " -"+ANSI_RED+" READ"+ANSI_RESET+"\nУдалить заметку:\n\t\t\t\t -"+ANSI_RED+" DELETE"+ANSI_RESET+"\n" +
                    "Подготовить к отправке:\n\t\t\t\t -"+ANSI_RED+" SAVE\n"+ANSI_RESET+
                    "Выйти:\n\t\t\t\t - "+ANSI_RED+"EXIT\n" +ANSI_RESET+
                    "Отредактировать заметку:\n\t\t\t\t -"+ANSI_RED+" UPDATE\n" +ANSI_RESET+ANSI_BLUE+
                    "Поле для ввода команды: "+ANSI_RESET);
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    String heading = prompt(ANSI_BLUE+"Заголовок: "+ANSI_RESET);
                    String textNote = prompt(ANSI_BLUE+"Текст заметки: "+ANSI_RESET);
                    String date = prompt(ANSI_BLUE+"Дата записи: "+ANSI_RESET);
                    userController.saveNote(new Note(heading,textNote,date));
                    break;
                case READ:
                    String id = prompt(ANSI_BLUE+"Заметку под каким номером хотите прочитать: "+ANSI_RESET);
                    try {
                        Note note = userController.readNote(id);
                        System.out.println(note);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;


                case UPDATE:
                    System.out.println(ANSI_BLUE+"Ниже указан список имеющихся заметок: "+ANSI_RESET);
                    userController.viewAllNotes();
                    String id1 = prompt(ANSI_BLUE+"наберите порядковый номер какую хотите изменить: "+ANSI_RESET);
                    try {
                        Note note = userController.readNote(id1);
                        System.out.println(note);
                        userController.updateNote(note);
                        System.out.println(ANSI_RED+"\t\t\t\t\t\t\t\t\tЗаметка отредактирована"+ANSI_RESET);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case DELETE:
                    System.out.println(ANSI_BLUE+"Ниже указан список имеющихся заметок: "+ANSI_RESET);
                    userController.viewAllNotes();
                    String id2 = prompt(ANSI_BLUE+"наберите порядковый номер заметки какую хотите удалить: "+ANSI_RESET);
                    try {
                        userController.deleteNote(id2);
                        System.out.println(ANSI_RED+"Заметка под номером "+id2+" успешно удалена"+ANSI_RESET);
                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    break;
                case SAVE:
                    String commandSave = prompt(ANSI_BLUE+"***** Выберите вариант обработки данных для отправки (сохранения) ******\n" +ANSI_RESET+
                            "Сохранить каждую заметку в отдельном фале:\n" +
                            "\t\t\t\t\t\t - "+ANSI_RED+"SAVEEACHNOTE\n" +ANSI_RESET+
                            "Сохранить записную книжку в архивном файле:\n" +
                            "\t\t\t\t\t\t - "+ANSI_RED+"SAVETOZIP\n" +ANSI_RESET+
                            "Отправить записную книжку на адрес электронной почты:\n" +
                            "\t\t\t\t\t\t - "+ANSI_RED+"SENDTOWEB\n" +ANSI_RESET+
                            "Выйти на уровень выше:\n" +
                            "\t\t\t\t\t\t -"+ANSI_RED+" EXIT\n"+ANSI_RESET+ANSI_BLUE+"Поле для ввода команды: "+ANSI_RESET);
                    commandsSave = CommandsSave.valueOf(commandSave.toUpperCase());
                    if (commandsSave == CommandsSave.EXIT) break;
                    switch (commandsSave){
                        case SAVEEACHNOTE:
                            try {
                                userController.saveEach();
                            }
                            catch (Exception e){
                                throw new RuntimeException(e);
                            }
                            break;
                        case SAVETOZIP:
                            try{
                                userController.saveZip();
                            }
                            catch (Exception e){
                                throw new RuntimeException(e);
                            }
                            break;
                        case SENDTOWEB:
                            try {
                                userController.sendWeb();
                            }
                            catch (Exception e){
                                throw  new RuntimeException(e);
                            }

                    }

            }
        }
    }

    public static String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}