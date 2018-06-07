package application.forms;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;

public class ModifyUserController implements Initializable {

	@FXML
	ComboBox<Profile> comboPerfil;

	@FXML
	ComboBox<Distrito> comboDistrito;

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField usernameTf = new TextField();

	@FXML
	TextField passwordTf = new TextField();

	@FXML
	TextField repeatPasswordTf = new TextField();

	@FXML
	Button modify;

	DataManager dataManager = new DataManagerImp();

	User user;

	@FXML
	CheckBox isActive = new CheckBox();

	public void setUser(User user) {
		if (user != null) {
			this.user = user;
			setValues();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboPerfil.setItems(FXCollections.observableArrayList(dataManager.findProfiles(Boolean.TRUE)));
		List<Distrito> distritos = dataManager.findDistritos(Boolean.TRUE);
		if (distritos != null && !distritos.isEmpty())
			comboDistrito.setItems(FXCollections.observableArrayList(distritos));

	}

	public void setValues() {
		if (this.user != null) {
			nomeTf.setText(user.getName());
			usernameTf.setText(user.getUsername());
			comboPerfil.setValue(user.getProfile());
			comboDistrito.setValue(user.getDistrito());
		}
	}

	public void modify() {// fazer controle de permissoes
		String nome = nomeTf.getText();
		String username = usernameTf.getText();
		String password = passwordTf.getText();
		String repeat = repeatPasswordTf.getText();
		Profile profile = comboPerfil.getSelectionModel().getSelectedItem();
		Distrito distrito = comboDistrito.getSelectionModel().getSelectedItem();

		if (nome != null && !nome.isEmpty() && username != null && !username.isEmpty() && profile != null
				&& distrito != null) {
			if (password.equals(repeat)) {
				this.user.setActive(true);
				this.user.setName(nome);
				this.user.setProfile(profile);
				this.user.setDistrito(distrito);
				this.user.setUsername(username);
				if (!password.isEmpty()) {
					this.user.setPassword(password);
					}
				try {
					dataManager.updateUser(this.user);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (GeneralSecurityException e) {
					e.printStackTrace();
				}
				AlertUtils.alertSucesso("Usuario modificado com sucesso");
				Stage stage = (Stage) modify.getScene().getWindow();
				stage.close();
			} else {
				AlertUtils.alertErroInsercaoDados();
			}

		} else {
			AlertUtils.alertErroInsercaoDados();
		}
	}

	public void selectActive() {
		if (isActive.isSelected()) {
			passwordTf.setDisable(false);
			repeatPasswordTf.setDisable(false);
		} else {
			passwordTf.setDisable(true);
			repeatPasswordTf.setDisable(true);
		}
	}

}
