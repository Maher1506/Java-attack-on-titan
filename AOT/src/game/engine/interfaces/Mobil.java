package game.engine.interfaces;

//for all objects that can move
public interface Mobil {
	
	//retrieves the mobil's distance from the target
	int getDistance();
	//changes the mobil's distance from the target
	void setDistance(int distance);
	//retrieves the mobil's speed
	int getSpeed();
	//changes the mobil's speed
	void setSpeed(int speed);
}
