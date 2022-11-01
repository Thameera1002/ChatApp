package com.tm.chat_app.utill;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import jdk.nashorn.internal.runtime.Scope;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;

public class Client {

    //This class in to initialize a client
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String userName;
    private VBox vBox;
    private BufferedImage bufferedImage;
    private Image fxImage;
    private VBox senderVBox;


    public Client(Socket socket,String userName,VBox vBox){
        try {
            this.socket=socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.userName=userName;
            this.vBox = vBox;
        } catch (IOException e) {
            closeAll(this.socket,this.bufferedReader,this.bufferedWriter);
        }
    }

    public void closeAll(Socket socket ,BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
                if(bufferedReader != null) bufferedReader.close();
                if (bufferedWriter != null) bufferedWriter.close();
                if(socket != null) socket.close();
        }catch (Exception e){

        }
    }
}
