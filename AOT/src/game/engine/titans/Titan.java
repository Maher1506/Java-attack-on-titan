package game.engine.titans;

public abstract class Titan {
	int baseHealth;
	int currentHealth;
	int baseDamage;
	int heightInMeters;
	int distanceFromBase;
	int speed;
	int resourcesValue;
	int dangerLevel;
	
	Titan(int baseHealth, int baseDamage, int heightInMeters, 
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

}