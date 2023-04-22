package com.codetreatise.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Reader;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.ReaderService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ReaderController implements Initializable{

	@FXML
    private Button btnLogout;
	
	@FXML
    private Button btnUnlock;
	
	@FXML
    private Button btnBlock;
	
	@FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    
    @FXML
    private RadioButton rbUnlocked;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton rbBlocked;
   
    @FXML
    private TextField blockreadertextbox;

    @FXML
    private TextField unlockreadertextbox;
    
    
    @FXML
    private Button reset;
	
	@FXML
    private Button saveUser;
	
	@FXML
	private TableView<Reader> readerTable;

	@FXML
	private TableColumn<Reader, Long> colReaderId;

	@FXML
	private TableColumn<Reader, String> colName;

	@FXML
	private TableColumn<Reader, LocalDate> colSurname;

	@FXML
	private TableColumn<Reader, LocalDate> colStatus;

	
	@FXML
    private MenuItem deleteUsers;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private ReaderService readerService;
	
	private ObservableList<Reader> readerList = FXCollections.observableArrayList();
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }
	
	@Autowired
	private MyBeanID myBean;
	
	@FXML
    private void addbook(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.BOOK);  
    }
	
	@FXML
    private void mainwindow(ActionEvent event) throws IOException{
		String userRole= myBean.getRoleUSer();
		if (userRole.contains("Admin")) {
			stageManager.switchScene(FxmlView.MAINADMIN);
		}
		if (userRole.contains("User")) {
			stageManager.switchScene(FxmlView.MAINWORKER);
		}
		if (userRole.contains("Owner")) {
			stageManager.switchScene(FxmlView.MAINOWNER);
			
		}
    }
	
	@FXML
    private void clearfields(ActionEvent event) throws IOException{
		clearFields();
    }
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }
	
	
    @FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    @FXML
    private void library(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);  
    }
	
    
    @FXML
    private void readers(ActionEvent event) throws IOException {
    	stageManager.newScene(FxmlView.READER);    	
    }
    
    @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
    
    public Integer getBlockText() {
    	
    	Integer year;
    	try {
    		   year = Integer.parseInt(blockreadertextbox.getText()) ;
    		}
    		catch (NumberFormatException e) {
    		   year = 0;
    		}
    	return year; 
    			
	}
    
  public Integer getUnlockText() {
    	
    	Integer year;
    	try {
    		   year = Integer.parseInt(unlockreadertextbox.getText()) ;
    		}
    		catch (NumberFormatException e) {
    		   year = 0;
    		}
    	return year; 
    			
	}
    
    @FXML
    void block(ActionEvent event) {
    	Long readerID=new Long(getBlockText());
    	if(readerID!=0) {
    		Reader reader = readerService.find(readerID);
    		if(reader.getStatus()) {
    			reader.setStatus(false);
    			readerService.update(reader);
    			updateAlert(reader);
    		}
    		else {
    			Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Block reader Error");
    	        alert.setContentText("Reader is already blocked!");
    	        alert.showAndWait();
    		}
		
    	
    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Block reader Error");
	        alert.setContentText("Reader doesn't exist!");
	        alert.showAndWait();
    	}
    		
    	loadUserDetails();
    	blockreadertextbox.clear();
    }
    
    
    @FXML
    void unlock(ActionEvent event) {
    	Long readerID=new Long(getUnlockText());
    	if(readerID!=0) {
    		Reader reader = readerService.find(readerID);
    		if(reader.getStatus()) {
    			Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Block reader Error");
    	        alert.setContentText("Reader is already unlocked!");
    	        alert.showAndWait();
    			
    		}
    		if(!reader.getStatus()) {
    			reader.setStatus(true);
    			readerService.update(reader);
    			updateAlert(reader);
    		}

    	}
    	else {
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Block reader Error");
	        alert.setContentText("Reader doesn't exist!");
	        alert.showAndWait();
    	}
    		
    	loadUserDetails();
    	unlockreadertextbox.clear();
    }
    
	private void updateAlert(Reader reader){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("reader updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user "+reader.getName()+" "+reader.getSurname() +" has been updated.");
		alert.showAndWait();
	}
    
    @FXML
    private void saveUser(ActionEvent event){
    	
    	if(validate("First Name", getFirstName(), "[a-zA-Z]+") &&
    	   validate("Last Name", getLastName(), "[a-zA-Z]+")){
    		
    				Reader reader = new Reader();
    				reader.setName(getFirstName());
    				reader.setSurname(getLastName());
    				reader.setStatus(getGender());
        			
        			Reader newReader = readerService.save(reader);
        			
        			saveAlert(newReader);
    			}
    			
    		
    		clearFields();
    		loadUserDetails();
    	}
    	
    	
    
    
    @FXML
    private void deleteUsers(ActionEvent event){
    	List<Reader> reader = readerTable.getSelectionModel().getSelectedItems();
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) readerService.deleteInBatch(reader);
    	
    	loadUserDetails();
    }
    
   	private void clearFields() {
		firstName.clear();
		lastName.clear();
		rbUnlocked.setSelected(true);
		blockreadertextbox.clear();
		unlockreadertextbox.clear();
		
	}
	
	private void saveAlert(Reader reader){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user "+reader.getName()+" "+reader.getSurname() +" has been created.\n");
		alert.showAndWait();
	}
	

	public String getFirstName() {
		return firstName.getText();
	}

	public String getLastName() {
		return lastName.getText();
	}

	public boolean getGender(){
		return rbUnlocked.isSelected();
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		readerTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		
		setColumnProperties();
		
		// Add all users into table
		loadUserDetails();
	}
	
	/*
	 *  Set All userTable column properties
	 */
	private void setColumnProperties(){
		colReaderId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colSurname.setCellValueFactory(new PropertyValueFactory<>("surname"));
		colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
	}
	
	
	/*
	 *  Add All users to observable list and update table
	 */
	private void loadUserDetails(){
		readerList.clear();
		readerList.addAll(readerService.findAll());

		readerTable.setItems(readerList);
	}
	
	/*
	 * Validations
	 */
	private boolean validate(String field, String value, String pattern){
		if(!value.isEmpty()){
			Pattern p = Pattern.compile(pattern);
	        Matcher m = p.matcher(value);
	        if(m.find() && m.group().equals(value)){
	            return true;
	        }else{
	        	validationAlert(field, false);            
	            return false;            
	        }
		}else{
			validationAlert(field, true);            
            return false;
		}        
    }
	

	
	private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Role")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
}
