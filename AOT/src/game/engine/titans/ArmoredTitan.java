package game.engine.titans;

public class ArmoredTitan extends Titan {
	public final static int TITAN_CODE = 3;

	public ArmoredTitan(int baseHealth, int baseDamage, int heightInMeters, int distanceFromBase, int speed,
			int resourcesValue, int dangerLevel) {
		super(baseHealth, baseDamage, heightInMeters, distanceFromBase, speed, resourcesValue, dangerLevel);
	}
	
	@Override
	public int takeDamage(int damage)
	{
		int takenDamage = damage / 4;
		setCurrentHealth(getCurrentHealth() - takenDamage);
		
		if (isDefeated())
		{
			return getResourcesValue();
		}
		else
		{
			return 0;
		}
	}
}
