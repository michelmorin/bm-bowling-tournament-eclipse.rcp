package com.morin.bowling.billmackenzie.utilities;

import com.morin.bowling.billmackenzie.model.BMTeam;

public class WrappedTeam {
	
	private BMTeam team;
	private int value;
	private boolean visible;
	
	public WrappedTeam(BMTeam team, int value)
	{
		this.team = team;
		this.value = value;
		visible=true;
	}

	public BMTeam getTeam() {
		return team;
	}

	public void setTeam(BMTeam team) {
		this.team = team;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
