package com.example.btlexample.screen;

import com.example.btlexample.function.teacher.TeacherFunction;
import com.example.btlexample.function.teacher.TeacherFunctionImpl;
import com.example.btlexample.objects.TeacherObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeViewController {
    Stage stage;

    @FXML
    private Label welcomeLabel;
    @FXML
    private AnchorPane welcomeScenePane;
    @FXML
    private AnchorPane listAnchorPane;
    @FXML
    private TableView<TeacherObject> teacherTableView;
    @FXML
    private TextField searchField;

    public void displayName(String username){
        welcomeLabel.setText("Hello: "+username);
    }

    public void stats(ActionEvent event) throws IOException {
        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("stats-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Stats");
        stage.setScene(new Scene(loader.load()));
        StatsViewController controller = loader.getController();
        controller.populateData(teacherFunction.getTeachers());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(welcomeScenePane.getScene().getWindow());
        stage.showAndWait();
    }

    public void listAllTeacher(ActionEvent event){
        setUpTableView();
        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());
        ObservableList<TeacherObject> data = FXCollections.observableArrayList(teacherFunction.getTeachers());
        teacherTableView.setItems(data);
        teacherTableView.setVisible(true);
    }

    public void search(ActionEvent event){
        setUpTableView();
        TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());;
        ObservableList<TeacherObject> data = FXCollections.observableArrayList(teacherFunction.getSimilarTeachers(searchField.getText()));
        teacherTableView.setItems(data);
        teacherTableView.setVisible(true);
    }

    private void setUpTableView() {
        teacherTableView.getItems().clear();
        teacherTableView.getColumns().clear();
        TableColumn<TeacherObject, String> usernameCol = new TableColumn<>("Username");
        TableColumn<TeacherObject, String> fullnameCol = new TableColumn<>("Fullname");
        TableColumn<TeacherObject, String> emailCol = new TableColumn<>("Email");
        TableColumn<TeacherObject, String> phoneCol = new TableColumn<>("Phone");
        TableColumn<TeacherObject, String> addressCol = new TableColumn<>("Address");
        TableColumn<TeacherObject, String> genderCol = new TableColumn<>("Gender");
        TableColumn<TeacherObject, Integer> ageCol = new TableColumn<>("Age");
        TableColumn<TeacherObject, String> majorCol = new TableColumn<>("Major");
        TableColumn<TeacherObject, String> birthdayCol = new TableColumn<>("Birthday");
        TableColumn<TeacherObject, String> degreeCol = new TableColumn<>("Degree");
        TableColumn<TeacherObject, String> classCol = new TableColumn<>("Class");

        usernameCol.setCellValueFactory(new PropertyValueFactory<>("teacher_username"));
        fullnameCol.setCellValueFactory(new PropertyValueFactory<>("teacher_fullname"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("teacher_email"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("teacher_phone"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("teacher_address"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("teacher_gender"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("teacher_age"));
        majorCol.setCellValueFactory(new PropertyValueFactory<>("teacher_major"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<>("teacher_birthday"));
        degreeCol.setCellValueFactory(new PropertyValueFactory<>("teacher_degree"));
        classCol.setCellValueFactory(new PropertyValueFactory<>("teacher_class"));

        TableColumn<TeacherObject, Task> actionCol = new TableColumn<>("Edit");
        actionCol.setCellFactory(param -> new EditCell("Edit"));
        TableColumn<TeacherObject, Task> actionCol2 = new TableColumn<>("Remove");
        actionCol2.setCellFactory(param -> new RemoveCell("Remove"));


        teacherTableView.getColumns().addAll(usernameCol,fullnameCol,emailCol,phoneCol,addressCol,genderCol,ageCol,majorCol,birthdayCol,degreeCol,classCol,actionCol,actionCol2);
        teacherTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    public void logout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Are you sure you want to logout?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            TeacherApp.setTeacherObject(null);
            try {
                TeacherApp.getConnectionPool().releaseConnection(TeacherApp.getConnectionPool().getConnection("Teacher"),"Teacher" );
            } catch (SQLException e) {
                TeacherFunctionImpl.printSQLException(e);
            }
            stage = (Stage) welcomeScenePane.getScene().getWindow();
            stage.close();
        }
    }

    public void add(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addteacher-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Add Teacher");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(welcomeScenePane.getScene().getWindow());
        stage.showAndWait();
    }

    private class EditCell extends TableCell<TeacherObject, Task> {
        Stage stage;
        Parent root;
        private final Button cellButton;
        EditCell(String name) {

            cellButton = new Button(name);
            cellButton.setOnAction(event -> {
                TeacherObject record = super.getTableRow().getItem();

                stage = new Stage();
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("editteacher-view.fxml"));
                    root = fxmlLoader.load();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initOwner(super.getScene().getWindow());
                    EditTeacherViewController editTeacherViewController = fxmlLoader.getController();
                    editTeacherViewController.initData(record);
                    stage.setTitle("Editing");
                    stage.showAndWait();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                //System.out.println(record);
            });
        }

        @Override
        protected void updateItem(Task record, boolean empty) {
            super.updateItem(record, empty);
            if (!empty) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }
    private class RemoveCell extends TableCell<TeacherObject, Task> {
        private final Button cellButton;
        RemoveCell(String name) {
            cellButton = new Button(name);
            cellButton.setOnAction(event -> {
                TeacherObject record = super.getTableRow().getItem();

                if (TeacherApp.getTeacherObject().equals(record)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error!");
                    alert.setHeaderText("You can't remove yourself!");
                    alert.initOwner(super.getScene().getWindow());
                    alert.showAndWait();
                    return;
                }

                TeacherFunction teacherFunction = new TeacherFunctionImpl(TeacherApp.getConnectionPool());
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Removing: "+record.getTeacher_fullname());
                alert.setContentText("Are you sure you want to remove this?");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    if (teacherFunction.removeTeacher(record)) {
                        teacherTableView.getItems().remove(record);
                    }
                }
            });
        }

        @Override
        protected void updateItem(Task record, boolean empty) {
            super.updateItem(record, empty);
            if (!empty) {
                setGraphic(cellButton);
            } else {
                setGraphic(null);
            }
        }
    }
}
