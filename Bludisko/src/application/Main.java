package application;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Main extends Application {
	private boolean gameCompleted = false;
	int pokus;

	@Override
	public void start(Stage primaryStage) {
		Group root = new Group();
		Game g = new Game();
		root.getChildren().add(g);
		Scene scene = new Scene(root, 930, 700);
		scene.setFill(Color.BURLYWOOD);
		primaryStage.setScene(scene);
		primaryStage.show();
		MyTimer t = new MyTimer(g);
		t.start();
		AnimationTimer animationTimer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (!gameCompleted && g.stopniTo()) {
					t.stop();
					gameCompleted = true;
					pokus = g.getPokus();
					showGameCompletionAlert();
				}
			}
		};

		animationTimer.start();

	}

	private void showGameCompletionAlert() {
		Platform.runLater(() -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Hra ukončena");
			alert.setHeaderText(null);
			alert.setContentText(
					"Gratulujem, dokončil si hru! Na prejdenie 6 levelov si potreboval " + pokus + " pokusov");
			alert.showAndWait();
			Platform.exit();
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
