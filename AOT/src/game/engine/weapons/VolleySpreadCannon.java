package game.engine.weapons;

import game.engine.titans.AbnormalTitan;
import game.engine.titans.Titan;

import java.util.PriorityQueue;

public class VolleySpreadCannon extends Weapon {
	public final static int WEAPON_CODE = 3;
	private final int minRange;
	private final int maxRange;

	public static void main(String[] args)
	{
		AbnormalTitan t1 = new AbnormalTitan( 100, 0, 0, 100, 0,10,0); //d
		AbnormalTitan t2 = new AbnormalTitan( 200, 0, 0, 4000, 0,10,0);
		AbnormalTitan t3 = new AbnormalTitan( 200, 0, 0, 20, 0,10,0);//d
		AbnormalTitan t4 = new AbnormalTitan( 4, 0, 0, 30, 0,10,0);//dead
		AbnormalTitan t5 = new AbnormalTitan( 300, 0, 0, 455, 0,0,0);//d
		AbnormalTitan t6 = new AbnormalTitan( 10, 0, 0, 70, 0,100,0);//dead
		
		PriorityQueue<Titan> tit = new PriorityQueue<Titan>();
		tit.offer(t1);
		tit.offer(t2);
		tit.offer(t3);
		tit.offer(t4);
		tit.offer(t5);
		tit.offer(t6);
		
		VolleySpreadCannon p = new VolleySpreadCannon(30, 30, 500);
		
		System.out.println(p.turnAttack(tit));
		System.out.println();

		for(Titan t : tit)
		{
			System.out.println(t.getDistance());
			System.out.println(t.getCurrentHealth());
			System.out.println();
		}
	}
	
	public VolleySpreadCannon(int baseDamage, int minRange, int maxRange) {
		super(baseDamage);
		this.maxRange = maxRange;
		this.minRange = minRange;
	}

	public int turnAttack(PriorityQueue<Titan> laneTitans){
		
		int resourcesGained = 0;
		if(laneTitans.isEmpty()){
			return 0;
		}
		else 
		{
			PriorityQueue<Titan> duplicate = laneTitans;
			PriorityQueue<Titan> temp = new PriorityQueue<Titan>();

			int pqSize = duplicate.size();
			for (int i = 0; i < pqSize; i++)
			{
				if(duplicate.peek().getDistance() >= getMinRange() && duplicate.peek().getDistance() <= getMaxRange())
				{
					int resources = this.attack(duplicate.peek());
					if(duplicate.peek().isDefeated())
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
			return resourcesGained;
		}
	}

	public int getMinRange() {
		return minRange;
	}

	public int getMaxRange() {
		return maxRange;
	}	
}
