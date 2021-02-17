package com.morin.bowling.billmackenzie.test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import com.morin.bowling.billmackenzie.actions.OpenSaveTournamentOperation;
import com.morin.bowling.billmackenzie.utilities.*;

public class AdultPOAPoolReportTest {
	
	TeamTestReport teamTestReport = new TeamTestReport();
		
	@Test
	public void TestAdultPOAPoolReportGame1() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 40),
			new WrappedTeam(TeamTestReport.TEAM5, 39),
			new WrappedTeam(TeamTestReport.TEAM4, 25)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolPOA(0);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAPoolReportGame2() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 27),
			new WrappedTeam(TeamTestReport.TEAM4, 4),
			new WrappedTeam(TeamTestReport.TEAM1, -27)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolPOA(1);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAPoolReportGame3() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM4, 76),
			new WrappedTeam(TeamTestReport.TEAM1, 7),
			new WrappedTeam(TeamTestReport.TEAM5, -2)};
			
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolPOA(2);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAPoolReportGame4() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM1, 0),
			new WrappedTeam(TeamTestReport.TEAM5, -16),
			new WrappedTeam(TeamTestReport.TEAM4, -16)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolPOA(3);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
	
	@Test
	public void TestAdultPOAPoolReportGame5() {
		OpenSaveTournamentOperation.openTournamentFromFile(new File("C:\\Documents and Settings\\mmorin\\My Documents\\Test_BM.xml"));
		WrappedTeam[] expectedList = {
			new WrappedTeam(TeamTestReport.TEAM5, 12),
			new WrappedTeam(TeamTestReport.TEAM4, 12),
			new WrappedTeam(TeamTestReport.TEAM1, 11)};
		
		WrappedTeam[] teamList = BMStandings.handleAdultPoolPOA(4);	
		boolean PassFail = teamTestReport.compareReport(teamList, expectedList);
		assertTrue(teamTestReport.getErrorMessage(), PassFail);
	}
}
