package application.views;

import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class MainController implements Initializable {
	
	/*
	 * 
	 * version 1.3
	 */

	int DIA_ENVIO_MENSAGEM = 28;
	@FXML
	Label usernameLbl = new Label();

	@FXML
	Label userProfileLbl = new Label();

	@FXML
	Label districNameLbl = new Label();

	@FXML
	Label developerMessage = new Label();

	User user;

	DataManager dataManager = new DataManagerImp();

	FrameManager frameManager = new FrameManager();

	NotificationManager notifcationManager = new NotificationManagerImp();

	public void addMembro() {
		frameManager.addMembro(user);
	}

	public void addPagamento() {
		frameManager.addPagamento(user);
	}

	public void addDistrito() {
		frameManager.addDistrito(user);
	}

	public void addUser() {
		frameManager.addUser(user);
	}

	public void viewMembros() {
		frameManager.viewMembros(user);
	}

	public void viewDistritos() {
		frameManager.viewDistritos(user);
	}

	public void viewMaps() {
		frameManager.viewMaps(user);
	}

	public void viewUsers() {
		frameManager.viewUsers(user);
	}

	public void relatorioMembros() {
		frameManager.reportMembroMaps(user);
	}

	public void informMembers() {
		Calendar calendar = Calendar.getInstance();
		String mes = "";
		@SuppressWarnings("deprecation")
		int mesI = calendar.getTime().getMonth();
		switch (mesI) {
		case 0:
			mes = "Janeiro";
			break;
		case 1:
			mes = "Fevreiro";
			break;
		case 2:
			mes = "Março";
			break;
		case 3:
			mes = "Abril";
			break;
		case 4:
			mes = "Maio";
			break;
		case 5:
			mes = "Junho";
			break;
		case 6:
			mes = "Julho";
			break;
		case 7:
			mes = "Agosto";
			break;
		case 8:
			mes = "Setembro";
			break;
		case 9:
			mes = "Outubro";
			break;
		case 10:
			mes = "Novembro";
			break;
		case 11:
			mes = "Dezembro";
			break;
		}
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação de SMS");
		alert.setHeaderText(null);
		alert.setContentText(
				"Tem certeza que deseja informar aos membros sobre o pagamento de quotas referentes ao mês "
						+ mes.toUpperCase() + " ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			List<Membro> membros = dataManager.findMembros(null, null, null, user.getDistrito(), Boolean.TRUE, Boolean.FALSE);
			if (membros != null) {
				notifcationManager.sendSmsNotification(membros);
			}
		}
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		if(DIA_ENVIO_MENSAGEM == Calendar.getInstance().get(Calendar.DATE)) {
			AlertUtils.alertDataDeEnvioAutomatico(DIA_ENVIO_MENSAGEM);
			List<Membro> membros = dataManager.findMembros(null, null, null, user.getDistrito(), Boolean.TRUE, Boolean.FALSE);
			try {
				notifcationManager.sendSms(user.getTelefone(), "Mensagens enviadas com sucesso");
			} catch (IOException e) {
				e.printStackTrace();
			}
			notifcationManager.sendSmsNotification(membros);
		}
		if (user != null) {
			usernameLbl.setText(user.getName());
			userProfileLbl.setText(user.getProfile().getProfilename());
			districNameLbl.setText(user.getDistrito().getNome());
		}
		developerMessage.setText("Unidade, Paz e Desenvolvimento" + 
				" FRELIMO a Força da Mudança");
	}
}
