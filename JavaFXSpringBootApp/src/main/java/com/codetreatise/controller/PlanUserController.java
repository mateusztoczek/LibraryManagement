package com.codetreatise.controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import com.codetreatise.config.StageManager;
import com.codetreatise.view.FxmlView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
public class PlanUserController implements Initializable {

	private ObservableList<String> months = FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private Label lblLogin;

    @FXML
    private Label userId;
    @FXML
    private ComboBox<String> Month;
    
    @Lazy
    @Autowired
    private StageManager stageManager;

   

    @FXML
    void close(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.MAINWORKER);  
    }



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Month.setItems(months);
		
		
	}
}
