package com.codetreatise.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */
@Controller
public class MainWorkerController implements Initializable{
	@Autowired
	private MyBeanID myBean;

	@FXML
    private Button btnLogout;
	
	@FXML
    private Button btnLogin;
	
	@FXML
    private Button btnCopies;
	
	@FXML
    private Button btnBooks;
	
	@FXML
    private Button btnReaders;
	
	@FXML
    private Button btnUsers;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }
	
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }
	
	@FXML
    private void logout(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.LOGIN);   
	}
	
	@FXML
    private void readers(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.READER);   
	}
	
	@FXML
    private void books(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.BOOK);  
    }
	@FXML
    private void libraries(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);  
    }
	@FXML
    private void plan(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.PLANUSER);  
    }
	
	@FXML
    private void hire(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.HIRE);
    }
	
	
	@FXML
    private void users(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
