package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class TeamPOABestEventReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
	
	@Test
	public void TestTeamPOABestEventReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 39),
			new WrappedTeam(TeamTestReport.TEAM3, 23)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(0, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOABestEventReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 52),
			new WrappedTeam(TeamTestReport.TEAM3, 49)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(1, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOABestEventReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 8),
			new WrappedTeam(TeamTestReport.TEAM3, 7)};		
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(2, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOABestEventhReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 52),
			new WrappedTeam(TeamTestReport.TEAM5, -37)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(3, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestTeamPOABestEventReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {

			new WrappedTeam(TeamTestReport.TEAM3, 88),
			new WrappedTeam(TeamTestReport.TEAM5, -39)};
		
		WrappedTeam[] teamList = BMStandings.handleTeamPOA(4, true);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
