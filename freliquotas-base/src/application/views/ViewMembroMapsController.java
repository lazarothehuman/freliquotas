package application.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.managers.NotificationManager;
import mz.humansolutions.managers.NotificationManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.Mes;
import mz.humansolutions.models.Pagamento;
import mz.humansolutions.models.TipoPagamento;
import mz.humansolutions.models.User;
import mz.humansolutions.utils.AlertUtils;
import mz.humansolutions.utils.FrameManager;

public class ViewMembroMapsController implements Initializable {

	// meter colunas para identifcar o mes

	int ANO = Calendar.getInstance().get(Calendar.YEAR);

	List<Membro> membros;

	@FXML
	ComboBox<Distrito> distrito;

	@FXML
	TextField nomeTf = new TextField();

	@FXML
	TextField anoTf = new TextField();

	@FXML
	Label lblUser = new Label();

	@FXML
	Label lblProfile = new Label();

	@FXML
	Label lblTotal = new Label();

	@FXML
	TableView<Membro> tableMembros;
	@FXML
	TableColumn<Membro, String> nomeColumn;
	@FXML
	TableColumn<Membro, String> quotaJanColumn;
	@FXML
	TableColumn<Membro, String> quotaAprColumn;
	@FXML
	TableColumn<Membro, String> quotaFevColumn;
	@FXML
	TableColumn<Membro, String> quotaMarchColumn;
	@FXML
	TableColumn<Membro, String> quotaMayColumn;
	@FXML
	TableColumn<Membro, String> quotaJunColumn;
	@FXML
	TableColumn<Membro, String> quotaJulColumn;
	@FXML
	TableColumn<Membro, String> quotaAugColumn;
	@FXML
	TableColumn<Membro, String> quotaSepColumn;
	@FXML
	TableColumn<Membro, String> quotaOctColumn;
	@FXML
	TableColumn<Membro, String> quotaNovColumn;
	@FXML
	TableColumn<Membro, String> quotaDecColumn;
	@FXML
	TableColumn<Membro, String> fcJanColumn;
	@FXML
	TableColumn<Membro, String> fcFevColumn;
	@FXML
	TableColumn<Membro, String> fcMarchColumn;
	@FXML
	TableColumn<Membro, String> fcAprColumn;
	@FXML
	TableColumn<Membro, String> fcMayColumn;
	@FXML
	TableColumn<Membro, String> fcJunColumn;
	@FXML
	TableColumn<Membro, String> fcJulColumn;
	@FXML
	TableColumn<Membro, String> fcAugColumn;
	@FXML
	TableColumn<Membro, String> fcSepColumn;
	@FXML
	TableColumn<Membro, String> fcOctColumn;
	@FXML
	TableColumn<Membro, String> fcNovColumn;
	@FXML
	TableColumn<Membro, String> fcDezColumn;

	PropertyValueFactory<Membro, String> janFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> fevFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> marchFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> aprFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> mayFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> junFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> julFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> augFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> sepFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> octFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> novFactory = new PropertyValueFactory<>("pagamentos");
	PropertyValueFactory<Membro, String> decFactory = new PropertyValueFactory<>("pagamentos");
	DataManager dataManager = new DataManagerImp();

	NotificationManager notificationManager = new NotificationManagerImp();

	FrameManager frameManager = new FrameManager();

	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		Distrito distrito = null;
		anoTf.setText(Calendar.getInstance().get(Calendar.YEAR) + "");
		if (user != null) {
			distrito = user.getDistrito();
			lblUser.setText("" + user.getName());
			lblProfile.setText("" + user.getProfile().getProfilename());
		}
		List<Distrito> distritos = dataManager.findDistritos(Boolean.TRUE);
		if (distritos != null)
			this.distrito.setItems(FXCollections.observableArrayList(distritos));
		membros = dataManager.findMembros(null, null, null, distrito, Boolean.TRUE, null);
		nomeColumn.setCellValueFactory(new PropertyValueFactory<Membro, String>("nome"));

		quotaJanColumn.setCellValueFactory(janFactory);
		quotaFevColumn.setCellValueFactory(fevFactory);
		quotaMarchColumn.setCellValueFactory(marchFactory);
		quotaAprColumn.setCellValueFactory(aprFactory);
		quotaMayColumn.setCellValueFactory(mayFactory);
		quotaJunColumn.setCellValueFactory(junFactory);
		quotaJulColumn.setCellValueFactory(julFactory);
		quotaAugColumn.setCellValueFactory(augFactory);
		quotaSepColumn.setCellValueFactory(sepFactory);
		quotaOctColumn.setCellValueFactory(octFactory);
		quotaNovColumn.setCellValueFactory(novFactory);
		quotaDecColumn.setCellValueFactory(decFactory);
		fcJanColumn.setCellValueFactory(janFactory);
		fcFevColumn.setCellValueFactory(fevFactory);
		fcMarchColumn.setCellValueFactory(marchFactory);
		fcAprColumn.setCellValueFactory(aprFactory);
		fcMayColumn.setCellValueFactory(mayFactory);
		fcJunColumn.setCellValueFactory(junFactory);
		fcJulColumn.setCellValueFactory(julFactory);
		fcAugColumn.setCellValueFactory(augFactory);
		fcSepColumn.setCellValueFactory(sepFactory);
		fcOctColumn.setCellValueFactory(octFactory);
		fcNovColumn.setCellValueFactory(novFactory);
		fcDezColumn.setCellValueFactory(decFactory);

		quotaJanColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Janeiro) && pagamento.getAno() == ANO) {
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
							}
						}
						return new SimpleStringProperty(null);
					}
				});

		fcJanColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Janeiro) && pagamento.getAno() == ANO) {
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
							}
						}
						return new SimpleStringProperty(null);
					}
				});

		quotaFevColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Fevereiro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcFevColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Fevereiro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		quotaMarchColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Março) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcMarchColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Março) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaAprColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Abril) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcAprColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Abril) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaMayColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Maio) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcMayColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Maio) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaJunColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Junho) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcJunColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Junho) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaJulColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Julho) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcJulColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Julho) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaAugColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Agosto) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcAugColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Agosto) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaSepColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Setembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcSepColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Setembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaOctColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Outubro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcOctColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Outubro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaNovColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Novembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcNovColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Novembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		quotaDecColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
									&& pagamento.getMes().equals(Mes.Dezembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});

		fcDezColumn.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Membro, String>, ObservableValue<String>>() {

					@Override
					public ObservableValue<String> call(CellDataFeatures<Membro, String> membro) {
						Membro member = membro.getValue();
						for (Pagamento pagamento : member.getPagamentos()) {
							if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
									&& pagamento.getMes().equals(Mes.Dezembro) && pagamento.getAno() == ANO) 
								return new SimpleStringProperty(pagamento.getValor() + " Mt");
						}
						return new SimpleStringProperty(null);
					}
				});
		
		if (membros != null) {
			tableMembros.setItems(FXCollections.observableArrayList(membros));
			lblTotal.setText(membros.size() + "");
		}
	}

	public void goHome() {
		Stage stage = (Stage) tableMembros.getScene().getWindow();
		stage.close();
	}

	public void about() {
		// Alert alert = new Alert(AlertType.INFORMATION);
		// alert.setTitle("Sobre esta janela");
		// alert.setContentText("Esta janela tem objectivo de ajudar a visualizar todos
		// "
		// + "os usuários gravados no sistema. Do lado direito da tela vais encontrar um
		// conjunto de filtros, "
		// + "preencha os para uma busca mais refinada. Para sair desta tela, apenas
		// prima voltar ou "
		// + "no canto superior direito para tirar a janela. Nesta janela também é
		// possível adicionar, modificar e remover um usuário. "
		// + "Para voltar para janela principal, é necessário clicar no HOME ou no x no
		// canto superior a direita");
		// alert.setHeaderText(null);
		// alert.showAndWait();
	}

	public void doubleClickOnUser(MouseEvent event) {
		if (event.getClickCount() == 2) {
			// frameManager.modiyPagamento(user, )
		}
	}

	public void enterKeyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ENTER)
			pesquisar();
	}

	public void pesquisar() {
		String nome = nomeTf.getText();
		Distrito distritos = distrito.getValue();
		membros = dataManager.findMembros(nome, null, null, distritos, Boolean.TRUE, null);
		if (membros != null) {
			tableMembros.setItems(FXCollections.observableArrayList(membros));
			lblTotal.setText(membros.size() + "");
			for (Pagamento pagamento : membros.get(0).getPagamentos()) {
				System.out.println("Pagamento: " + pagamento.getValor());
			}
		} else {
			AlertUtils.pesquisaVazia();
			tableMembros.setItems(null);
		}
	}

	public void inform() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmação de envio de sms");
		alert.setHeaderText(null);
		alert.setContentText(
				"Tem certeza que deseja notificar aos membros presentes na tabela disposta para pagar as quotas ?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			if (membros != null)
				notificationManager.sendSmsNotification(membros);
		}
	}

	public void addPayment() {
		if (user != null) {
			frameManager.addPagamento(user);
			refreshItems();
		}
	}

	public void visualizarTotais() {
		double totalJaneiro = 0;
		double totalFevereiro = 0;
		double totalMarco = 0;
		double totalAbril = 0;
		double totalMaio = 0;
		double totalJunho = 0;
		double totalJulho = 0;
		double totalAgosto = 0;
		double totalSetembro = 0;
		double totalOutubro = 0;
		double totalNovembro = 0;
		double totalDecembro = 0;
		List<Pagamento> todosPagamentos = new ArrayList<>();
		if (membros != null) {
			for (Membro membro : membros) {
				todosPagamentos.addAll(membro.getPagamentos());
			}
			for (Pagamento pagamento : todosPagamentos) {
				if (pagamento.getMes().equals(Mes.Janeiro))
					totalJaneiro += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Fevereiro))
					totalFevereiro += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Março))
					totalMarco += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Abril))
					totalAbril += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Maio))
					totalMaio += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Junho))
					totalJunho += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Julho))
					totalJulho += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Agosto))
					totalAgosto += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Setembro))
					totalSetembro += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Outubro))
					totalOutubro += pagamento.getValor();
				else if (pagamento.getMes().equals(Mes.Novembro))
					totalNovembro += pagamento.getValor();
				else
					totalDecembro += pagamento.getValor();

			}
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText("Janeiro : " + totalJaneiro + " Mt\nFevereiro : " + totalFevereiro + "Mt\nMarço : "
				+ totalMarco + "Mt\nAbril : " + totalAbril + " Mt\nMaio:" + totalMaio + " Mt\nJunho: " + totalJunho
				+ " Mt\nJulho : " + totalJulho + "Mt\nAgosto : " + totalAgosto + " Mt\nSetembro:" + totalSetembro
				+ "Mt\nOutubro : " + totalOutubro + "Mt\nNovembro : " + totalNovembro + "Mt\nDecembro : "
				+ totalDecembro + " Mt");
		alert.setHeaderText(null);
		alert.setTitle("Totais mensais");
		alert.showAndWait();
	}

	private void refreshItems() {
		pesquisar();
	}

}
