package game.engine.lanes;

import java.util.*;

import game.engine.base.Wall;
import game.engine.titans.Titan;
import game.engine.weapons.Weapon;

public class Lane implements Comparable<Lane> {

	private final PriorityQueue<Titan> titans;
	private final ArrayList<Weapon> weapons;
	private final Wall laneWall;
	private int dangerLevel;

	public Lane(Wall laneWall) {
		this.laneWall = laneWall;
		titans = new PriorityQueue<Titan>();
		weapons = new ArrayList<Weapon>();
		dangerLevel = 0;
	}

	public int getDangerLevel() {
		return dangerLevel;
	}

	public void setDangerLevel(int dangerLevel) {
		this.dangerLevel = dangerLevel;
	}

	public Wall getLaneWall() {
		return laneWall;
	}

	public PriorityQueue<Titan> getTitans() {
		return titans;
	}

	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}

	@Override
	public int compareTo(Lane L) {

		return this.dangerLevel - L.dangerLevel;
	}

}
