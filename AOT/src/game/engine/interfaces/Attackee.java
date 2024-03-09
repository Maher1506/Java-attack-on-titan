package game.engine.interfaces;

//for all objects that can be attacked
public interface Attackee {
	
	//retrieves health
	int getCurrentHealth();
	//changes attackee's health
	void setCurrentHealth(int health);
	//retrieves resource value of the attackee
	int getResourceValue();
}
