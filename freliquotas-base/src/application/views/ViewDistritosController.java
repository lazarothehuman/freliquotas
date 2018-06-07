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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
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
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewDistritosController implements Initializable {

	@FXML
	Button pesquisar;

	@FXML
	Button addDistrito;

	@FXML
	Button modifyDistrito;

	@FXML
	Button removeDistrito;

	@FXML
	Label lblUser = new Label();

	@FXML
	Label lblProfile = new Label();

	@FXML
	Label lblTotal = new Label();

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	Hyperlink home;

	@FXML
	Hyperlink about;

	@FXML
	CheckBox activeCb;

	@FXML
	TableView<Distrito> tableDistrito;

	@FXML
	TableColumn<Distrito, String> nomeColumn;

	List<Distrito> distritos = new ArrayList<>();

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		if (user != null) {
			lblUser.setText(user.getName().toLowerCase());
			lblProfile.setText(user.getProfile().getProfilename());
		}
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Distrito, String>("nome"));
		distritos = dataManager.findDistritos(Boolean.TRUE);

		if (distritos != null)
			tableDistrito.setItems(FXCollections.observableArrayList(distritos));
	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

	public void pesquisar() {
		String nome = nomeTf.getText();
		Boolean active = !activeCb.isSelected();
		distritos = dataManager.findDistritos(nome, active);
		if (distritos != null) {
			tableDistrito.setItems(FXCollections.observableArrayList(distritos));
			lblTotal.setText("" + distritos.size());
		}
	}

	public void goHome() {
		Stage stage = (Stage) addDistrito.getScene().getWindow();
		stage.close();
	}
	
	public void about() {
		
	}

	public void addDistrito() {
		if (user != null)
			frameManager.addDistrito(user);
	}
	
	public void modifyDistrito() {
		Distrito selectedDistrito = tableDistrito.getSelectionModel().getSelectedItem();
		if(selectedDistrito!=null)
			frameManager.modifyDistrito(selectedDistrito, user);
		else {
			AlertUtils.alertErroSelecionar("Deve selecionar um orgão para poder modificar");
		}
	}
	
	public void removerDistrito() {
		Distrito selectedDistrito = tableDistrito.getSelectionModel().getSelectedItem();
		if(selectedDistrito!=null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmação de remoção");
			alert.setHeaderText(null);
			alert.setContentText("Tem certeza que deseja remover o orgão?" + selectedDistrito.getNome());
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				selectedDistrito.setActive(false);
				dataManager.updateDistrito(selectedDistrito);
				distritos.remove(selectedDistrito);
				refreshItems();				
			}
			
		}
	}

	public void refreshItems() {
		tableDistrito.setItems(FXCollections.observableArrayList(distritos));	
	}

}
