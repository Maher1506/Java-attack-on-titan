package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class SniperCannon extends Weapon {
	public final static int WEAPON_CODE = 2;

	public SniperCannon(int baseDamage) {
		super(baseDamage);
	}
	
	@Override
	public int turnAttack(PriorityQueue<Titan> laneTitans){

		int resourcesGained = 0;
		if(laneTitans.isEmpty()){
			resourcesGained = 0;
		}
		else {
		int resources = this.attack(laneTitans.peek());
		if(resources != 0)
		{
			resourcesGained += resources;
			laneTitans.remove();
		}
		}
		return resourcesGained;
	}
}
