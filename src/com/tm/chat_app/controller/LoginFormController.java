package com.tm.chat_app.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField tfUserName;
    public AnchorPane loginFormContext;


    public void startChatOnAction(ActionEvent actionEvent) throws IOException {
        if (!tfUserName.getText().trim().isEmpty()){
           /* Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ClientForm.fxml"))));
            stage.setTitle(tfUserName.getText());
            Stage st = (Stage)loginFormContext.getScene().getWindow();
            st.close();
            stage.show(); */
            Stage st = (Stage)loginFormContext.getScene().getWindow();
            st.setTitle(tfUserName.getText());
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/ClientForm.fxml"));
            Parent parent = fxmlLoader.load();
            ClientFormController controller = fxmlLoader.getController();
            controller.setClientName(tfUserName.getText());
            st.setScene(new Scene(parent));
        }else {
            new Alert(Alert.AlertType.WARNING,"User Name is required !").show();
        }
    }
}
