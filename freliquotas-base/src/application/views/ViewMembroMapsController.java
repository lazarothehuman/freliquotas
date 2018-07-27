package application.views;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
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
	
	int ANO= Calendar.getInstance().get(Calendar.YEAR);

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
	TableColumn<Membro, List<Pagamento>> quotaJanColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaAprColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaFevColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaMarchColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaMayColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaJunColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaJulColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaAugColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaSepColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaOctColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaNovColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> quotaDecColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcJanColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcFevColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcMarchColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcAprColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcMayColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcJunColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcJulColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcAugColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcSepColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcOctColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcNovColumn;
	@FXML
	TableColumn<Membro, List<Pagamento>> fcDezColumn;
	DataManager dataManager = new DataManagerImp();

	NotificationManager notificationManager = new NotificationManagerImp();

	FrameManager frameManager = new FrameManager();

	User user;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		user = dataManager.findCurrentUser();
		Distrito distrito = null;
		anoTf.setText(Calendar.getInstance().get(Calendar.YEAR)+"");
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
		PropertyValueFactory<Membro, List<Pagamento>> janFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> fevFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> marchFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> aprFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> mayFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> junFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> julFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> augFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> sepFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> octFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> novFactory = new PropertyValueFactory<>("pagamentos");
		PropertyValueFactory<Membro, List<Pagamento>> decFactory = new PropertyValueFactory<>("pagamentos");
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
		quotaJanColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Janeiro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcJanColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Janeiro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		quotaFevColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Fevereiro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcFevColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Fevereiro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		quotaMarchColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Março)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcMarchColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Março)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaAprColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Abril)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcAprColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Abril)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaMayColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Maio)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcMayColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Maio)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		quotaJulColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Julho)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcJulColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Julho)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaJunColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Junho)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcJunColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Junho) && pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaAugColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Agosto)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcAugColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Agosto)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaSepColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Setembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcSepColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Setembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		quotaOctColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Outubro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcOctColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Outubro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaNovColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Novembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcNovColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Novembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});

		quotaDecColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Quota)
								&& pagamento.getMes().equals(Mes.Dezembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
			}
		});
		fcDezColumn.setCellFactory(col -> new TableCell<Membro, List<Pagamento>>() {
			@Override
			public void updateItem(List<Pagamento> pagamentos, boolean empty) {
				if (empty)
					setText(null);
				else {
					for (Pagamento pagamento : pagamentos) {
						if (pagamento.getTipoPagamento().equals(TipoPagamento.Fundo_comité)
								&& pagamento.getMes().equals(Mes.Dezembro)&& pagamento.getAno()==ANO)
							setText(pagamento.getValor() + "Mt");
					}
				}
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
