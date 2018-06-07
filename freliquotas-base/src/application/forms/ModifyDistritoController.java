package application.forms;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;

public class ModifyDistritoController implements Initializable {

	Distrito distrito;

	@FXML
	Button modifyBtn;

	@FXML
	Button clearBtn;

	@FXML
	private TextField nomeTf = new TextField();

	private DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void modify() {
		String nome = nomeTf.getText();
		if (nome != null && !nome.isEmpty()) {
			distrito.setNome(nome);
			dataManager.updateDistrito(distrito);
		}
	}

	public void clear() {
		nomeTf.setText("");
	}

	public void setDistrito(Distrito distrito) {
		if (distrito != null) {
			this.distrito = distrito;
			setValues();
		}

	}

	private void setValues() {
		if (this.distrito != null)
			nomeTf.setText(this.distrito.getNome());

	}
}
