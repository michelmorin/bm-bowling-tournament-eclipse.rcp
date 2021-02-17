package com.morin.bowling.billmackenzie.model;

import com.morin.bowling.billmackenzie.model.Bowler;

public class YouthBowler extends Bowler {
	
	boolean scratchPot = false;
	boolean poaPot = false;
	int average;
	int[] games = new int[5]; 

	public YouthBowler(String name, int average, boolean scratchPot, boolean poaPot) {
		super(name, null, null);
		this.average = average;
		this.scratchPot = scratchPot;
		this.poaPot = poaPot;
		for (int i = 0; i < games.length; i++) {
			games[i] = -1;			
		}
	}

	public int getAverage() {
		return average;
	}

	public void setAverage(int average) {
		this.average = average;
	}

	public boolean isPoaPot() {
		return poaPot;
	}

	public void setPoaPot(boolean poaPot) {
		this.poaPot = poaPot;
	}

	public boolean isScratchPot() {
		return scratchPot;
	}

	public void setScratchPot(boolean scratchPot) {
		this.scratchPot = scratchPot;
	}
	
	public int[] getAllGames() {
		return games;
	}
	
	public int getGame(int index) {
		return games[index];
	}

	public void setAllGames(int[] games) {
		this.games = games;
	}
	
	public void setGame(int index, int score) {
		games[index]= score;
	}

}
