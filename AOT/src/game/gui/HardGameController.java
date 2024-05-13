package game.gui;

import java.io.IOException;

import game.engine.Battle;
import game.engine.BattlePhase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class HardGameController {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Battle b = new Battle(1, 0, 100, 5, 125);
	
	@FXML
	private Label scoreLabel;
	@FXML
	private Label turnLabel;
	@FXML
	private Label phaseLabel;
	@FXML
	private Label resourcesLabel;
	
	public HardGameController() throws IOException
	{
		//System.out.println(b.getScore());
	}
	
	public void updateGUI()
	{
		updateScore();
		updateTurn();
		updatePhase();
		updateResources();
	}
	
	public void updateScore()
	{
		int score = b.getScore();
		scoreLabel.setText(Integer.toString(score));
	}
	public void updateTurn()
	{
		int turns = b.getNumberOfTurns();
		turnLabel.setText(Integer.toString(turns));
	}
	public void updatePhase()
	{
		BattlePhase phase = b.getBattlePhase();
		phaseLabel.setText(phase.toString());
	}
	public void updateResources()
	{
		int resources = b.getResourcesGathered();
		resourcesLabel.setText(Integer.toString(resources));
	}
	
	public void startGameAction(ActionEvent e) throws IOException
	{
		root = FXMLLoader.load(getClass().getResource("EasyGame.fxml"));
		stage = (Stage)((Node)e.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
