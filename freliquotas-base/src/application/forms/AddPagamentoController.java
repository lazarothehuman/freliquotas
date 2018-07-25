package application.forms;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.Mes;
import mz.humansolutions.models.Pagamento;
import mz.humansolutions.models.TipoPagamento;
import mz.humansolutions.utils.AlertUtils;

public class AddPagamentoController implements Initializable {

	@FXML
	ComboBox<Mes> comboMes;

	@FXML
	TextField nomeMembroTf;

	@FXML
	ComboBox<TipoPagamento> comboTipoPagamento;

	@FXML
	TextField valorTf = new TextField();

	@FXML
	Button registerBtn;

	DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Membro> membros = dataManager.findMembros(Boolean.TRUE);
		if (membros != null)
		{
			ArrayList<String> nomeMembros = new ArrayList<>();
			for (Membro membro : membros) {
				nomeMembros.add(membro.getNome());
			}
			
			TextFields.bindAutoCompletion(nomeMembroTf, nomeMembros.toArray(new String[nomeMembros.size()]));
		}
		comboMes.setItems(FXCollections.observableArrayList(Mes.values()));
		comboTipoPagamento.setItems(FXCollections.observableArrayList(TipoPagamento.values()));
	
		

	}

	public void registar() {
		String nome = nomeMembroTf.getText().trim();
		Membro membro = null;
		if(nome!=null && !nome.isEmpty()) {
			membro = dataManager.findMembro(null,nome);
		}else {
			membro =null;
		}
		Mes mes = comboMes.getValue();
		TipoPagamento tipoPagamento = comboTipoPagamento.getValue();
		Double valor = Double.parseDouble(valorTf.getText());
		//validar mes aqui
		if (membro != null && mes != null && tipoPagamento != null && valor != null) {
			Pagamento pagamento = new Pagamento();
			pagamento.setMembro(membro);
			pagamento.setValor(valor);
			pagamento.setTipoPagamento(tipoPagamento);
			pagamento.setMes(mes);
			pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
			pagamento.setRegistrador(dataManager.findCurrentUser());
			membro.getPagamentos().add(pagamento);
			dataManager.updateMembro(membro);
			AlertUtils.alertSucesso("Pagamento registado com sucesso. \nNome: " + membro.getNome() + "\nValor: "
					+ pagamento.getValor() + "\nPagamento: " + pagamento.getTipoPagamento());
			Stage stage = (Stage) comboMes.getScene().getWindow();
			stage.close();
		} else
			AlertUtils.alertErroInsercaoDados();
	}

}
