package game.gui;

import java.awt.Rectangle;
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
import game.engine.weapons.PiercingCannon;
import game.engine.weapons.SniperCannon;
import game.engine.weapons.VolleySpreadCannon;
import game.engine.weapons.WallTrap;
import game.engine.weapons.Weapon;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EasyGameController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	private Battle b = new Battle(1, 0, 99, 3, 250);
	
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
		
	//Titan spawn location hBoxes
	@FXML
    private Pane lane0Box;
	@FXML
    private Pane lane1Box;
	@FXML
    private Pane lane2Box;
	
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
	
	//LANE WALL HEALTH LABELS
	@FXML
	private Label lane0Health;
	@FXML
	private Label lane1Health;
	@FXML
	private Label lane2Health;
	
	//IMAGES FOR DISPLAYING LANE IS LOST
	@FXML
	private ImageView lane0Image;
	@FXML
	private ImageView lane1Image;
	@FXML
	private ImageView lane2Image;
	
	//LANE WALL IMAGES
	@FXML
	private ImageView lane0WallImage;
	@FXML
	private ImageView lane1WallImage;
	@FXML
	private ImageView lane2WallImage;
	
	//GRID PANE FOR SPAWNING TITANS IN
	@FXML
	private GridPane gridPane;
	
	public EasyGameController() throws IOException
	{

	}
	//INITIALIZE AFTER SCENE CREATED
	@FXML
    private void initialize() {
        // Call this method to spawn a circle when the scene is initialized
		updateGUI();
	}
	//SPAWNING WEAPONS
	private void spawnWeapon(Pane container, Button src, int i, ActionEvent e) throws InsufficientResourcesException, InvalidLaneException
	{
		// Create a new Circle object
        Circle circle = new Circle(15, Color.RED); // Radius = 50, Color = Red
        
        if(src.getId() == wallTrapBtn.getId())
        {
        	circle = new Circle(5, Color.GREEN);
        	b.purchaseWeapon(4, b.getOriginalLanes().get(i));
        	updateGUI();

    		checkGameOver(e);
        }
        else if(src.getId() == volleySpreadBtn.getId())
        {
        	circle = new Circle(5, Color.BLUE);
        	b.purchaseWeapon(3, b.getOriginalLanes().get(i));
        	updateGUI();
        	
        	checkGameOver(e);
        }
        else if(src.getId() == sniperCannonBtn.getId())
        {
        	circle = new Circle(5, Color.PURPLE);
        	b.purchaseWeapon(2, b.getOriginalLanes().get(i));
        	updateGUI();
        	
        	checkGameOver(e);
        }
        else if(src.getId() == piercingCannonBtn.getId())
        {
        	circle = new Circle(5, Color.ORANGE);
        	b.purchaseWeapon(1, b.getOriginalLanes().get(i));
        	updateGUI();
        	
        	checkGameOver(e);
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

               
        // Add radio buttons to a VBox
        VBox vBox = new VBox();
        vBox.getChildren().addAll(radioButton1, radioButton2, radioButton3);

        // Create an "OK" button
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            // Close the pop-up dialog when the button is clicked
        	if(radioButton1.isSelected())
    		{
    			try {
					spawnWeapon(weapon0Box, sourceBtn, 0, e);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showResourcesPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showLanePopup();
				}
    		}
    		else if(radioButton2.isSelected())
    		{
    			try {
					spawnWeapon(weapon1Box, sourceBtn, 1, e);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showResourcesPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showLanePopup();
				}
    		}
    		else if(radioButton3.isSelected())
    		{
    			try {
					spawnWeapon(weapon2Box, sourceBtn, 2, e);
				} catch (InsufficientResourcesException e1) {
					// TODO Auto-generated catch block
					showResourcesPopup();
				} catch (InvalidLaneException e1) {
					// TODO Auto-generated catch block
					showLanePopup();
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
	private void showResourcesPopup() {
        
		Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Insufficient Resources");
        alert.setHeaderText(null);
        alert.setContentText("You do not have enough resources to purchase");
        alert.showAndWait();
    }
	private void showLanePopup() 
	{
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Invalid Lane");
        alert.setHeaderText(null);
        alert.setContentText("This lane is destroyed");
        alert.showAndWait();
    }
	//PASS TURN
	public void passTurn(ActionEvent e) throws IOException
	{
		b.passTurn();
		updateGUI();

		checkGameOver(e);
	}
	//SPAWNING TITANS
	public void spawnTitan(Pane container, Titan titan, int xPos, int yPos) {
		
		Color color = Color.PINK;
		String titanName = "";
		int size = 5;
		
		if(titan instanceof PureTitan)
		{
			color = Color.RED;
			titanName = "Pure Titan";
			size = 7;
		}
		else if(titan instanceof ColossalTitan)
		{
			color = Color.BROWN;
			titanName = "Colossal Titan";
			size = 15;
		}
		else if(titan instanceof ArmoredTitan)
		{
			color = Color.BLACK;
			titanName = "ArmoredTitan";
			size = 7;
		}
		else if(titan instanceof AbnormalTitan)
		{
			color = Color.CYAN;
			titanName = "AbnormalTitan";
			size = 5;
		}
		
		String titanData = "Name: " + titanName + 
				"\nHealth: " + titan.getCurrentHealth() + 
        		"\nDamage: " + titan.getDamage() + 
        		"\nHeight: " + titan.getHeightInMeters() + "m" + 
        		"\nResources Value: " + titan.getResourcesValue() + 
        		"\nSpeed: " + titan.getSpeed();
        		
		// Create a new Circle object
        Circle circle = new Circle(size, color);
        
        Tooltip tooltip = new Tooltip(titanData);
        Tooltip.install(circle, tooltip);

        // Create a VBox to stack the label above the circle
        VBox vbox = new VBox(5); // 5 is the spacing between the label and circle
        vbox.getChildren().addAll(circle);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);

        // Create a StackPane to center the VBox
        StackPane stackPane = new StackPane(vbox);
        stackPane.setAlignment(javafx.geometry.Pos.CENTER);
        
        gridPane.setClip(null);
        // Radius = 5, Color = Red
        gridPane.add(stackPane, xPos, yPos);
    }
	//CLEARS TITANS IN GRID PANE
	public static void clearAllTitans(GridPane gridPane) 
	{
        gridPane.getChildren().removeIf(node -> node instanceof StackPane);
    }
	//UPDATING UI
	private void updateGUI()
	{
		updateTitans();
		
		updateScore();
		updateTurn();
		updatePhase();
		updateResources();
		
		updateDangerLevels();
		updateHealthLevels();
		
		updateLostLanes();
        updateLaneHover();
	}
	private void updateLaneHover()
	{
		String lane0Description = "Lane: 1" + generateAllItemsInLane(b.getOriginalLanes().get(0));
		Tooltip lane0ToolTip = new Tooltip(lane0Description);
        Tooltip.install(lane0Health, lane0ToolTip);
        
        String lane1Description = "Lane: 2" + generateAllItemsInLane(b.getOriginalLanes().get(1));
		Tooltip lane1ToolTip = new Tooltip(lane1Description);
        Tooltip.install(lane1Health, lane1ToolTip);
        
        String lane2Description = "Lane: 3" + generateAllItemsInLane(b.getOriginalLanes().get(2));
		Tooltip lane2ToolTip = new Tooltip(lane2Description);
        Tooltip.install(lane2Health, lane2ToolTip);
        
        
	}
	private String generateAllItemsInLane(Lane lane)
	{
		String res = "\nTitans: " + lane.getTitans().size();;
		for(Titan titan : lane.getTitans())
		{
			if(titan instanceof PureTitan)
			{
				res += "\nPure Titan";
			}
			else if(titan instanceof ColossalTitan)
			{
				res += "\nColossal Titan";
			}
			else if(titan instanceof ArmoredTitan)
			{
				res += "\nArmoredTitan";
			}
			else if(titan instanceof AbnormalTitan)
			{
				res += "\nAbnormalTitan";
			}
		}
		res += "\nWeapons: " + lane.getWeapons().size();;
		for(Weapon weapon : lane.getWeapons())
		{
			if(weapon instanceof PiercingCannon)
			{
				res += "\nPiercing Cannon";
			}
			else if(weapon instanceof SniperCannon)
			{
				res += "\nSniper Cannon";
			}
			else if(weapon instanceof VolleySpreadCannon)
			{
				res += "\nArmoredTitan";
			}
			else if(weapon instanceof WallTrap)
			{
				res += "\nAbnormalTitan";
			}
		}
		return res;
	}
	private void checkGameOver(ActionEvent e)
	{
		if(b.isGameOver())
		{
			try {
				GameOverController.score = b.getScore();
				root = FXMLLoader.load(getClass().getResource("GameOver.fxml"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			stage = (Stage)((Node)e.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
	}
	private void updateLostLanes()
	{
		for(Lane lane : b.getOriginalLanes())
		{
			if(lane.isLaneLost())
			{
				if(b.getOriginalLanes().get(0) == lane)
				{
					lane0Image.setVisible(true);
				}
				else if(b.getOriginalLanes().get(1) == lane)
				{
					lane1Image.setVisible(true);
				}
				else if(b.getOriginalLanes().get(2) == lane)
				{
					lane2Image.setVisible(true);
				}
				
			}
		}
		
	}
	private void updateHealthLevels()
	{
		lane0Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(0).getLaneWall().getCurrentHealth()));
		lane1Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(1).getLaneWall().getCurrentHealth()));
		lane2Health.setText("Health: " + Integer.toString(b.getOriginalLanes().get(2).getLaneWall().getCurrentHealth()));
		
	}
	private void updateDangerLevels()
	{
		lane0DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(0).getDangerLevel()));
		lane1DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(1).getDangerLevel()));
		lane2DangerLevel.setText("Danger level: " + Integer.toString(b.getOriginalLanes().get(2).getDangerLevel()));
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
	private void updateTitans()
	{
		clearAllTitans(gridPane);
		
		for(Lane lane : b.getOriginalLanes())
		{
			for(Titan titan : lane.getTitans())
			{
				if(lane == b.getOriginalLanes().get(0))
				{
					spawnTitan(lane0Box, titan, titan.getDistance(), 0);
				}
				else if(lane == b.getOriginalLanes().get(1))
				{
					spawnTitan(lane1Box, titan, titan.getDistance(), 1);
				}
				else if(lane == b.getOriginalLanes().get(2))
				{
					spawnTitan(lane2Box, titan, titan.getDistance(), 2);
				}
				
			}
		}
	}
	//GAME OVER SCENE
	public int getScore()
	{
		return b.getScore();
	}
}
