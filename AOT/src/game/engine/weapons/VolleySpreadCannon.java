package game.engine.weapons;

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

}
