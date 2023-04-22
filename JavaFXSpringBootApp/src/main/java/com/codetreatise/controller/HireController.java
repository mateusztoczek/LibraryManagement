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

import com.codetreatise.bean.User;
import com.codetreatise.bean.Book;
import com.codetreatise.bean.Hire;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.UserService;
import com.codetreatise.service.HireService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

@Controller
public class HireController implements Initializable{

	@FXML
    private Button btnLogout;
	
	@FXML
    private TextField idReader;

    @FXML
    private TextField idCopy;
    
    @FXML
    private TextField penalty;

    @FXML
    private Button reset;
	
    @FXML
    private Button btnRefresh;
    
	@FXML
    private Button saveUser;
	
	@FXML
	private TableView<Hire> hireTable;

	@FXML
	private TableColumn<Hire, Long> colHireId;

	@FXML
	private TableColumn<Hire, Long> colReader;
	
	@FXML
	private TableColumn<Hire, Long> colCopy;

	@FXML
	private TableColumn<Hire, LocalDate> colHireDate;
	
	@FXML
    private TableColumn<Hire, LocalDate> colReturnDate;

	@FXML
	private TableColumn<Hire, Integer> colPenalty;
	
	@FXML
    private MenuItem deleteUsers;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	
	@Autowired
	private HireService hireService;
	
	private ObservableList<Hire> hireList = FXCollections.observableArrayList();
	
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
    private void clearfields(ActionEvent event) throws IOException{
		clearFields();
    }
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }
	
	@FXML
    private void refresh(ActionEvent event) throws IOException{
		loadUserDetails();
    }
	
	@FXML
    private void onlyreturned(ActionEvent event) throws IOException{
			List<Hire> hire = hireService.findByDateBack(null);
			loadUserDetails(hire);
			clearFields();
			return;
    }
	
	@FXML
    private void unreturned(ActionEvent event) throws IOException{
			List<Hire> hire = hireService.findByDateBack(null);
			loadUserDetails(hire);
			clearFields();
			return;
    }
	
	@FXML
    private void nopenalty(ActionEvent event) throws IOException{
			List<Hire> hire = hireService.findByPenalty(0);
			loadUserDetails(hire);
			clearFields();
			return;
    }
	
    @FXML
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    
    @FXML
    private void readers(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    
    @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
    
    @FXML
    private void saveUser(ActionEvent event){
    	
    	Long readerID = new Long(getReaderID());
    	Long copyID = new Long(getCopyID());
    	Integer penalty =getPenalty();
    	 
		if(!(readerID==0)) {
			List<Hire> hire = hireService.findByReader_id(readerID);
			loadUserDetails(hire);
			clearFields();
			return;
		}
		if(!(copyID==0)) {
			List<Hire> hire = hireService.findByCopy_id(copyID);
			loadUserDetails(hire);
			clearFields();
			return;
		}
		if(!(penalty==0)) {
			List<Hire> hire = hireService.findByPenalty(penalty);
			loadUserDetails(hire);
			clearFields();
			return;
		}
		
		
		
    	clearFields();
    }
    
   	private void clearFields() {
   		idReader.clear();
		idCopy.clear();
		penalty.clear();
	}
   	
   	private void loadUserDetails(List<Hire> hire){
		hireList.clear();
		hireList.addAll(hire);
		
		hireTable.setItems(hireList);
	}
	
	private void saveAlert(User user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user "+user.getFirstName()+" "+user.getLastName() +" has been created and \n"+getGenderTitle(user.getGender())+" id is "+ user.getId() +".");
		alert.showAndWait();
	}
	
	private void updateAlert(User user){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("User updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user "+user.getFirstName()+" "+user.getLastName() +" has been updated.");
		alert.showAndWait();
	}
	
	private String getGenderTitle(String gender){
		return (gender.equals("Male")) ? "his" : "her";
	}

	public Integer getReaderID() {
		Integer year;
		try {
			   year = Integer.parseInt(idReader.getText()) ;
			}
			catch (NumberFormatException e) {
			   year = 0;
			}
		return year; 
	}

	public Integer getCopyID() {
		Integer year;
		try {
			   year = Integer.parseInt(idCopy.getText()) ;
			}
			catch (NumberFormatException e) {
			   year = 0;
			}
		return year; 
	}

	public Integer getPenalty() {
		Integer year;
		try {
			   year = Integer.parseInt(idReader.getText()) ;
			}
			catch (NumberFormatException e) {
			   year = 0;
			}
		return year; 
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setColumnProperties();
		
		// Add all users into table
		loadUserDetails();
	}
	
	
	
	/*
	 *  Set All userTable column properties
	 */
	private void setColumnProperties(){
		colHireId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colReader.setCellValueFactory(new PropertyValueFactory<>("idReader"));
		colCopy.setCellValueFactory(new PropertyValueFactory<>("idCopy"));
		colHireDate.setCellValueFactory(new PropertyValueFactory<>("dateHire"));
		colReturnDate.setCellValueFactory(new PropertyValueFactory<>("dateBack"));
		colPenalty.setCellValueFactory(new PropertyValueFactory<>("penalty"));
	}
	
	
	
	/*
	 *  Add All users to observable list and update table
	 */
	private void loadUserDetails(){
		hireList.clear();
		hireList.addAll(hireService.findAll());

		hireTable.setItems(hireList);
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
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
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
