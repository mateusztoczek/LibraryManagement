package com.codetreatise.controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.User;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.LibraryService;
import com.codetreatise.service.UserService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */
@Controller
public class MainAdminController implements Initializable{


	private ObservableList<User> userList = FXCollections.observableArrayList();
	
	@Autowired
	private UserService userService;
	
	@FXML
    private Button btnLogout;
	
	@FXML
    private Button btnLogin;
	
	@FXML
    private Button btnLibraries;
	
	@FXML
    private Button btnBooks;
	
	@FXML
    private Button btnUsers;
	
	@FXML
    private Button btnRentals;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	

	@FXML
	private TableView<User> userTable;

	@FXML
	private TableColumn<User, Long> colUserId;

	@FXML
	private TableColumn<User, String> colFirstName;

	@FXML
	private TableColumn<User, String> colLastName;

	@FXML
	private TableColumn<User, LocalDate> colDOB;

	@FXML
	private TableColumn<User, String> colGender;
	
	@FXML
	private TableColumn<User, String> colEmail;
	
	
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }
	
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }
	
	@FXML
    private void hire(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.HIRE);
    }
	
	@FXML
    private void logout(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.LOGIN);   
	}
	
	@FXML
    private void books(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.BOOK);  
    }
	
	@FXML
    private void rentals(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.READER);		
    }
	
	@FXML
    private void libraries(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);		 
    }
	
	@FXML
    private void users(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }
	 @FXML
	    private void plan(ActionEvent event) throws IOException{
			stageManager.newScene(FxmlView.PLANADMIN);  
	    }

	
	private void loadUserDetails(){
		userList.clear();
		userList.addAll(userService.findByRole("Admin"));

		userTable.setItems(userList);
	}
	
	private void setColumnProperties(){
		colUserId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colDOB.setCellValueFactory(new PropertyValueFactory<>("dob"));
		colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		setColumnProperties();
		loadUserDetails();
	}

}
