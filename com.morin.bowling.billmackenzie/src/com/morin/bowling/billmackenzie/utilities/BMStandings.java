package com.morin.bowling.billmackenzie.utilities;

import java.util.ArrayList;

import com.morin.bowling.billmackenzie.Activator;
import com.morin.bowling.billmackenzie.model.BMTeam;

public class BMStandings {
	
	public static WrappedTeam[] handleAdultPoolPOA(int gameIndex)
	{
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		ArrayList<BMTeam> filteredTeams = new ArrayList<BMTeam>();
		for (int i = 0; i < allTeams.length; i++) 
		{
			if ( allTeams[i].getAdult().isPoaPot() )
			{
				filteredTeams.add(allTeams[i]);
			}
		}
		
		WrappedTeam[] teams = new WrappedTeam[filteredTeams.size()];
		allTeams = (BMTeam[]) filteredTeams.toArray(new BMTeam[filteredTeams.size()]);
		
		for (int i = 0; i < allTeams.length; i++) 
		{
			int value = allTeams[i].getAdult().getGame(gameIndex) != -1 ? allTeams[i].getAdult().getGame(gameIndex) - allTeams[i].getAdult().getAverage() : 0;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
			
		return sortTeams(teams, 0);
	}
	
	public static WrappedTeam[] handleAdultPoolScratch(int gameIndex)
	{
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		ArrayList<BMTeam> filteredTeams = new ArrayList<BMTeam>();
		for (int i = 0; i < allTeams.length; i++) 
		{
			if ( allTeams[i].getAdult().isScratchPot() )
			{
				filteredTeams.add(allTeams[i]);
			}
		}
		
		WrappedTeam[] teams = new WrappedTeam[filteredTeams.size()];
		allTeams = (BMTeam[]) filteredTeams.toArray(new BMTeam[filteredTeams.size()]);
		
		for (int i = 0; i < allTeams.length; i++) 
		{
			int value = allTeams[i].getAdult().getGame(gameIndex) != -1 ? allTeams[i].getAdult().getGame(gameIndex) : 0;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
			
		return sortTeams(teams, 0);
	}
	
	public static WrappedTeam[] handleYouthPoolPOA(int gameIndex)
	{
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		ArrayList<BMTeam> filteredTeams = new ArrayList<BMTeam>();
		for (int i = 0; i < allTeams.length; i++) 
		{
			if ( allTeams[i].getYouth().isPoaPot() )
			{
				filteredTeams.add(allTeams[i]);
			}
		}
		
		WrappedTeam[] teams = new WrappedTeam[filteredTeams.size()];
		allTeams = (BMTeam[]) filteredTeams.toArray(new BMTeam[filteredTeams.size()]);
		
		for (int i = 0; i < allTeams.length; i++) 
		{
			int value = allTeams[i].getYouth().getGame(gameIndex) != -1 ? allTeams[i].getYouth().getGame(gameIndex) - allTeams[i].getYouth().getAverage() : 0;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
			
		return sortTeams(teams, 0);
	}
	
	public static WrappedTeam[] handleYouthPoolScratch(int gameIndex)
	{
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		ArrayList<BMTeam> filteredTeams = new ArrayList<BMTeam>();
		for (int i = 0; i < allTeams.length; i++) 
		{
			if ( allTeams[i].getYouth().isScratchPot() )
			{
				filteredTeams.add(allTeams[i]);
			}
		}
		
		WrappedTeam[] teams = new WrappedTeam[filteredTeams.size()];
		allTeams = (BMTeam[]) filteredTeams.toArray(new BMTeam[filteredTeams.size()]);
		
		for (int i = 0; i < allTeams.length; i++) 
		{
			int value = allTeams[i].getYouth().getGame(gameIndex) != -1 ? allTeams[i].getYouth().getGame(gameIndex) : 0;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
			
		return sortTeams(teams, 0);
	}
	
	public static WrappedTeam[] handleTeamScratch(int gameIndex, boolean bestEventFilter)
	{	
		if ( bestEventFilter )
		{
			WrappedTeam[] teamScratchList = getTeamScratchList(gameIndex);
			WrappedTeam[] teamPOAList = getTeamPOAList(gameIndex);
			
			ArrayList<WrappedTeam> teamScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> teamPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < teamScratchList.length; i++) 
			{
				for (int j = i; j < teamScratchList.length; j++) 
				{
					if ( teamScratchList[j].isVisible())
					{
						teamScratchArrayList.add(teamScratchList[j]);
						teamScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamPOAList.length; k++) 
						{
							if ( teamScratchList[j].getTeam().getId() == teamPOAList[k].getTeam().getId())
							{
								teamPOAList[k].setVisible(false);
							}
						}
						
						j = teamScratchList.length;
					}
				}
				for (int j = i; j < teamPOAList.length; j++) 
				{
					if ( teamPOAList[j].isVisible())
					{
						teamPOAArrayList.add(teamPOAList[j]);
						teamPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamScratchList.length; k++) 
						{
							if ( teamPOAList[j].getTeam().getId() == teamScratchList[k].getTeam().getId())
							{
								teamScratchList[k].setVisible(false);
							}
						}
						
						j = teamPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) teamScratchArrayList.toArray(new WrappedTeam[teamScratchArrayList.size()]);
			
		}
		else
		{
			return getTeamScratchList(gameIndex);
		}
	}
	
	public static int getTeamScratchPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
		
		if ( bestEventFilter )
		{
			WrappedTeam[] teamScratchList = getTeamScratchList(gameIndex);
			WrappedTeam[] teamPOAList = getTeamPOAList(gameIndex);
			
			ArrayList<WrappedTeam> teamScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> teamPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < teamScratchList.length; i++) 
			{
				for (int j = i; j < teamScratchList.length; j++) 
				{
					if ( teamScratchList[j].isVisible())
					{
						teamScratchArrayList.add(teamScratchList[j]);
						teamScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamPOAList.length; k++) 
						{
							if ( teamScratchList[j].getTeam().getId() == teamPOAList[k].getTeam().getId())
							{
								teamPOAList[k].setVisible(false);
							}
						}
						
						j = teamScratchList.length;
					}
				}
				for (int j = i; j < teamPOAList.length; j++) 
				{
					if ( teamPOAList[j].isVisible())
					{
						teamPOAArrayList.add(teamPOAList[j]);
						teamPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamScratchList.length; k++) 
						{
							if ( teamPOAList[j].getTeam().getId() == teamScratchList[k].getTeam().getId())
							{
								teamScratchList[k].setVisible(false);
							}
						}
						
						j = teamPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) teamScratchArrayList.toArray(new WrappedTeam[teamScratchArrayList.size()]);
		}
		else
		{
			teams = getTeamScratchList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	private static WrappedTeam[] getTeamScratchList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			int adultValue=0;
			int youthValue=0;
			for (int j = 0; j <= gameIndex; j++) {
				int tempAdultValue = allTeams[i].getAdult().getGame(j) != -1 ? allTeams[i].getAdult().getGame(j) : 0;
				int tempYouthValue = allTeams[i].getYouth().getGame(j) != -1 ? allTeams[i].getYouth().getGame(j) : 0;
				adultValue+=tempAdultValue;
				youthValue+=tempYouthValue;
			}
			int value = adultValue + youthValue;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}

	public static WrappedTeam[] handleTeamPOA(int gameIndex, boolean bestEventFilter)
	{	
		if ( bestEventFilter )
		{
			WrappedTeam[] teamScratchList = getTeamScratchList(gameIndex);
			WrappedTeam[] teamPOAList = getTeamPOAList(gameIndex);
			
			ArrayList<WrappedTeam> teamScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> teamPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < teamScratchList.length; i++) 
			{
				for (int j = i; j < teamScratchList.length; j++) 
				{
					if ( teamScratchList[j].isVisible())
					{
						teamScratchArrayList.add(teamScratchList[j]);
						teamScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamPOAList.length; k++) 
						{
							if ( teamScratchList[j].getTeam().getId() == teamPOAList[k].getTeam().getId())
							{
								teamPOAList[k].setVisible(false);
							}
						}
						
						j = teamScratchList.length;
					}
				}
				for (int j = i; j < teamPOAList.length; j++) 
				{
					if ( teamPOAList[j].isVisible())
					{
						teamPOAArrayList.add(teamPOAList[j]);
						teamPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamScratchList.length; k++) 
						{
							if ( teamPOAList[j].getTeam().getId() == teamScratchList[k].getTeam().getId())
							{
								teamScratchList[k].setVisible(false);
							}
						}
						
						j = teamPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) teamPOAArrayList.toArray(new WrappedTeam[teamPOAArrayList.size()]);
		}
		else
		{
			return getTeamPOAList(gameIndex);
		}
	}
	
	public static int getTeamPOAPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
	
		if ( bestEventFilter )
		{
			WrappedTeam[] teamScratchList = getTeamScratchList(gameIndex);
			WrappedTeam[] teamPOAList = getTeamPOAList(gameIndex);
			
			ArrayList<WrappedTeam> teamScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> teamPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < teamScratchList.length; i++) 
			{
				for (int j = i; j < teamScratchList.length; j++) 
				{
					if ( teamScratchList[j].isVisible())
					{
						teamScratchArrayList.add(teamScratchList[j]);
						teamScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamPOAList.length; k++) 
						{
							if ( teamScratchList[j].getTeam().getId() == teamPOAList[k].getTeam().getId())
							{
								teamPOAList[k].setVisible(false);
							}
						}
						
						j = teamScratchList.length;
					}
				}
				for (int j = i; j < teamPOAList.length; j++) 
				{
					if ( teamPOAList[j].isVisible())
					{
						teamPOAArrayList.add(teamPOAList[j]);
						teamPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < teamScratchList.length; k++) 
						{
							if ( teamPOAList[j].getTeam().getId() == teamScratchList[k].getTeam().getId())
							{
								teamScratchList[k].setVisible(false);
							}
						}
						
						j = teamPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) teamPOAArrayList.toArray(new WrappedTeam[teamPOAArrayList.size()]);
		}
		else
		{		
			teams = getTeamPOAList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	public static WrappedTeam[] getTeamPOAList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			int adultValue=0;
			int youthValue=0;
			for (int j = 0; j <= gameIndex; j++) {
				int tempAdultValue = allTeams[i].getAdult().getGame(j) != -1 ? allTeams[i].getAdult().getGame(j) - allTeams[i].getAdult().getAverage() : 0;
				int tempYouthValue = allTeams[i].getYouth().getGame(j) != -1 ? allTeams[i].getYouth().getGame(j) - allTeams[i].getYouth().getAverage() : 0;
				adultValue+=tempAdultValue;
				youthValue+=tempYouthValue;
				
			}
			int value = adultValue + youthValue;
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}
	
	public static WrappedTeam[]handleAdultPOA(int gameIndex, boolean bestEventFilter)
	{
		if ( bestEventFilter )
		{
			WrappedTeam[] adultScratchList = getAdultScratchList(gameIndex);
			WrappedTeam[] adultPOAList = getAdultPOAList(gameIndex);
			
			ArrayList<WrappedTeam> adultScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> adultPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < adultScratchList.length; i++) 
			{
				for (int j = i; j < adultScratchList.length; j++) 
				{
					if ( adultScratchList[j].isVisible())
					{
						adultScratchArrayList.add(adultScratchList[j]);
						adultScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultPOAList.length; k++) 
						{
							if ( adultScratchList[j].getTeam().getId() == adultPOAList[k].getTeam().getId())
							{
								adultPOAList[k].setVisible(false);
							}
						}
						
						j = adultScratchList.length;
					}
				}
				for (int j = i; j < adultPOAList.length; j++) 
				{
					if ( adultPOAList[j].isVisible())
					{
						adultPOAArrayList.add(adultPOAList[j]);
						adultPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultScratchList.length; k++) 
						{
							if ( adultPOAList[j].getTeam().getId() == adultScratchList[k].getTeam().getId())
							{
								adultScratchList[k].setVisible(false);
							}
						}
						
						j = adultPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) adultPOAArrayList.toArray(new WrappedTeam[adultPOAArrayList.size()]);
			
		}
		else
		{
			return getAdultPOAList(gameIndex);
		}	
	}
	
	public static int getAdultPOAPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
		
		if ( bestEventFilter )
		{
			WrappedTeam[] adultScratchList = getAdultScratchList(gameIndex);
			WrappedTeam[] adultPOAList = getAdultPOAList(gameIndex);
			
			ArrayList<WrappedTeam> adultScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> adultPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < adultScratchList.length; i++) 
			{
				for (int j = i; j < adultScratchList.length; j++) 
				{
					if ( adultScratchList[j].isVisible())
					{
						adultScratchArrayList.add(adultScratchList[j]);
						adultScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultPOAList.length; k++) 
						{
							if ( adultScratchList[j].getTeam().getId() == adultPOAList[k].getTeam().getId())
							{
								adultPOAList[k].setVisible(false);
							}
						}
						
						j = adultScratchList.length;
					}
				}
				for (int j = i; j < adultPOAList.length; j++) 
				{
					if ( adultPOAList[j].isVisible())
					{
						adultPOAArrayList.add(adultPOAList[j]);
						adultPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultScratchList.length; k++) 
						{
							if ( adultPOAList[j].getTeam().getId() == adultScratchList[k].getTeam().getId())
							{
								adultScratchList[k].setVisible(false);
							}
						}
						
						j = adultPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) adultPOAArrayList.toArray(new WrappedTeam[adultPOAArrayList.size()]);
			
		}
		else
		{
			teams = getAdultPOAList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	private static WrappedTeam[] getAdultPOAList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) 
		{
			int value=0;
			for (int j = 0; j <= gameIndex; j++) 
			{
				int tempAdultValue = allTeams[i].getAdult().getGame(j) != -1 ? allTeams[i].getAdult().getGame(j) - allTeams[i].getAdult().getAverage() : 0;
				value+=tempAdultValue;
			}
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}


	public static WrappedTeam[] handleAdultScratch(int gameIndex, boolean bestEventFilter)
	{
		if ( bestEventFilter )
		{
			WrappedTeam[] adultScratchList = getAdultScratchList(gameIndex);
			WrappedTeam[] adultPOAList = getAdultPOAList(gameIndex);
			
			ArrayList<WrappedTeam> adultScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> adultPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < adultScratchList.length; i++) 
			{
				for (int j = i; j < adultScratchList.length; j++) 
				{
					if ( adultScratchList[j].isVisible())
					{
						adultScratchArrayList.add(adultScratchList[j]);
						adultScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultPOAList.length; k++) 
						{
							if ( adultScratchList[j].getTeam().getId() == adultPOAList[k].getTeam().getId())
							{
								adultPOAList[k].setVisible(false);
							}
						}
						
						j = adultScratchList.length;
					}
				}
				for (int j = i; j < adultPOAList.length; j++) 
				{
					if ( adultPOAList[j].isVisible())
					{
						adultPOAArrayList.add(adultPOAList[j]);
						adultPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultScratchList.length; k++) 
						{
							if ( adultPOAList[j].getTeam().getId() == adultScratchList[k].getTeam().getId())
							{
								adultScratchList[k].setVisible(false);
							}
						}
						
						j = adultPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) adultScratchArrayList.toArray(new WrappedTeam[adultScratchArrayList.size()]);
			
		}
		else
		{
			return getAdultScratchList(gameIndex);
		}	
	}
	
	public static int getAdultScratchPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
		
		if ( bestEventFilter )
		{
			WrappedTeam[] adultScratchList = getAdultScratchList(gameIndex);
			WrappedTeam[] adultPOAList = getAdultPOAList(gameIndex);
			
			ArrayList<WrappedTeam> adultScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> adultPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < adultScratchList.length; i++) 
			{
				for (int j = i; j < adultScratchList.length; j++) 
				{
					if ( adultScratchList[j].isVisible())
					{
						adultScratchArrayList.add(adultScratchList[j]);
						adultScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultPOAList.length; k++) 
						{
							if ( adultScratchList[j].getTeam().getId() == adultPOAList[k].getTeam().getId())
							{
								adultPOAList[k].setVisible(false);
							}
						}
						
						j = adultScratchList.length;
					}
				}
				for (int j = i; j < adultPOAList.length; j++) 
				{
					if ( adultPOAList[j].isVisible())
					{
						adultPOAArrayList.add(adultPOAList[j]);
						adultPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < adultScratchList.length; k++) 
						{
							if ( adultPOAList[j].getTeam().getId() == adultScratchList[k].getTeam().getId())
							{
								adultScratchList[k].setVisible(false);
							}
						}
						
						j = adultPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) adultScratchArrayList.toArray(new WrappedTeam[adultScratchArrayList.size()]);
			
		}
		else
		{
			teams = getAdultScratchList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	private static WrappedTeam[] getAdultScratchList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			int value=0;
			for (int j = 0; j <= gameIndex; j++) {
				int tempAdultValue = allTeams[i].getAdult().getGame(j) != -1 ? allTeams[i].getAdult().getGame(j) : 0;
				value+=tempAdultValue;
			}
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}
	
	public static WrappedTeam[] handleYouthPOA(int gameIndex, boolean bestEventFilter)
	{	
		if ( bestEventFilter )
		{
			WrappedTeam[] youthScratchList = getYouthScratchList(gameIndex);
			WrappedTeam[] youthPOAList = getYouthPOAList(gameIndex);
			
			ArrayList<WrappedTeam> youthScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> youthPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < youthScratchList.length; i++) 
			{
				for (int j = i; j < youthScratchList.length; j++) 
				{
					if ( youthScratchList[j].isVisible())
					{
						youthScratchArrayList.add(youthScratchList[j]);
						youthScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthPOAList.length; k++) 
						{
							if ( youthScratchList[j].getTeam().getId() == youthPOAList[k].getTeam().getId())
							{
								youthPOAList[k].setVisible(false);
							}
						}
						
						j = youthScratchList.length;
					}
				}
				for (int j = i; j < youthPOAList.length; j++) 
				{
					if ( youthPOAList[j].isVisible())
					{
						youthPOAArrayList.add(youthPOAList[j]);
						youthPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthScratchList.length; k++) 
						{
							if ( youthPOAList[j].getTeam().getId() == youthScratchList[k].getTeam().getId())
							{
								youthScratchList[k].setVisible(false);
							}
						}
						
						j = youthPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) youthPOAArrayList.toArray(new WrappedTeam[youthPOAArrayList.size()]);
			
		}
		else
		{
			return getYouthPOAList(gameIndex);
		}	
	}
	
	public static int getYouthPOAPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
		
		if ( bestEventFilter )
		{
			WrappedTeam[] youthScratchList = getYouthScratchList(gameIndex);
			WrappedTeam[] youthPOAList = getYouthPOAList(gameIndex);
			
			ArrayList<WrappedTeam> youthScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> youthPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < youthScratchList.length; i++) 
			{
				for (int j = i; j < youthScratchList.length; j++) 
				{
					if ( youthScratchList[j].isVisible())
					{
						youthScratchArrayList.add(youthScratchList[j]);
						youthScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthPOAList.length; k++) 
						{
							if ( youthScratchList[j].getTeam().getId() == youthPOAList[k].getTeam().getId())
							{
								youthPOAList[k].setVisible(false);
							}
						}
						
						j = youthScratchList.length;
					}
				}
				for (int j = i; j < youthPOAList.length; j++) 
				{
					if ( youthPOAList[j].isVisible())
					{
						youthPOAArrayList.add(youthPOAList[j]);
						youthPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthScratchList.length; k++) 
						{
							if ( youthPOAList[j].getTeam().getId() == youthScratchList[k].getTeam().getId())
							{
								youthScratchList[k].setVisible(false);
							}
						}
						
						j = youthPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) youthPOAArrayList.toArray(new WrappedTeam[youthPOAArrayList.size()]);
			
		}
		else
		{
			teams = getYouthPOAList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	private static WrappedTeam[] getYouthPOAList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			int value=0;
			for (int j = 0; j <= gameIndex; j++) {
				int tempYouthValue = allTeams[i].getYouth().getGame(j) != -1 ? allTeams[i].getYouth().getGame(j) - allTeams[i].getYouth().getAverage() : 0;
				value+=tempYouthValue;
			}
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}
	
	public static WrappedTeam[]handleYouthScratch(int gameIndex, boolean bestEventFilter)
	{
		if ( bestEventFilter )
		{
			WrappedTeam[] youthScratchList = getYouthScratchList(gameIndex);
			WrappedTeam[] youthPOAList = getYouthPOAList(gameIndex);
			
			ArrayList<WrappedTeam> youthScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> youthPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < youthScratchList.length; i++) 
			{
				for (int j = i; j < youthScratchList.length; j++) 
				{
					if ( youthScratchList[j].isVisible())
					{
						youthScratchArrayList.add(youthScratchList[j]);
						youthScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthPOAList.length; k++) 
						{
							if ( youthScratchList[j].getTeam().getId() == youthPOAList[k].getTeam().getId())
							{
								youthPOAList[k].setVisible(false);
							}
						}
						
						j = youthScratchList.length;
					}
				}
				for (int j = i; j < youthPOAList.length; j++) 
				{
					if ( youthPOAList[j].isVisible())
					{
						youthPOAArrayList.add(youthPOAList[j]);
						youthPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthScratchList.length; k++) 
						{
							if ( youthPOAList[j].getTeam().getId() == youthScratchList[k].getTeam().getId())
							{
								youthScratchList[k].setVisible(false);
							}
						}
						
						j = youthPOAList.length;
					}
				}
			}
			
			return (WrappedTeam[]) youthScratchArrayList.toArray(new WrappedTeam[youthScratchArrayList.size()]);
			
		}
		else
		{
			return getYouthScratchList(gameIndex);
		}	
	}
	
	public static int getYouthScratchPosition(int gameIndex, boolean bestEventFilter, int teamID)
	{
		WrappedTeam[] teams;
		
		if ( bestEventFilter )
		{
			WrappedTeam[] youthScratchList = getYouthScratchList(gameIndex);
			WrappedTeam[] youthPOAList = getYouthPOAList(gameIndex);
			
			ArrayList<WrappedTeam> youthScratchArrayList = new ArrayList<WrappedTeam>();
			ArrayList<WrappedTeam> youthPOAArrayList = new ArrayList<WrappedTeam>();
			
			for (int i = 0; i < youthScratchList.length; i++) 
			{
				for (int j = i; j < youthScratchList.length; j++) 
				{
					if ( youthScratchList[j].isVisible())
					{
						youthScratchArrayList.add(youthScratchList[j]);
						youthScratchList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthPOAList.length; k++) 
						{
							if ( youthScratchList[j].getTeam().getId() == youthPOAList[k].getTeam().getId())
							{
								youthPOAList[k].setVisible(false);
							}
						}
						
						j = youthScratchList.length;
					}
				}
				for (int j = i; j < youthPOAList.length; j++) 
				{
					if ( youthPOAList[j].isVisible())
					{
						youthPOAArrayList.add(youthPOAList[j]);
						youthPOAList[j].setVisible(false);  //Set to false so it's not found again in the list in case it skips a few
						//find team added in other list and disable it
						for (int k = 0; k < youthScratchList.length; k++) 
						{
							if ( youthPOAList[j].getTeam().getId() == youthScratchList[k].getTeam().getId())
							{
								youthScratchList[k].setVisible(false);
							}
						}
						
						j = youthPOAList.length;
					}
				}
			}
			
			teams = (WrappedTeam[]) youthScratchArrayList.toArray(new WrappedTeam[youthScratchArrayList.size()]);
			
		}
		else
		{
			teams = getYouthScratchList(gameIndex);
		}
		
		for (int i = 0; i < teams.length; i++) {
			if ( teamID == teams[i].getTeam().getId())
				return i + 1;
		}
		
		//team not found
		return -1;
	}
	
	private static WrappedTeam[] getYouthScratchList(int gameIndex) {
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			int value=0;
			for (int j = 0; j <= gameIndex; j++) {
				
				int tempYouthValue = allTeams[i].getYouth().getGame(j) != -1 ? allTeams[i].getYouth().getGame(j) : 0;
				value+=tempYouthValue;
			}
			teams[i] = new WrappedTeam(allTeams[i], value);
		}
		
		sortTeams(teams, 0);
		return teams;
	}

	private static WrappedTeam[] sortTeams(WrappedTeam[] teams, int order)
	{
		//Sort Array according to Value		
		if (order == 1) {
			for (int j=0; j<teams.length-1; j++) 
			{
				for (int k=0; k<teams.length-1-j; k++)
				{
				    if (teams[k+1].getValue() < teams[k].getValue()) 
				    {
				      WrappedTeam tmp = teams[k];
				      teams[k] = teams[k+1];
				      teams[k+1] = tmp;
				    }
				}
			}
		}
		else
		{
			for (int j=0; j<teams.length-1; j++) 
			{
				for (int k=0; k<teams.length-1-j; k++)
				{
				    if (teams[k+1].getValue() > teams[k].getValue()) 
				    {
				      WrappedTeam tmp = teams[k];
				      teams[k] = teams[k+1];
				      teams[k+1] = tmp;
				    }
				}
			}
		}
		
		
		return teams;
	}
	
	public static WrappedTeam[] handleTeamAverage()
	{
		BMTeam[] allTeams = Activator.getDefault().getTournament().getAllTeams();
		WrappedTeam[] teams = new WrappedTeam[allTeams.length];
		for (int i = 0; i < allTeams.length; i++) {
			teams[i] = new WrappedTeam(allTeams[i], allTeams[i].getId());
		}
		
		sortTeams(teams, 1);
		return teams;
	}
}
