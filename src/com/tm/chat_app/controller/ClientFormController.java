package com.tm.chat_app.controller;

import javafx.scene.input.MouseEvent;

public class ClientFormController {

    String clientName;

    public void initialize(){
        System.out.println("Initialize");
    }
    public void setClientName(String name){
        clientName=name;
        System.out.println(clientName);
    }
    public void exitClientOnClick(MouseEvent mouseEvent) {
    }
}
