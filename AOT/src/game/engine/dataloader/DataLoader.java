package game.engine.dataloader;

import game.engine.titans.TitanRegistry;
import game.engine.weapons.WeaponRegistry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {

	private final static String TITANS_FILE_NAME = "titans.csv";
	private final static String WEAPONS_FILE_NAME = "weapons.csv";
	
	/*public static void main(String[] args) throws IOException
	{
		for (Integer name: readTitanRegistry().keySet()) {
		    String key = name.toString();
		    String value = readTitanRegistry().get(name).toString();
		    System.out.println(key + " " + value);
		}
		System.out.println();
		for (Integer name: readWeaponRegistry().keySet()) {
		    String key = name.toString();
		    String value = readWeaponRegistry().get(name).toString();
		    System.out.println(key + " " + value);
		}
	}*/
	
	public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException
	{
		HashMap<Integer, TitanRegistry> titanHashMap = new HashMap();
		try
		{
			FileReader fr = new FileReader(TITANS_FILE_NAME);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while ((line = br.readLine()) != null)
			{
				String[] temp = line.split(",");
				TitanRegistry titanRegistry = new TitanRegistry(Integer.parseInt(temp[0]),
						Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
						Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[5]), Integer.parseInt(temp[6]));
				titanHashMap.put(titanRegistry.getCode(), titanRegistry);
			}
		}
		catch (IOException e){ e.printStackTrace(); }
		
		return titanHashMap;
	}
	public static HashMap<Integer, WeaponRegistry> readWeaponRegistry() throws IOException
	{
		HashMap<Integer, WeaponRegistry> weaponHashMap = new HashMap();
		WeaponRegistry weaponRegistry;
		
		try
		{
			FileReader fr = new FileReader(WEAPONS_FILE_NAME);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while ((line = br.readLine()) != null)
			{
				String[] temp = line.split(",");
				if (temp.length == 4)
				{
					weaponRegistry = new WeaponRegistry(Integer.parseInt(temp[0]),
							Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3]);
				}
				else{
					weaponRegistry = new WeaponRegistry(Integer.parseInt(temp[0]),
							Integer.parseInt(temp[1]), Integer.parseInt(temp[2]), temp[3],
							Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
				}
					
				weaponHashMap.put(weaponRegistry.getCode(), weaponRegistry);
			}
		}
		catch (IOException e){ e.printStackTrace(); }
		
		return weaponHashMap;
	}
}
