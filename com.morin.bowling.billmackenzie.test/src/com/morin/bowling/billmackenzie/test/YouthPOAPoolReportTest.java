package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class YouthPOAPoolReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestYouthPOAPoolReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 17),
			new WrappedTeam(TeamTestReport.TEAM3, 1),
			new WrappedTeam(TeamTestReport.TEAM5, -12),		
			new WrappedTeam(TeamTestReport.TEAM2, -30)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolPOA(0);
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAPoolReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM2, 0),
			new WrappedTeam(TeamTestReport.TEAM5, -2),
			new WrappedTeam(TeamTestReport.TEAM3, -26),
			new WrappedTeam(TeamTestReport.TEAM1, -52)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolPOA(1);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAPoolReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM2, 3),
			new WrappedTeam(TeamTestReport.TEAM1, -11),
			new WrappedTeam(TeamTestReport.TEAM3, -41),
			new WrappedTeam(TeamTestReport.TEAM5, -42)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolPOA(2);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAPoolReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 16),
			new WrappedTeam(TeamTestReport.TEAM2, -12),
			new WrappedTeam(TeamTestReport.TEAM5, -29),
			new WrappedTeam(TeamTestReport.TEAM1, -59)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolPOA(3);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestYouthPOAPoolReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM3, 14),
			new WrappedTeam(TeamTestReport.TEAM2, 11),
			new WrappedTeam(TeamTestReport.TEAM5, -14),
			new WrappedTeam(TeamTestReport.TEAM1, -17)};
		
		WrappedTeam[] teamList = BMStandings.handleYouthPoolPOA(4);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
