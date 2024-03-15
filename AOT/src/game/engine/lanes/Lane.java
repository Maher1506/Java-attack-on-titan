package game.engine.lanes;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

import java.util.*;

public class Lane implements Comparable<Lane>{
	
	private final Wall laneWall;
	private int dangerLevel;
	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;
	
	public Lane(Wall laneWall)
	{
		this.laneWall = laneWall;
		this.dangerLevel = 0;
		this.titans = new PriorityQueue();
		this.weapons = new ArrayList();
	}
	
	public Wall getLaneWall()
	{
		return laneWall;
	}
	
	public void setDangerLevel(int dangerLevel)
	{
		if (dangerLevel >= 0)
		{
			this.dangerLevel = dangerLevel;		}
		else
		{
			this.dangerLevel = 0;
		}		
	}
	public int getDangerLevel()
	{
		return dangerLevel;
	}
	
	public PriorityQueue<Titan> getTitans() {
		return titans;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	public int compareTo(Lane o)
	{
		return this.dangerLevel - o.dangerLevel;
	}
}
