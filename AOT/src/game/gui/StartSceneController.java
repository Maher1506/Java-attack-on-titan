package game.gui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class StartSceneController {
	
	@FXML
	private RadioButton easyBtn;
	@FXML
	private RadioButton hardBtn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	public void startGameAction(ActionEvent e) throws IOException
	{
		if(easyBtn.isSelected())
		{
			root = FXMLLoader.load(getClass().getResource("EasyGame.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		else
		{
			root = FXMLLoader.load(getClass().getResource("HardGame.fxml"));
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
	}
}
