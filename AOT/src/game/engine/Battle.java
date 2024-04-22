package game.engine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.base.Wall;
import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.exceptions.InvalidLaneException;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.FactoryResponse;
import game.engine.weapons.factory.WeaponFactory;

public class Battle {

	private final static int[][] PHASES_APPROACHING_TITANS =
		{		{ 1, 1, 1, 2, 1, 3, 4 },
				{ 2, 2, 2, 1, 3, 3, 4 },
				{ 4, 4, 4, 4, 4, 4, 4 } };
	private final static int WALL_BASE_HEALTH =10000 ;
	
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final  ArrayList<Titan> approachingTitans;
	private	final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;
	
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;

	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
			int initialResourcesPerLane) throws IOException {
		
		this.numberOfTurns = numberOfTurns;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		
		resourcesGathered = initialNumOfLanes*initialResourcesPerLane;
		titansArchives = DataLoader.readTitanRegistry();
		
		approachingTitans = new ArrayList<Titan>();
		lanes = new PriorityQueue<Lane>();
		originalLanes = new ArrayList<Lane>();
		weaponFactory = new WeaponFactory();
		
		initializeLanes(initialNumOfLanes);
	}
	
	private void initializeLanes(int numOfLanes) {
			
			for(int i = 0 ; i < numOfLanes;i++) {
				Lane l = new Lane(new Wall(WALL_BASE_HEALTH));
				originalLanes.add(l);
				lanes.add(l);
				
			}
	}
	public void refillApproachingTitans()
	{
		int[] earlyCodes = PHASES_APPROACHING_TITANS[0];
		int[] intenseCodes = PHASES_APPROACHING_TITANS[1];
		int[] grumblingCodes = PHASES_APPROACHING_TITANS[2];
	
		switch(battlePhase)
		{
			case EARLY:
				for (int i = 0; i < earlyCodes.length; i++)
				{
					TitanRegistry chosenRegistry = titansArchives.get(earlyCodes[i]);
					approachingTitans.add(chosenRegistry.spawnTitan(titanSpawnDistance));
				}
			case INTENSE:
				for (int i = 0; i < intenseCodes.length; i++)
				{
					TitanRegistry chosenRegistry = titansArchives.get(intenseCodes[i]);
					approachingTitans.add(chosenRegistry.spawnTitan(titanSpawnDistance));
				}			
			case GRUMBLING:
				for (int i = 0; i < grumblingCodes.length; i++)
				{
					TitanRegistry chosenRegistry = titansArchives.get(grumblingCodes[i]);
					approachingTitans.add(chosenRegistry.spawnTitan(titanSpawnDistance));
				}
		}
	}
	public void purchaseWeapon(int weaponCode, Lane lane) throws InsufficientResourcesException,
	InvalidLaneException, IOException
	{
		if(!lane.isLaneLost())
		{
			WeaponFactory wf = new WeaponFactory();
			FactoryResponse resp = wf.buyWeapon(resourcesGathered, weaponCode);
			lane.addWeapon(resp.getWeapon());
			resourcesGathered = resp.getRemainingResources();
			
			performTurn();
		}
		else
		{
			throw new InvalidLaneException();
		}
		
	}
	public void passTurn()
	{
		performTurn();
	}
	private void addTurnTitansToLane()
	{
		if (approachingTitans.isEmpty())
		{
			refillApproachingTitans();
		}
		
		Lane leastDangerLevel = lanes.peek();
		Titan titanToAdd = approachingTitans.get(0);
		
		for(int i = 0; i < numberOfTitansPerTurn; i++)
		{
			leastDangerLevel.addTitan(titanToAdd);
		}
		
		approachingTitans.remove(0);
	}
	private void moveTitans()
	{
		for(Lane l : lanes)
		{
			l.moveLaneTitans();
		}
	}
	private int performWeaponsAttacks()
	{
		int resourcesGathered = 0;
		for(Lane l : lanes)
		{
			resourcesGathered += l.performLaneWeaponsAttacks();
		}
		return resourcesGathered;
	}
	private int performTitansAttacks()
	{
		int resourcesGathered = 0;
		for(Lane l : lanes)
		{
			resourcesGathered += l.performLaneTitansAttacks();
		}
		return resourcesGathered;
	}
	private void updateLanesDangerLevels()
	{
		for(Lane l : lanes)
		{
			l.updateLaneDangerLevel();
		}
	}
	private void finalizeTurns()
	{
		if(numberOfTurns < 15)
		{
			battlePhase = BattlePhase.EARLY;
		}
		else if(numberOfTurns >= 15 && numberOfTurns < 30)
		{
			battlePhase = BattlePhase.INTENSE;
		}
		else if(numberOfTurns >= 30)
		{
			battlePhase = BattlePhase.GRUMBLING;
			if(numberOfTurns > 30 && numberOfTurns % 5 == 0)
			{
				numberOfTitansPerTurn = numberOfTitansPerTurn * 2;
			}
		}
	}
	private void performTurn()
	{
		moveTitans();
		performWeaponsAttacks();
		performTitansAttacks();
		addTurnTitansToLane();
		
		updateLanesDangerLevels();
		finalizeTurns();
	}
	public boolean isGameOver()
	{
		boolean gameState = true;
		for(Lane l : lanes)
		{
			if(!l.isLaneLost())
			{
				gameState = false;
				return gameState;
			}
		}
		return gameState;
	}
	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		this.numberOfTurns = numberOfTurns;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		this.resourcesGathered = resourcesGathered;
	}

	public BattlePhase getBattlePhase() {
		return battlePhase;
	}

	public void setBattlePhase(BattlePhase battlePhase) {
		this.battlePhase = battlePhase;
	}

	public int getNumberOfTitansPerTurn() {
		return numberOfTitansPerTurn;
	}

	public void setNumberOfTitansPerTurn(int numberOfTitansPerTurn) {
		this.numberOfTitansPerTurn = numberOfTitansPerTurn;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		this.titanSpawnDistance = titanSpawnDistance;
	}

	public int[][] getPHASES_APPROACHING_TITANS() {
		return PHASES_APPROACHING_TITANS;
	}

	public int getWALL_BASE_HEALTH() {
		return WALL_BASE_HEALTH;
	}

	public WeaponFactory getWeaponFactory() {
		return weaponFactory;
	}

	public HashMap<Integer, TitanRegistry> getTitansArchives() {
		return titansArchives;
	}

	public ArrayList<Titan> getApproachingTitans() {
		return approachingTitans;
	}

	public PriorityQueue<Lane> getLanes() {
		return lanes;
	}

	public ArrayList<Lane> getOriginalLanes() {
		return originalLanes;
	}
}
