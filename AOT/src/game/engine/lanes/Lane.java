package game.engine.lanes;

import java.util.*;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane> {

	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;
	private final Wall laneWall;
	private int dangerLevel;

	public Lane(Wall laneWall) {
		this.laneWall = laneWall;
		titans = new PriorityQueue<Titan>();
		weapons = new ArrayList<Weapon>();
		dangerLevel = 0;
	}

	public void addTitan(Titan titan){
		// offer adds element to PriorityQueue
		titans.offer(titan);       
	}
	public void addWeapon(Weapon weapon){
		weapons.add(weapon);       
	}
	
	public void moveLaneTitans(){
		 for (Titan titan : titans){
			 if (!titan.hasReachedTarget()){
				 titan.move();
			 }
		 }
	 }

	public  int performLaneTitansAttacks(){
		int resourcesGathered = 0;
		for (Titan titan : titans) //CANNOT LOOP THROUGH PriorityQueue
		{
			if (titan.hasReachedTarget()){
				 resourcesGathered += titan.attack(laneWall);
			}
		}
		return resourcesGathered;
	 }
	
	 public int performLaneWeaponsAttacks(){
		 int resourcesGathered = 0;
		 for (Weapon weapon : weapons){
			 //weapon.turnAttack(titans);
		 }
		 return resourcesGathered;
	 }
	 
	 public boolean isLaneLost(){
		 if (this.laneWall.isDefeated()){
			 return true;
		 }
		 else 
			 return false;
	 }
	 
	 public void updateLaneDangerLevel(){
		 for (Titan titan : titans)  //CANNOT LOOP THROUGH PriorityQueue
		 {
			 dangerLevel += titan.getDangerLevel();
		 }
	 }
	 
	 public int getDangerLevel() {
		return dangerLevel;
	}
	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}
	public Wall getLaneWall() {
		return laneWall;
	}
	public PriorityQueue<Titan> getTitans() {
		return titans;
	}
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	@Override
	public int compareTo(Lane L) {
	
		return this.dangerLevel - L.dangerLevel;
	}
}
