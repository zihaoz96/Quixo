package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

	public static Scene homePage, gamePage;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent gameParent = FXMLLoader.load(getClass().getResource("GamePage.fxml"));

		Launcher.gamePage = new Scene(gameParent, gameParent.getLayoutY(), gameParent.getLayoutX());

		primaryStage.setTitle("Quixo");
		primaryStage.setResizable(false);
		primaryStage.setWidth(1000);
		primaryStage.setHeight(650);

		primaryStage.setScene(gamePage);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}