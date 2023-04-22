package com.codetreatise.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.codetreatise.bean.Reader;
import com.codetreatise.bean.Copy;
import com.codetreatise.bean.Hire;
import com.codetreatise.config.MyBeanID;
import com.codetreatise.config.StageManager;
import com.codetreatise.service.CopyService;
import com.codetreatise.service.HireService;
import com.codetreatise.service.ReaderService;
import com.codetreatise.view.FxmlView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
/**
 * @author Mateusz Toczek & Beata Korzeniewska
 * @since 08-01-2023
 */

@Controller
public class RentController implements Initializable{
	private long idCopy;
	@Autowired
	private MyBeanID myBean;
    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField idReaderLabel;
    @Autowired
    private HireService hireService;
    
    @Autowired
    private ReaderService readerService;
    @Autowired
    private CopyService copyService;
    
    @Lazy
    @Autowired
    private StageManager stageManager;
    
    @FXML
	private void cancel(ActionEvent event) throws IOException{
    	clearField();
    	stageManager.newScene(FxmlView.COPYOFBOOK);
    }


    @FXML
    private void accept(ActionEvent event) throws IOException{
    	
	    if(getIdReader()!= null && readerService.existsById(Long.parseLong(getIdReader())))
	    {
	    	Hire hire= new Hire();
			Reader reader= readerService.find(Long.parseLong(getIdReader()));
			if(reader.getStatus()) {
				Copy copy= copyService.find(this.idCopy);
				copy.setStatus("no");
				Copy newCopy=copyService.update(copy);
				hire.setCopy(copy);
				hire.setDateHire(LocalDate.now());
				hire.setReader(reader);
				Hire newHire=hireService.save(hire);
				saveAlert(newHire, reader);
			}
			else {
				Alert alert = new Alert(AlertType.WARNING);
    	        alert.setTitle("Rent Error");
    	        alert.setContentText("Reader accout is already blocked!");
    	        alert.showAndWait();
    			
			}
			
			stageManager.newScene(FxmlView.COPYOFBOOK);
			
			
	    }
	    else
	    {
	    	validationAlert();
	    }
    }
    
	private void saveAlert(Hire hire, Reader reader){
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("New hire added successfully.");
		alert.setHeaderText(null);
		alert.setContentText("The user "+reader.getName()+" "+reader.getSurname() +" rent book");
		alert.showAndWait();
	}
	
    public String getIdReader() {
		return idReaderLabel.getText();
	}

    private void validationAlert(){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Reader doesn't exist yet");
        alert.showAndWait();
	}
    
	private void clearField() {
		idReaderLabel.clear();
	}
    public void setIdCopy(long text) {
       this.idCopy=text;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.idCopy = myBean.getIdCopy();
		
		System.out.println(this.idCopy);
	
	}

}
