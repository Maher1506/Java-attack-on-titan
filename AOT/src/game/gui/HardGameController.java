package game.gui;

import java.io.IOException;
import java.util.List;

import game.engine.Battle;
import game.engine.BattlePhase;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.ArmoredTitan;
import game.engine.titans.ColossalTitan;
import game.engine.titans.PureTitan;
import game.engine.titans.Titan;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HardGameController {
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Battle b = new Battle(1, 0, 910, 5, 125);
	
	//UI Labels
	@FXML
	private Label scoreLabel;
	@FXML
	private Label turnLabel;
	@FXML
	private Label phaseLabel;
	@FXML
	private Label resourcesLabel;
	
	//Weapon spawn location vBoxes
	@FXML
    private Pane weapon0Box;
	@FXML
	private Pane weapon1Box;
	@FXML
	private Pane weapon2Box;
	@FXML
	private Pane weapon3Box;
	@FXML
	private Pane weapon4Box;
		
	//Titan spawn location hBoxes
	@FXML
    private Pane lane0Box;
	@FXML
    private Pane lane1Box;
	@FXML
    private Pane lane2Box;
	@FXML
    private Pane lane3Box;
	@FXML
    private Pane lane4Box;
	
	//WEAPON SHOP BTNS
	@FXML
	private Button wallTrapBtn;
	@FXML
	private Button volleySpreadBtn;
	@FXML
	private Button sniperCannonBtn;
	@FXML
	private Button piercingCannonBtn;
	
	//LANE DANGER LEVELS LABELS
	@FXML
	private Label lane0DangerLevel;
	@FXML
	private Label lane1DangerLevel;
	@FXML
	private Label lane2DangerLevel;
	@FXML
	private Label lane3DangerLevel;
	@FXML
	private Label lane4DangerLevel;
	
	//LANE WALL HEALTH LABELS
	@FXML
	private Label lane0Health;
	@FXML
	private Label lane1Health;
	@FXML
	private Label lane2Health;
	@FXML
	private Label lane3Health;
	@FXML
	private Label lane4Health;
	
	public HardGameController() throws IOException
	{
		//System.out.println(b.getScore());
	}
	//INITIALIZE AFTER SCENE CREATED
	@FXML
    private void initialize() {
        // Call this method to spawn a circle when the scene is initialized
		updateGUI();
		/*spawnTitanLane0();
		spawnTitanLane1();
		spawnTitanLane2();
		spawnTitanLane3();
		spawnTitanLane4();*/
	}
	//SPAWNING WEAPONS
	private void spawnWeapon(Pane container, Button src, int i) throws InsufficientResourcesException, InvalidLaneException
	{
		// Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        if(src.getId() == wallTrapBtn.getId())
        {
        	circle = new Circle(10, Color.GREEN);
        	b.purchaseWeapon(4, b.getOriginalLanes().get(i));
        	updateGUI();
        }
        else if(src.getId() == volleySpreadBtn.getId())
        {
        	circle = new Circle(10, Color.BLUE);
        	b.purchaseWeapon(3, b.getOriginalLanes().get(i));
        	updateGUI();
        }
        else if(src.getId() == sniperCannonBtn.getId())
        {
        	circle = new Circle(10, Color.PURPLE);
        	b.purchaseWeapon(2, b.getOriginalLanes().get(i));
        	updateGUI();
        }
        else if(src.getId() == piercingCannonBtn.getId())
        {
        	circle = new Circle(10, Color.ORANGE);
        	b.purchaseWeapon(1, b.getOriginalLanes().get(i));
        	updateGUI();
        }
        
        // Set the position of the circle within the containerPane
        double xCoordinate = container.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = container.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 75); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        container.getChildren().add(circle);
	}
	public void showChooseLanePopup(ActionEvent e)
	{
		Button sourceBtn = (Button)e.getSource();
			
		// Create a toggle group for radio buttons
        ToggleGroup toggleGroup = new ToggleGroup();

        // Create radio buttons
        RadioButton radioButton1 = new RadioButton("Lane 1");
        radioButton1.setToggleGroup(toggleGroup);
        radioButton1.setSelected(true); // Select the first option by default

        RadioButton radioButton2 = new RadioButton("Lane 2");
        radioButton2.setToggleGroup(toggleGroup);

        RadioButton radioButton3 = new RadioButton("Lane 3");
        radioButton3.setToggleGroup(toggleGroup);

        RadioButton radioButton4 = new RadioButton("Lane 4");
        radioButton4.setToggleGroup(toggleGroup);

        RadioButton radioButton5 = new RadioButton("Lane 5");
        radioButton5.setToggleGroup(toggleGroup);
        
        // Add radio buttons to a VBox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(radioButton1, radioButton2, radioButton3, radioButton4, radioButton5);

        // Create an "OK" button
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            // Close the pop-up dialog when the button is clicked
        	if(radioButton1.isSelected())
    		{
    			try {
					spawnWeapon(weapon0Box, sourceBtn, 0);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showPopup();
				}
    		}
    		else if(radioButton2.isSelected())
    		{
    			try {
					spawnWeapon(weapon1Box, sourceBtn, 1);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showPopup();
				}
    		}
    		else if(radioButton3.isSelected())
    		{
    			try {
					spawnWeapon(weapon2Box, sourceBtn, 2);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showPopup();
				}
    		}
    		else if(radioButton4.isSelected())
    		{
    			try {
					spawnWeapon(weapon3Box, sourceBtn, 3);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showPopup();
				}
    		}
    		else if(radioButton5.isSelected())
    		{
    			try {
					spawnWeapon(weapon4Box, sourceBtn, 4);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showPopup();
				}
    		}
            ((Stage) okButton.getScene().getWindow()).close();
        });

        // Add the OK button to the VBox
        vBox.getChildren().add(okButton);

        // Create an alert
        Alert alert = new Alert(Alert.AlertType.NONE);
        
        alert.getDialogPane().setPrefWidth(300);
        alert.getDialogPane().setPrefHeight(300);
        alert.setHeaderText(null);
        alert.setTitle("Choose a lane to build the weapon in");
        alert.getDialogPane().setContent(vBox);

        // Show the alert
        alert.showAndWait();
	}
	//POPUPS FOR GAME EXCEPTIONS
	private void showPopup() {
        
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Insufficient Resources");
        alert.setHeaderText(null);
        alert.setContentText("You do not have enough resources to purchase");
        alert.showAndWait();
    }
	//PASS TURN
	public void passTurn() throws IOException
	{
		if(!b.isGameOver())
		{
			b.passTurn();
			updateGUI();
		}
		else
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));
	        Parent gameOverRoot = loader.load();
	        Scene gameOverScene = new Scene(gameOverRoot);
	        Stage primaryStage = (Stage) scoreLabel.getScene().getWindow(); // Assuming scoreLabel is in your scene
	        primaryStage.setScene(gameOverScene);
	        primaryStage.show();
		}
	}
	//SPAWNING TITANS
	public void spawnTitanLane0() {
		
		for(Lane lane:b.getOriginalLanes())
		{
			for(Titan titan : lane.getTitans()) {
				if(titan instanceof PureTitan)
				{
					  Circle circle = new Circle(15, Color.PINK); // Radius = 50, Color = Red
				        
				        // Set the position of the circle within the containerPane
				        double xCoordinate = lane0Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
				        double yCoordinate = lane0Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
				        circle.setLayoutX(xCoordinate + 1000); // Set x-coordinate
				        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
				        
				        // Add the circle to the containerPane's children
				        lane0Box.getChildren().add(circle);
				        
				}
				else if(titan instanceof ColossalTitan)
				{
					  Circle circle = new Circle(15, Color.BLACK); // Radius = 50, Color = Red
				        
				        // Set the position of the circle within the containerPane
				        double xCoordinate = lane0Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
				        double yCoordinate = lane0Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
				        circle.setLayoutX(xCoordinate + 1000); // Set x-coordinate
				        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
				        
				        // Add the circle to the containerPane's children
				        lane0Box.getChildren().add(circle);
				}
				else if(titan instanceof ArmoredTitan)
				{
					  Circle circle = new Circle(15, Color.YELLOW); // Radius = 50, Color = Red
				        
				        // Set the position of the circle within the containerPane
				        double xCoordinate = lane0Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
				        double yCoordinate = lane0Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
				        circle.setLayoutX(xCoordinate + 1000); // Set x-coordinate
				        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
				        
				        // Add the circle to the containerPane's children
				        lane0Box.getChildren().add(circle);
				}
				else if(titan instanceof AbnormalTitan)
				{
					  Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
				        
				        // Set the position of the circle within the containerPane
				        double xCoordinate = lane0Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
				        double yCoordinate = lane0Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
				        circle.setLayoutX(xCoordinate + 1000); // Set x-coordinate
				        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
				        
				        // Add the circle to the containerPane's children
				        lane0Box.getChildren().add(circle);
				}
			}
			
		}
        
		
		
		// Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        // Set the position of the circle within the containerPane
        double xCoordinate = lane0Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = lane0Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 1000); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        lane0Box.getChildren().add(circle);
    }
	public void spawnTitanLane1() {
        // Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        // Set the position of the circle within the containerPane
        double xCoordinate = lane1Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = lane1Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 75); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        lane1Box.getChildren().add(circle);
    }
	public void spawnTitanLane2() {
        // Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        // Set the position of the circle within the containerPane
        double xCoordinate = lane2Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = lane2Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 75); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        lane2Box.getChildren().add(circle);
    }
	public void spawnTitanLane3() {
        // Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        // Set the position of the circle within the containerPane
        double xCoordinate = lane3Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = lane3Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 75); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        lane3Box.getChildren().add(circle);
    }
	public void spawnTitanLane4() {
        // Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        // Set the position of the circle within the containerPane
        double xCoordinate = lane4Box.getWidth() - circle.getRadius(); // Calculate x-coordinate
        double yCoordinate = lane4Box.getHeight() / 2.0; // Set y-coordinate to the middle of the pane
        circle.setLayoutX(xCoordinate + 75); // Set x-coordinate
        circle.setLayoutY(yCoordinate + 40); // Set y-coordinate
        
        // Add the circle to the containerPane's children
        lane4Box.getChildren().add(circle);
    }
	//UPDATING UI
	private void updateGUI()
	{
		updateScore();
		updateTurn();
		updatePhase();
		updateResources();
		updateDangerLevels();
		updateHealthLevels();
	}
	private void updateHealthLevels()
	{
		lane0Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()));
		lane1Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()));
		lane2Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()));
		lane3Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(3).getLaneWall().getCurrentHealth()));
		lane4Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(4).getLaneWall().getCurrentHealth()));
	}
	private void updateDangerLevels()
	{
		lane0DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(0).getDangerLevel()));
		lane1DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(1).getDangerLevel()));
		lane2DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(2).getDangerLevel()));
		lane3DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(3).getDangerLevel()));
		lane4DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(4).getDangerLevel()));
	}
	private void updateScore()
	{
		int score = b.getScore();
		scoreLabel.setText("Score: " + Integer.toString(score));
	}
	private void updateTurn()
	{
		int turns = b.getNumberOfTurns();
		turnLabel.setText("Turn: " + Integer.toString(turns));
	}
	private void updatePhase()
	{
		BattlePhase phase = b.getBattlePhase();
		phaseLabel.setText("Phase: " + phase.toString());
	}
	private void updateResources()
	{
		int resources = b.getResourcesGathered();
		resourcesLabel.setText("Resources: " + Integer.toString(resources));
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
