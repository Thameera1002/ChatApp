package com.tm.chat_app.utill;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> allClients = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;

    private VBox vBox;
    public String userName;

    public ClientHandler(Socket socket,VBox vBox){
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.printWriter = new PrintWriter(socket.getOutputStream(),true);
            this.userName= bufferedReader.readLine();
            this.vBox=vBox;
            allClients.add(this);
        }catch (IOException e){

        }
    }

    public void closeAll(Socket socket ,BufferedReader bufferedReader, BufferedWriter bufferedWriter){
        try {
            if(bufferedReader != null) bufferedReader.close();
            if (bufferedWriter != null) bufferedWriter.close();
            if(socket != null) socket.close();
        }catch (Exception e){
            closeAll(this.socket,this.bufferedReader,this.bufferedWriter);
        }
    }

    @Override
    public void run() {

    }
}
