package application.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewMembroController implements Initializable {

	@FXML
	TableView<Membro> tableMembro;

	@FXML
	TableColumn<Membro, String> columnNome;

	@FXML
	TableColumn<Membro, String> columnTelefone;

	@FXML
	TableColumn<Membro, String> columnBi;

	@FXML
	TableColumn<Membro, String> columnCartao;

	@FXML
	TableColumn<Membro, String> columnEmail;

	@FXML
	TableColumn<Membro, Distrito> columnDistrito;

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField telefoneTf = new TextField();

	@FXML
	TextField biTf = new TextField();

	@FXML
	Label lblTotal = new Label();
	
	@FXML
	Label lblProfile = new Label();
	
	@FXML
	Label lblUser = new Label();

	@FXML
	ComboBox<Distrito> comboDistrito;

	List<Membro> membroList = new ArrayList<>();

	@FXML
	CheckBox inactives;
	
	@FXML
	CheckBox paidAllYear;

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Distrito> distritosList = dataManager.findDistritos(Boolean.TRUE);
		if (distritosList != null)
			comboDistrito.setItems(FXCollections.observableArrayList(distritosList));
		columnNome.setCellValueFactory(new PropertyValueFactory<Membro, String>("nome"));
		columnEmail.setCellValueFactory(new PropertyValueFactory<Membro, String>("email"));
		columnTelefone.setCellValueFactory(new PropertyValueFactory<Membro, String>("telefone"));
		columnBi.setCellValueFactory(new PropertyValueFactory<Membro, String>("bi"));
		columnCartao.setCellValueFactory(new PropertyValueFactory<Membro, String>("numeroCartao"));
		columnDistrito.setCellValueFactory(new PropertyValueFactory<Membro, Distrito>("distrito"));
		user = dataManager.findCurrentUser();
		if(user!=null) {
			lblProfile.setText(user.getProfile().getProfilename());
			lblUser.setText(user.getName());
		}

	}

	public void pesquisar() {
		String nome = nomeTf.getText();
		String telefone = telefoneTf.getText();
		String bi = biTf.getText();
		Distrito distrito = comboDistrito.getSelectionModel().getSelectedItem();
		Boolean paid = paidAllYear.isSelected();
		if(!paid)
			paid = false;
		membroList = dataManager.findMembros(nome, telefone, bi, distrito, !inactives.isSelected(), paid);
		if (membroList != null) {
			tableMembro.setItems(FXCollections.observableArrayList(membroList));
			lblTotal.setText(membroList.size() + "");
		}else {
			AlertUtils.pesquisaVazia();
			tableMembro.setItems(null);
		}

	}

	public void addMembro() {
		frameManager.addMembro(user);
		pesquisar();
	}

	public void modifyMembro() {
		Membro selectedMembro = tableMembro.getSelectionModel().getSelectedItem();
		if (selectedMembro != null)
			frameManager.modifyMembro(user, selectedMembro);
	}

	public void removeMembro() {
		Membro selectedMembro = tableMembro.getSelectionModel().getSelectedItem();
		if (selectedMembro != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação de remoção");
			alert.setHeaderText(null);
			alert.setContentText("Tem certeza que deseja remover o membro " + selectedMembro.getNome()+" ?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				selectedMembro.setActive(false);
				dataManager.updateMembro(selectedMembro);
				membroList.remove(selectedMembro);
				refreshItems();
			}
		}
	}

	private void refreshItems() {
		if(membroList!=null) {
			tableMembro.setItems(FXCollections.observableArrayList(membroList));
			lblTotal.setText(membroList.size()+"");
		}
	}

	public void goHome() {
		Stage stage = (Stage) nomeTf.getScene().getWindow();
		stage.close();
	}
	
	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

	public void about() {

	}

}
