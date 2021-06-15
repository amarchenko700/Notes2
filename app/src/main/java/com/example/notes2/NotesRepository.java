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
        return result;
    }


}
