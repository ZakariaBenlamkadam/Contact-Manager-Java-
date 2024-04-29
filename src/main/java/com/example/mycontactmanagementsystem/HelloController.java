package com.example.mycontactmanagementsystem;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class HelloController {

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainForm;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private double x = 0.0;
    private double y = 0.0;
    
    //and here create a table admin with columns 'username' and 'password' so u can log in 

    public void loginAdmin() {
        String sql = "SELECT * FROM admin WHERE username = ? and password = ?";
        this.connect = DatabaseConnection.connectDb();

        try {
            this.prepare = this.connect.prepareStatement(sql);
            this.prepare.setString(1, this.username.getText());
            this.prepare.setString(2, this.password.getText());
            this.result = this.prepare.executeQuery();
            Alert alert;
            if (!this.username.getText().isEmpty() && !this.password.getText().isEmpty()) {
                if (this.result.next()) {
                    getData.username = this.username.getText();
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText((String) null);
                    alert.setContentText("Successfully Login");
                    alert.showAndWait();
                    this.loginButton.getScene().getWindow().hide();
                    Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("dashboard.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    root.setOnMousePressed((event) -> {
                        this.x = event.getSceneX();
                        this.y = event.getSceneY();
                    });
                    root.setOnMouseDragged((event) -> {
                        stage.setX(event.getScreenX() - this.x);
                        stage.setY(event.getScreenY() - this.y);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText((String) null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText((String) null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }


    public void exit() {
        System.exit(0);

    }
}