package com.morin.bowling.billmackenzie.test;

import com.morin.bowling.billmackenzie.model.AdultBowler;
import com.morin.bowling.billmackenzie.model.BMTeam;
import com.morin.bowling.billmackenzie.model.YouthBowler;
import com.morin.bowling.billmackenzie.utilities.WrappedTeam;

public class TeamTestReport {
	
	public static final BMTeam TEAM1 = new BMTeam(1, "Team1", new AdultBowler("Adult1", 194, false, true), new YouthBowler("Youth1", 242, true, true));
	public static final BMTeam TEAM2 = new BMTeam(2, "Team2", new AdultBowler("Adult2", 204, true, false), new YouthBowler("Youth2", 187, false, true));
	public static final BMTeam TEAM3 = new BMTeam(3, "Team3", new AdultBowler("Adult3", 146, false, false), new YouthBowler("Youth3", 215, true, true));
	public static final BMTeam TEAM4 = new BMTeam(4, "Team4", new AdultBowler("Adult4", 231, true, true), new YouthBowler("Youth4", 201, false, false));
	public static final BMTeam TEAM5 = new BMTeam(5, "Team5", new AdultBowler("Adult5", 192, true, true), new YouthBowler("Youth5", 215, true, true));
	
	public static final int CHECK_TEAM = 0;
	public static final int CHECK_YOUTH = 1;
	public static final int CHECK_ADULT = 2;
	
	private String errorMessage;
		
	public TeamTestReport() {
		super();
		this.errorMessage = "";
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean compareReport(WrappedTeam[] returnedList, WrappedTeam[] expectedList) {
		
		if ( returnedList.length != expectedList.length ) {
			errorMessage = "The number of teams don't match, expected number of teams: " + String.valueOf(expectedList.length) + " returned number of teams: " + String.valueOf(returnedList.length);
			return false;
		}
						
		for (int i = 0; i < returnedList.length; i++) {
			if ( expectedList[i].getValue() != returnedList[i].getValue() ) {
				errorMessage = "The value of " + String.valueOf(returnedList[i].getValue()) + " in team ID: " + String.valueOf(returnedList[i].getTeam().getId()) +  " don't match, expected value " + String.valueOf(expectedList[i].getValue()) + " in team ID: " + String.valueOf(expectedList[i].getTeam().getId());
				return false;
			}
			
		}
		
		for (int i = 0; i < returnedList.length; i++) {
			if ( !expectedList[i].getTeam().getBowlingCentre().equals(returnedList[i].getTeam().getBowlingCentre()) ) {
				errorMessage = "The returned team " + String.valueOf(returnedList[i].getTeam().getId()) + " with value of " + returnedList[i].getTeam().getBowlingCentre() + " doesn't match the expected team " + String.valueOf(expectedList[i].getTeam().getId() + " with value of " + expectedList[i].getTeam().getBowlingCentre() );
				return false;
			}
			if ( !expectedList[i].getTeam().getAdult().getName().equals(returnedList[i].getTeam().getAdult().getName()) ) {
				errorMessage = "The returned team " + String.valueOf(returnedList[i].getTeam().getId()) + " with value of " + returnedList[i].getTeam().getAdult().getName() + " doesn't match the expected team " + String.valueOf(expectedList[i].getTeam().getId() + " with value of " + expectedList[i].getTeam().getAdult().getName() );
				return false;
			}
			if ( !expectedList[i].getTeam().getYouth().getName().equals(returnedList[i].getTeam().getYouth().getName()) ) {
				errorMessage = "The returned team " + String.valueOf(returnedList[i].getTeam().getId()) + " with value of " + returnedList[i].getTeam().getYouth().getName() + " doesn't match the expected team " + String.valueOf(expectedList[i].getTeam().getId() + " with value of " + expectedList[i].getTeam().getYouth().getName() );
				return false;
			}		
			
		}
		
		return true;
	}
}
