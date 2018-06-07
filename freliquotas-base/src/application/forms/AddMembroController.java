package application.forms;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.Validations;

public class AddMembroController implements Initializable {

	@FXML
	Button addBtn;

	@FXML
	ComboBox<Distrito> distritoCombo;

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField telefoneTf = new TextField();

	@FXML
	TextField emailTf = new TextField();

	@FXML
	TextField cartaoMembroTf = new TextField();

	@FXML
	TextField biTf = new TextField();
	
	@FXML
	Label validNumberlbl = new Label();
	
	boolean valid = false;

	DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Distrito> distritos = dataManager.findDistritos(Boolean.TRUE);
		if (distritos != null)
			distritoCombo.setItems(FXCollections.observableArrayList(distritos));
		validNumberlbl.setText(null);
	}
	
	public void validateNumber() {// ver bem
		String telefone = telefoneTf.getText();
		if (telefone != null && !telefone.isEmpty()) {
			valid = Validations.isValidForSMSNotification(telefone);
		}
		if(valid) {
			validNumberlbl.setText("Numero valido");
		}else {
			validNumberlbl.setText("Numero inválido");
			// validNumberlbl.setStyle("-fx-");
		}
	}

	public void add() {
		
		boolean valid = false;
		String telefone = telefoneTf.getText();
		if (telefone != null && !telefone.isEmpty()) {
			valid = Validations.isValidForSMSNotification(telefone);
		}
		String nome = nomeTf.getText();
		String bi = biTf.getText();
		String email = emailTf.getText();
		String numeroCartao = cartaoMembroTf.getText();
		Distrito distrito = distritoCombo.getValue();
		if (nome != null && !nome.isEmpty() && distrito != null && valid == true) {
			Membro membro = new Membro();
			membro.setNome(nome);
			membro.setDistrito(distrito);
			membro.setTelefone(telefone);
			if (email != null && !email.isEmpty())
				membro.setEmail(email);
			if(bi!=null && !bi.isEmpty())
				membro.setBi(bi);
			if(numeroCartao!=null && !numeroCartao.isEmpty())
				membro.setNumeroCartao(numeroCartao);
			dataManager.createMembro(membro);
			AlertUtils.alertSucesso("Membro");
			Stage stage = (Stage) addBtn.getScene().getWindow();
			stage.close();
		}else
			AlertUtils.alertErroInsercaoDados();

	}

}
