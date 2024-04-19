package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class WallTrap extends Weapon {
	public final static int WEAPON_CODE = 4;

	public WallTrap(int baseDamage) {
		super(baseDamage);
	}
	
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		int resourcesGained = 0;
		if (laneTitans.isEmpty()){
			return resourcesGained = 0;}
		Titan attackedTitan = laneTitans.peek();
		 if(attackedTitan.getDistance()==0) {
				 resourcesGained += this.attack(attackedTitan);
				 if(attackedTitan.isDefeated()==false){
					 laneTitans.add(attackedTitan);
	             }    	
		}
		return resourcesGained ;
	}
}
