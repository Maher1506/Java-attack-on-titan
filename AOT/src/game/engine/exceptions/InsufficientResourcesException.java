package game.engine.exceptions;

public class InsufficientResourcesException extends GameActionException{
	
	private static final String MSG = "Not enough resources, resources provided = ";
	private int resourcesProvided;
	
	public InsufficientResourcesException(int resourcesProvided) {
		super(MSG + resourcesProvided);
		this.resourcesProvided = resourcesProvided;
	}
	public InsufficientResourcesException(String message, int resourcesProvided) {
		super(message);
		this.resourcesProvided = resourcesProvided;
	}
	
	
	
	public static String getMsg() {
		return MSG;
	}
	public int getResourcesProvided() {
		return resourcesProvided;
	}
	public void setResourcesProvided(int resourcesProvided) {
		if (resourcesProvided >= 0)
		{
			this.resourcesProvided = resourcesProvided;	
		}
		else
		{
			this.resourcesProvided = 0;
		}
	}
}
