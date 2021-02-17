package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class TeamPOAReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestTeamPOAReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 57),
			new WrappedTeam(TeamTestReport.TEAM4, 39),
			new WrappedTeam(TeamTestReport.TEAM5, 27),
			new WrappedTeam(TeamTestReport.TEAM3, 23),
			new WrappedTeam(TeamTestReport.TEAM2, -36)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOAReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 52),
			new WrappedTeam(TeamTestReport.TEAM3, 49),
			new WrappedTeam(TeamTestReport.TEAM4, 29),
			new WrappedTeam(TeamTestReport.TEAM1, -22),
			new WrappedTeam(TeamTestReport.TEAM2, -67)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOAReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 102),
			new WrappedTeam(TeamTestReport.TEAM5, 8),
			new WrappedTeam(TeamTestReport.TEAM3, 7),
			new WrappedTeam(TeamTestReport.TEAM1, -26),
			new WrappedTeam(TeamTestReport.TEAM2, -63)};
			
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOAReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 125),
			new WrappedTeam(TeamTestReport.TEAM3, 52),
			new WrappedTeam(TeamTestReport.TEAM5, -37),
			new WrappedTeam(TeamTestReport.TEAM2, -81),
			new WrappedTeam(TeamTestReport.TEAM1, -85)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOAReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 141),
			new WrappedTeam(TeamTestReport.TEAM3, 88),
			new WrappedTeam(TeamTestReport.TEAM5, -39),
			new WrappedTeam(TeamTestReport.TEAM2, -60),
			new WrappedTeam(TeamTestReport.TEAM1, -91)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
