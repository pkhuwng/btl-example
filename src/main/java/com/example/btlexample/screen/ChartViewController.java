package com.example.btlexample.screen;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class ChartViewController {
    @FXML
    PieChart pieChart;
    @FXML
    BarChart barChart;

    public void drawPieChart(int male, int female, int other) {
        pieChart.getData().clear();
        PieChart.Data male1 = new PieChart.Data("Male", male);
        PieChart.Data female1 = new PieChart.Data("Female", female);
        PieChart.Data other1 = new PieChart.Data("Other", other);
        pieChart.getData().add(male1);
        pieChart.getData().add(female1);
        pieChart.getData().add(other1);
    }

    public void drawBarChart(int superyoung, int young, int midlife, int old) {
        barChart.getData().clear();
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Teacher");
        series1.getData().add(new XYChart.Data<>("20-29", superyoung));
        series1.getData().add(new XYChart.Data<>("30-39", young));
        series1.getData().add(new XYChart.Data<>("40-49", midlife));
        series1.getData().add(new XYChart.Data<>("50+", old));
        barChart.getData().add(series1);
    }
    public void closeModal(){
        Stage stage = (Stage) pieChart.getScene().getWindow();
        stage.close();
    }
}
