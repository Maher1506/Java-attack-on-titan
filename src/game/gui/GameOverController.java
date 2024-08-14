package game.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GameOverController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public static int score;
	
	//GAME OVER SCORE TEXT
	@FXML
	private Label gameOverScoreLabel;
	
	//INITIALIZE AFTER SCENE CREATED
	@FXML
    private void initialize() {
		// Call this method to spawn a circle when the scene is initialized
		gameOverScoreLabel.setText("Your Score: " + score);
	}
	//SWITC BACK TO MAIN MENU
	public void switchToScene1(ActionEvent e) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("Scene1.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
