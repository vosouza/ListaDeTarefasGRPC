package org.example;

import java.util.ArrayList;
import java.util.List;

public class Repository {

    private final List<TodoItem> lista;

    Repository() {
        lista = new ArrayList<>();
    }

    public void addItem(TodoItem item) {
        TodoItem newItem = TodoItem.newBuilder().setStatus(item.getStatus())
                .setId(lista.size()+1).setToDoMessage(item.getToDoMessage()).build();
        lista.add(newItem);
    }


    public void changeItem(int id){
        lista.forEach( item ->{
            if(item.getId() == id){
                if(item.getStatus().equals("Incompleto")){
                    TodoItem itemChanged = TodoItem.newBuilder().setId(item.getId()).setStatus("Completo").setToDoMessage(item.getToDoMessage()).build();
                    lista.set(lista.indexOf(item), itemChanged);
                }else{
                    TodoItem itemChanged = TodoItem.newBuilder().setId(item.getId()).setStatus("Incompleto").setToDoMessage(item.getToDoMessage()).build();
                    lista.set(lista.indexOf(item), itemChanged);
                }
            }
        });
    }
    public List<TodoItem> listAll() {
        return lista;
    }
}
