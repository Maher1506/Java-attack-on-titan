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
			return 0;
		}
		else 
		{
			int resources = laneTitans.peek().takeDamage(this.getDamage()); 
			if(laneTitans.peek().isDefeated())
			{
				resourcesGained += resources;
				laneTitans.remove();
			}
			return resourcesGained;
		}
	}
}
