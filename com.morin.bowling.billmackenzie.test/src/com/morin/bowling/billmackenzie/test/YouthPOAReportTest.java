package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class YouthPOAReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestYouthPOAReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 17),
			new WrappedTeam(TeamTestReport.TEAM4, 14),
			new WrappedTeam(TeamTestReport.TEAM3, 1),
			new WrappedTeam(TeamTestReport.TEAM5, -12),		
			new WrappedTeam(TeamTestReport.TEAM2, -30)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPOA(0, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 0),
			new WrappedTeam(TeamTestReport.TEAM5, -14),
			new WrappedTeam(TeamTestReport.TEAM3, -25),
			new WrappedTeam(TeamTestReport.TEAM2, -30),
			new WrappedTeam(TeamTestReport.TEAM1, -35)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPOA(1, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, -3),
			new WrappedTeam(TeamTestReport.TEAM2, -27),
			new WrappedTeam(TeamTestReport.TEAM1, -46),
			new WrappedTeam(TeamTestReport.TEAM5, -56),
			new WrappedTeam(TeamTestReport.TEAM3, -66)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPOA(2, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 36),
			new WrappedTeam(TeamTestReport.TEAM2, -39),
			new WrappedTeam(TeamTestReport.TEAM3, -50),
			new WrappedTeam(TeamTestReport.TEAM5, -85),
			new WrappedTeam(TeamTestReport.TEAM1, -105)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPOA(3, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 40),
			new WrappedTeam(TeamTestReport.TEAM2, -28),
			new WrappedTeam(TeamTestReport.TEAM3, -36),	
			new WrappedTeam(TeamTestReport.TEAM5, -99),
			new WrappedTeam(TeamTestReport.TEAM1, -122)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPOA(4, false);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
