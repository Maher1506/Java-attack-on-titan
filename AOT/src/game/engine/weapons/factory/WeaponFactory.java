package game.engine.weapons.factory;

import game.engine.weapons.WeaponRegistry;

import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {
	
	private final HashMap<Integer , WeaponRegistry> weaponShop;
	
	public WeaponFactory() throws IOException
	{
		weaponShop = new HashMap();
	}

	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
	
}
