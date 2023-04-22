package com.codetreatise.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Copy;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.CopyService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
@Controller
public class CopyOfBookController implements Initializable{
	
    @Autowired
    private CopyService copyService;
    @Autowired
	private MyBeanID myBean;
    private long idCopy;
    private long idBook;
    private long idLibrary;
    private ObservableList<Copy> copyList = FXCollections.observableArrayList();
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableColumn<Copy, Long> colCopyId;
    @FXML
    private TableColumn<Copy, String> colLibraryName;
    @FXML
    private TableColumn<Copy, String> colAddress;
    @FXML
    private TableColumn<Copy, String> colStatus;

    @FXML
    private TableView<Copy> copyTable;
    @FXML
    private MenuItem Rent;
    @FXML
    private MenuItem Details;
    @FXML
    private Button btnAvailable;
    @FXML
    private Button btnLibrary;
    @FXML
    private Button btnFindCity;
    @FXML
    private Button btnFindStreet;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnFindCode;
    @FXML
    private TextField City;
    @FXML
    private TextField Street;
    @FXML
    private TextField Code;

    
	@FXML
    private void mainwindow(ActionEvent event) throws IOException{
		if(myBean.getRoleUSer().contains("Admin")) {
			stageManager.newScene(FxmlView.MAINADMIN);  
		}
		else if(myBean.getRoleUSer().contains("Owner")) {
			stageManager.newScene(FxmlView.MAINOWNER);  
		}
		else {
			stageManager.newScene(FxmlView.MAINWORKER);  
		}
		
    }
	
	@FXML
    private void readers(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }
	
	@FXML
    private void books(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.BOOK);  
    }
	@FXML
    private void library(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);  
    }
	
	@FXML
    private void users(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }

	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

	@FXML
    private void logout(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.LOGIN);  
    }
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }
    
    private void setColumnProperties(){	
    	colCopyId.setCellValueFactory(cd-> new SimpleLongProperty(((Copy)cd.getValue()).getId()).asObject());
    	colLibraryName.setCellValueFactory((cd-> new SimpleStringProperty(((Copy)cd.getValue()).getLibraryName())));
    	colAddress.setCellValueFactory(cd-> new SimpleStringProperty(((Copy)cd.getValue()).getLocationAddress()));
    	colStatus.setCellValueFactory(cd-> new SimpleStringProperty(((Copy)cd.getValue()).getStatus()));
    	
	}
    @FXML
    void searchAvailable(ActionEvent event) {
		
		List<Copy> copy = copyService.findByBook_idBookAndStatus(this.idBook, "yes");
		loadUserDetails(copy);
		
    }
    @FXML
    void searchCity(ActionEvent event) {
		
		List<Copy> copy = copyService.findByBook_idBookAndLibrary_Location_city(this.idBook, getCity());
		loadUserDetails(copy);
    	
    }
    
    @FXML
    void searchStreet(ActionEvent event) {
		
		List<Copy> copy = copyService.findByBook_idBookAndLibrary_Location_street(this.idBook, getStreet());
		loadUserDetails(copy);
    	
    }
    
    @FXML
    void searchCode(ActionEvent event) {
		
		List<Copy> copy = copyService.findByBook_idBookAndLibrary_Location_postCode(this.idBook, getCode());
		loadUserDetails(copy);
    	
    }
    
    
    @FXML
    void searchThisLibrary(ActionEvent event) {
    	
		if(!myBean.getRoleUSer().contains("Admin")) {
			List<Copy> copys = copyService.findByLibrary_idLibrary(myBean.getIdLibrary());
		loadUserDetails(copys);
		}
    	
    }
    
    @FXML
    void Clear(ActionEvent event) {	
    }
    
	public String getCity() {
		return City.getText();
	}
	
	public String getStreet() {
		return Street.getText();
	}
	
	public String getCode() {
		return Code.getText();
	}
    
    @FXML
    void addCopie(ActionEvent event) {
    }
    
	
	private void loadUserDetails(List<Copy> copys){
		copyList.clear();
		copyList.addAll(copys);
		
		copyTable.setItems(copyList);
	}
    
    @FXML
    private void rent(ActionEvent event) throws IOException{
    	
		Copy copy = copyTable.getSelectionModel().getSelectedItem();
		this.idCopy = copy.getId();
		myBean.setIdCopy(this.idCopy);
		stageManager.newScene(FxmlView.RENT);  //------------------------------dodac rentbook  okno
    }
    
    @FXML
    private void details(ActionEvent event) throws IOException{
    	Copy copy = copyTable.getSelectionModel().getSelectedItem();
		this.idCopy = copy.getId();
		myBean.setIdCopy(this.idCopy);
    	stageManager.newScene(FxmlView.RENT); //------------------------------dodac rentbook  okno
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.idBook = myBean.getIdBook();
		
		copyTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		List<Copy> copys = copyService.findByBook_idBook(this.idBook);
		setColumnProperties();
		
		loadUserDetails(copys);	
		
	}
}
