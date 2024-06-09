package com.example.btlexample.screen;

import com.example.btlexample.function.teacher.TeacherFunction;
import com.example.btlexample.function.teacher.TeacherFunctionImpl;
import com.example.btlexample.objects.TeacherObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginViewController {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label validateLabel;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void login(ActionEvent actionEvent) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            validateLabel.setText("Username and Password are required");
            return;
        }
        if (!username.matches("^[a-zA-Z0-9_-]{3,15}$")){
            validateLabel.setText("Username is not valid");
            return;
        }

        //ConnectionPool connectionPool = ???????;
        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());

        //TeacherObject teacherObject = teacherFunction.getTeacher(username, password);
        TeacherApp.setTeacherObject(teacherFunction.getTeacher(username, password));
        TeacherObject teacherObject=TeacherApp.getTeacherObject();
        if (teacherObject == null) {
            validateLabel.setText("Username or Password is incorrect");
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome-view.fxml"));
        root = fxmlLoader.load();

        WelcomeViewController welcomeViewController = fxmlLoader.getController();
        welcomeViewController.displayName(TeacherApp.getTeacherObject().getTeacher_fullname());

        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onSignupRedirClick(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("signup-view.fxml")));
        stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
