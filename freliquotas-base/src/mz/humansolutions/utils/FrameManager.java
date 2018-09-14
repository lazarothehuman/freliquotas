package mz.humansolutions.utils;

import application.forms.ModifyDistritoController;
import application.forms.ModifyMembroController;
import application.forms.ModifyUserController;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import mz.humansolutions.managers.DataManager;
import mz.humansolutions.managers.DataManagerImp;
import mz.humansolutions.models.Distrito;
import mz.humansolutions.models.Membro;
import mz.humansolutions.models.Profile;
import mz.humansolutions.models.Transaccao;
import mz.humansolutions.models.User;

public class FrameManager {

	DataManager dataManager = new DataManagerImp();

	public void mainController() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/Main.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Freliquotas");
			primaryStage.getIcons().add(new Image("frelimo.jpg"));
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void login() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/login/Login.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Freliquotas Login");
			primaryStage.getIcons().add(new Image("frelimo.jpg"));
			primaryStage.show();
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				Platform.exit();
				System.exit(0);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void searchCliente() {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/application/views/View-Client.fxml"));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Procurar ");
			primaryStage.getIcons().add(new Image("frelimo.jpg"));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void addMembro(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(201l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}

	public void addPagamento(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(202l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}

	public void addUser(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(203l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}

	public void addDistrito(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(204l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}

	private void load(String url) {
		Stage primaryStage = new Stage();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(url));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Freliquotas");
			primaryStage.getIcons().add(new Image("frelimo.jpg"));
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void load(String url, Object object) {
		Stage primaryStage = new Stage();
		Membro membro = null;
		User user = null;
		Distrito distrito = null;
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource(url));
			loader.load();
			Parent root = loader.getRoot();
			Scene scene = new Scene(root);
			if(object instanceof Membro) {
				membro = (Membro)object;
				ModifyMembroController modifyMembroController = loader.getController();
				modifyMembroController.setMembro(membro);
			}else {
				if(object instanceof User) {
					user = (User) object;
					if (user != null) {
						ModifyUserController modifyUserController = loader.getController();
						modifyUserController.setUser(user);
					}
				}else {
					if(object instanceof Distrito) {
						distrito = (Distrito) object;
						ModifyDistritoController modify = loader.getController();
						modify.setDistrito(distrito);
					}
				}
			}
			scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void modifyUser(User user, User selectedUser) {
		if (user != null && user!=null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(206l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl(), selectedUser);
			else
				AlertUtils.alertSemPrivelegio();
		}

	}

	public void modifyDistrito(Distrito selectedDistrito, User user) {
		if (user != null && user!=null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(207l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl(), selectedDistrito);
			else
				AlertUtils.alertSemPrivelegio();
		}

	}

	public void modifyMembro(User user, Membro selectedMembro) {
		if (user != null && selectedMembro!=null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(205l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl(), selectedMembro);
			else
				AlertUtils.alertSemPrivelegio();
		}

	}

	public void viewMembros(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(301l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}

	}

	public void viewDistritos(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(302l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}

	}

	public void viewUsers(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(303l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		
	}

	public void viewMaps(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(304l);
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
	}

	public void reportMembroMaps(User user) {
		if (user != null) {
			Profile profile = user.getProfile();
			Transaccao transaction = dataManager.findTransaccao(401l);//still null
			if (transaction.getProfiles().contains(profile))
				load(transaction.getUrl());
			else
				AlertUtils.alertSemPrivelegio();
		}
		
	}


}
