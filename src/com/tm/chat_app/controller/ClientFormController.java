package com.tm.chat_app.controller;

import com.tm.chat_app.utill.Client;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.Socket;

public class ClientFormController {

    public TextField txtMessageBox;
    public VBox vbox_msg;
    public Label lbl_Client;
    public Client client;

    public static String userName="";
    public static VBox senderVBox;
    public ScrollPane scrollPane;

    public void initialize(){
        System.out.println("Initialize");
    }
    public void setClientName(String name){
        new Thread(()->{
            try {
                userName=name;
                senderVBox = vbox_msg;
                lbl_Client.setText(name);
                client = new Client(new Socket("localhost",5000),name,vbox_msg);
                System.out.println("Connected to the server...");
                client.listenForMessage(vbox_msg,name);
                client.sendMessage(name+" has joined to the chat.",vbox_msg,"SERVER");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
        vbox_msg.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue((Double) newValue);
        });
    }
    public void exitClientOnClick(MouseEvent mouseEvent) {
    }

    public static void displayMessageOnRight(String messageToSend, VBox vBox){
        if(!messageToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,5,5,10));
            Text msgText = new Text(messageToSend);
            TextFlow textFlow   = new TextFlow(msgText);
            textFlow.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 10 10 0 10");
            textFlow.setPadding(new Insets(5,5,5,10));
            msgText.setFill(Color.WHITE);
            hBox.getChildren().add(textFlow);
            Platform.runLater(()->{
                vBox.getChildren().add(hBox);
            });
        }
    }

    public static void displayMessageOnLeft(String message, VBox vBox){
        if(!message.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5,5,5,10));
            Text msgText = new Text(message);
            TextFlow textFlow   = new TextFlow(msgText);
            textFlow.setStyle("-fx-background-color: #16a085; -fx-background-radius: 10 10 0 10");
            textFlow.setPadding(new Insets(5,10,5,10));
            msgText.setFill(Color.WHITE);
            hBox.getChildren().add(textFlow);
            Platform.runLater(()->{
                vBox.getChildren().add(hBox);
            });
        }
    }

    public void sendMessageOnAction(MouseEvent mouseEvent) {
        client.sendMessage(txtMessageBox.getText(),vbox_msg,userName);
        txtMessageBox.clear();
    }
}
