package org.example;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final List<TodoItem> lista;

    Repository() {
        lista = new ArrayList<>();
    }

    public void addItem(TodoItem item) {
        lista.add(item);
    }


    public List<TodoItem> listAll() {
        return lista;
    }
}
