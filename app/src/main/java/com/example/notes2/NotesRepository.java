package com.example.notes2;

import java.util.ArrayList;
import java.util.List;

public class NotesRepository {

    public static List<Note> getNotes(){
        ArrayList<Note> result = new ArrayList<>();
        // типа паттерн-репозиторий
        result.add(new Note("Заметка 1", "Текст заметки 1"));
        result.add(new Note("Заметка 2", "Текст заметки 2"));
        result.add(new Note("Заметка 3", "Текст заметки 3"));
        result.add(new Note("Заметка 4", "Текст заметки 4"));
        result.add(new Note("Заметка 5", "Текст заметки 5"));
        result.add(new Note("Заметка 6", "Текст заметки 6"));
        result.add(new Note("Заметка 7", "Текст заметки 7"));
        result.add(new Note("Заметка 8", "Текст заметки 8"));
        result.add(new Note("Заметка 9", "Текст заметки 9"));
        result.add(new Note("Заметка 10", "Текст заметки 10"));
        result.add(new Note("Заметка 11", "Текст заметки 11"));
        result.add(new Note("Заметка 12", "Текст заметки 12"));
        result.add(new Note("Заметка 13", "Текст заметки 13"));
        result.add(new Note("Заметка 14", "Текст заметки 14"));
        result.add(new Note("Заметка 15", "Текст заметки 15"));
        result.add(new Note("Заметка 16", "Текст заметки 16"));
        return result;
    }


}
