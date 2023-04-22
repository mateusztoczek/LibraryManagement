package com.codetreatise.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Copy;
import com.codetreatise.bean.Library;
import com.codetreatise.bean.Location;

import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.LibraryService;
import com.codetreatise.service.LocationService;
import com.codetreatise.service.UserService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;




@Controller
public class LibraryController implements Initializable{

	@Autowired
	private MyBeanID myBean;
	
	@FXML
    private Label libraryId;
	
	@FXML
    private TextField Name;

    @FXML
    private TextField Telephone;
    @FXML
    private TextField City;
    @FXML
    private TextField Street;
    @FXML
    private TextField Number;
    @FXML
    private TextField Apartment;
    @FXML
    private TextField Owner;

    @FXML
    private TextField PostCode;

    @FXML
    private TextField Employess;
    
    @FXML
    private Button reset;
	
	@FXML
    private Button save;
	
	@FXML
	private TableView<Library> libraryTable;

	@FXML
	private TableColumn<Library, Long> colId;

	@FXML
	private TableColumn<Library, String> colName;

	@FXML
	private TableColumn<Library, String> colOwner;

	@FXML
	private TableColumn<Library, String> colTelephone;

	@FXML
	private TableColumn<Library, String> colAddress;
	

	
	@FXML
    private TableColumn<Library, Boolean> colEdit;
	
	@FXML
    private MenuItem deleteLibra;
	
	@Lazy
    @Autowired
    private StageManager stageManager;
	@Autowired
	private LibraryService libraryService;
	@Autowired
	private LocationService locationService;

	@Autowired
	private UserService userService;
	
	private ObservableList<Library> libraryList = FXCollections.observableArrayList();

	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
    }

	
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
    private void logout(ActionEvent event) throws IOException {
    	stageManager.switchScene(FxmlView.LOGIN);    	
    }
    
    @FXML
    private void readers(ActionEvent event) throws IOException {
    	stageManager.newScene(FxmlView.READER);    	
    }
    
    @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
    //
    
    @FXML
    private void save(ActionEvent event){
    	
    	if(myBean.getRoleUSer().contains("Admin") ){
    		
    		if(libraryId.getText() == null || libraryId.getText() == ""){
    			if(userService.existsById(Long.parseLong(getOwner()))&&userService.find(Long.parseLong(getOwner())).getRole().contains("Owner")){
    				
    				Library library = new Library();
    				library.setName(getName());
    				library.setOwner(userService.find(Long.parseLong(getOwner())));
    				Location location = new Location();
    				location.setApartment(Integer.parseInt(getApartment()));
    				location.setCity(getCity());
    				location.setNumber(getNumber());
    				location.setPostCode(getPostCode());
    				location.setStreet(getStreet());
    				
    				Location newLocation= locationService.save(location);
    				library.setLocation(newLocation);
    				library.setTelephone(getTelephone());
        			
    				Library newLibrary = libraryService.save(library);
        			
        			saveAlert(newLibrary);
    			}
    			else
    			{
    				Alert alert = new Alert(AlertType.WARNING);
    		        alert.setTitle("Validation Error");
    		        alert.setHeaderText(null);
    		        alert.setContentText("The indicated user does not have the qualifications of an owner");
    		        
    		        alert.showAndWait();
    			}
    			
    		}else{
    			Library library = libraryService.find(Long.parseLong(libraryId.getText()));
    			library.setName(getName());
    			Location location = library.getLocation();
    			location.setApartment( Integer.parseInt(getApartment()));
				location.setCity(getCity());
				location.setNumber(getNumber());
				location.setPostCode(getPostCode());
				location.setStreet(getStreet());
				Library updatedLibrary =  libraryService.update(library);
    			updateAlert(updatedLibrary);
    		}
    		
    		clearFields();
    		loadUserDetails();
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Validation Error");
	        alert.setHeaderText(null);
	        alert.setContentText("You do not have permission");
	        
	        alert.showAndWait();
    	}
    	
    	
    }
    
    @FXML
    private void deleteLibra(ActionEvent event){
    	if(myBean.getRoleUSer().contains("Admin") ){
    	List<Library> library = libraryTable.getSelectionModel().getSelectedItems();
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete selected?");
		Optional<ButtonType> action = alert.showAndWait();
		
		if(action.get() == ButtonType.OK) libraryService.deleteInBatch(library);
    	
    	loadUserDetails();
    	}
    	else
    	{
    		Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("Validation Error");
	        alert.setHeaderText(null);
	        alert.setContentText("You do not have permission");
	        
	        alert.showAndWait();
    	}
    }
    
   	private void clearFields() {
   		libraryId.setText(null);
   		Name.clear();
   		Telephone.clear();
   		City.clear();
   		Street.clear();
   		Number.clear();
   		Apartment.clear();
   		PostCode.clear();
		Owner.clear();
		Employess.clear();
	}
	
	private void saveAlert(Library library){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Library saved successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The library "+library.getName()+" "+library.getId()+" has been created.");
		alert.showAndWait();
	}
	
	private void updateAlert(Library library){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Library updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The library "+library.getName()+" "+library.getId()+" has been updated.");
		alert.showAndWait();
	}
	


	public String getName() {
		return Name.getText();
	}

	public String getTelephone() {
		return Telephone.getText();
	}

	public String getCity() {
		return City.getText();
	}

	public String getStreet(){
		return Street.getText();
	}
	
	public String getNumber() {
		return Number.getText();
	}

	public String getApartment() {
		return Apartment.getText();
	}

	public String getPostCode() {
		return PostCode.getText();
	}
	public String getEmployess() {
		return Employess.getText();
	}
	public String getOwner() {
		return Owner.getText();
	}

	public void setColumnProperties(){
		colId.setCellValueFactory(cd-> new SimpleLongProperty(((Library)cd.getValue()).getId()).asObject());

		colName.setCellValueFactory(cd-> new SimpleStringProperty(((Library)cd.getValue()).getName()));
		colOwner.setCellValueFactory(cd-> new SimpleStringProperty(((Library)cd.getValue()).getOwnerName()));
		colTelephone.setCellValueFactory(cd-> new SimpleStringProperty(((Library)cd.getValue()).getTelephone()));
		colAddress.setCellValueFactory(cd-> new SimpleStringProperty(((Library)cd.getValue()).getAddress()));
		colEdit.setCellFactory(cellFactory);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		libraryTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		setColumnProperties();
		loadUserDetails();

	}
	
	
	
	Callback<TableColumn<Library, Boolean>, TableCell<Library, Boolean>> cellFactory = 
			new Callback<TableColumn<Library, Boolean>, TableCell<Library, Boolean>>()
	{
		@Override
		public TableCell<Library, Boolean> call( final TableColumn<Library, Boolean> param)
		{
			final TableCell<Library, Boolean> cell = new TableCell<Library, Boolean>()
			{
				Image imgEdit = new Image(getClass().getResourceAsStream("/images/edit.png"));
				final Button btnEdit = new Button();
				
				@Override
				public void updateItem(Boolean check, boolean empty)
				{
					super.updateItem(check, empty);
					if(empty)
					{
						setGraphic(null);
						setText(null);
					}
					else{
						btnEdit.setOnAction(e ->{
							Library library = getTableView().getItems().get(getIndex());
							updateUser(library);
						});
						
						btnEdit.setStyle("-fx-background-color: transparent;");
						ImageView iv = new ImageView();
				        iv.setImage(imgEdit);
				        iv.setPreserveRatio(true);
				        iv.setSmooth(true);
				        iv.setCache(true);
						btnEdit.setGraphic(iv);
						
						setGraphic(btnEdit);
						setAlignment(Pos.CENTER);
						setText(null);
					}
				}

				private void updateUser(Library library) {
					libraryId.setText(Long.toString(library.getId()));
					Name.setText(library.getName());
					Owner.setText(Long.toString(library.getOwner()));
					Location location= library.getLocation();
					Telephone.setText(library.getTelephone());
					City.setText(location.getCity());
					Street.setText(location.getStreet());
					Number.setText(location.getNumber());
					Apartment.setText(String.valueOf(location.getApartment()));
					PostCode.setText(location.getPostCode());
					Employess.setText(String.valueOf(library.getNumEmployees()));
					
				}
			};
			return cell;
		}
	};

	private void loadUserDetails(){
		libraryList.clear();
		libraryList.addAll(libraryService.findAll());

		libraryTable.setItems(libraryList);
	}
	
}
