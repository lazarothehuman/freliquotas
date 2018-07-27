package application.forms;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	TextField nomeMembroTf;

	@FXML
	TextField quotaTf = new TextField();

	@FXML
	TextField fcTf = new TextField();

	@FXML
	Button registerBtn;

	@FXML
	CheckBox janeiroCb = new CheckBox();

	@FXML
	CheckBox fevreiroCb = new CheckBox();

	@FXML
	CheckBox marciCb = new CheckBox();

	@FXML
	CheckBox abrilCb = new CheckBox();

	@FXML
	CheckBox maioCb = new CheckBox();

	@FXML
	CheckBox junhoCb = new CheckBox();

	@FXML
	CheckBox julhoCb = new CheckBox();

	@FXML
	CheckBox agostoCb = new CheckBox();

	@FXML
	CheckBox setembroCb = new CheckBox();

	@FXML
	CheckBox outubroCb = new CheckBox();

	@FXML
	CheckBox novembroCb = new CheckBox();

	@FXML
	CheckBox dezembroCb = new CheckBox();

	DataManager dataManager = new DataManagerImp();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		List<Membro> membros = dataManager.findMembros(Boolean.TRUE);
		if (membros != null) {
			ArrayList<String> nomeMembros = new ArrayList<>();
			for (Membro membro : membros) 
				nomeMembros.add(membro.getNome());
			TextFields.bindAutoCompletion(nomeMembroTf, nomeMembros.toArray(new String[nomeMembros.size()]));
		}
	}

	public void registar() {
		String nome = nomeMembroTf.getText().trim();
		Membro membro = null;
		if (nome != null && !nome.isEmpty()) {
			membro = dataManager.findMembro(null, nome);
		} else {
			membro = null;
		}
		if (membro != null) {
			if (janeiroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Janeiro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Janeiro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);

				}
			}

			if (fevreiroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Fevereiro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Fevereiro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (marciCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Março);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Março);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (abrilCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Abril);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Abril);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (maioCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Maio);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Maio);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (maioCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Maio);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Maio);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (junhoCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Junho);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Junho);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (julhoCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Julho);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Julho);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (agostoCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Agosto);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Agosto);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (setembroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Setembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Setembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (outubroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Outubro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Outubro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (novembroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Novembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Novembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}

			if (dezembroCb.isSelected()) {
				if (!quotaTf.getText().isEmpty() && quotaTf.getText() != null) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Dezembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Quota);
					try {
						Double valor = Double.parseDouble(quotaTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}

				if (fcTf.getText() != null && !fcTf.getText().isEmpty()) {
					Pagamento pagamento = new Pagamento();
					pagamento.setMembro(membro);
					pagamento.setMes(Mes.Dezembro);
					pagamento.setAno(Calendar.getInstance().get(Calendar.YEAR));
					pagamento.setRegistrador(dataManager.findCurrentUser());
					pagamento.setTipoPagamento(TipoPagamento.Fundo_comité);
					try {
						Double valor = Double.parseDouble(fcTf.getText());
						pagamento.setValor(valor);
					} catch (NumberFormatException a) {
					}
					membro.getPagamentos().add(pagamento);
				}
			}
			dataManager.updateMembro(membro);
			AlertUtils.alertSucesso("Pagamento registado com sucesso.");
			Stage stage = (Stage) registerBtn.getScene().getWindow();
			stage.close();

			// Double valor = Double.parseDouble(valorTf.getText());
			// validar mes aqui

		} else
			AlertUtils.alertErroInsercaoDados();
	}
}
