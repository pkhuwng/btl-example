package com.example.btlexample.screen;

import com.example.btlexample.function.teacher.TeacherFunction;
import com.example.btlexample.function.teacher.TeacherFunctionImpl;
import com.example.btlexample.objects.TeacherObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EditTeacherViewController implements Initializable {
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField addressField;
    @FXML
    private ChoiceBox genderField;
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
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private TextField fullnameField;
    @FXML
    private Label editingLabel;

    private TeacherObject teacherObject;

    private String[] genderList = {"Male", "Female", "Other"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderField.getItems().addAll(genderList);
    }

    public void setEditingLabel(String name) {editingLabel.setText("Editing: "+name);}

    public void initData(TeacherObject teacherObject) {
        setEditingLabel(teacherObject.getTeacher_fullname());

        usernameField.setText(teacherObject.getTeacher_username());
        usernameField.setEditable(false);
        fullnameField.setText(teacherObject.getTeacher_fullname());
        emailField.setText(teacherObject.getTeacher_email());
        addressField.setText(teacherObject.getTeacher_address());
        genderField.setValue(teacherObject.getTeacher_gender());
        addressField.setText(teacherObject.getTeacher_address());
        majorField.setText(teacherObject.getTeacher_major());
        degreeField.setText(teacherObject.getTeacher_degree());
        ageField.setText(String.valueOf(teacherObject.getTeacher_age()));
        phoneField.setText(teacherObject.getTeacher_phone());
        birthdayField.setValue(LocalDate.parse(teacherObject.getTeacher_birthday()));
        classField.setText(teacherObject.getTeacher_class());

        this.teacherObject = teacherObject;
    }

    public void edit(ActionEvent event){
        String username = usernameField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();
        String fullname = fullnameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String address = addressField.getText();
        String gender = String.valueOf(genderField.getValue());
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

        TeacherObject teacherObject = new TeacherObject(this.teacherObject);
        teacherObject.setTeacher_age(ageInt).setTeacher_username(username).setTeacher_password(password).setTeacher_address(address)
                .setTeacher_gender(gender).setTeacher_class(class_).setTeacher_phone(phone).setTeacher_birthday(String.valueOf(birthday)).setTeacher_fullname(fullname)
                .setTeacher_major(major).setTeacher_degree(degree).setTeacher_email(email);

        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());
        if (teacherFunction.editTeacher(teacherObject)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Records for "+teacherObject.getTeacher_fullname()+" updated!");
            alert.initOwner(validateLabel.getScene().getWindow());
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("Failed to update records for "+this.teacherObject.getTeacher_fullname());
            alert.initOwner(validateLabel.getScene().getWindow());
            alert.showAndWait();
        }
    }

}
