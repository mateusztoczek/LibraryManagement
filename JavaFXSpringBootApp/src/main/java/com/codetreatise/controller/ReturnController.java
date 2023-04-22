package com.codetreatise.controller;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
public class ReturnController implements Initializable{
	
	@Autowired
	private MyBeanID myBean;
    @FXML
    private Button accept;

    @FXML
    private Button cancel;

    @FXML
    private TextField idReader;
    @FXML
    private TextField idCopy;
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
    	List<Hire> hires= hireService.findByCopy_idAndReader_id(Long.parseLong(getIdCopy()), Long.parseLong(getIdReader()));
	    if(hires!=null)
	    {
	    	for (Hire hire : hires) {
	    		
	    		Hire newHire = hireService.find(hire.getId());
	    		newHire.setDateBack(LocalDate.now());
	    		LocalDate rent= newHire.getDateHire();
	    		if(ChronoUnit.DAYS.between(rent, LocalDate.now())>30) {
	    			newHire.setPenalty(2*(int)ChronoUnit.DAYS.between(rent, LocalDate.now()));
	
	    		}
	    		Copy copy= copyService.find(Long.parseLong(getIdCopy()));
				copy.setStatus("yes");
				Copy newCopy=copyService.update(copy);
	    		hireService.update(newHire);
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
		alert.setContentText("The user  "+reader.getName()+" "+reader.getSurname() +" return book");
		alert.showAndWait();
	}
	
    public String getIdReader() {
		return idReader.getText();
	}
    public String getIdCopy() {
		return idCopy.getText();
	}

    private void validationAlert(){
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        alert.setContentText("Hire doesn't exist yet");
        alert.showAndWait();
	}
    
	private void clearField() {
		idReader.clear();
		idCopy.clear();
	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		System.out.println(this.idCopy);
	
	}

}
