package org.cliente;
import org.example.TodoItem;

import java.util.List;
import java.util.Scanner;

public class MenuOpcoes {
    ClientRepository repository = new ClientRepository();
    public void exibirMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Listar");
            System.out.println("2. Gravar");
            System.out.println("3. Sair");
            System.out.println("----------------");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    listar();
                    break;
                case 2:
                    gravarTarefa(scanner);
                    break;
                case 3:
                    System.out.println("Saindo do programa.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }
    }

    public void listar() {
        System.out.println("Opção Listar selecionada:");
        System.out.println("----------------");

        List<TodoItem> lista = repository.lerTarefas();
        lista.forEach( item -> {
            System.out.println("Mensagem: "+item.getToDoMessage());
            System.out.println("Status: "+item.getStatus());
            System.out.println("----------------");
        });
    }

    public void gravarTarefa(Scanner scanner) {
        System.out.println("Opção Gravar selecionada. Adicione a lógica necessária.");
        System.out.println("Digite a descrição da tarefa:");
        scanner.nextLine(); // Limpa o buffer do scanner
        String descricaoTarefa = scanner.nextLine();
        repository.gravarTarefa(descricaoTarefa);
    }
}