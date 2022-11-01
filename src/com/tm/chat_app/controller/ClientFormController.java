package com.tm.chat_app.controller;

import com.tm.chat_app.utill.Client;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.Socket;

public class ClientFormController {

    public TextField txtMessageBox;
    public VBox vbox_msg;
    public Label lbl_Client;
    String clientName;
    public Client client;

    public static VBox senderVBox;

    public void initialize(){
        System.out.println("Initialize");
    }
    public void setClientName(String name){
        new Thread(()->{
            try {
                senderVBox = vbox_msg;
                lbl_Client.setText(name);
                client = new Client(new Socket("localhost",5000),name,vbox_msg);
                System.out.println("Connected to the server...");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        clientName=name;
        System.out.println(clientName);
    }
    public void exitClientOnClick(MouseEvent mouseEvent) {
    }
}
