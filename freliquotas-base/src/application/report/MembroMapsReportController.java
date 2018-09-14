package application.report;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Mes;

public class MembroMapsReportController implements Initializable {

	@FXML
	private TextField year;

	@FXML
	ComboBox<Distrito> orgaos;

	@FXML
	ComboBox<Mes> months;

	DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		List<Distrito> distritos = dataManager.findDistritos(Boolean.TRUE);
		if (distritos != null)
			orgaos.setItems(FXCollections.observableArrayList(distritos));
		
	}
	
	public void print() {
		
	}

}
