package com.example.btlexample.screen;

import com.example.btlexample.objects.TeacherObject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class StatsViewController {
    @FXML
    private Label total;
    @FXML
    private Label male;
    @FXML
    private Label female;
    @FXML
    private Label averageage;
    @FXML
    private Label superyoung;
    @FXML
    private Label young;
    @FXML
    private Label midlife;
    @FXML
    private Label old;
    @FXML
    private Label phd;

    int _total = 0;
    int _other=0;
    int _male=0;
    int _female=0;
    double _averageage = 0;
    int _superyoung = 0;
    int _young=0;
    int _midlife=0;
    int _old=0;
    int _phd=0;
    public void closeModal(){
        Stage stage = (Stage) total.getScene().getWindow();
        stage.close();
    }

    public void populateData(ArrayList<TeacherObject> teacherObjects){
        _total = teacherObjects.size();
        for (TeacherObject teacherObject : teacherObjects) {
            _averageage += teacherObject.getTeacher_age();
            if (isBetween(teacherObject.getTeacher_age(), 20, 29)) {
                _superyoung += 1;
            } else if (isBetween(teacherObject.getTeacher_age(), 30, 39)) {
                _young += 1;
            } else if (isBetween(teacherObject.getTeacher_age(), 40, 49)) {
                _midlife += 1;
            } else if (teacherObject.getTeacher_age() >= 50) {
                _old += 1;
            }
            if (Objects.equals(teacherObject.getTeacher_gender(), "Male")) {
                _male += 1;
            } else if (Objects.equals(teacherObject.getTeacher_gender(), "Female")) {
                _female += 1;
            } else if (Objects.equals(teacherObject.getTeacher_gender(), "Other")) {
                _other +=1;
            }
            if (Objects.equals(teacherObject.getTeacher_degree(), "PhD")) {
                _phd += 1;
            }
        }
        _averageage /= _total;

        this.total.setText("Total number of teacher: "+ _total);
        this.male.setText("Number of male teachers: "+ _male);
        this.female.setText("Number of female teachers: "+ _female);
        this.averageage.setText("Average age of teachers: "+ String.format("%.2f",_averageage));
        this.superyoung.setText("Number of teachers from 20-29: "+ _superyoung);
        this.young.setText("Number of teachers from 30-39: "+ _young);
        this.midlife.setText("Number of teachers from 40-49: "+ _midlife);
        this.old.setText("Number of teachers from 50+: "+ _old);
        this.phd.setText("Number of PhD teacher: "+ _phd);
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }

    public void viewChart(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("chart-view.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Chart");
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(total.getScene().getWindow());

        ChartViewController chartViewController = fxmlLoader.getController();
        chartViewController.drawPieChart(_male,_female,_other);
        chartViewController.drawBarChart(_superyoung,_young,_midlife,_old);
        stage.showAndWait();
    }
}
