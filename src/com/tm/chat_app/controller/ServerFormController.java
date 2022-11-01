package com.tm.chat_app.controller;

import com.tm.chat_app.utill.Server;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerFormController {

    public ScrollPane scrollPane;
    public VBox vbox_msgs;

    private Server server;
    public void initialize(){
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(5000);

                System.out.println("Server Started...");
                server = new Server(serverSocket);
                server.startServer(vbox_msgs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public TextField txtMessageBox;

    public void shutdownServerOnClick(MouseEvent mouseEvent) {
    }
}

