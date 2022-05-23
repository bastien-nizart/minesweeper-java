package me.nizart.bastien.demineur;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.nizart.bastien.demineur.controller.DemineurController;
import me.nizart.bastien.demineur.model.Demineur;

import java.io.IOException;

public class DemineurApplication extends Application {
	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/demineur.fxml"));
		Parent viewContent = fxmlLoader.load();
		Scene scene = new Scene(viewContent, 1000, 700);
		stage.setScene(scene);

		Demineur facade = new Demineur();
		DemineurController controller = fxmlLoader.getController();

		controller.setModel(facade);
		facade.setController(controller);
		controller.commencerPartie();

		stage.setTitle("Démineur");
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}