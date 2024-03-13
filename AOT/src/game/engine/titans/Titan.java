package game.engine.titans;

import game.engine.interfaces.*;

public abstract class Titan implements Attackee, Attacker, Mobil, Comparable<Titan> {
	
	private final int baseHealth;
	private int currentHealth;
	private final int baseDamage;
	private final int heightInMeters;
	private int distanceFromBase;
	private int speed;
	private final int resourcesValue;
	private final int dangerLevel;
	
	public Titan(int baseHealth, int baseDamage, int heightInMeters, 
		  int distanceFromBase, int speed, int resourcesValue, int dangerLevel)
	{
		this.baseHealth = baseHealth;
		this.baseDamage = baseDamage;
		this.heightInMeters = heightInMeters;
		this.distanceFromBase = distanceFromBase;
		this.speed = speed;
		this.resourcesValue = resourcesValue;
		this.dangerLevel = dangerLevel;
		this.currentHealth = baseHealth;
	}
	
	public int getBaseHealth()
	{
		return this.baseHealth;
	}
	
	public int getCurrentHealth()
	{
		return this.currentHealth;
	}
	
	public void setCurrentHealth(int health)
	{
		if (health >= 0)
		{
			this.currentHealth = health;
		}
		else
		{
			this.currentHealth = 0;
		}	
	}
	
	public int getDamage()
	{
		return this.baseDamage;
	}
	
	public int getHeightInMeters()
	{
		return this.heightInMeters;
	}
	
	public int getDistance()
	{
		return this.distanceFromBase;
	}
	
	public void setDistance(int distance)
	{
		this.distanceFromBase = distance;
	}
	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public void setSpeed(int speed)
	{
		this.speed = speed;
	}

	public int getResourcesValue()
	{
		return this.resourcesValue; 
	}
	
	public int getDangerLevel()
	{
		return this.dangerLevel;
	}
	
	public int compareTo(Titan o)
	{
		return this.distanceFromBase - o.distanceFromBase;
	}
	
	
	
	
	
	
	
	
}
	