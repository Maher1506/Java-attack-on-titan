package game.engine.dataloader;

import game.engine.titans.TitanRegistry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataLoader {

	private final String TITANS_FILE_NAME = "titans.csv";
	private final String WEAPONS_FILE_NAME = "weapons.csv";
	
	public static void main(String[] args) throws IOException
	{
		System.out.println(readTitanRegistry());
	}
	
	public static HashMap<Integer, TitanRegistry> readTitanRegistry() throws IOException
	{
		HashMap<Integer, TitanRegistry> registry = new HashMap();
		try
		{
			FileReader fr = new FileReader("TITANS_FILE_NAME");
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while ((line = br.readLine()) != null)
			{
				String[] temp = line.split(",");
				TitanRegistry titanRegistry = new TitanRegistry(Integer.parseInt(temp[0]),
						Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
						Integer.parseInt(temp[3]), Integer.parseInt(temp[4]),
						Integer.parseInt(temp[5]), Integer.parseInt(temp[6]));
				registry.put(titanRegistry.getCode(), titanRegistry);
			}
		}
		catch (IOException e){ e.printStackTrace(); }
		
		return registry;
	}
}
