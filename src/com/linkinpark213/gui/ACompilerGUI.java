package com.linkinpark213.gui;/**
 * Created by ooo on 2017/7/17 0017.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ACompilerGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setScene(new Scene(anchorPane));
        primaryStage.setTitle("A Simple Compiler");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
