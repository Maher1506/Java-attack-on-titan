package game.engine;

import game.engine.base.Wall;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import game.engine.dataloader.DataLoader;
import game.engine.lanes.Lane;
import game.engine.titans.Titan;
import game.engine.titans.TitanRegistry;
import game.engine.weapons.factory.WeaponFactory;


public class Battle {
	private static final int[][] PHASES_APPROACHING_TITANS = {{1,1,1,2,1,3,4},
															  {2,2,2,1,3,3,4},
															  {4,4,4,4,4,4,4}};
	private static final int WALL_BASE_HEALTH = 10000;
		
	private int numberOfTurns;
	private int resourcesGathered;
	private BattlePhase battlePhase;
	private int numberOfTitansPerTurn;
	private int score;
	private int titanSpawnDistance;
	
	private final WeaponFactory weaponFactory;
	private final HashMap<Integer, TitanRegistry> titansArchives;
	private final ArrayList<Titan> approachingTitans;
	private final PriorityQueue<Lane> lanes;
	private final ArrayList<Lane> originalLanes;
	
	public Battle(int numberOfTurns, int score, int titanSpawnDistance, int initialNumOfLanes,
			int initialResourcesPerLane) throws IOException
	{			
		this.numberOfTurns = numberOfTurns;
		this.resourcesGathered = initialResourcesPerLane * initialNumOfLanes;
		this.battlePhase = BattlePhase.EARLY;
		this.numberOfTitansPerTurn = 1;
		this.score = score;
		this.titanSpawnDistance = titanSpawnDistance;
		
		this.weaponFactory = new WeaponFactory();
		this.titansArchives = DataLoader.readTitanRegistry();
		this.approachingTitans = new ArrayList();
		this.lanes = new PriorityQueue();
		this.originalLanes = new ArrayList();
	}

	public static void main(String[] args) throws IOException
	{
		Battle b = new Battle(3, 100,100, 8,100);
		b.initializeLanes(4);
		System.out.println(b.originalLanes.size());
		System.out.println(b.lanes.size());
	}

	private void initializeLanes(int numOfLanes)
	{;
		for(int i = 0; i<numOfLanes; i++)
		{
			Wall w = new Wall(WALL_BASE_HEALTH);
			Lane l = new Lane(w);
			originalLanes.add(l);
			lanes.add(l);
		}
	}

	public static int[][] getPhasesApproachingTitans() {
		return PHASES_APPROACHING_TITANS;
	}

	public static int getWallBaseHealth() {
		return WALL_BASE_HEALTH;
	}

	public void setNumberOfTurns(int numberOfTurns) {
		if (numberOfTurns >= 0)
		{

			this.numberOfTurns = numberOfTurns;
		}
		else
		{
			this.numberOfTurns = 0;
		}
	}

	public int getNumberOfTurns() {
		return numberOfTurns;
	}

	public int getResourcesGathered() {
		return resourcesGathered;
	}

	public void setResourcesGathered(int resourcesGathered) {
		if (resourcesGathered >= 0)
		{
			this.resourcesGathered = resourcesGathered;
		}
		else
		{
			this.resourcesGathered = 0;
		}
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
		
		if (resourcesGathered > 0)
		{
			this.numberOfTitansPerTurn = numberOfTitansPerTurn;	
		}
		else
		{
			this.numberOfTitansPerTurn = 1;
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		if (score >= 0)
		{
			this.score = score;
		}
		else
		{
			this.score = 0;
		}
	}

	public int getTitanSpawnDistance() {
		return titanSpawnDistance;
	}

	public void setTitanSpawnDistance(int titanSpawnDistance) {
		if (titanSpawnDistance >= 0)
		{

			this.titanSpawnDistance = titanSpawnDistance;
		}
		else
		{
			this.titanSpawnDistance = 0;
		}
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
