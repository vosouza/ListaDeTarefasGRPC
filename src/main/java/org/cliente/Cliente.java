package org.cliente;

import org.example.Server;

import java.util.logging.Logger;

public class Cliente {

    private static final Logger logger = Logger.getLogger(Server.class.getName());
    public static void main(String[] args) {
        new MenuOpcoes().exibirMenu();
    }
}
