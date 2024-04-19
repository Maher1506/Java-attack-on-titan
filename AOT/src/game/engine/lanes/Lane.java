package game.engine.lanes;

import java.util.*;

import game.engine.base.Wall;
import game.engine.titans.AbnormalTitan;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane> {

	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;
	private final Wall laneWall;
	private int dangerLevel;

	public static void main(String[] args)
	{
		AbnormalTitan t1 = new AbnormalTitan( 0, 0, 0, 100, 0,0,0);
		AbnormalTitan t2 = new AbnormalTitan( 0, 0, 0, 4000, 0,0,0);
		AbnormalTitan t3 = new AbnormalTitan( 0, 0, 0, 20, 0,0,0);
		AbnormalTitan t4 = new AbnormalTitan( 0, 0, 0, 30, 0,0,0);
		PriorityQueue<Titan> tit = new PriorityQueue<Titan>();
		tit.offer(t1);
		tit.offer(t2);
		tit.offer(t3);
		tit.offer(t4);
		
		PriorityQueue<Titan> tit2 = new PriorityQueue<Titan>();
		ArrayList<Titan> nazmy2 = new ArrayList<>(tit2);
		System.out.println(nazmy2.get(0));
		System.out.println(tit2.peek());
		System.out.println();
		
		ArrayList<Titan> nazmy = new ArrayList<>(tit);
		for (Titan t: tit)
		{
			System.out.println(t.getDistance());
		}
		
		System.out.println();
		for (Titan t : tit)
		{
			tit2.offer(t);
		}
		for(Titan t : tit2)
		{
			System.out.println(t.getDistance());
		}
		
		System.out.println();
		System.out.println(tit.peek().getDistance());
		System.out.println();
		
		for(Titan t : tit)
		{
			System.out.println(t.getDistance());
		}
	}
	
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
		for (Titan titan : titans) 
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
			 weapon.turnAttack(titans);
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
		 for (Titan titan : titans)  
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
