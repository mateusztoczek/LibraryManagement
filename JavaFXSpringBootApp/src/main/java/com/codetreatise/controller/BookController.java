package com.codetreatise.controller;


import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Book;
import com.codetreatise.bean.Copy;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.BookService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;


@Controller
public class BookController implements Initializable{
    
	private ObservableList<Book> bookList = FXCollections.observableArrayList();
	
	private ObservableList<String> categories = FXCollections.observableArrayList("Fantasy", "Romance","Science-Fiction", "Crime","Adventure");
	
    @Autowired
    private BookService bookService;
	@Autowired
	private MyBeanID myBean;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @FXML
    private TextField searchbar;
    
    @FXML
    private Button btnsearch;
    
	@FXML
	private TableView<Book> bookTable;
    
	@FXML
    private TextField title;
	
	@FXML
    private TextField author;

    @FXML
    private TextField publisher;

    @FXML
    private ComboBox<String> cbCategory;

    @FXML
    private TextField releaseyear;
    
    @FXML
    private Button clear;
    
    @FXML
    private Button addBook;
    
	
    @FXML
	private TableColumn<Book, Long> colBookId;

	@FXML
	private TableColumn<Book, String> colTitle;

	@FXML
	private TableColumn<Book, String> colAuthor;

	@FXML
	private TableColumn<Book, String> colPublisher;

	@FXML
	private TableColumn<Book, String> colCategory;
	
	@FXML
    private TableColumn<Book, Integer> colRlsYear;

	@FXML
	private TableColumn<Book, Integer> colRentals;
	
	
	@FXML
    private MenuItem rentbook;
	
	
	@FXML
	private void exit(ActionEvent event) {
		Platform.exit();
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
    private void logout(ActionEvent event) throws IOException{
		stageManager.switchScene(FxmlView.LOGIN);  
    }
	
	@FXML
    private void users(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }
	@FXML
    private void library(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);		 
    }
	@FXML
    private void readers(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.READER);		
    }
	
	@FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.ABOUT);
    }
	
	@FXML
    private void rentbook(ActionEvent event) throws IOException{
		Book book = bookTable.getSelectionModel().getSelectedItem();
		myBean.setIdBook(book.getId());
		stageManager.newScene(FxmlView.COPYOFBOOK);  //------------------------------dodac rentbook  okno
    }
	
	@FXML
    void clear(ActionEvent event) {
    	clearFields();
    }
	
	@FXML
    void search(ActionEvent event) {
		
		String searchText =getsearchText();
		System.out.print("Wyszukiwana nazwa: "+searchText+"\n");  
		if(!searchText.isEmpty()) {
			List<Book> books = bookService.findByTitleContaining(searchText);
			loadUserDetails(books);
		}
		else {
			loadUserDetails();
		}
		
		
    	searchbar.clear();
    	
    }
	
	public String getsearchText() {
		return searchbar.getText().trim();
	}
	
	
	private void clearFields() {
		title.clear();
		author.clear();
		publisher.clear();
		cbCategory.getSelectionModel().clearSelection();
		releaseyear.clear();
	}
	
	
	
	private void loadUserDetails(){
		bookList.clear();
		bookList.addAll(bookService.findAll());

		bookTable.setItems(bookList);
	}
	
	private void loadUserDetails(List<Book> books){
		bookList.clear();
		bookList.addAll(books);
		
		bookTable.setItems(bookList);
	}
	
	private void setColumnProperties(){	
		colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
		colPublisher.setCellValueFactory(new PropertyValueFactory<>("print"));
		colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
		colRlsYear.setCellValueFactory(new PropertyValueFactory<>("year"));
		colRentals.setCellValueFactory(new PropertyValueFactory<>("num_rent"));
	
	}
	
private void addSuccessAlert(Book book){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Book added successfully.");
		alert.setHeaderText(null);
		alert.setContentText("Book: "+book.getTitle()+" has been added to library with ID: "+ book.getId() +".");
		alert.showAndWait();
	}


public String getTitle() {
	return title.getText().trim();
}

public String getAuthor() {
	return author.getText().trim();
}

public String getPublisher() {
	return publisher.getText().trim();
}

public String getCategory() {
	return publisher.getText().trim();
}

public Integer getRelYear() {
	Integer year;
	try {
		   year = Integer.parseInt(releaseyear.getText()) ;
		}
		catch (NumberFormatException e) {
		   year = 0;
		}
	return year; 
			
			
}

@FXML
private void addBook(ActionEvent event) throws IOException {
	
	if(emptyValidation("Title", getTitle().isEmpty()) && 
		emptyValidation("Author", getAuthor().isEmpty()) &&
		emptyValidation("Publisher", getPublisher().isEmpty()) &&
		emptyValidation("Category", getCategory().isEmpty())){
			
			if(checkNumInYear(getRelYear())) {
				Book book = new Book();
	        	book.setTitle(getTitle());
	        	book.setAuthor(getAuthor());
	        	book.setPrint(getPublisher());
	        	book.setCategory(getCategory());
	        	book.setYear(getRelYear());
	        			
	        	Book newBook = bookService.save(book);
	        			
	        	addSuccessAlert(newBook);
			}

			
			
	}
	clearFields();
	loadUserDetails();
	
	
}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cbCategory.setItems(categories);
		
		bookTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
		setColumnProperties();
		
		loadUserDetails();
	}
	
	
	private boolean emptyValidation(String field, boolean empty){
        if(!empty){
            return true;
        }else{
        	validationAlert(field, true);            
            return false;            
        }
    }	
	
	
	private boolean checkNumInYear(Integer year){
		System.out.print("Year: "+year);
        if(year!=0){
            return true;
        }
        else{
        	noYearAlert();        
            return false;            
        }
    }	
	
	
	private void validationAlert(String field, boolean empty){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if(field.equals("Category")) alert.setContentText("Please Select "+ field);
        else{
        	if(empty) alert.setContentText("Please Enter "+ field);
        	else alert.setContentText("Please Enter Valid "+ field);
        }
        alert.showAndWait();
	}
	
	
	private void noYearAlert(){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        
       alert.setContentText("Please enter vaild number");
        alert.showAndWait();
	}
	
	

}