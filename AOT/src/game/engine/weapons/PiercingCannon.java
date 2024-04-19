package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class PiercingCannon extends Weapon {
	public final static int WEAPON_CODE = 1;

	public PiercingCannon(int baseDamage) {
		super(baseDamage);
	}
	
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		int resourcesGained = 0;
		if (laneTitans.isEmpty())
			resourcesGained = 0;
		else{
			 Titan[] nazmy = laneTitans.toArray(new Titan[laneTitans.size()]);
			laneTitans.clear();;
			for(int i =0;i<5;i++ ){
			Titan attackedTitan = nazmy[i];
			resourcesGained += this.attack(attackedTitan);
			 if(attackedTitan.isDefeated()==false){
				 laneTitans.add(attackedTitan);
	             }
			 			 }
		 }
		return resourcesGained;
			 }
}
