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

	 public void addTitan(Titan titan){
		 titans.offer(titan);       // offer de bt3ml add gowa PriorityQueue
	 }
	 public void addWeapon(Weapon weapon){
		 weapons.add(weapon);       // offer de bt3ml add gowa arraylist 
	 }
	
	 public  int performLaneTitansAttacks(){
		 int number_of_resources_gathered = 0;
		 for (Titan titan : titans){
			 if (titan.hasReachedTarget()){
				  number_of_resources_gathered += titan.attack(laneWall);
			 }
		 }
		 	return number_of_resources_gathered;
	 }
	 public int performLaneWeaponsAttacks(){
		 int number_of_resources_gathered = 0;
		 for (int i = 0; i < weapons.size(); i++){
		 for (Titan titan : titans){
			  number_of_resources_gathered += weapons.get(i).attack(titan);  
		 }
	 }
		 return number_of_resources_gathered;
}
	 public boolean isLaneLost(){
		 if (this.laneWall.isDefeated()){
			 return true;
		 }
		 else 
			 return false;
	}
	 public void updateLaneDangerLevel(){
		 for (Titan titan : titans){
			 dangerLevel =+ titan.getDangerLevel();
		 }
	 }
	 public void moveLaneTitans(){
		 for (Titan titan : titans){
			 if (titan.getDistance()!=0){
				 titan.move();
			 }
		 }
	 }
}
