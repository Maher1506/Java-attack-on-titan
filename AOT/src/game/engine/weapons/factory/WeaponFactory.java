package game.engine.weapons.factory;

import game.engine.dataloader.DataLoader;
import game.engine.weapons.WeaponRegistry;

import java.io.IOException;
import java.util.HashMap;

public class WeaponFactory {
	
	private final HashMap<Integer , WeaponRegistry> weaponShop;
	
	public WeaponFactory() throws IOException
	{
		weaponShop = DataLoader.readWeaponRegistry();
	}

	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
	
}
