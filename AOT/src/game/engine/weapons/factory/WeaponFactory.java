package game.engine.weapons.factory;

import java.io.IOException;
import java.util.HashMap;

import game.engine.dataloader.DataLoader;
import game.engine.exceptions.InsufficientResourcesException;
import game.engine.weapons.WeaponRegistry;

public class WeaponFactory {

	private final HashMap<Integer, WeaponRegistry> weaponShop;

	public WeaponFactory() throws IOException {
		weaponShop = DataLoader.readWeaponRegistry();
	}

	public FactoryResponse buyWeapon(int resources, int weaponCode) throws InsufficientResourcesException
	{
		WeaponRegistry intendedWeaponRegistry = weaponShop.get(weaponCode);
		
		if (resources >= intendedWeaponRegistry.getPrice())
		{
			return new FactoryResponse(intendedWeaponRegistry.buildWeapon(), 
									   resources - intendedWeaponRegistry.getPrice());
		}
		else
		{
			throw new InsufficientResourcesException(resources);
		}
	}
	
	public void addWeaponToShop(int code, int price)
	{
		weaponShop.put(code, new WeaponRegistry(code, price));
	}
	public void addWeaponToShop(int code, int price, int damage, String name)
	{
		weaponShop.put(code, new WeaponRegistry(code, price, damage, name));
	}
	public void addWeaponToShop(int code, int price, int damage, String name, 
			int minRange, int maxRange)
	{
		weaponShop.put(code, new WeaponRegistry(code, price, damage, name, minRange, maxRange));
	}
	
	public HashMap<Integer, WeaponRegistry> getWeaponShop() {
		return weaponShop;
	}
}
