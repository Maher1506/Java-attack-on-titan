package game.engine.titans;

public class ColossalTitan extends Titan {
	public final static int TITAN_CODE = 4;
	
	/*public static void main(String[] args)
	{
		ColossalTitan c = new ColossalTitan(0,0,0,100,5,0,0);
		c.move();
		System.out.println(c.getDistance());
		System.out.println(c.getSpeed());
		System.out.println();
		
		c.move();
		System.out.println(c.getDistance());
		System.out.println(c.getSpeed());
		System.out.println();
		
		c.move();
		System.out.println(c.getDistance());
		System.out.println(c.getSpeed());
		System.out.println();
	}*/
	
	public ColossalTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}
	
	@Override
	public boolean move()
	{
		setDistance(getDistance() - getSpeed());
		if (hasReachedTarget())
		{
			return true;
		}
		else
		{
			setSpeed(getSpeed() + 1);
			return false;
		}
	}
}
