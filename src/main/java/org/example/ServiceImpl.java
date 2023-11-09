package org.example;

import io.grpc.stub.StreamObserver;

import java.util.logging.Logger;

public class ServiceImpl extends TodoListGrpc.TodoListImplBase {
    private final Logger logger;
    private final Repository repository = new Repository();

    public ServiceImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void saveTodoItem(TodoItem request, StreamObserver<SaveTodoItemReply> responseObserver) {
        SaveTodoItemReply reply;
        try {
            repository.addItem(request);
            logger.info("NOVO ITEM:" + request.toString());
            reply = SaveTodoItemReply.newBuilder().setStatus(SaveTodoItemReply.SaveStatus.SUCCESS).build();
        } catch (Exception e){
            reply = SaveTodoItemReply.newBuilder().setStatus(SaveTodoItemReply.SaveStatus.FAILED).build();
        }
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void getAllTodoItems(AllTodoItemRequest request, StreamObserver<TodoItemReply> responseObserver) {
        TodoItemReply reply = TodoItemReply.newBuilder().addAllItems( repository.listAll() ).build();
        logger.info("Lista de tarefas enviadas;");
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
