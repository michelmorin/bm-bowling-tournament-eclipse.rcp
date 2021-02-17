package com.morin.bowling.platform;

public class Tournament {
	
	private String perspectiveID;
	private String name;
	private String Description;
	
	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPerspectiveID() {
		return perspectiveID;
	}

	public void setPerspectiveID(String perspectiveID) {
		this.perspectiveID = perspectiveID;
	}

	public Tournament(String perspectiveID, String name, String description) {
		super();
		this.perspectiveID = perspectiveID;
		this.name = name;
		Description = description;
	}
	
	public String toString() {
		return name;
	}

}
