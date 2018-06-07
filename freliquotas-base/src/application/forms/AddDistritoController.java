package application.forms;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.utils.AlertUtils;

public class AddDistritoController implements Initializable {
	
	@FXML
	Button addBtn;
	
	@FXML
	Button clearBtn;
	
	@FXML
	private TextField nomeTf = new TextField();

	private DataManager dataManager = new DataManagerImp();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void add() {
		String nome = nomeTf.getText();
		if(nome!=null && !nome.isEmpty()) {
			Distrito distrito = new Distrito();
			distrito.setNome(nome);
			dataManager.createDistrito(distrito);
			AlertUtils.alertSucesso("Orgão adicionado com sucesso");
			Stage stage = (Stage) addBtn.getScene().getWindow();
			stage.close();
		}
		
	}
	
	public void clear() {
		nomeTf.setText("");
	}
}
