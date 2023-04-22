package com.codetreatise.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Plan;
import com.codetreatise.bean.User;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.PlanService;
import com.codetreatise.service.UserService;
import com.codetreatise.view.FxmlView;

import javafx.application.Platform;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */
@Controller
public class PlanAdminController implements Initializable{

    @Autowired
	private MyBeanID myBean;	
	@Autowired
	private PlanService planService;
	@Autowired
	private UserService userService;
    @FXML
    private ResourceBundle resources;
    private ObservableList<Plan> planList = FXCollections.observableArrayList();

    @FXML
    private URL location;
    
    @FXML
    private TableView<Plan> plansTable;

    @FXML
    private TableColumn<Plan, Long> colPlanId;
    @FXML
    private TableColumn<Plan, Long> colUserId;
    @FXML
    private TableColumn<Plan, String> colMonday;
    @FXML
    private TableColumn<Plan, String> colThuesday;
    @FXML
    private TableColumn<Plan, String> colWednesday;
    @FXML
    private TableColumn<Plan, String> colThursday;
    @FXML
    private TableColumn<Plan, String> colFriday;
    @FXML
    private TableColumn<Plan, Boolean> colEdit;
    
    @FXML
    private TextField UserID;
    @FXML
    private TextField MondayH;
    @FXML
    private TextField ThuesdayH;
    @FXML
    private TextField WednesdayH;
    @FXML
    private TextField ThursdayH;  
    @FXML
    private TextField FridayH;


    @FXML
    private MenuItem deleteFiled;

    @FXML
    private Label planId;
    @FXML
    private Button reset;
    @FXML
    private Button save;
    
    @Lazy
    @Autowired
    private StageManager stageManager;

    @FXML
    void deleteFiled(ActionEvent event) {

    }
    @FXML
    void reset(ActionEvent event) {
    	clearFields();
    }
   
    @FXML
    private void library(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.LIBRARY);  
    }
    
    @FXML
    void saveChange(ActionEvent event) {
    	
		if(planId.getText() == null || planId.getText() == ""){
				Plan plan = new Plan();
				plan.setUser(userService.find((Long.parseLong(UserID.getText()))));
				plan.setMonday(getMondayH());
				plan.setTuesday(getThuesdayH());
				plan.setWednesday(getWednesdayH());
				plan.setThursday(getThursdayH());
				plan.setFriday(getFridayH());
    			Plan newPlan = planService.save(plan);
    			
    			saveAlert(newPlan);
			
			
		}else{
			Plan plan = planService.find(Long.parseLong(planId.getText()));
			plan.setUser(userService.find((Long.parseLong(UserID.getText()))));
			plan.setMonday(getMondayH());
			plan.setTuesday(getThuesdayH());
			plan.setWednesday(getWednesdayH());
			plan.setThursday(getThursdayH());
			plan.setFriday(getFridayH());
			Plan updatedPlan =  planService.update(plan);
			updateAlert(updatedPlan);
		}
		
		clearFields();
		loadDetails();
    	    	
    }

  
    @FXML
    void mainwindow(ActionEvent event) throws IOException{
		stageManager.newScene(FxmlView.MAINADMIN);  
    }

    @FXML
    void goBook(ActionEvent event)  throws IOException{
		stageManager.newScene(FxmlView.BOOK);  
    }

    @FXML
    void goReaders(ActionEvent event)throws IOException{
		stageManager.newScene(FxmlView.READER);  
    }
    
    @FXML
    void goUsers(ActionEvent event)throws IOException{
		stageManager.newScene(FxmlView.USER);  
    }


    @FXML
    void exit(ActionEvent event) {
    	Platform.exit();
    }

    @FXML
    void clearfields(ActionEvent event)  throws IOException{
		clearFields();
    }

    @FXML
    private void about(ActionEvent event) throws IOException{
		stageManager.newWindow(FxmlView.ABOUT);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	plansTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setColumnProperties();
		
		loadDetails();	
    	
    }
	private void saveAlert(Plan plan){
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Plan saved successfully.");
			alert.setHeaderText(null);
			alert.setContentText("The plan for "+plan.getNameUser()+" was saving.");
			alert.showAndWait();
		}
	private void updateAlert(Plan plan){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Plan updated successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The plan for "+plan.getNameUser()+" was updating.");
		alert.showAndWait();
	}

    
    private void loadDetails(){
		planList.clear();
		planList.addAll(planService.findAll());
		plansTable.setItems(planList);
	}
    
	private void clearFields() {
		planId.setText(null);
		UserID.clear();
		MondayH.clear();
		ThuesdayH.clear();
		WednesdayH.clear();
		ThursdayH.clear();
		FridayH.clear();
	} 
	
	public String getMondayH() {
		return MondayH.getText();
	}
	public String getThuesdayH() {
		return ThuesdayH.getText();
	}
	public String getWednesdayH() {
		return WednesdayH.getText();
	}
	public String getThursdayH() {
		return ThursdayH.getText();
	}
	public String getFridayH() {
		return FridayH.getText();
	}
	public String getUserID() {
		return UserID.getText();
	}
	
    private void setColumnProperties(){	
    	
    	colPlanId.setCellValueFactory(cd-> new SimpleLongProperty(((Plan)cd.getValue()).getId()).asObject());
    	colUserId.setCellValueFactory((cd-> new SimpleLongProperty(((Plan)cd.getValue()).getIdUser()).asObject()));
    	colMonday.setCellValueFactory(cd-> new SimpleStringProperty(((Plan)cd.getValue()).getMonday()));
    	colThuesday.setCellValueFactory(cd-> new SimpleStringProperty(((Plan)cd.getValue()).getTuesday()));
    	colWednesday.setCellValueFactory(cd-> new SimpleStringProperty(((Plan)cd.getValue()).getWednesday()));
    	colThursday.setCellValueFactory(cd-> new SimpleStringProperty(((Plan)cd.getValue()).getThursday()));
    	colFriday.setCellValueFactory(cd-> new SimpleStringProperty(((Plan)cd.getValue()).getFriday()));
    	colEdit.setCellFactory(cellFactory);
    	
	}
    
    Callback<TableColumn<Plan, Boolean>, TableCell<Plan, Boolean>> cellFactory = 
			new Callback<TableColumn<Plan, Boolean>, TableCell<Plan, Boolean>>()
	{
		@Override
		public TableCell<Plan, Boolean> call( final TableColumn<Plan, Boolean> param)
		{
			final TableCell<Plan, Boolean> cell = new TableCell<Plan, Boolean>()
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
							Plan plan = getTableView().getItems().get(getIndex());
							updatePlan(plan);
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

				private void updatePlan(Plan plan) {
					planId.setText(Long.toString(plan.getId()));
					UserID.setText(Long.toString(plan.getIdUser()));
					MondayH.setText(plan.getMonday());
					ThuesdayH.setText(plan.getTuesday());
					WednesdayH.setText(plan.getWednesday());
					ThursdayH.setText(plan.getThursday());
					FridayH.setText(plan.getFriday());

				}
			};
			return cell;
		}

		
	};
}
