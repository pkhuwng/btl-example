package com.example.btlexample.screen;

import com.example.btlexample.function.teacher.TeacherFunctionImpl;
import com.example.btlexample.objects.TeacherObject;
import com.example.btlexample.utils.ConnectionPool;
import com.example.btlexample.utils.ConnectionPoolImpl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Arrays;

public class TeacherApp extends Application {
    //initiate connectionPool
    private static final ConnectionPool connectionPool = new ConnectionPoolImpl();

    public static ConnectionPool getConnectionPool() {
        return connectionPool;
    }
    //spawn our own user
    private static TeacherObject teacherObject = new TeacherObject();
    public static TeacherObject getTeacherObject() {
        return teacherObject;
    }
    public static void setTeacherObject(TeacherObject teacherObject) {
        TeacherApp.teacherObject = teacherObject;
    }

    //start app
    @Override
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(TeacherApp.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Teacher App");
            stage.setScene(scene);
            stage.show();

            stage.setOnCloseRequest(event -> {
                event.consume();
                quit(stage);
            });

        } catch (Exception e) {
            exceptionHandler(e);
        }
    }
    //exit app handler
    public void quit(Stage stage){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Quit");
        alert.setHeaderText("You're about to quit!");
        alert.setContentText("Are you sure you want to quit the application?");

        if (alert.showAndWait().get() == ButtonType.OK){
            try {
                TeacherApp.getConnectionPool().releaseConnection(TeacherApp.getConnectionPool().getConnection("Teacher"),"Teacher" );
            } catch (SQLException e) {
                TeacherFunctionImpl.printSQLException(e);
            }
            System.out.println("You successfully quitted the app");
            stage.close();
        }
    }
    public static void exceptionHandler(@NotNull Exception e){
        e.printStackTrace(System.err);
        System.err.println("Message: "+e.getMessage());
        System.err.println("StackTrace: "+ Arrays.toString(e.getStackTrace()));
        System.err.println("Caused by: "+e.getCause());
    }
    public static void main(String[] args) {
        launch();
    }
}