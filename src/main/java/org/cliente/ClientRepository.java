package org.cliente;

import io.grpc.Grpc;
import io.grpc.InsecureChannelCredentials;
import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import org.example.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ClientRepository {

    private final TodoListGrpc.TodoListBlockingStub stub;

    ClientRepository(){
        String target = "localhost:50051";
        ManagedChannel channel = Grpc.newChannelBuilder(target, InsecureChannelCredentials.create())
                .build();

        stub = TodoListGrpc.newBlockingStub(channel);
    }

    public void gravarTarefa(String tarefa){
        TodoItem item = TodoItem.newBuilder().setToDoMessage(tarefa).setId(1).setStatus("Incompleto").build();
        SaveTodoItemReply response;
        try {
            response = stub.saveTodoItem(item);
            System.out.println(response.toString());
        } catch (StatusRuntimeException e) {
            System.out.println("Falha ao fazer chamada de gravação");
            System.out.println(e.toString());
            return;
        }
    }

    public List<TodoItem> lerTarefas(){
        AllTodoItemRequest item = AllTodoItemRequest.newBuilder().build();
        TodoItemReply response;
        try {
            response = stub.getAllTodoItems(item);
            return response.getItemsList();
        } catch (StatusRuntimeException e) {
            System.out.println("Falha ao fazer chamada de gravação");
            System.out.println(e.toString());
            return new ArrayList<TodoItem>();
        }
    }
}
