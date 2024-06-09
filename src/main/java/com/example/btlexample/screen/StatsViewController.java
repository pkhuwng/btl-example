package com.example.btlexample.screen;

import com.example.btlexample.objects.TeacherObject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public void closeModal(){
        Stage stage = (Stage) total.getScene().getWindow();
        stage.close();
    }

    public void populateData(ArrayList<TeacherObject> teacherObjects){
        int total = teacherObjects.size();
        int male=0;
        int female=0;
        double averageage = 0;
        int superyoung = 0;
        int young=0;
        int midlife=0;
        int old=0;
        int phd=0;
        for (TeacherObject teacherObject : teacherObjects) {
            averageage += teacherObject.getTeacher_age();
            if (isBetween(teacherObject.getTeacher_age(), 20, 29)) {
                superyoung += 1;
            } else if (isBetween(teacherObject.getTeacher_age(), 30, 39)) {
                young += 1;
            } else if (isBetween(teacherObject.getTeacher_age(), 40, 49)) {
                midlife += 1;
            } else if (teacherObject.getTeacher_age() >= 50) {
                old += 1;
            }
            if (Objects.equals(teacherObject.getTeacher_gender(), "Male")) {
                male += 1;
            } else if (Objects.equals(teacherObject.getTeacher_gender(), "Female")) {
                female += 1;
            }
            if (Objects.equals(teacherObject.getTeacher_degree(), "PhD")) {
                phd += 1;
            }
        }
        averageage /= total;

        this.total.setText("Total number of teacher: "+ total);
        this.male.setText("Number of male teachers: "+ male);
        this.female.setText("Number of female teachers: "+ female);
        this.averageage.setText("Average age of teachers: "+ String.format("%.2f",averageage));
        this.superyoung.setText("Number of teachers from 20-29: "+ superyoung);
        this.young.setText("Number of teachers from 30-39: "+ young);
        this.midlife.setText("Number of teachers from 40-49: "+ midlife);
        this.old.setText("Number of teachers from 50+: "+ old);
        this.phd.setText("Number of PhD teacher: "+ phd);
    }

    public static boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
