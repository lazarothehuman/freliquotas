package application.forms;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.Validations;

public class ModifyMembroController implements Initializable {

	@FXML
	Button modifyBtn;

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

	Membro membro;

	DataManager dataManager = new DataManagerImp();

	public void setMembro(Membro membro) {
		if (membro != null) {
			this.membro = membro;
			setValues();
		}
	}

	private void setValues() {
		if (membro != null) {
			distritoCombo.setValue(membro.getDistrito());
			nomeTf.setText(membro.getNome());
			telefoneTf.setText(membro.getTelefone());
			emailTf.setText(membro.getEmail());
			cartaoMembroTf.setText(membro.getNumeroCartao());
			biTf.setText(membro.getBi());
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Distrito> distritos = dataManager.findDistritos(Boolean.TRUE);
		if (distritos != null)
			distritoCombo.setItems(FXCollections.observableArrayList(distritos));
	}

	public void modify() {
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
			membro.setNome(nome);
			membro.setDistrito(distrito);
			membro.setTelefone(telefone);
			if (email != null && !email.isEmpty())
				membro.setEmail(email);
			if (bi != null && !bi.isEmpty())
				membro.setBi(bi);
			if (numeroCartao != null && !numeroCartao.isEmpty())
				membro.setNumeroCartao(numeroCartao);
			dataManager.updateMembro(membro);
			AlertUtils.alertSucesso("Membro");
			Stage stage = (Stage) modifyBtn.getScene().getWindow();
			stage.close();
		} else
			AlertUtils.alertErroInsercaoDados();

	}

}
