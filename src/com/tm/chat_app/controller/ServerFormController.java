package com.tm.chat_app.controller;

import com.tm.chat_app.utill.Server;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerFormController {

    public ScrollPane scrollPane;
    public VBox vbox_msgs;

    public TextField txtMessageBox;

    private Server server;
    public void initialize(){
        new Thread(()->{
            try {
                ServerSocket serverSocket = new ServerSocket(5000);

                System.out.println("Server Started...");
                displayMessageOnRight("Server Started...");
                server = new Server(serverSocket);
                server.startServer(vbox_msgs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
        vbox_msgs.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue((Double) newValue);
        });
    }

    public static void displayMessageOnRight(String messageToSend){
        if(!messageToSend.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_RIGHT);
            hBox.setPadding(new Insets(5,5,5,10));
            Text msgText = new Text(messageToSend);
            TextFlow textFlow   = new TextFlow(msgText);
            textFlow.setStyle("-fx-background-color: #2c3e50; -fx-background-radius: 10 10 10 10");
            textFlow.setPadding(new Insets(5,5,5,10));
            msgText.setFill(Color.WHITE);
            hBox.getChildren().add(textFlow);

        }
    }

    public static void displayMessageOnLeft(String messageFromClient, VBox vBox){
        if(!messageFromClient.isEmpty()){
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5,5,5,10));
            Text msgText = new Text(messageFromClient);
            TextFlow textFlow   = new TextFlow(msgText);
            textFlow.setStyle("-fx-background-color: #16a085; -fx-background-radius: 10 0 10 10");
            textFlow.setPadding(new Insets(5,10,5,10));
            msgText.setFill(Color.WHITE);
            hBox.getChildren().add(textFlow);
            Platform.runLater(()->{
                vBox.getChildren().add(hBox);
            });
        }
    }

    public void shutdownServerOnClick(MouseEvent mouseEvent) {
        server.closeServerSocket();
        Platform.exit();
    }
}

