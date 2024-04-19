package game.engine.weapons;

import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon {
	public final static int WEAPON_CODE = 3;
	private final int minRange;
	private final int maxRange;

	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.maxRange = maxRange;
		this.minRange = minRange;
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}
	public int turnAttack(PriorityQueue<Titan> laneTitans){
		
		int resourcesGained = 0;
		if(laneTitans.isEmpty()){
			resourcesGained = 0;
		}
		else {
		PriorityQueue<Titan> duplicate = laneTitans;
		PriorityQueue<Titan> temp = new PriorityQueue<Titan>();
		int pqSize = duplicate.size();
		for (int i = 0; i < pqSize; i++){
		if (duplicate.peek().getDistance() > 19 && duplicate.peek().getDistance() > 51 ){
			
			int resources = this.attack(duplicate.peek());
			if(resources != 0)
			{
				resourcesGained += resources;
				duplicate.remove();
			}
			else
			{
				temp.offer(duplicate.poll());
			}
		}
		else
		{
			temp.offer(duplicate.poll());
		}
	}
		laneTitans.clear();
		for(Titan t : temp)
		{
			laneTitans.offer(t);
		}
		}
		return resourcesGained;
	}	
}
