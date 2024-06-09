package com.example.btlexample.screen;

import com.example.btlexample.function.teacher.TeacherFunction;
import com.example.btlexample.function.teacher.TeacherFunctionImpl;
import com.example.btlexample.objects.TeacherObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SignupViewController implements Initializable {
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField fullnameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox<String> genderField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField majorField;
    @FXML
    private DatePicker birthdayField;
    @FXML
    private TextField degreeField;
    @FXML
    private TextField classField;
    @FXML
    private Label validateLabel;

    private Parent root;
    private Scene scene;
    private Stage stage;
    private String[] genderList = {"Male", "Female", "Other"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderField.getItems().addAll(genderList);
    }

    public void signup(ActionEvent event) throws IOException {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String gender = genderField.getValue();
        String age = ageField.getText();
        String major = majorField.getText();
        LocalDate birthday = birthdayField.getValue();
        String degree = degreeField.getText();
        String class_ = classField.getText();
        int ageInt;

        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || fullname.isEmpty() || email.isEmpty() || phone.isEmpty() || address.isEmpty() || gender.isEmpty() || age.isEmpty() || major.isEmpty() || birthday == null || degree.isEmpty()) {
            validateLabel.setText("Please fill all the fields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            validateLabel.setText("Passwords do not match");
            return;
        }
        if (!phone.matches("^(\\+\\d{1,2}\\s?)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$")){
            validateLabel.setText("Invalid phone number");
            return;
        }
        try {
            ageInt = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            validateLabel.setText("Invalid age");
            return;
        }

        TeacherObject teacherObject = new TeacherObject();
        teacherObject.setTeacher_age(ageInt).setTeacher_username(username).setTeacher_password(password).setTeacher_address(address)
                .setTeacher_gender(gender).setTeacher_class(class_).setTeacher_phone(phone).setTeacher_birthday(String.valueOf(birthday)).setTeacher_fullname(fullname)
                .setTeacher_major(major).setTeacher_degree(degree).setTeacher_email(email);

        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());
        if (teacherFunction.addTeacher(teacherObject)){
            //auto login
            TeacherApp.setTeacherObject(teacherObject);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("welcome-view.fxml"));
            root = fxmlLoader.load();

            WelcomeViewController welcomeViewController = fxmlLoader.getController();
            //welcomeViewController.displayName(teacherObject.getTeacher_fullname());
            welcomeViewController.displayName(TeacherApp.getTeacherObject().getTeacher_fullname());

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
