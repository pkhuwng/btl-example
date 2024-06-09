module com.example.btlexample {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires annotations;
    requires java.desktop;

    exports com.example.btlexample.objects;
    opens com.example.btlexample.objects to javafx.base;
    exports com.example.btlexample.screen;
    opens com.example.btlexample.screen to javafx.fxml, javafx.graphics;

    exports com.example.btlexample.utils to javafx.base;
}